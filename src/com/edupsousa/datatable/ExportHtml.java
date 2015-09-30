package com.edupsousa.datatable;

import java.util.LinkedHashMap;

public class ExportHtml implements ExportFormat {

	@Override
	public String export(DataTable dt, LinkedHashMap<String, Integer> columnsTypes) {
		DataTableRow row;
		String output = "";
		output += "<table>\n";
		output += "<tr>";
		for (String collumnName : columnsTypes.keySet()) {
			output += "<td>" + collumnName + "</td>";
		}
		output += "</tr>\n";
		for (int i = 0; i < dt.rowsCount(); i++) {
			row = dt.getRow(i);
			output += "<tr>";
			for (String collumnName : columnsTypes.keySet()) {
				if (columnsTypes.get(collumnName) == DataTable.TYPE_STRING) {
					output += "<td>" + row.getValue(collumnName) + "</td>";
				} else {
					output += "<td>" + row.getValue(collumnName) + "</td>";
				}
			}
			output += "</tr>\n";
		}
		return output += "</table>\n";
	}
}
