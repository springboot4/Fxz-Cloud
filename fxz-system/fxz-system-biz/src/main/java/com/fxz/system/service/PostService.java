package com.fxz.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fxz.system.dto.PostDto;
import com.fxz.system.entity.Post;

import java.util.List;

/**
 * 岗位信息表
 *
 * @author fxz
 * @date 2022-04-05
 */
public interface PostService extends IService<Post> {

	/**
	 * 添加
	 */
	Boolean addPost(PostDto postDto);

	/**
	 * 修改
	 */
	Boolean updatePost(PostDto postDto);

	/**
	 * 分页
	 */
	IPage<Post> pagePost(Page<Post> pageParam, Post post);

	/**
	 * 获取单条
	 */
	Post findById(Long id);

	/**
	 * 获取全部
	 */
	List<Post> findAll();

	/**
	 * 删除
	 */
	Boolean deletePost(Long id);

}