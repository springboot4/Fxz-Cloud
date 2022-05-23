package com.fxz.common.core.utils;

import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * i18n 工具类 默认中文，可以通过LocaleContextHolder.getLocale() 动态设置语言环境
 *
 * @author fxz
 */
@UtilityClass
public class MsgUtils {

	/**
	 * 通过code 获取中文错误信息
	 * @param code 错误编码
	 * @return 错误信息
	 */
	public String getMessage(String code) {
		MessageSource messageSource = SpringUtil.getBean("messageSource");
		return messageSource.getMessage(code, null, Locale.CHINA);
	}

	/**
	 * 通过code 和参数获取中文错误信息
	 * @param code 错误编码
	 * @param objects 参数
	 * @return 错误信息
	 */
	public String getMessage(String code, Object... objects) {
		MessageSource messageSource = SpringUtil.getBean("messageSource");
		return messageSource.getMessage(code, objects, Locale.CHINA);
	}

}
