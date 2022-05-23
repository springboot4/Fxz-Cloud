package com.fxz.auth.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fxz.common.core.constant.FxzConstant;
import com.fxz.common.core.constant.SecurityConstants;
import com.fxz.common.core.exception.FxzAuthException;
import com.fxz.common.mp.result.PageResult;
import com.fxz.common.mp.result.Result;
import com.fxz.common.security.annotation.Ojbk;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-27 16:28
 */
@SuppressWarnings("all")
@Slf4j
@RestController
@RequiredArgsConstructor
public class SecurityEndpoint {

	private final ConsumerTokenServices consumerTokenServices;

	private final RedisTemplate redisTemplate;

	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}

	/**
	 * 退出登录
	 */
	@Ojbk
	@DeleteMapping("/myLogout")
	public Result<Void> logout(HttpServletRequest request) {
		// 获取请求头信息
		String authorization = request.getHeader("Authorization");
		// 提取token
		String token = StringUtils.replace(authorization, FxzConstant.OAUTH2_TOKEN_TYPE, "");
		token = StringUtils.replace(token, FxzConstant.OAUTH2_TOKEN_TYPE_LOW, "");
		log.info("退出登录,token:{}", token);

		return removeToken(token);
	}

	/**
	 * 删除token
	 */
	@SneakyThrows
	@DeleteMapping("/token/{token}")
	public Result<Void> removeToken(@PathVariable("token") String token) {
		// 此处调用了redis删除缓存的登录信息
		if (!consumerTokenServices.revokeToken(token)) {
			throw new FxzAuthException("退出登录失败");
		}

		return Result.success();
	}

	/**
	 * 分页查询token
	 * @param page 分页参数
	 * @return
	 */
	@GetMapping("/token/page")
	public Result<PageResult> tokenList(Page page) {
		Set<String> keys = redisTemplate.keys(SecurityConstants.TOKEN_PREFIX.concat("access:").concat("*"));
		redisTemplate.setValueSerializer(RedisSerializer.java());
		List<DefaultOAuth2AccessToken> list = redisTemplate.opsForValue().multiGet(keys);
		redisTemplate.setValueSerializer(RedisSerializer.java());
		page.setTotal(list.size());
		page.setRecords(list);

		return Result.success(PageResult.success(page));
	}

}
