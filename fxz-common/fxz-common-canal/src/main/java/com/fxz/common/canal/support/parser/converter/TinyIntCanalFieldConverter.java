package com.fxz.common.canal.support.parser.converter;

import java.sql.JDBCType;

/**
 * @author fxz
 */
public class TinyIntCanalFieldConverter extends BaseCanalFieldConverter<Integer> {

	/**
	 * 单例
	 */
	public static final BaseCanalFieldConverter<Integer> X = new TinyIntCanalFieldConverter();

	private TinyIntCanalFieldConverter() {
		super(JDBCType.TINYINT, Integer.class);
	}

	@Override
	protected Integer convertInternal(String source) {
		return IntCanalFieldConverter.X.convert(source);
	}

}
