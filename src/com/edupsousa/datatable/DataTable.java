package com.edupsousa.datatable;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataTable {

	public static final int TYPE_INT = 0;
	public static final int TYPE_STRING = 1;

	public static final int FORMAT_CSV = 0;
	public static final int FORMAT_HTML = 1;

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
		return rows.get(rows.size() - 1);
	}

	public int getCollumnType(String collumn) {
		return columnsTypes.get(collumn);
	}

	private void checkRowCompatibilityAndThrows(DataTableRow row) {
		for (String collumnName : columnsTypes.keySet()) {
			if (row.hasValueFor(collumnName)
					&& !(isValueCompatible(columnsTypes.get(collumnName),
							row.getValue(collumnName)))) {
				throw new ClassCastException("Wrong type for collumn "
						+ collumnName + ".");
			}
		}
	}

	private boolean isValueCompatible(int type, Object value) {
		if (type == this.TYPE_INT && !(value instanceof Integer)) {
			return false;
		} else if (type == this.TYPE_STRING && !(value instanceof String)) {
			return false;
		}
		return true;
	}

	public DataTableRow getRow(int i) {
		return rows.get(i);
	}

	public String export(int format) {
		ExportFormat ef;
		if (format == 0) {
			ef = new ExportCsv();
		} else {
			ef = new ExportHtml();
		}
		return ef.export(this, columnsTypes);
	}

	public void insertRowAt(DataTableRow row, int index) {
		rows.add(index, row);
	}

	public DataTable filterEqual(String collumn, Object value) {
		DataTable dt = this;
		for (int i = 0; i < dt.rows.size(); i++) {
			if (dt.rows.get(i).getValue(collumn) != value) {
				dt.rows.remove(i);
			}
		}
		return dt;
	}

	public DataTable filterNotEqual(String collumn, Object value) {
		DataTable dt = this;
		for (int i = 0; i < dt.rows.size(); i++) {
			if (dt.rows.get(i).getValue(collumn) == value) {
				dt.rows.remove(i);
			}
		}
		return dt;
	}

	public DataTable sortAscending(String collumn) {
		DataTable dt = this;
		if (dt.getCollumnType(collumn) == 1) {
			throw new ClassCastException("Only Integer columns can be sorted.");
		} else {
			for (int i = 0; i < dt.rows.size() - 1; i++) {
				for (int j = 0; j < dt.rows.size() - 1; j++) {
					if ((int) dt.rows.get(j).getValue(collumn) > (int) dt.rows
							.get(j + 1).getValue(collumn)) {
						DataTableRow menor = dt.rows.get(j + 1);
						DataTableRow maior = dt.rows.get(j);
						dt.rows.remove(j);
						dt.rows.remove(j);
						dt.rows.add(j, maior);
						dt.rows.add(j, menor);
					}
				}
			}
		}
		return dt;
	}

	public DataTable sortDescending(String collumn) {
		DataTable dt = this;
		if (dt.getCollumnType(collumn) == 1) {
			throw new ClassCastException("Only Integer columns can be sorted.");
		} else {
			for (int i = 0; i < dt.rows.size() - 1; i++) {
				for (int j = 0; j < dt.rows.size() - 1; j++) {
					if ((int) dt.rows.get(j).getValue(collumn) < (int) dt.rows
							.get(j + 1).getValue(collumn)) {
						DataTableRow menor = dt.rows.get(j + 1);
						DataTableRow maior = dt.rows.get(j);
						dt.rows.remove(j);
						dt.rows.remove(j);
						dt.rows.add(j, maior);
						dt.rows.add(j, menor);
					}
				}
			}
		}
		return dt;
	}
}
