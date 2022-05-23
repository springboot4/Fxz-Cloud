package com.fxz.common.Idempotent;

import com.fxz.common.Idempotent.aspect.IdempotentAspect;
import com.fxz.common.Idempotent.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.fxz.common.Idempotent.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.fxz.common.Idempotent.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-11 22:12
 */
@SuppressWarnings("all")
@AutoConfiguration
public class IdempotentAutoConfig {

	@Bean
	public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
		return new DefaultIdempotentKeyResolver();
	}

	@Bean
	public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
		return new ExpressionIdempotentKeyResolver();
	}

	@Bean
	public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate RedisTemplate) {
		return new IdempotentRedisDAO(RedisTemplate);
	}

	@Bean
	public IdempotentAspect idempotentAspect(IdempotentRedisDAO idempotentRedisDAO) {
		return new IdempotentAspect(idempotentRedisDAO);
	}

}
