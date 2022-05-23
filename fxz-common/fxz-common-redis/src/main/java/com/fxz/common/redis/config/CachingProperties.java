package com.fxz.common.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fxz
 */
@Data
@ConfigurationProperties(prefix = "fxz.common.redis")
public class CachingProperties {

	/**
	 * 默认超时时间 30分钟
	 */
	private Integer defaultTtl = 60 * 30;

	/**
	 * key的自定义过期时间 秒
	 */
	private Map<String, Integer> keysTtl = new HashMap<>();

}
