package com.fxz.scheduled.utils;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.fxz.scheduled.constant.ScheduleConstants;
import com.fxz.scheduled.entity.JobLog;
import com.fxz.scheduled.entity.SysJob;
import com.fxz.scheduled.service.JobLogService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 抽象quartz调用
 *
 * @author fxz
 */
public abstract class AbstractQuartzJob implements Job {

	private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

	/**
	 * 线程本地变量
	 */
	private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SysJob sysJob = new SysJob();
		BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES), sysJob);
		try {
			before(context, sysJob);
			if (sysJob != null) {
				doExecute(context, sysJob);
			}
			after(context, sysJob, null);
		}
		catch (Exception e) {
			log.error("任务执行异常  - ：", e);
			after(context, sysJob, e);
		}
	}

	/**
	 * 执行前
	 * @param context 工作执行上下文对象
	 * @param SysJob 系统计划任务
	 */
	protected void before(JobExecutionContext context, SysJob SysJob) {
		threadLocal.set(new Date());
	}

	/**
	 * 执行后
	 * @param context 工作执行上下文对象
	 * @param SysJob 系统计划任务
	 */
	protected void after(JobExecutionContext context, SysJob SysJob, Exception e) {
		Date startTime = threadLocal.get();
		threadLocal.remove();

		final JobLog JobLog = new JobLog();
		JobLog.setJobName(SysJob.getJobName());
		JobLog.setJobGroup(SysJob.getJobGroup());
		JobLog.setInvokeTarget(SysJob.getInvokeTarget());
		JobLog.setStartTime(startTime);
		JobLog.setStopTime(new Date());
		long runMs = JobLog.getStopTime().getTime() - JobLog.getStartTime().getTime();
		JobLog.setJobMessage(JobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
		if (e != null) {
			JobLog.setStatus("1");
			String errorMsg = StringUtils.substring(ExceptionUtil.getMessage(e), 0, 2000);
			JobLog.setExceptionInfo(errorMsg);
		}
		else {
			JobLog.setStatus("0");
		}

		// 写入数据库当中
		SpringUtil.getBean(JobLogService.class).addJobLog(JobLog);
	}

	/**
	 * 执行方法，由子类重载
	 * @param context 工作执行上下文对象
	 * @param SysJob 系统计划任务
	 * @throws Exception 执行过程中的异常
	 */
	protected abstract void doExecute(JobExecutionContext context, SysJob SysJob) throws Exception;

}
