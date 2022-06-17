package com.fxz.scheduled.dto;

import com.fxz.scheduled.entity.JobLog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 定时任务调度日志表
 *
 * @author fxz
 * @date 2022-04-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class JobLogDto extends JobLog {

	private static final Long serialVersionUID = -1L;

}