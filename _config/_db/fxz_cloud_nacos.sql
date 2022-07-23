SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (1, 'fxz-auth.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8101\n\nwechat:\n  weapp:\n    appid: wx39812109a4b6cd07\n    secret: 288643a1ca15e972403a8fab6e7045c6\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_base?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.entity.system\n  mapper-locations: classpath:mapper/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nfxz:\n  cloud:\n    security:\n      anonUris: /token/**', '865e4a07f001d37340eed146ced2cc5b', '2021-12-16 18:39:32', '2022-06-26 11:13:38', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (4, 'fxz-gateway.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8301\nspring:\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000\n  cloud:\n    gateway:\n      routes:\n        - id: fxz-auth\n          uri: lb://fxz-auth\n          predicates:\n            - Path=/auth/**\n          # filters:\n          #   - name: Hystrix\n          #     args:\n          #       name: authfallback\n          #       fallbackUri: forward:/fallback/fxz-auth\n        - id: fxz-server-system\n          uri: lb://fxz-server-system\n          predicates:\n            - Path=/system/**\n          # filters:\n          #   - name: Hystrix\n          #     args:\n          #       name: systemfallback\n          #       fallbackUri: forward:/fallback/fxz-server-system\n        - id: fxz-generate\n          uri: lb://fxz-generate\n          predicates:\n            - Path=/generate/**\n        - id: fxz-job\n          uri: lb://fxz-job\n          predicates:\n            - Path=/schedule/**\n        - id: fxz-mall-product\n          uri: lb://fxz-mall-product\n          predicates:\n            - Path=/product/**\n        - id: fxz-mall-order\n          uri: lb://fxz-mall-order\n          predicates:\n            - Path=/order/**\n        - id: fxz-mall-user\n          uri: lb://fxz-mall-user\n          predicates:\n            - Path=/user/**\n        - id: fxz-mall-search\n          uri: lb://fxz-mall-search\n          predicates:\n            - Path=/search/**\n      default-filters:\n        - StripPrefix=1\n\n\nmanagement:\n  endpoint:\n    health:\n      show-details: ALWAYS\n  endpoints:\n    web:\n      exposure:\n        include: health,info,gateway', '468e0229ad2112c8a3ed162dfb0f5b32', '2021-12-16 18:52:35', '2022-05-27 14:14:21', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (6, 'fxz-server-system.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8201\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_base?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000 \n   # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: canal_key\n        queue:\n          name: canal_queue\n        exchange:\n          name: canal_exchange\n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n  common:\n    sequence:\n      type: redis\n      \n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud\n\naliyun:\n  sms:\n    accessKeyId: LTAI5tJymbcizqk5nnVvQUU6\n    accessKeySecret: 9iszXlrnjoXuLLWIXFiaFCzWg2ttW2\n    domain: dysmsapi.aliyuncs.com\n    regionId: cn-hangzhou\n    templateCode: SMS_154950909\n    signName: 阿里云短信测试', '8072d5766d59fa0985ebcadc606aa62c', '2021-12-17 19:01:56', '2022-06-26 11:13:54', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (70, 'fxz-generate.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8400\n\njasypt:\n  encryptor:\n    password: fxz #根密码\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_codegen?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  \n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false', '2fec285de1e3d4dbb779147f0a9e7c17', '2022-03-31 11:02:50', '2022-06-26 11:14:06', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (80, 'fxz-job.yaml', 'DEFAULT_GROUP', 'server:\n  port: 8500\n\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_job?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  \n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false', '9ecb803bf4bdf15a59b8ee4e22013659', '2022-04-03 17:45:50', '2022-06-26 11:14:15', 'nacos', '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (102, 'fxz-mall-product.yaml', 'DEFAULT_GROUP', 'server:\n  port: 6001\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_mall_product?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n\n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud', 'b7d4a781f485e8a66ae9b0f78658d79e', '2022-05-05 12:12:57', '2022-06-26 11:14:25', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (108, 'fxz-mall-order.yaml', 'DEFAULT_GROUP', 'server:\n  port: 6101\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_mall_order?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: order.create.routing.key\n        queue:\n          name: order.delay.queue\n          dead-letter-exchange: order.exchange\n          dead-letter-routing-key: order.close.routing.key\n          arguments:\n            # 1分钟(测试)，单位毫秒,没有被消费则进入死信队列\n            x-message-ttl: 60000 \n        exchange:\n          name: order.exchange\n      # 订单取消队列\n      - routing-key: order.close.routing.key\n        queue:\n          name: order.close.queue\n        exchange:\n          name: order.exchange\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n\n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud', '38bb591c225397d381a3cd248f9efb44', '2022-05-13 08:42:53', '2022-06-26 11:14:35', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (110, 'fxz-mall-user.yaml', 'DEFAULT_GROUP', 'server:\n  port: 6201\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: fxzcloud-mysql-password\n    url: jdbc:mysql://fxz-mysql/fxz_mall_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n', 'af3757bfbb5de8611921822cbfe04e20', '2022-05-14 03:09:20', '2022-06-26 11:14:44', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (126, 'fxz-mall-search.yaml', 'DEFAULT_GROUP', 'server:\n  port: 6301\n\nspring:\n   # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: canal_key\n        queue:\n          name: canal_queue\n        exchange:\n          name: canal_exchange\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n  ', 'c645f67aeed5281e78cb373948cb41c1', '2022-05-27 14:03:25', '2022-05-27 14:03:25', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` (`id`, `data_id`, `group_id`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `app_name`, `tenant_id`, `c_desc`, `c_use`, `effect`, `type`, `c_schema`) VALUES (135, 'fxz-application-common.yaml', 'DEFAULT_GROUP', 'biz:\n  oss:\n    resources-url: http://localhost:8301', '6dcf1e18dde8ccdf42fbfef24842c1c0', '2022-06-26 12:10:13', '2022-06-26 12:11:56', 'nacos', '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(20) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  KEY `idx_gmt_create` (`gmt_create`) USING BTREE,
  KEY `idx_gmt_modified` (`gmt_modified`) USING BTREE,
  KEY `idx_did` (`data_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (0, 135, 'fxz-mall-search.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 6301\n\nspring:\n   # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: canal_key\n        queue:\n          name: canal_queue\n        exchange:\n          name: canal_exchange\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n  ', 'c645f67aeed5281e78cb373948cb41c1', '2010-05-05 00:00:00', '2022-05-27 14:03:25', NULL, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (4, 136, 'fxz-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 8301\nspring:\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000\n  cloud:\n    gateway:\n      routes:\n        - id: fxz-auth\n          uri: lb://fxz-auth\n          predicates:\n            - Path=/auth/**\n          # filters:\n          #   - name: Hystrix\n          #     args:\n          #       name: authfallback\n          #       fallbackUri: forward:/fallback/fxz-auth\n        - id: fxz-server-system\n          uri: lb://fxz-server-system\n          predicates:\n            - Path=/system/**\n          # filters:\n          #   - name: Hystrix\n          #     args:\n          #       name: systemfallback\n          #       fallbackUri: forward:/fallback/fxz-server-system\n        - id: fxz-generate\n          uri: lb://fxz-generate\n          predicates:\n            - Path=/generate/**\n        - id: fxz-job\n          uri: lb://fxz-job\n          predicates:\n            - Path=/schedule/**\n        - id: fxz-mall-product\n          uri: lb://fxz-mall-product\n          predicates:\n            - Path=/product/**\n        - id: fxz-mall-order\n          uri: lb://fxz-mall-order\n          predicates:\n            - Path=/order/**\n        - id: fxz-mall-user\n          uri: lb://fxz-mall-user\n          predicates:\n            - Path=/user/**\n        - id: fxz-server-test\n          uri: lb://fxz-server-test\n          predicates:\n            - Path=/test/**\n          # filters:\n          #   - name: Hystrix\n          #     args:\n          #       name: testfallback\n          #       fallbackUri: forward:/fallback/fxz-server-test\n      default-filters:\n        - StripPrefix=1\n\n  # hystrix:\n  #   command:\n  #     default:\n  #       execution:\n  #         isolation:\n  #           thread:\n  #             timeoutInMilliseconds: 3000\n\n\nmanagement:\n  endpoint:\n    health:\n      show-details: ALWAYS\n  endpoints:\n    web:\n      exposure:\n        include: health,info,gateway', '6ead53d055a965361a609672071a1b4a', '2010-05-05 00:00:00', '2022-05-27 14:14:21', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (1, 137, 'fxz-auth.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 8101\n\nwechat:\n  weapp:\n    appid: wx39812109a4b6cd07\n    secret: 288643a1ca15e972403a8fab6e7045c6\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_base?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.entity.system\n  mapper-locations: classpath:mapper/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nfxz:\n  cloud:\n    security:\n      anonUris: /token/**', 'c39b766d95fa9b7bf634b37d0dc77cb4', '2010-05-05 00:00:00', '2022-06-26 11:13:38', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (6, 138, 'fxz-server-system.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 8201\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_base?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000 \n   # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: canal_key\n        queue:\n          name: canal_queue\n        exchange:\n          name: canal_exchange\n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n  common:\n    sequence:\n      type: redis\n      \n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud\n\naliyun:\n  sms:\n    accessKeyId: LTAI5tJymbcizqk5nnVvQUU6\n    accessKeySecret: 9iszXlrnjoXuLLWIXFiaFCzWg2ttW2\n    domain: dysmsapi.aliyuncs.com\n    regionId: cn-hangzhou\n    templateCode: SMS_154950909\n    signName: 阿里云短信测试', '7f10c6bd16ac4fde75b2949149dc1677', '2010-05-05 00:00:00', '2022-06-26 11:13:54', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (70, 139, 'fxz-generate.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 8400\n\njasypt:\n  encryptor:\n    password: fxz #根密码\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_codegen?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  \n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false', '09f6200521e5e47929d05b1c9327f543', '2010-05-05 00:00:00', '2022-06-26 11:14:06', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (80, 140, 'fxz-job.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 8500\n\n\nspring:\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_cloud_job?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  \n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\n\nmybatis-plus:\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false', '34055784179f423f386c3bc4b7d48c57', '2010-05-05 00:00:00', '2022-06-26 11:14:15', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (102, 141, 'fxz-mall-product.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 6001\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_mall_product?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      #token-info-uri:  http://${fxz-gateway}:8301/auth/oauth/check_token\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n\n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud', '964c36774bb036a73c218cc9bf7c5c0b', '2010-05-05 00:00:00', '2022-06-26 11:14:25', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (108, 142, 'fxz-mall-order.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 6101\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_mall_order?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  # RabbitMQ 配置\n  rabbitmq:\n    host: fxz-rabbitmq\n    port: 5672\n    username: fxz\n    password: 123456\n    # 动态创建和绑定队列、交换机的配置\n    modules: \n      # 订单延时队列，到了过期的时间会被转发到订单取消队列\n      - routing-key: order.create.routing.key\n        queue:\n          name: order.delay.queue\n          dead-letter-exchange: order.exchange\n          dead-letter-routing-key: order.close.routing.key\n          arguments:\n            # 1分钟(测试)，单位毫秒,没有被消费则进入死信队列\n            x-message-ttl: 60000 \n        exchange:\n          name: order.exchange\n      # 订单取消队列\n      - routing-key: order.close.routing.key\n        queue:\n          name: order.close.queue\n        exchange:\n          name: order.exchange\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n\n# 文件系统\noss:\n  endpoint: http://fxz-oss:9000\n  access-key: admin\n  secret-key: admin123456\n  bucketName: fxzcloud', '0d17d4c40482ad2d9214fd5758d267af', '2010-05-05 00:00:00', '2022-06-26 11:14:35', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (110, 143, 'fxz-mall-user.yaml', 'DEFAULT_GROUP', '', 'server:\n  port: 6201\n\nspring:\n  cache:\n    type: redis\n  datasource:\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    username: root\n    password: root\n    url: jdbc:mysql://fxz-mysql/fxz_mall_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8\n  redis:\n    database: 0\n    host: fxz-redis\n    port: 6379\n    #password: fxzcloud-127\n    lettuce:\n      pool:\n        min-idle: 8\n        max-idle: 500\n        max-active: 2000\n        max-wait: 10000\n    timeout: 5000  \n  autoconfigure:\n    exclude: org.springframework.cloud.sleuth.instrument.hystrix.SleuthHystrixAutoConfiguration\n\nsecurity:\n  oauth2:\n    resource:\n      id: ${spring.application.name}\n      user-info-uri: http://fxz-gateway:8301/auth/user\n\nmybatis-plus:\n  type-aliases-package: com.fxz.common.core.entity.system\n  mapper-locations: classpath:mapper/*/*.xml\n  configuration:\n    jdbc-type-for-null: null\n  global-config:\n    banner: false\n\ninfo:\n  app:\n    name: ${spring.application.name}\n    description: \"@project.description@\"\n    version: \"@project.version@\"\n\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n  endpoint:\n    health:\n      show-details: ALWAYS\n\nfeign:\n  hystrix:\n    enabled: true\n\nhystrix:\n  shareSecurityContext: true\n\nfxz:\n  cloud:\n    security: \n      anonUris: /find,/swagger-ui.html,/webjars/**,/swagger-resources/**,/v2/api-docs/**,/,/csrf,/actuator/**,/user/findByName/*,/menu/findUserPermissions/**,/user/test,/kk-anti-reptile/**\n', '6aadb5cd31c5def3d970510a98bc2d5a', '2010-05-05 00:00:00', '2022-06-26 11:14:44', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (0, 144, 'fxz-application-common.yaml', 'DEFAULT_GROUP', '', 'a', '0cc175b9c0f1b6a831c399e269772661', '2010-05-05 00:00:00', '2022-06-26 12:10:13', NULL, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` (`id`, `nid`, `data_id`, `group_id`, `app_name`, `content`, `md5`, `gmt_create`, `gmt_modified`, `src_user`, `src_ip`, `op_type`, `tenant_id`) VALUES (135, 145, 'fxz-application-common.yaml', 'DEFAULT_GROUP', '', 'a', '0cc175b9c0f1b6a831c399e269772661', '2010-05-05 00:00:00', '2022-06-26 12:11:56', 'nacos', '0:0:0:0:0:0:0:1', 'U', '');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`username`, `role`) VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(500) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
