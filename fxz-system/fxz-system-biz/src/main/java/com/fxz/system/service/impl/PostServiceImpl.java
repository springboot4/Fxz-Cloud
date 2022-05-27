package com.fxz.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fxz.system.dto.PostDto;
import com.fxz.system.entity.Post;
import com.fxz.system.entity.UserPost;
import com.fxz.system.mapper.PostMapper;
import com.fxz.system.service.PostService;
import com.fxz.system.service.UserPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位信息表
 *
 * @author fxz
 * @date 2022-04-05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

	private final PostMapper postMapper;

	private final UserPostService userPostService;

	/**
	 * 添加
	 */
	@Override
	public Boolean addPost(PostDto postDto) {
		Post post = new Post();
		BeanUtils.copyProperties(postDto, post);
		postMapper.insert(post);
		return Boolean.TRUE;
	}

	/**
	 * 修改
	 */
	@Override
	public Boolean updatePost(PostDto postDto) {
		Post post = new Post();
		BeanUtils.copyProperties(postDto, post);
		postMapper.updateById(post);
		return Boolean.TRUE;
	}

	/**
	 * 分页
	 */
	@Override
	public IPage<Post> pagePost(Page<Post> pageParam, Post post) {
		return postMapper.selectPage(pageParam,
				Wrappers.<Post>lambdaQuery()
						.like(StringUtils.isNotBlank(post.getPostCode()), Post::getPostCode, post.getPostCode())
						.like(StringUtils.isNotBlank(post.getPostName()), Post::getPostName, post.getPostName()));
	}

	/**
	 * 获取单条
	 */
	@Override
	public Post findById(Long id) {
		return postMapper.selectById(id);
	}

	/**
	 * 获取全部
	 */
	@Override
	public List<Post> findAll() {
		return postMapper.selectList(Wrappers.emptyWrapper());
	}

	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean deletePost(Long id) {
		userPostService.remove(Wrappers.<UserPost>lambdaQuery().eq(UserPost::getPostId, id));
		postMapper.deleteById(id);
		return Boolean.TRUE;
	}

}