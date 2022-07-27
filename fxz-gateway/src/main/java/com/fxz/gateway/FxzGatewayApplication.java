package com.fxz.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-12-07 10:10
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FxzGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxzGatewayApplication.class, args);
	}

}
