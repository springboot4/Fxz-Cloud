package com.fxz.job.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fxz.common.mp.base.BaseEntity;
import com.fxz.job.constant.ScheduleConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 定时任务调度表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Data
@TableName("sys_job")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysJob extends BaseEntity {

	private static final Long serialVersionUID = -1L;

	/**
	 * 任务ID
	 */
	@TableId(type = IdType.AUTO)
	private Long jobId;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务组名
	 */
	private String jobGroup;

	/**
	 * 调用目标字符串
	 */
	private String invokeTarget;

	/**
	 * cron执行表达式
	 */
	private String cronExpression;

	/**
	 * cron计划策略
	 */
	private String misfirePolicy = ScheduleConstants.MISFIRE_DEFAULT;

	/**
	 * 是否并发执行（0允许 1禁止）
	 */
	private String concurrent;

	/**
	 * 任务状态（0正常 1暂停）
	 */
	private String status;

	/**
	 * 备注信息
	 */
	private String remark;

}
