package com.fxz.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzException;
import com.fxz.common.core.param.PageParam;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import com.fxz.common.security.entity.FxzAuthUser;
import com.fxz.common.security.util.SecurityUtil;
import com.fxz.mall.user.entity.Member;
import com.fxz.mall.user.feign.RemoteMemberService;
import com.fxz.system.entity.SystemUser;
import com.fxz.system.entity.UserInfo;
import com.fxz.system.dto.UserInfoDto;
import com.fxz.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author fxz
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final IUserService userService;

	private final RemoteMemberService remoteMemberService;

	@GetMapping("/getUserById/{id}")
	public Result<SystemUser> getUserById(@PathVariable("id") Long id) {
		return Result.success(userService.getUserById(id));
	}

	@GetMapping
	@PreAuthorize("@pms.hasPermission('sys:user:view')")
	public Result<PageResult<SystemUser>> userList(PageParam pageParam, UserInfoDto userInfoDto) {
		return Result.success(PageResult.success(userService.findUserDetail(userInfoDto, pageParam)));
	}

	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys:user:add')")
	public void addUser(@RequestBody SystemUser user) {
		this.userService.createUser(user);
	}

	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys:user:update')")
	public void updateUser(@RequestBody SystemUser user) {
		this.userService.updateUser(user);
	}

	@DeleteMapping("/{userIds}")
	@PreAuthorize("@pms.hasPermission('sys:user:delete')")
	public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FxzException {
		String[] ids = userIds.split(StringPool.COMMA);
		this.userService.deleteUsers(ids);
	}

	/**
	 * 通过用户名查找用户信息
	 */
	@GetMapping("/findByName/{username}")
	public SystemUser findByName(@PathVariable("username") String username) {
		return this.userService.findByName(username);
	}

	/**
	 * 通过手机号查找用户信息
	 * @param mobile 手机号
	 * @return 用户信息
	 */
	@Ojbk(inner = true)
	@GetMapping("/findByMobile/{mobile}")
	public SystemUser findByMobile(@PathVariable("mobile") String mobile) {
		return this.userService.findByMobile(mobile);

	}

	/**
	 * 获取当前用户全部信息
	 */
	@GetMapping("/info")
	public Result<UserInfo> userInfo() {
		FxzAuthUser user = SecurityUtil.getUser();
		String clientId = SecurityUtil.getOAuth2ClientId();

		// 分别查询系统端和商城端用户信息
		if (clientId.equals(SecurityConstants.ADMIN_CLIENT_ID)) {
			SystemUser systemUser = userService
					.getOne(Wrappers.<SystemUser>lambdaQuery().eq(SystemUser::getUserId, user.getUserId()));
			return Result.success(userService.findUserInfo(systemUser));
		}
		else {
			Member member = remoteMemberService.loadUserByUserId(user.getUserId(), SecurityConstants.FROM_IN).getData();
			return Result.success(new UserInfo().setSysUser(new SystemUser().setUserId(member.getId())
					.setAvatar(member.getAvatarUrl()).setUsername(member.getNickName())));
		}
	}

}