package com.fxz.job.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.param.PageParam;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.job.entity.JobLog;
import com.fxz.job.service.JobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务调度日志
 *
 * @author fxz
 * @date 2022-04-03
 */
@RestController
@RequestMapping("/jobLog")
@RequiredArgsConstructor
public class JobLogController {

	private final JobLogService jobLogService;

	@PostMapping(value = "/add")
	public Result add(@RequestBody JobLog param) {
		return Result.success(jobLogService.add(param));
	}

	@PostMapping(value = "/update")
	public Result update(@RequestBody JobLog param) {
		return Result.success(jobLogService.update(param));
	}

	@DeleteMapping(value = "/delete")
	public Result delete(Long id) {
		return Result.judge(true);
	}

	@GetMapping(value = "/findById")
	public Result findById(Long id) {
		return Result.success(jobLogService.findById(id));
	}

	@GetMapping(value = "/findAll")
	public Result findAll() {
		return Result.success(jobLogService.findAll());
	}

	@GetMapping(value = "/page")
	public Result<PageResult<JobLog>> page(Page<JobLog> page, JobLog jobLog) {
		return Result.success(jobLogService.page(page, jobLog));
	}

}