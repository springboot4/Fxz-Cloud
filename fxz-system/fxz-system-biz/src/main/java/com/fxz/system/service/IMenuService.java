package com.fxz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.common.core.entity.router.VueRouter;
import com.fxz.system.entity.Menu;

import java.util.List;
import java.util.Set;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-29 18:34
 */
public interface IMenuService extends IService<Menu> {

	/**
	 * 通过用户名查询用户权限信息
	 * @param username 用户名
	 * @return 权限信息
	 */
	Set<String> findUserPermissions(String username);

	/**
	 * 通过用户名创建对应的 Vue路由信息
	 * @param username 用户名
	 * @return 路由信息
	 */
	List<VueRouter<Menu>> getUserRouters(String username);

	/**
	 * 获取全部的树形菜单信息(包括按钮)
	 * @return 树形菜单信息
	 */
	List<VueRouter<Object>> getAllMenuTree();

	/**
	 * 获取菜单下拉框
	 * @return 树形菜单下拉框
	 */
	List<VueRouter<Object>> getTreeSelect();

	/**
	 * 保存路由信息
	 * @param vueRouter
	 */
	void saveMenu(VueRouter vueRouter);

	/**
	 * 根据id查询路由信息
	 * @param id
	 * @return
	 */
	VueRouter getMenuById(Long id);

	/**
	 * 更新路由
	 */
	void updateMenu(VueRouter vueRouter);

}
