package com.fxz.common.jackson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fxz.common.jackson.util.JacksonUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022/6/30 17:44
 */
@AutoConfiguration
public class JacksonUtilsConfiguration implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
			throws BeansException {
		ObjectMapper bean = configurableListableBeanFactory.getBean(ObjectMapper.class);
		JacksonUtil.setObjectMapper(bean);
	}

}
