package com.fxz.common.mq.rabbit.dynamic;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author fxz
 */
@AutoConfiguration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitModuleProperties {

	private List<RabbitModuleInfo> modules;

}
