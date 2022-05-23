package com.fxz.gen;

import com.fxz.common.database.annotation.EnableDynamicDataSource;
import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-03-03 16:28
 */
@EnableFeignClients(basePackages = { "com.fxz" })
@EnableDynamicDataSource
@EnableFxzCloudResourceServer
@SpringBootApplication
@MapperScan("com.fxz.gen")
public class FxzGenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzGenerateApplication.class, args);
	}

}
