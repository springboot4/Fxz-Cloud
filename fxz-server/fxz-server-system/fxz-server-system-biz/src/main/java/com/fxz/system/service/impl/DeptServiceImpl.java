package com.fxz.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.dataPermission.annotation.DataPermission;
import com.fxz.system.entity.Dept;
import com.fxz.system.mapper.DeptMapper;
import com.fxz.system.service.IDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 18:39
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

	/**
	 * 获取部门树
	 */
	@Override
	public Dept getDeptTree() {
		return this.baseMapper.getDeptTree();
	}

	/**
	 * 根据id删除部门
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean delete(Long id) throws FxzException {
		// 判断是否存在下级
		Long count = this.getBaseMapper().selectCount(Wrappers.<Dept>lambdaQuery().eq(Dept::getParentId, id));
		if (count > 0) {
			throw new FxzException("存在子部门,无法直接删除");
		}
		// 删除数据
		return this.getBaseMapper().deleteById(id) > 0;
	}

	/**
	 * 保存部门信息
	 */
	@Override
	public Boolean addDept(Dept dept) {
		return this.getBaseMapper().insert(dept) > 0;
	}

	/**
	 * 根据Pid查询下级部门 避免数据权限查询时循环调用
	 */
	@DataPermission(enable = false)
	@Cacheable(value = "fxz_cloud:dept:", key = "#pId", unless = "#result==null")
	@Override
	public List<Dept> getDeptsByParentId(Long pId) {
		Dept dept = this.baseMapper.getDeptsByParentId(pId);

		List<Dept> list = treeToList(dept);
		return list;
	}

	private List<Dept> treeToList(Dept dept) {
		LinkedList<Dept> list = new LinkedList<>();
		list.add(dept);
		List<Dept> children = dept.getChildren();
		if (CollectionUtils.isNotEmpty(children)) {
			children.forEach(item -> {
				List<Dept> depts = treeToList(item);
				if (CollectionUtils.isNotEmpty(depts)) {
					list.addAll(depts);
				}
			});
		}
		return list;
	}

}
