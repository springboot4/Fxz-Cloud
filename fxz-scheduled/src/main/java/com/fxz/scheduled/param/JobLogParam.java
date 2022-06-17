package com.fxz.scheduled.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 定时任务调度日志表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Data
@Accessors(chain = true)
public class JobLogParam {

	private Long id;

}