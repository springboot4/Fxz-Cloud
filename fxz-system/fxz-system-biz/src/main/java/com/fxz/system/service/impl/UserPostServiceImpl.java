package com.fxz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.system.dto.UserPostDto;
import com.fxz.system.entity.UserPost;
import com.fxz.system.mapper.UserPostMapper;
import com.fxz.system.service.UserPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPost> implements UserPostService {

	private final UserPostMapper userPostMapper;

	/**
	 * 添加
	 */
	@Override
	public Boolean addUserPost(UserPostDto userPostDto) {
		UserPost userPost = new UserPost();
		BeanUtils.copyProperties(userPostDto, userPost);
		userPostMapper.insert(userPost);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updateUserPost(UserPostDto userPostDto) {
		UserPost userPost = new UserPost();
		BeanUtils.copyProperties(userPostDto, userPost);
		userPostMapper.updateById(userPost);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<UserPost> pageUserPost(Page<UserPost> pageParam, UserPost userPost) {
		return userPostMapper.selectPage(pageParam, Wrappers.emptyWrapper());
	}

	/**
	 * 获取单条
	 */
	@Override
	public UserPost findById(Long id) {
		return userPostMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<UserPost> findAll() {
		return userPostMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteUserPost(Long id) {
		userPostMapper.deleteById(id);
		return Boolean.TRUE;
	}

}