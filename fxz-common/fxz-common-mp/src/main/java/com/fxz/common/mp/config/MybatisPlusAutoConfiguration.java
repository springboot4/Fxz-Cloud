package com.fxz.common.mp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fxz.common.mp.handler.DefaultFieldHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-25 17:26
 */
@AutoConfiguration
public class MybatisPlusAutoConfiguration {

	/**
	 * 分页插件, 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		return interceptor;
	}

	/**
	 * 审计字段自动填充
	 * @return {@link MetaObjectHandler}
	 */
	@Bean
	public DefaultFieldHandler mybatisPlusMetaObjectHandler() {
		return new DefaultFieldHandler();
	}

}
