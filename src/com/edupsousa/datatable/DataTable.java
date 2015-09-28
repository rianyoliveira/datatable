package com.edupsousa.datatable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataTable {

	public static final int TYPE_INT = 0;
	private LinkedHashMap<String, Integer> columnsTypes = new LinkedHashMap<String, Integer>();
	private ArrayList<DataTableRow> rows = new ArrayList<DataTableRow>();
	
	public int columnsCount() {
		return columnsTypes.size();
	}

	public int rowsCount() {
		return rows.size();
	}

	public void addCollumn(String name, int type) {
		columnsTypes.put(name, type);
	}

	public boolean hasCollumn(String name) {
		return columnsTypes.containsKey(name);
	}

	public DataTableRow createRow() {
		return new DataTableRow(this);
	}

	public void insertRow(DataTableRow row) {
		checkRowCompatibilityAndThrows(row);
		rows.add(row);
	}

	public DataTableRow lastRow() {
		return rows.get(rows.size()-1);
	}

	public int getCollumnType(String collumn) {
		return columnsTypes.get(collumn);
	}
	
	private void checkRowCompatibilityAndThrows(DataTableRow row) {
		for (String collumnName : columnsTypes.keySet()) {
			if (row.hasValueFor(collumnName) && 
					!(isValueCompatible(columnsTypes.get(collumnName), row.getValue(collumnName)))) {
				throw new ClassCastException("Wrong type for collumn " + collumnName + ".");
			}
		}
	}
	
	private boolean isValueCompatible(int type, Object value) {
		if (type == this.TYPE_INT && !(value instanceof Integer))
			return false;
		return true;
	}
}
