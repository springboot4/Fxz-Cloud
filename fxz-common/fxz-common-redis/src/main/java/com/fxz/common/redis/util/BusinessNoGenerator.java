package com.fxz.common.redis.util;

import cn.hutool.core.date.DatePattern;
import com.fxz.common.core.constant.RedisConstant;
import com.fxz.common.core.enums.BusinessTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 业务序号生成
 *
 * @author fxz
 */
@SuppressWarnings("all")
@RequiredArgsConstructor
@Component
@Slf4j
public class BusinessNoGenerator {

	private final RedisTemplate redisTemplate;

	/**
	 * @param businessType 业务类型枚举
	 * @param digit 业务序号位数
	 * @return 业务序号
	 */
	public String generate(BusinessTypeEnum businessType, Integer digit) {
		String date = LocalDateTime.now(ZoneOffset.of("+8")).format(DatePattern.PURE_DATETIME_FORMATTER);

		String key = RedisConstant.BUSINESS_PREFIX + businessType.getValue() + ":" + date;

		Long increment = redisTemplate.opsForValue().increment(key);

		return date + businessType.getValue() + String.format("%0" + digit + "d", increment);
	}

	public String generate(BusinessTypeEnum businessType) {
		Integer defaultDigit = 6;
		return generate(businessType, defaultDigit);
	}

}
