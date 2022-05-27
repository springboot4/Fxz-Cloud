package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.log.annotation.OperLogAnn;
import com.fxz.common.log.enums.BusinessType;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictDto;
import com.fxz.system.entity.Dict;
import com.fxz.system.entity.DictItem;
import com.fxz.system.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典表
 *
 * @author fxz
 * @date 2022-04-04
 */
@RestController
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

	private final DictService dictService;

	/**
	 * 添加
	 */
	@OperLogAnn(title = "字典管理", businessType = BusinessType.INSERT)
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody DictDto dictDto) {
		return Result.success(dictService.addDict(dictDto));
	}

	/**
	 * 修改
	 */
	@OperLogAnn(title = "字典管理", businessType = BusinessType.UPDATE)
	@PostMapping(value = "/update")
	public Result<Void> update(@RequestBody DictDto dictDto) {
		return dictService.updateDict(dictDto);
	}

	/**
	 * 删除
	 */
	@OperLogAnn(title = "字典管理", businessType = BusinessType.DELETE)
	@DeleteMapping(value = "/delete")
	public Result<Void> delete(Long id) {
		return dictService.deleteDict(id);
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<Dict> findById(Long id) {
		return Result.success(dictService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@OperLogAnn(title = "字典管理", businessType = BusinessType.OTHER)
	@GetMapping(value = "/findAll")
	public Result<List<Dict>> findAll() {
		return Result.success(dictService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<Dict>> pageDict(Page<Dict> pageParam, Dict dict) {
		return Result.success(PageResult.success(dictService.pageDict(pageParam, dict)));
	}

	/**
	 * 根据字典类型获取字典下的所有字典项
	 * @param type 字典类型
	 * @return 字典项
	 */
	@GetMapping("/getDictItemsByType")
	public Result<List<DictItem>> getDictItemsByType(String type) {
		return Result.success(dictService.getDictItemsByType(type));
	}

}