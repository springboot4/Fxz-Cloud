package com.fxz.system;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fxz
 */
@EnableFxzCloudResourceServer
@EnableFeignClients(basePackages = { "com.fxz" })
@MapperScan("com.fxz.system.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class FxzServerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzServerSystemApplication.class, args);
	}

}
