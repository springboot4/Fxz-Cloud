package com.fxz.common.canal;

import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.ModelTable;
import com.fxz.common.canal.support.adapter.SourceAdapterFacade;
import com.fxz.common.canal.support.processor.CanalBinlogEventProcessorFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * @author fxz
 */
@RequiredArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class DefaultCanalBase implements CanalBase {

	private final CanalBinlogEventProcessorFactory canalBinlogEventProcessorFactory;

	@Override
	public void process(String content) {
		CanalBinLogEvent event = SourceAdapterFacade.X.adapt(CanalBinLogEvent.class, content);
		ModelTable modelTable = ModelTable.of(event.getDatabase(), event.getTable());
		canalBinlogEventProcessorFactory.get(modelTable).forEach(processor -> processor.process(event));
	}

}
