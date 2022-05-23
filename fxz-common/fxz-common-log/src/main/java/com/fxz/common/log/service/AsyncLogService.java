package com.fxz.common.log.service;

import cn.hutool.core.bean.BeanUtil;
import com.fxz.system.dto.OperLogDto;
import com.fxz.system.entity.OperLog;
import com.fxz.system.feign.RemoteLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 异步操作日志服务
 *
 * @author fxz
 */
@Slf4j
@EnableAsync
@AutoConfiguration
public class AsyncLogService {

	@Autowired
	private RemoteLogService remoteLogService;

	/**
	 * 保存系统日志记录
	 */
	@Async
	public void saveSysLog(OperLog operLog) {
		log.info("调用异步方法,{}", Thread.currentThread().getId());
		OperLogDto operLogDto = new OperLogDto();
		BeanUtil.copyProperties(operLog, operLogDto);
		remoteLogService.add(operLogDto);
	}

}
