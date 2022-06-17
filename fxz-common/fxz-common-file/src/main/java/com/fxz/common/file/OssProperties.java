package com.fxz.common.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fxz
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

	private String endpoint;

	private Boolean pathStyleAccess = true;

	private String accessKey;

	private String secretKey;

	private String bucketName = "fxzCloud";

}
