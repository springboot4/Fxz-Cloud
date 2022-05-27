package com.fxz.common.canal.support.parser;

import java.util.Map;
import java.util.function.Function;

/**
 * @author fxz
 */
public abstract class BaseCommonEntryFunction<T> implements Function<Map<String, String>, T> {

	@Override
	public T apply(Map<String, String> entry) {
		throw new UnsupportedOperationException();
	}

}
