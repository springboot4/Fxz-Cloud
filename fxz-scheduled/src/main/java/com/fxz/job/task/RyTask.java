package com.fxz.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author fxz
 */
@Slf4j
@Component("fxzTask")
public class RyTask {

	public void fxzMultipleParamsTask(String s, Boolean b, Long l, Double d, Integer i) {
		log.info("执行多参方法:{},{},{},{},{}", s, b, l, d, i);
	}

	public void fxzParamsTask(String params) {
		log.info("执行有参方法：{}", params);
	}

	public void fxzNoParamsTask() {
		log.info("执行无参方法");
	}

}
