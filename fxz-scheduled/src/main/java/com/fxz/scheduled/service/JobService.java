package com.fxz.scheduled.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.exception.TaskException;
import com.fxz.common.mp.result.PageResult;
import com.fxz.scheduled.constant.ScheduleConstants;
import com.fxz.scheduled.entity.SysJob;
import com.fxz.scheduled.mapper.JobMapper;
import com.fxz.scheduled.utils.ScheduleUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

/**
 * 定时任务调度表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

	private final Scheduler scheduler;

	private final JobMapper jobMapper;

	/**
	 * 添加任务
	 */
	@SneakyThrows
	public SysJob add(SysJob sysJob) {
		sysJob.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int count = jobMapper.insert(sysJob);
		if (count > 0) {
			// 创建定时任务
			ScheduleUtils.createScheduleJob(scheduler, sysJob);
		}
		return sysJob;
	}

	/**
	 * 修改
	 */
	@SneakyThrows
	public SysJob update(SysJob param) {
		SysJob properties = jobMapper.selectById(param.getJobId());
		int count = jobMapper.updateById(param);
		if (count > 0) {
			updateSchedulerJob(param, properties.getJobGroup());
		}
		return param;
	}

	/**
	 * 更新任务
	 * @param job 任务对象
	 * @param jobGroup 任务组名
	 */
	public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException {
		Long jobId = job.getJobId();
		// 判断是否存在
		JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
		if (scheduler.checkExists(jobKey)) {
			// 防止创建时存在数据问题 先移除，然后在执行创建操作
			scheduler.deleteJob(jobKey);
		}
		ScheduleUtils.createScheduleJob(scheduler, job);
	}

	/**
	 * 分页
	 */
	public PageResult<SysJob> page(Page<SysJob> page, SysJob sysJob) {
		Page<SysJob> pageResult = jobMapper.selectPage(page, Wrappers.<SysJob>lambdaQuery()
				.like(StringUtils.isNotBlank(sysJob.getJobName()), SysJob::getJobName, sysJob.getJobName()));
		return PageResult.success(pageResult);
	}

	/**
	 * 获取单条
	 */
	public SysJob findById(Long id) {
		return jobMapper.selectById(id);
	}

	/**
	 * 根据id删除任务
	 */
	@SneakyThrows
	public Boolean deleteByJobId(Long id) {
		SysJob sysJob = jobMapper.selectById(id);
		Long jobId = sysJob.getJobId();
		String jobGroup = sysJob.getJobGroup();
		int count = jobMapper.deleteById(id);
		if (count > 0) {
			scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return jobMapper.deleteById(id) > 0;
	}

	/**
	 * 定时任务状态修改
	 */
	@SneakyThrows
	public Boolean changeStatus(SysJob job) {
		SysJob newJob = jobMapper.selectById(job.getJobId());
		newJob.setStatus(job.getStatus());
		int rows = 0;
		String status = job.getStatus();
		if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
			rows = resumeJob(job);
		}
		else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
			rows = pauseJob(job);
		}
		return rows > 0;
	}

	/**
	 * 暂停任务
	 * @param job 调度信息
	 */
	public int pauseJob(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
		int rows = jobMapper.updateById(job);
		if (rows > 0) {
			scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return rows;
	}

	/**
	 * 恢复任务
	 * @param job 调度信息
	 */
	public int resumeJob(SysJob job) throws SchedulerException {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
		int rows = jobMapper.updateById(job);
		if (rows > 0) {
			scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
		}
		return rows;
	}

	/**
	 * 定时任务立即执行一次
	 */
	@SneakyThrows
	public void run(SysJob job) {
		Long jobId = job.getJobId();
		String jobGroup = job.getJobGroup();
		SysJob properties = jobMapper.selectById(job.getJobId());
		// 参数
		JobDataMap dataMap = new JobDataMap();
		dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
		scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
	}

}