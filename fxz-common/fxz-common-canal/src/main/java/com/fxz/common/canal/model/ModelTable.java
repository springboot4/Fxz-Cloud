package com.fxz.common.canal.model;

/**
 * @author fxz
 */
public interface ModelTable {

	String database();

	String table();

	static ModelTable of(String database, String table) {
		return DefaultModelTable.of(database, table);
	}

}
