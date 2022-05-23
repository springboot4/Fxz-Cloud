package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictItemDto;
import com.fxz.system.entity.DictItem;
import com.fxz.system.service.DictItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典项
 *
 * @author fxz
 * @date 2022-04-04
 */
@RestController
@RequestMapping("/dictItem")
@RequiredArgsConstructor
public class DictItemController {

	private final DictItemService dictItemService;

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody DictItemDto dictItemDto) {
		return Result.success(dictItemService.addDictItem(dictItemDto));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Void> update(@RequestBody DictItemDto dictItemDto) {
		return dictItemService.updateDictItem(dictItemDto);
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Void> delete(Long id) {
		return dictItemService.deleteDictItem(id);
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<DictItem> findById(Long id) {
		return Result.success(dictItemService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<DictItem>> findAll() {
		return Result.success(dictItemService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<DictItem>> pageDictItem(Page<DictItem> pageParam, DictItem dictItem) {
		return Result.success(PageResult.success(dictItemService.pageDictItem(pageParam, dictItem)));
	}

	/**
	 * 校验字典项编码是否已经被使用
	 * @param id 字典项id
	 * @param dictId 字典id
	 * @param value 字典项编码
	 * @return true or false
	 */
	@GetMapping("/itemExistsByCode")
	public Result<Boolean> itemExistsByCode(Long id, Long dictId, String value) {
		return Result.success(dictItemService.itemExistsByCode(id, dictId, value));
	}

}