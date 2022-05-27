package com.fxz.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.system.dto.UserPostDto;
import com.fxz.system.entity.UserPost;
import com.fxz.system.service.UserPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户与岗位关联表
 *
 * @author fxz
 * @date 2022-04-05
 */
@RestController
@RequestMapping("/userPost")
@RequiredArgsConstructor
public class UserPostController {

	private final UserPostService userPostService;

	/**
	 * 添加
	 */
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody UserPostDto userPostDto) {
		return Result.success(userPostService.addUserPost(userPostDto));
	}

	/**
	 * 修改
	 */
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody UserPostDto userPostDto) {
		return Result.success(userPostService.updateUserPost(userPostDto));
	}

	/**
	 * 删除
	 */
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.judge(userPostService.deleteUserPost(id));
	}

	/**
	 * 获取单条
	 */
	@GetMapping(value = "/findById")
	public Result<UserPost> findById(Long id) {
		return Result.success(userPostService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@GetMapping(value = "/findAll")
	public Result<List<UserPost>> findAll() {
		return Result.success(userPostService.findAll());
	}

	/**
	 * 分页
	 */
	@GetMapping(value = "/page")
	public Result<PageResult<UserPost>> pageUserPost(Page<UserPost> pageParam, UserPost userPost) {
		return Result.success(PageResult.success(userPostService.pageUserPost(pageParam, userPost)));
	}

}