package com.fxz.job.dto;

import com.fxz.job.entity.SysJob;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 定时任务调度表
 *
 * @author fxz
 * @date 2022-04-03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SysJobDto extends SysJob {

	private static final Long serialVersionUID = -1L;

}