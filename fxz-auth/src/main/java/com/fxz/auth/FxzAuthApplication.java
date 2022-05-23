package com.fxz.auth;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fxz
 */
@EnableFxzCloudResourceServer
@EnableFeignClients(basePackages = { "com.fxz" })
@SpringBootApplication
public class FxzAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzAuthApplication.class, args);
	}

}
