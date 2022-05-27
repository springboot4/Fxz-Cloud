package com.fxz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.system.dto.UserPostDto;
import com.fxz.system.entity.UserPost;

import java.util.List;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
public interface UserPostService extends IService<UserPost> {

	/**
	 * 添加
	 */
	Boolean addUserPost(UserPostDto userPostDto);

	/**
	 * 修改
	 */
	Boolean updateUserPost(UserPostDto userPostDto);

	/**
	 * 分页
	 */
	IPage<UserPost> pageUserPost(Page<UserPost> pageParam, UserPost userPost);

	/**
	 * 获取单条
	 */
	UserPost findById(Long id);

	/**
	 * 获取全部
	 */
	List<UserPost> findAll();

	/**
	 * 删除
	 */
	Boolean deleteUserPost(Long id);

}