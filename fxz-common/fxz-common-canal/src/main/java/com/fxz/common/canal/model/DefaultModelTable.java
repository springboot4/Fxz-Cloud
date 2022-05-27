package com.fxz.common.canal.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

/**
 * @author fxz
 */
@RequiredArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
public class DefaultModelTable implements ModelTable {

	private final String database;

	private final String table;

	@Override
	public String database() {
		return database;
	}

	@Override
	public String table() {
		return table;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DefaultModelTable that = (DefaultModelTable) o;
		return Objects.equals(database, that.database) && Objects.equals(table, that.table);
	}

	@Override
	public int hashCode() {
		return Objects.hash(database, table);
	}

	@Override
	public String toString() {
		return database() + "." + table();
	}

}
