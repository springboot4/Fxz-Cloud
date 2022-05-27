package com.fxz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.enums.DictTypeEnum;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictDto;
import com.fxz.system.entity.Dict;
import com.fxz.system.entity.DictItem;
import com.fxz.system.mapper.DictMapper;
import com.fxz.system.service.DictItemService;
import com.fxz.system.service.DictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典表
 *
 * @author fxz
 * @date 2022-04-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	private final DictMapper dictMapper;

	private final DictItemService dictItemService;

	/**
	 * 添加
	 */
	@Override
	public Boolean addDict(DictDto dictDto) {
		Dict dict = new Dict();
		BeanUtils.copyProperties(dictDto, dict);
		dictMapper.insert(dict);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Result<Void> updateDict(DictDto dictDto) {
		if (DictTypeEnum.SYSTEM.getType().equals(dictDto.getSystemFlag())) {
			return Result.failed("系统内置字典，不可修改!");
		}
		Dict dict = new Dict();
		BeanUtils.copyProperties(dictDto, dict);
		dictMapper.updateById(dict);
		return Result.success();
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Dict> pageDict(Page<Dict> pageParam, Dict dict) {
		return dictMapper.selectPage(pageParam,
				Wrappers.<Dict>lambdaQuery().like(StringUtils.isNotBlank(dict.getType()), Dict::getType, dict.getType())
						.eq(StringUtils.isNotBlank(dict.getSystemFlag()), Dict::getSystemFlag, dict.getSystemFlag()));
	}

	/**
	 * 获取单条
	 */
	@Override
	public Dict findById(Long id) {
		return dictMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Dict> findAll() {
		return dictMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Result<Void> deleteDict(Long id) {
		Dict dict = this.getById(id);
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag())) {
			return Result.failed("系统内置字典，不可删除!");
		}

		// 删除所有字典项
		dictItemService.remove(Wrappers.<DictItem>lambdaQuery().eq(DictItem::getDictId, id));
		// 删除字典
		dictMapper.deleteById(id);
		return Result.success();
	}

	/**
	 * 根据字典类型获取字典下的所有字典项
	 * @param type 字典类型
	 * @return 字典项
	 */
	@Override
	public List<DictItem> getDictItemsByType(String type) {
		return dictItemService
				.list(Wrappers.<DictItem>lambdaQuery().eq(DictItem::getType, type).orderByAsc(DictItem::getSortOrder));
	}

}