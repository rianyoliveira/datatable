package com.edupsousa.datatable;

import java.util.LinkedHashMap;

public interface ExportFormat {
	public String export(DataTable dt, LinkedHashMap<String, Integer> columnsTypes);
}
