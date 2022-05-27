package com.fxz.common.canal.support.parser;

import com.fxz.common.canal.support.parser.converter.BaseCanalFieldConverter;
import lombok.Data;

/**
 * @author fxz
 */
@Data
public class ColumnMetadata {

	private String columnName;

	private BaseCanalFieldConverter<?> converter;

}
