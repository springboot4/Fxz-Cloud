package com.fxz.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.enums.DictTypeEnum;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.DictItemDto;
import com.fxz.system.entity.Dict;
import com.fxz.system.entity.DictItem;
import com.fxz.system.mapper.DictItemMapper;
import com.fxz.system.mapper.DictMapper;
import com.fxz.system.service.DictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典项
 *
 * @author fxz
 * @date 2022-04-04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {

	private final DictItemMapper dictItemMapper;

	private final DictMapper dictMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addDictItem(DictItemDto dictItemDto) {
		DictItem dictItem = new DictItem();
		BeanUtils.copyProperties(dictItemDto, dictItem);
		dictItemMapper.insert(dictItem);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Result<Void> updateDictItem(DictItemDto dictItemDto) {
		Dict dict = dictMapper.selectById(dictItemDto.getDictId());
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag())) {
			return Result.failed("系统内置字典，不可修改!");
		}

		DictItem dictItem = new DictItem();
		BeanUtils.copyProperties(dictItemDto, dictItem);
		dictItemMapper.updateById(dictItem);
		return Result.success();
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<DictItem> pageDictItem(Page<DictItem> pageParam, DictItem dictItem) {
		return dictItemMapper.selectPage(pageParam,
				Wrappers.<DictItem>lambdaQuery()
						.eq(dictItem.getDictId() != null, DictItem::getDictId, dictItem.getDictId())
						.orderByAsc(DictItem::getSortOrder));
	}

	/**
	 * 获取单条
	 */
	@Override
	public DictItem findById(Long id) {
		return dictItemMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<DictItem> findAll() {
		return dictItemMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Result<Void> deleteDictItem(Long id) {
		DictItem item = this.getById(id);
		Dict dict = dictMapper.selectById(item.getDictId());
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag())) {
			return Result.failed("系统内置字典，不可删除!");
		}

		dictItemMapper.deleteById(id);
		return Result.success();
	}

	/**
	 * 校验字典项编码是否已经被使用 新增且编码被使用、修改且编码被别的字典项使用 true 其他条件下返回false
	 * @param id 字典项id
	 * @param dictId 字典id
	 * @param value 字典项编码
	 * @return true or false
	 */
	@Override
	public Boolean itemExistsByCode(Long id, Long dictId, String value) {
		// 查询此字典下 该编码是否已经使用
		DictItem dictItem = dictItemMapper.selectOne(
				Wrappers.<DictItem>lambdaQuery().eq(DictItem::getDictId, dictId).eq(DictItem::getValue, value));

		// 如果已经被使用 判断此次是否允许操作
		if (ObjectUtil.isNotNull(dictItem)) {
			if (id == null) {
				// 如果是新增 则不可以新增
				return Boolean.TRUE;
			}
			else {
				// 如果是修改 判断id是否和使用此编码的字典项相同
				return !(id.equals(dictItem.getId()));
			}
		}

		return Boolean.FALSE;
	}

}