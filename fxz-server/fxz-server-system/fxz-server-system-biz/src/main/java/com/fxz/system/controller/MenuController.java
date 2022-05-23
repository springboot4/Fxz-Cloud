package com.fxz.system.controller;

import com.fxz.common.core.entity.router.VueRouter;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.system.entity.Menu;
import com.fxz.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-29 18:47
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

	private final IMenuService menuService;

	/**
	 * 获取用户路由信息和用户权限信息
	 */
	@GetMapping("/nav")
	public Result<Map<String, Object>> getUserRouters() {
		long start = System.currentTimeMillis();

		Map<String, Object> result = new HashMap<>();
		Optional.ofNullable(SecurityUtil.getUser()).map(FxzAuthUser::getUsername).ifPresent(userName -> {
			// 构建用户路由对象
			List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(userName);
			// 封装用户路由信息
			result.put("routes", userRouters);
			// 封装用户权限信息
			result.put("permissions", SecurityUtil.getUser().getAuthorities().toArray());
		});

		long end = System.currentTimeMillis();
		log.info("方法耗时:{}", end - start);

		return Result.success(result);
	}

	/**
	 * 获取全部的树形菜单信息(包括按钮)
	 * @return 树形菜单信息
	 */
	@GetMapping("/getAllMenuTree")
	public Result<List<VueRouter<Object>>> getAllMenuTree() {
		return Result.success(this.menuService.getAllMenuTree());
	}

	/**
	 * 获取菜单下拉框
	 * @return 树形菜单下拉框
	 */
	@GetMapping("/getTreeSelect")
	public Result<List<VueRouter<Object>>> getTreeSelect() {
		return Result.success(this.menuService.getTreeSelect());
	}

	/**
	 * 保存路由信息
	 * @param vueRouter 路由信息
	 */
	@PostMapping("/save")
	public void saveMenu(@RequestBody VueRouter vueRouter) {
		this.menuService.saveMenu(vueRouter);
	}

	/**
	 * 根据id删除路由信息
	 * @param id
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteMenu(@PathVariable("id") Long id) {
		this.menuService.removeById(id);
	}

	/**
	 * 根据id查询路由信息
	 * @param id
	 * @return
	 */
	@GetMapping("/getMenuById/{id}")
	public Result<VueRouter> getMenuById(@PathVariable("id") Long id) {
		return Result.success(this.menuService.getMenuById(id));
	}

	/**
	 * 更新路由
	 */
	@PostMapping("/update")
	public void updateMenu(@RequestBody VueRouter vueRouter) {
		this.menuService.updateMenu(vueRouter);
	}

	/**
	 * 通过用户名查询权限信息
	 * @param username 用户名称
	 * @return 权限信息
	 */
	@GetMapping("/findUserPermissions/{username}")
	public Set<String> findUserPermissions(@PathVariable("username") String username) {
		return menuService.findUserPermissions(username);
	}

}
