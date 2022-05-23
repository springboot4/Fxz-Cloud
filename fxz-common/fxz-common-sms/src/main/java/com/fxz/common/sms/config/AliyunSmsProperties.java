package com.fxz.common.sms.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fxz
 */
@ConfigurationProperties(prefix = "aliyun.sms")
@AutoConfiguration
@Data
public class AliyunSmsProperties {

	private String accessKeyId;

	private String accessKeySecret;

	private String domain;

	private String regionId;

	private String templateCode;

	private String signName;

}
