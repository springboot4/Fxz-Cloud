package com.fxz.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 21:00
 */
@Data
@SpringBootConfiguration
@PropertySource(value = { "classpath:fxz-server-system.properties" })
@ConfigurationProperties(prefix = "fxz.server.system")
public class FxzServerSystemProperties {

	/**
	 * 免认证 URI，多个值的话以逗号分隔
	 */
	private String anonUrl;

	private FxzSwaggerProperties swagger = new FxzSwaggerProperties();

}
