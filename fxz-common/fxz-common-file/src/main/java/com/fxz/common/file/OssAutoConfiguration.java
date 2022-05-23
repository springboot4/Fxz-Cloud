package com.fxz.common.file;

import com.fxz.common.file.service.OssTemplate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@AllArgsConstructor
@EnableConfigurationProperties({ OssProperties.class })
public class OssAutoConfiguration {

	private final OssProperties properties;

	@Bean
	@ConditionalOnMissingBean(OssTemplate.class)
	@ConditionalOnProperty(name = "oss.enable", havingValue = "true", matchIfMissing = true)
	public OssTemplate ossTemplate() {
		return new OssTemplate(properties);
	}

}
