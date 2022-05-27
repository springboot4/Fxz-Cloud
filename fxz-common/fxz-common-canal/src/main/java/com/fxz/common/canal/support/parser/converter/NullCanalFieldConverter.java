package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;

/**
 * @author fxz
 */
public class NullCanalFieldConverter extends BaseCanalFieldConverter<Void> {

	/**
	 * 单例
	 */
	public static final BaseCanalFieldConverter<Void> X = new NullCanalFieldConverter();

	private NullCanalFieldConverter() {
		super(JDBCType.NULL, Void.class);
	}

	@Override
	protected Void convertInternal(String source) {
		return null;
	}

}
