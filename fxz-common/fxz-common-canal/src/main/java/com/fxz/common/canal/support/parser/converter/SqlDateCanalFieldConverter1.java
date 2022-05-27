package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;

/**
 * @author fxz
 */
public class SqlDateCanalFieldConverter1 extends BaseCanalFieldConverter<java.sql.Date> {

	public static final BaseCanalFieldConverter<java.sql.Date> X = new SqlDateCanalFieldConverter1();

	private SqlDateCanalFieldConverter1() {
		super(JDBCType.DATE, java.sql.Date.class);
	}

	@Override
	protected java.sql.Date convertInternal(String source) {
		return java.sql.Date.valueOf(SqlDateCanalFieldConverter0.X.convert(source));
	}

}
