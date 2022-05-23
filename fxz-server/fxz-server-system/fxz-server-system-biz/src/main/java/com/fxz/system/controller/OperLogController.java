package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.system.dto.OperLogDto;
import com.fxz.system.entity.OperLog;
import com.fxz.system.service.OperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 *
 * @author fxz
 * @date 2022-04-07
 */
@RestController
@RequestMapping("/operLog")
@RequiredArgsConstructor
public class OperLogController {

	private final OperLogService operLogService;

	/**
	 * 保存日志
	 */
	@Ojbk
	@PostMapping(value = "/add")
	public void add(@RequestBody OperLogDto operLogDto) {
		operLogService.addOperLog(operLogDto);
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody OperLogDto operLogDto) {
		return Result.success(operLogService.updateOperLog(operLogDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(operLogService.deleteOperLog(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<OperLog> findById(Long id) {
		return Result.success(operLogService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<OperLog>> findAll() {
		return Result.success(operLogService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<OperLog>> pageOperLog(Page<OperLog> pageParam, OperLog operLog) {
		return Result.success(PageResult.success(operLogService.pageOperLog(pageParam, operLog)));
	}

}