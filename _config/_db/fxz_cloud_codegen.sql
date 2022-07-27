USE fxz_cloud_codegen;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_datasource_conf
-- ----------------------------
DROP TABLE IF EXISTS `gen_datasource_conf`;
CREATE TABLE `gen_datasource_conf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据源名称',
  `url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'jdbc-url',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1525410472652447747 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据源表';

-- ----------------------------
-- Records of gen_datasource_conf
-- ----------------------------
BEGIN;
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509435110175907841, 'fxzcloud_base', 'jdbc:mysql://127.0.0.1:3306/fxz_cloud_codegen?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'rM9IOsQdK5IUMmCF4Y5d0A==', '1', '2022-03-31 15:39:09', 'fxz', '2022-03-31 15:39:17', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509439122488958977, 'fxzcloud_base', 'jdbc:mysql://127.0.0.1/fxz_cloud_base?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'nQKqaW9qNnnEsWgmXM0Lag==', '0', '2022-03-31 15:55:05', 'fxz', '2022-04-02 16:04:44', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1510166239929495553, 'fxzcloud_gen', 'jdbc:mysql://127.0.0.1/fxz_cloud_codegen?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'E46KJNsvjWdoc0V5nt2jGg==', '1', '2022-04-02 16:04:24', 'fxz', '2022-04-02 16:04:24', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1510497293475336193, 'pig_base', 'jdbc:mysql://127.0.0.1/pig?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', '0+Mu9YWFYj0QlJHXVSzscQ==', '0', '2022-04-03 13:59:53', 'fxz', '2022-04-03 13:59:53', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1510886293184806913, 'fxzcloud_job', 'jdbc:mysql://127.0.0.1/fxz_cloud_job?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'KNIsOUmb6f9fQnThKdC3Sw==', '0', '2022-03-31 15:55:05', 'fxz', '2022-04-02 16:04:44', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1522211132668686338, 'fxz_mall_product', 'jdbc:mysql://fxz-mysql/fxz_mall_product?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'dXE3Vionlghs0GDyH9bxIQ==', '0', '2022-03-31 15:55:05', 'fxz', '2022-04-02 16:04:44', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1525314267901599745, 'fxz_mall_user', 'jdbc:mysql://fxz-mysql/fxz_mall_user?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'YgendBESfOjM+nsjm8mGFA==', '0', '2022-03-31 15:55:05', 'fxz', '2022-05-14 11:17:41', 'fxz');
INSERT INTO `gen_datasource_conf` (`id`, `name`, `url`, `username`, `password`, `del_flag`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1525410472652447746, 'fxz_mall_order', 'jdbc:mysql://fxz-mysql/fxz_mall_order?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8', 'root', 'hYJFfnEWvpTBdOMiU5RlYQ==', '0', '2022-05-14 17:39:32', 'fxz', '2022-05-14 17:39:32', 'fxz');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
