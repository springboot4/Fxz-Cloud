package com.fxz.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.system.entity.SystemUser;
import com.fxz.system.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 18:21
 */
@Mapper
public interface UserMapper extends BaseMapper<SystemUser> {

	/**
	 * 分页查找用户详细信息
	 * @param page 分页对象
	 * @param user 用户对象，用于传递查询条件
	 * @return Ipage
	 */
	@SuppressWarnings("all")
	IPage<SystemUser> findUserDetailPage(Page page, @Param("user") UserInfoDto user);

	/**
	 * 根据用户id获取用户信息
	 */
	SystemUser getUserById(Long id);

	/**
	 * 通过用户名查找用户信息
	 */
	SystemUser findByName(String username);

	/**
	 * 通过手机号查找用户信息
	 * @param mobile 手机号
	 * @return 用户信息
	 */
	SystemUser findByMobile(String mobile);

}
