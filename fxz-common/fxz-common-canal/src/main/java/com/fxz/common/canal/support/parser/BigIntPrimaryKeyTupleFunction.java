package com.fxz.common.canal.support.parser;

import com.fxz.common.canal.support.parser.converter.BigIntCanalFieldConverter;

import java.util.Map;

/**
 * @author fxz
 */
public class BigIntPrimaryKeyTupleFunction extends BasePrimaryKeyTupleFunction {

	/**
	 * 单例
	 */
	public static final BasePrimaryKeyTupleFunction X = new BigIntPrimaryKeyTupleFunction();

	private BigIntPrimaryKeyTupleFunction() {
	}

	@Override
	public Long apply(Map<String, String> before, Map<String, String> after, String primaryKey) {
		String temp;
		if (null != after && null != (temp = after.get(primaryKey))) {
			return BigIntCanalFieldConverter.X.convert(temp);
		}
		return null;
	}

}
