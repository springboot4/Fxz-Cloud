package com.fxz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictDto;
import com.fxz.system.entity.Dict;
import com.fxz.system.entity.DictItem;

import java.util.List;

/**
 * 字典表
 *
 * @author fxz
 * @date 2022-04-04
 */
public interface DictService extends IService<Dict> {

	/**
	 * 添加
	 */
	Boolean addDict(DictDto dictDto);

	/**
	 * 修改
	 */
	Result<Void> updateDict(DictDto dictDto);

	/**
	 * 分页
	 */
	IPage<Dict> pageDict(Page<Dict> pageParam, Dict dict);

	/**
	 * 获取单条
	 */
	Dict findById(Long id);

	/**
	 * 获取全部
	 */
	List<Dict> findAll();

	/**
	 * 删除
	 */
	Result<Void> deleteDict(Long id);

	/**
	 * 根据字典类型获取字典下的所有字典项
	 * @param type 字典类型
	 * @return 字典项
	 */
	List<DictItem> getDictItemsByType(String type);

}