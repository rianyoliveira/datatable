package com.edupsousa.datatable;

import java.util.HashMap;

public class DataTable {

	public static final int TYPE_INT = 0;
	private HashMap<String, Integer> columnsTypes = new HashMap<String, Integer>(); 
	
	public int columnsCount() {
		return columnsTypes.size();
	}

	public int rowsCount() {
		return 0;
	}

	public void addCollumn(String name, int type) {
		columnsTypes.put(name, type);
	}

	public boolean hasCollumn(String name) {
		return columnsTypes.containsKey(name);
	}

}
