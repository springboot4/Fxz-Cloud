package com.fxz.common.canal.support.parser.converter;

import lombok.Builder;
import lombok.Getter;

/**
 * @author fxz
 */
@Builder
@Getter
public class CanalFieldConvertResult {

	private final BaseCanalFieldConverter<?> converter;

}
