package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author fxz
 */
public class TimestampCanalFieldConverter1 extends BaseCanalFieldConverter<java.util.Date> {

	public static final BaseCanalFieldConverter<java.util.Date> X = new TimestampCanalFieldConverter1();

	private TimestampCanalFieldConverter1() {
		super(JDBCType.TIMESTAMP, java.util.Date.class);
	}

	@Override
	protected java.util.Date convertInternal(String source) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(source);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException(String.format("转换日期时间类型java.util.Date失败,原始字符串:%s", source), e);
		}
	}

}
