package com.fxz.job.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 定时任务调度日志表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Data
@TableName("sys_job_log")
@Accessors(chain = true)
public class JobLog implements Serializable {

	private static final Long serialVersionUID = -1L;

	/**
	 * ID
	 */
	@TableId
	private Long jobLogId;

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
	 * 日志信息
	 */
	private String jobMessage;

	/**
	 * 执行状态（0正常 1失败）
	 */
	private String status;

	/**
	 * 异常信息
	 */
	private String exceptionInfo;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 开始时间
	 */
	@TableField(exist = false)
	private Date startTime;

	/**
	 * 停止时间
	 */
	@TableField(exist = false)
	private Date stopTime;

}
