package com.fxz.common.canal.support.parser;

import com.fxz.common.canal.model.ModelTable;
import lombok.Data;

import java.util.Map;

/**
 * @author fxz
 */
@Data
public class ModelTableMetadata {

	private ModelTable modelTable;

	/**
	 * fieldName -> ColumnMetadata
	 */
	private Map<String, ColumnMetadata> fieldColumnMapping;

}
