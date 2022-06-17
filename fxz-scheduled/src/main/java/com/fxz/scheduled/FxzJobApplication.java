package com.fxz.scheduled;

import com.fxz.common.security.annotation.EnableFxzCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Fxz
 * @version 1.0
 * @date 2022-04-03 17:16
 */
@EnableFeignClients(basePackages = { "com.fxz" })
@SpringBootApplication
@EnableFxzCloudResourceServer
public class FxzJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzJobApplication.class, args);
	}

}
