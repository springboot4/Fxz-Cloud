package com.fxz.scheduled.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.scheduled.entity.JobLog;
import com.fxz.scheduled.mapper.JobLogMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务调度日志表
 *
 * @author fxz
 * @date 2022-04-03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JobLogService {

	private final JobLogMapper jobLogMapper;

	/**
	 * 添加
	 */
	public JobLog add(JobLog param) {
		jobLogMapper.insert(param);
		return param;
	}

	/**
	 * 修改
	 */
	public JobLog update(JobLog param) {
		jobLogMapper.updateById(param);
		return param;
	}

	/**
	 * 分页
	 */
	public PageResult<JobLog> page(Page<JobLog> page, JobLog jobLog) {
		return PageResult.success(jobLogMapper.selectPage(page,
				Wrappers.<JobLog>lambdaQuery()
						.eq(StringUtils.isNotBlank(jobLog.getJobName()), JobLog::getJobName, jobLog.getJobName())
						.eq(StringUtils.isNotBlank(jobLog.getJobGroup()), JobLog::getJobGroup, jobLog.getJobGroup())));
	}

	/**
	 * 获取单条
	 */
	public JobLog findById(Long id) {
		return jobLogMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	public List<JobLog> findAll() {
		return jobLogMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 保存job执行日志
	 * @param jobLog job执行信息
	 */
	public void addJobLog(JobLog jobLog) {
		jobLogMapper.insert(jobLog);
	}

}