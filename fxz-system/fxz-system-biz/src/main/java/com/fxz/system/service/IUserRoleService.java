package com.fxz.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.system.entity.UserRole;

/**
 * @author fxz
 */
public interface IUserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String[] roleIds);

	void deleteUserRolesByUserId(String[] userIds);

}