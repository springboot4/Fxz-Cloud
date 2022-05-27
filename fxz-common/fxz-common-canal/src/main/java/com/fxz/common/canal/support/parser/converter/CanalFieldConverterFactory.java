package com.fxz.common.canal.support.parser.converter;

/**
 * @author fxz
 */
public interface CanalFieldConverterFactory {

	default void registerConverter(BaseCanalFieldConverter<?> converter) {
		registerConverter(converter, true);
	}

	void registerConverter(BaseCanalFieldConverter<?> converter, boolean replace);

	CanalFieldConvertResult load(CanalFieldConvertInput input);

}
