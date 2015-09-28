package com.edupsousa.datatable.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.edupsousa.datatable.DataTable;

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
	
}
