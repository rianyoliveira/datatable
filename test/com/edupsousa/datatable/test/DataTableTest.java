package com.edupsousa.datatable.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edupsousa.datatable.DataTable;

public class DataTableTest {

	@Test
	public void emptyDataTable() {
		DataTable dt = new DataTable();
		assertEquals(0, dt.columnsCount());
		assertEquals(0, dt.rowsCount());
	}
	
}
