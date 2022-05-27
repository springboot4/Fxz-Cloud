package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fxz.common.core.entity.router.VueRouter;
import com.fxz.system.entity.Menu;

import java.util.List;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-29 18:28
 */
public interface MenuMapper extends BaseMapper<Menu> {

	/**
	 * 通过用户名查询权限信息
	 * @param username 用户名称
	 * @return 权限信息
	 */
	List<Menu> findUserPermissions(String username);

	/**
	 * 通过用户名查询菜单信息
	 * @param username 用户名
	 * @return 菜单信息
	 */
	List<Menu> findUserMenus(String username);

	/**
	 * 获取全部的树形菜单信息(包括按钮)
	 * @return 树形菜单信息
	 */
	List<VueRouter<Object>> getAllMenuTree();

}
