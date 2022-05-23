package com.fxz.common.core.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.util.Properties;

/**
 * yaml配置文件加载，一般配合@PropertySource使用
 *
 * @author fxz
 */
public class YamlPropertySourceFactory extends DefaultPropertySourceFactory {

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) {
		String sourceName = name != null ? name : resource.getResource().getFilename();
		Properties propertiesFromYaml = loadYml(resource);
		return new PropertiesPropertySource(sourceName, propertiesFromYaml);
	}

	private Properties loadYml(EncodedResource resource) {
		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(resource.getResource());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

}
