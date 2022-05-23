package com.fxz.common.seata.config;

import com.fxz.common.core.factory.YamlPropertySourceFactory;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * seata 自动配置类
 *
 * @author fxz
 */
@PropertySource(value = "classpath:seata-config.yml", factory = YamlPropertySourceFactory.class)
@EnableAutoDataSourceProxy
@AutoConfiguration
public class SeataAutoConfiguration {

}
