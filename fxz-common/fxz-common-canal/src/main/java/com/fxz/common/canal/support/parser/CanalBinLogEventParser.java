package com.fxz.common.canal.support.parser;

import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;

import java.util.List;

/**
 * @author fxz
 */
public interface CanalBinLogEventParser {

	/**
	 * 解析binlog事件
	 * @param event 事件，这个是上游的适配器组件的结果。
	 * @param klass 目标类型
	 * @param primaryKeyFunction 主键映射方法
	 * @param commonEntryFunction 非主键通用列-属性映射方法实例
	 * @return CanalBinLogResult
	 */
	<T> List<CanalBinLogResult<T>> parse(CanalBinLogEvent event, Class<T> klass,
			BasePrimaryKeyTupleFunction primaryKeyFunction, BaseCommonEntryFunction<T> commonEntryFunction);

}
