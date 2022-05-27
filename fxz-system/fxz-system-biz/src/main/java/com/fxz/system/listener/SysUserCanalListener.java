package com.fxz.system.listener;

import com.fxz.common.canal.model.CanalBinLogEvent;
import com.fxz.common.canal.model.CanalBinLogResult;
import com.fxz.common.canal.support.processor.BaseCanalBinlogEventProcessor;
import com.fxz.common.canal.support.processor.ExceptionHandler;
import com.fxz.common.core.exception.FxzException;
import com.fxz.system.entity.SystemUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fxz
 */
@Slf4j
@Component
public class SysUserCanalListener extends BaseCanalBinlogEventProcessor<SystemUser> {

	/**
	 * 插入
	 */
	@Override
	protected void processInsertInternal(CanalBinLogResult<SystemUser> result) {
		log.info("插入:{}", result);
	}

	/**
	 * 更新
	 */
	@Override
	protected void processUpdateInternal(CanalBinLogResult<SystemUser> result) {
		log.info("更新:{}", result);
	}

	/**
	 * 删除
	 */
	@Override
	protected void processDeleteInternal(CanalBinLogResult<SystemUser> result) {
		log.info("删除:{}", result);
	}

	@Override
	protected ExceptionHandler exceptionHandler() {
		return (CanalBinLogEvent event, Throwable throwable) -> {
			throw new FxzException("异常", throwable);
		};
	}

}
