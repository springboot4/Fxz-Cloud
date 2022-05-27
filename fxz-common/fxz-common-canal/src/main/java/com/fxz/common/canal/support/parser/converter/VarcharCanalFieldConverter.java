package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;

/**
 * @author fxz
 */
public class VarcharCanalFieldConverter extends BaseCanalFieldConverter<String> {

	/**
	 * 单例
	 */
	public static final BaseCanalFieldConverter<String> X = new VarcharCanalFieldConverter();

	private VarcharCanalFieldConverter() {
		super(JDBCType.VARCHAR, String.class);
	}

	@Override
	protected String convertInternal(String source) {
		return source;
	}

}
