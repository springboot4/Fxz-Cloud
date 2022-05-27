package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author fxz
 */
public class SqlDateCanalFieldConverter0 extends BaseCanalFieldConverter<LocalDate> {

	private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final BaseCanalFieldConverter<LocalDate> X = new SqlDateCanalFieldConverter0();

	public SqlDateCanalFieldConverter0() {
		super(JDBCType.DATE, LocalDate.class);
	}

	@Override
	protected LocalDate convertInternal(String source) {
		return LocalDate.parse(source, F);
	}

}
