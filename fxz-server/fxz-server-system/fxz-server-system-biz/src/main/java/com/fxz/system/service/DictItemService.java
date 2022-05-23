package com.fxz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictItemDto;
import com.fxz.system.entity.DictItem;

import java.util.List;

/**
 * 字典项
 *
 * @author fxz
 * @date 2022-04-04
 */
public interface DictItemService extends IService<DictItem> {

	/**
	 * 添加
	 */
	Boolean addDictItem(DictItemDto dictItemDto);

	/**
	 * 修改
	 */
	Result<Void> updateDictItem(DictItemDto dictItemDto);

	/**
	 * 分页
	 */
	IPage<DictItem> pageDictItem(Page<DictItem> pageParam, DictItem dictItem);

	/**
	 * 获取单条
	 */
	DictItem findById(Long id);

	/**
	 * 获取全部
	 */
	List<DictItem> findAll();

	/**
	 * 删除
	 */
	Result<Void> deleteDictItem(Long id);

	/**
	 * 校验字典项编码是否已经被使用
	 * @param id 字典项id
	 * @param dictId 字典id
	 * @param value 字典项编码
	 * @return true or false
	 */
	Boolean itemExistsByCode(Long id, Long dictId, String value);

}