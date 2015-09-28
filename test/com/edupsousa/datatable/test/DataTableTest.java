package com.edupsousa.datatable.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.edupsousa.datatable.DataTable;
import com.edupsousa.datatable.DataTableRow;

public class DataTableTest {

	DataTable dt;
	
	@Before
	public void instanceDataTable() {
		dt = new DataTable();
	}
	
	@Test
	public void emptyDataTable() {
		assertEquals(0, dt.columnsCount());
		assertEquals(0, dt.rowsCount());
	}
	
	@Test
	public void addCollumn() {
		dt.addCollumn("id", DataTable.TYPE_INT);
		assertEquals(1, dt.columnsCount());
		assertTrue(dt.hasCollumn("id"));
	}
	
	@Test
	public void addRow() {
		dt.addCollumn("id", DataTable.TYPE_INT);
		DataTableRow row = dt.createRow();
		row.setValue("id", 1);
		dt.insertRow(row);
		
		assertEquals(1, dt.rowsCount());
		assertEquals(1, dt.lastRow().getValue("id"));
	}
	
	@Test
	public void wrongTypeToIntegerCollumn() {
		dt.addCollumn("id", DataTable.TYPE_INT);
		DataTableRow row = dt.createRow();
		row.setValue("id", "A");
		try {
			dt.insertRow(row);
		} catch (ClassCastException e) {
			assertEquals("Wrong type for collumn id.", e.getMessage());
			return;
		}
		fail();
	}
	
	@Test
	public void wrongTypeToStringCollumn() {
		dt.addCollumn("name", DataTable.TYPE_STRING);
		DataTableRow row = dt.createRow();
		row.setValue("name", 1);
		try {
			dt.insertRow(row);
		} catch (ClassCastException e) {
			assertEquals("Wrong type for collumn name.", e.getMessage());
			return;
		}
		fail();
	}
	
	@Test
	public void add10RowsAndVerifyOrder() {
		DataTableRow row;
		dt.addCollumn("id", DataTable.TYPE_INT);
		dt.addCollumn("name", DataTable.TYPE_STRING);
		
		for (int i = 0; i < 10; i++) {
			row = dt.createRow();
			row.setValue("id", i);
			row.setValue("name", "row" + i);
			dt.insertRow(row);
		}
		
		for (int i = 0; i < 10; i++) {
			row = dt.getRow(i);
			assertEquals(i, row.getValue("id"));
			assertEquals("row" + i, row.getValue("name"));
		}
	}
	
	@Test
	public void exportToCSV() {
		DataTableRow row;
		dt.addCollumn("id", DataTable.TYPE_INT);
		dt.addCollumn("name", DataTable.TYPE_STRING);
		
		for (int i = 0; i < 3; i++) {
			row = dt.createRow();
			row.setValue("id", i);
			row.setValue("name", "row" + i);
			dt.insertRow(row);
		}
		String csvOutput = dt.export(DataTable.FORMAT_CSV);
		assertEquals("id;name;\n0;\"row0\";\n1;\"row1\";\n2;\"row2\";\n", csvOutput);
	}
}
