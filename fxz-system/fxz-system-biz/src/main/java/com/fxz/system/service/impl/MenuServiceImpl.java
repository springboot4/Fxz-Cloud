package com.fxz.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.common.core.entity.router.VueRouter;
import com.fxz.common.core.utils.TreeUtil;
import com.fxz.system.entity.Menu;
import com.fxz.system.mapper.MenuMapper;
import com.fxz.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-29 18:35
 */
@Slf4j
@Service("menuService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

	@Override
	public Set<String> findUserPermissions(String username) {
		List<Menu> userPermissions = this.baseMapper.findUserPermissions(username);
		return userPermissions.stream().map(Menu::getPerms).collect(Collectors.toSet());
	}

	@Override
	public List<VueRouter<Menu>> getUserRouters(String username) {
		List<Menu> menus = this.baseMapper.findUserMenus(username);
		List<VueRouter<Menu>> routes = menus.stream().map(menu -> CompletableFuture.supplyAsync(() -> {
			VueRouter<Menu> route = new VueRouter<>();
			route.setId(menu.getId().toString());
			route.setParentId(menu.getParentId().toString());
			route.setPath(menu.getPath());
			route.setRedirect(menu.getRedirect());
			route.setComponent(menu.getComponent());
			route.setName(menu.getName());
			route.setHidden(menu.getHidden());
			route.setTitle(menu.getTitle());
			route.setPerms(menu.getPerms());
			route.setIcon(menu.getIcon());
			return route;
		})).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
		return TreeUtil.buildVueRouter(routes);
	}

	/**
	 * 获取全部的树形菜单信息(包括按钮)
	 * @return 树形菜单信息
	 */
	@Override
	public List<VueRouter<Object>> getAllMenuTree() {
		return this.baseMapper.getAllMenuTree();
	}

	/**
	 * 获取菜单下拉框
	 * @return 树形菜单下拉框
	 */
	@Override
	public List<VueRouter<Object>> getTreeSelect() {
		List<VueRouter<Object>> allMenuTree = this.getAllMenuTree();
		VueRouter<Object> router = new VueRouter<>().setId("0").setTitle("顶级菜单").setChildren(allMenuTree);
		List<VueRouter<Object>> result = new ArrayList<>();
		result.add(router);
		return result;
	}

	/**
	 * 保存路由信息
	 * @param vueRouter
	 */
	@Override
	public void saveMenu(VueRouter vueRouter) {
		Menu menu = new Menu();
		menu.setHidden(vueRouter.getHidden());
		menu.setParentId(Convert.toLong(vueRouter.getParentId()));
		menu.setPerms(vueRouter.getPerms());
		menu.setTitle(vueRouter.getTitle());
		menu.setKeepAlive(Convert.toInt(vueRouter.getKeepAlive()));
		menu.setType(vueRouter.getType());
		menu.setName(vueRouter.getName());
		menu.setComponent(vueRouter.getComponent());
		menu.setPath(vueRouter.getPath());
		menu.setIcon(vueRouter.getIcon());
		menu.setRedirect(vueRouter.getRedirect());
		menu.setOrderNum(Convert.toInt(vueRouter.getOrderNum()));
		this.baseMapper.insert(menu);
	}

	/**
	 * 根据id查询路由信息
	 * @param id
	 * @return
	 */
	@Override
	public VueRouter getMenuById(Long id) {
		Menu menu = this.getById(id);
		VueRouter<Object> vueRouter = new VueRouter<>();
		vueRouter.setId(id.toString());
		vueRouter.setHidden(menu.getHidden());
		vueRouter.setParentId(Convert.toStr(menu.getParentId()));
		vueRouter.setPerms(menu.getPerms());
		vueRouter.setTitle(menu.getTitle());
		vueRouter.setKeepAlive(menu.getKeepAlive().toString());
		vueRouter.setType(menu.getType());
		vueRouter.setName(menu.getName());
		vueRouter.setComponent(menu.getComponent());
		vueRouter.setPath(menu.getPath());
		vueRouter.setIcon(menu.getIcon());
		vueRouter.setRedirect(menu.getRedirect());
		vueRouter.setOrderNum(Double.valueOf(menu.getOrderNum()));
		return vueRouter;
	}

	/**
	 * 更新路由
	 */
	@Override
	public void updateMenu(VueRouter vueRouter) {
		Menu menu = new Menu();
		BeanUtil.copyProperties(vueRouter, menu);
		this.baseMapper.updateById(menu);
	}

}
