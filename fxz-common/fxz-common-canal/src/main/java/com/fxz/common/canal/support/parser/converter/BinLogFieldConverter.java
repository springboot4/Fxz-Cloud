package com.fxz.common.canal.support.parser.converter;

/**
 * @author fxz
 */
public interface BinLogFieldConverter<SOURCE, TARGET> {

	TARGET convert(SOURCE source);

}
