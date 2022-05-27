package com.fxz.common.canal.support.processor;

import com.fxz.common.canal.model.ModelTable;

import java.util.List;

/**
 * @author fxz
 */
public interface CanalBinlogEventProcessorFactory {

	void register(ModelTable modelTable, BaseCanalBinlogEventProcessor<?> processor);

	List<BaseCanalBinlogEventProcessor<?>> get(ModelTable modelTable);

}
