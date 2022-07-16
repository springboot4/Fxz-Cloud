package com.fxz.common.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxz.common.redis.service.RedisService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-15 23:28
 */
@EnableCaching
@AutoConfiguration
@AutoConfigureBefore(name = { "org.redisson.spring.starter.RedissonAutoConfiguration",
		"org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration",
		"com.fxz.common.mq.redis.core.RedisMQTemplate" })
public class RedisTemplateConfig {

	/**
	 * 创建 RedisTemplate Bean，设置序列化方式
	 */
	@Primary
	@Bean
	@ConditionalOnClass(RedisOperations.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, ObjectMapper objectMapper) {
		// 创建 RedisTemplate 对象
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		// 设置 RedisConnection 工厂。
		template.setConnectionFactory(factory);

		// 默认使用了jdk的序列化方式，可读性差，我们使用 String 序列化方式，序列化 KEY 。
		template.setKeySerializer(RedisSerializer.string());
		template.setHashKeySerializer(RedisSerializer.string());

		// 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
		template.setValueSerializer(RedisSerializer.json());
		template.setHashValueSerializer(RedisSerializer.json());

		return template;
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisService redisService() {
		return new RedisService();
	}

}
