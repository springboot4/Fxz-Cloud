package com.fxz.common.canal.support.parser.converter;

import java.math.BigDecimal;
import java.sql.JDBCType;

/**
 * @author fxz
 */
public class DecimalCanalFieldConverter extends BaseCanalFieldConverter<BigDecimal> {

	/**
	 * 单例
	 */
	public static final BaseCanalFieldConverter<BigDecimal> X = new DecimalCanalFieldConverter();

	private DecimalCanalFieldConverter() {
		super(JDBCType.DECIMAL, BigDecimal.class);
	}

	@Override
	protected BigDecimal convertInternal(String source) {
		return new BigDecimal(source);
	}

}
