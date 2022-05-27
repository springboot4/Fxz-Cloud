package com.fxz.common.canal.support.processor;

import com.fxz.common.canal.model.CanalBinLogEvent;

/**
 * @author fxz
 */
@FunctionalInterface
public interface ExceptionHandler {

	void onError(CanalBinLogEvent event, Throwable e);

	ExceptionHandler NO_OP = (event, e) -> {
	};

}
