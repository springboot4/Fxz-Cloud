package com.fxz.job.utils;

import com.fxz.job.entity.SysJob;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author fxz
 */
public class QuartzJobExecution extends AbstractQuartzJob {

	@Override
	protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
		JobInvokeUtil.invokeMethod(sysJob);
	}

}
