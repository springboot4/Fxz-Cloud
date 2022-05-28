package com.fxz.job.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.constant.FxzConstant;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.job.entity.SysJob;
import com.fxz.job.service.JobService;
import com.fxz.job.utils.CronUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务调度
 *
 * @author fxz
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

	private final JobService jobService;

	@PostMapping(value = "/add")
	public Result<SysJob> add(@RequestBody SysJob sysJob) {
		validJob(sysJob);
		return Result.success(jobService.add(sysJob));
	}

	@PostMapping(value = "/update")
	public Result<SysJob> update(@RequestBody SysJob sysJob) {
		validJob(sysJob);
		return Result.success(jobService.update(sysJob));
	}

	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(jobService.deleteByJobId(id));
	}

	@GetMapping(value = "/findById")
	public Result<SysJob> findById(Long id) {
		return Result.success(jobService.findById(id));
	}

	@GetMapping(value = "/page")
	public Result<PageResult<SysJob>> page(Page<SysJob> page, SysJob sysJob) {
		return Result.success(jobService.page(page, sysJob));
	}

	/**
	 * 定时任务状态修改
	 */
	@PutMapping("/changeStatus")
	public Result<Boolean> changeStatus(@RequestBody SysJob job) {
		return Result.success(jobService.changeStatus(job));
	}

	/**
	 * 定时任务立即执行一次
	 */
	@PutMapping("/run")
	public Result<Void> run(@RequestBody SysJob job) {
		jobService.run(job);
		return Result.success();
	}

	private void validJob(SysJob sysJob) {
		Assert.isTrue(CronUtils.isValid(sysJob.getCronExpression()), "新增任务'" + sysJob.getJobName() + "'失败，Cron表达式不正确");
		Assert.isTrue(!StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), FxzConstant.LOOKUP_RMI),
				"新增任务'" + sysJob.getJobName() + "'失败，目标字符串不允许'rmi'调用");
		Assert.isTrue(
				!(StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), FxzConstant.LOOKUP_LDAPS)
						|| StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), FxzConstant.LOOKUP_LDAP)),
				"新增任务'" + sysJob.getJobName() + "'失败，目标字符串不允许'ldap(s)'调用");
		Assert.isTrue(
				!(StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), FxzConstant.HTTP)
						|| StringUtils.containsIgnoreCase(sysJob.getInvokeTarget(), FxzConstant.HTTPS)),
				"新增任务'" + sysJob.getJobName() + "'失败，目标字符串不允许'http(s)'调用");
	}

}