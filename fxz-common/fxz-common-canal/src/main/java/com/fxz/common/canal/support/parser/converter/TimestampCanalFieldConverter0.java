package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author fxz
 */
public class TimestampCanalFieldConverter0 extends BaseCanalFieldConverter<LocalDateTime> {

	private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static final BaseCanalFieldConverter<LocalDateTime> X = new TimestampCanalFieldConverter0();

	private TimestampCanalFieldConverter0() {
		super(JDBCType.TIMESTAMP, LocalDateTime.class);
	}

	@Override
	protected LocalDateTime convertInternal(String source) {
		return LocalDateTime.parse(source, F);
	}

}
