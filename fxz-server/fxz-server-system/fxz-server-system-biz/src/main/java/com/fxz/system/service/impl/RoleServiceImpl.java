package com.fxz.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.param.PageParam;
import com.fxz.common.core.entity.DeptDataPermissionRespDTO;
import com.fxz.common.dataPermission.enums.DataScopeEnum;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.system.entity.*;
import com.fxz.system.mapper.RoleMapper;
import com.fxz.system.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-02-27 17:52
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	private final IRoleMenuService roleMenuService;

	private final IUserRoleService userRoleService;

	private final IDeptService deptService;

	private final IUserService userService;

	private final RedisTemplate redisTemplate;

	/**
	 * 分页查询角色信息
	 */
	@Override
	public IPage<?> PageRole(PageParam pageParam, String roleName) {
		Page<Role> rolePage = new Page<>(pageParam.getCurrent(), pageParam.getSize());
		return this.getBaseMapper().selectPage(rolePage,
				Wrappers.<Role>lambdaQuery().like(StringUtils.isNotEmpty(roleName), Role::getRoleName, roleName));
	}

	/**
	 * 添加角色信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean addRole(Role role) {
		// 保存角色信息
		this.getBaseMapper().insert(role);

		// 保存角色菜单
		saveRoleMenu(role);

		return Boolean.TRUE;
	}

	/**
	 * 根据id获取角色信息
	 */
	@Cacheable(value = "fxz_cloud:role", key = "#id", unless = "#result==null")
	@Override
	public Role getRoleById(Long id) {
		return this.getBaseMapper().getRoleById(id);
	}

	/**
	 * 修改角色信息
	 */
	@CacheEvict(value = "fxz_cloud:role", key = "#role.roleId")
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean editRole(Role role) {
		this.getBaseMapper().updateById(role);

		// 删除角色原有菜单
		roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, role.getRoleId()));

		// 保存角色菜单
		saveRoleMenu(role);

		return Boolean.TRUE;
	}

	/**
	 * 删除角色信息
	 */
	@CacheEvict(value = "fxz_cloud:role", key = "#id")
	@Override
	public Boolean deleteRoleById(Long id) {
		// 删除角色菜单关联信息
		roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, id));
		// 删除角色用户关联信息
		userRoleService.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, id));
		// 删除角色信息
		return this.getBaseMapper().deleteById(id) > 0;
	}

	/**
	 * 获取当前用户角色下的数据权限
	 */
	@Cacheable(value = "fxz_cloud:user", key = "#user.userId+':dataScope'", unless = "#result==null")
	@Override
	public DeptDataPermissionRespDTO getDataPermission(FxzAuthUser user) {
		SystemUser loginUser = userService.findByName(user.getUsername());
		// 创建 DeptDataPermissionRespDTO 对象
		DeptDataPermissionRespDTO result = new DeptDataPermissionRespDTO();
		if (StringUtils.isNotBlank(loginUser.getRoleId())) {
			String[] roleIds = loginUser.getRoleId().split(StringPool.COMMA);
			if (roleIds.length > 0) {
				Arrays.stream(roleIds).forEach(roleId -> {
					String key = "fxz_cloud:role:" + roleId;
					Role role = (Role) redisTemplate.opsForValue().get(key);
					if (ObjectUtil.isNull(role)) {
						role = getRoleById(Long.valueOf(roleId));
					}
					// 为空时，跳过
					if (ObjectUtil.isNull(role.getDataScope())) {
						return;
					}
					// 情况一，ALL
					if (Objects.equals(role.getDataScope(), DataScopeEnum.ALL.getScope())) {
						result.setAll(true);
						return;
					}
					// 情况二，DEPT_CUSTOM
					if (Objects.equals(role.getDataScope(), DataScopeEnum.DEPT_CUSTOM.getScope())) {
						CollUtil.addAll(result.getDeptIds(), role.getDataScopeDeptIds());
						// 自定义可见部门时，保证可以看到自己所在的部门。否则，一些场景下可能会有问题。
						// 例如说，登录时，基于 t_user 的 username 查询会可能被 dept_id 过滤掉
						CollUtil.addAll(result.getDeptIds(), loginUser.getDeptId());
						return;
					}
					// 情况三，DEPT_ONLY
					if (Objects.equals(role.getDataScope(), DataScopeEnum.DEPT_ONLY.getScope())) {
						result.getDeptIds().add(loginUser.getDeptId());
						return;
					}
					// 情况四，DEPT_DEPT_AND_CHILD
					if (Objects.equals(role.getDataScope(), DataScopeEnum.DEPT_AND_CHILD.getScope())) {
						List<Dept> depts = deptService.getDeptsByParentId(loginUser.getDeptId());
						CollUtil.addAll(result.getDeptIds(),
								depts.stream().map(Dept::getDeptId).collect(Collectors.toList()));
						// 添加本身部门id
						CollUtil.addAll(result.getDeptIds(), loginUser.getDeptId());
						return;
					}
					// 情况五，SELF
					if (Objects.equals(role.getDataScope(), DataScopeEnum.SELF.getScope())) {
						result.setSelf(true);
						return;
					}
					// 未知情况，error log 即可
					log.error("[getDeptDataPermission][LoginUser({}) role({}) 无法处理]", loginUser.getUserId(), result);
				});
			}
		}
		return result;
	}

	/**
	 * 保存角色菜单
	 */
	private void saveRoleMenu(Role role) {
		String menuId = role.getMenuId();
		if (StringUtils.isNotEmpty(menuId)) {
			String[] menuIds = menuId.split(StringPool.COMMA);
			List<RoleMenu> roleMenuList = Arrays.stream(menuIds).map(item -> {
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRoleId(role.getRoleId());
				roleMenu.setMenuId(Long.valueOf(item));
				return roleMenu;
			}).collect(Collectors.toList());
			roleMenuService.saveBatch(roleMenuList);
		}
	}

}
