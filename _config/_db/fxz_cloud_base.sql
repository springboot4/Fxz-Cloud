/*
 Navicat Premium Data Transfer

 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Schema         : fxz_cloud_base

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 24/04/2022 00:19:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint NOT NULL COMMENT '上级部门ID',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `order_num` double(20,0) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (0, -1, 'fxzcloud', 0, '2022-02-28 16:42:49', '2022-02-28 16:42:51', NULL, NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1, 0, '研发部', 0, '2022-02-28 16:43:53', '2022-02-28 16:43:55', NULL, NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (2, 0, '市场部', -1, '2022-02-28 16:43:10', '2022-03-26 20:16:52', NULL, 'fxz');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (3, 2, '销售部', 0, '2022-02-28 16:43:27', '2022-03-25 19:32:45', NULL, 'fxz');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (4, 1, '前端组', 1, '2022-02-28 16:44:29', '2022-02-28 16:44:29', NULL, NULL);
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (5, 1, '后端组', 0, '2022-02-28 16:44:41', '2022-03-26 20:26:43', NULL, 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `system_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '是否是系统内置',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510864072018067457, 'ddd', 'dd', 'dd', '1', '1', '2022-04-04 14:17:20', 'fxz', 'fxz', '2022-04-04 14:17:20');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510865432566308866, 'dict_type', '字典类型', '字典类型', '1', '0', '2022-04-04 14:22:44', 'fxz', 'fxz', '2022-04-04 14:22:44');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510869546876649474, '1', '1', '1', '0', '1', '2022-04-04 14:39:05', 'fxz', 'fxz', '2022-04-04 14:39:05');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510870304607027202, '1231', '13213', '313', '0', '1', '2022-04-04 14:42:06', 'fxz', 'fxz', '2022-04-04 14:42:16');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510873495704817666, 'dasda', 'dsada', 'dasda', '0', '1', '2022-04-04 14:54:47', 'fxz', 'fxz', '2022-04-04 15:05:11');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894313813856257, '1', '1', '1', '0', '1', '2022-04-04 16:17:30', 'fxz', 'fxz', '2022-04-04 16:17:30');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894343949930498, '2', '2', '2', '0', '1', '2022-04-04 16:17:37', 'fxz', 'fxz', '2022-04-04 16:17:37');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894360676814850, '3', '3', '3', '0', '1', '2022-04-04 16:17:41', 'fxz', 'fxz', '2022-04-04 16:17:41');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894381933547521, '4', '4', '4', '0', '1', '2022-04-04 16:17:46', 'fxz', 'fxz', '2022-04-04 16:17:46');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894403840397314, '5', '5', '5', '0', '1', '2022-04-04 16:17:52', 'fxz', 'fxz', '2022-04-04 16:17:52');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894422765096962, '6', '6', '6', '0', '1', '2022-04-04 16:17:56', 'fxz', 'fxz', '2022-04-04 16:17:56');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894443795337218, '7', '7', '7', '0', '1', '2022-04-04 16:18:01', 'fxz', 'fxz', '2022-04-04 16:18:01');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894461734375425, '8', '8', '8', '0', '1', '2022-04-04 16:18:05', 'fxz', 'fxz', '2022-04-04 16:18:05');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510894481871228929, '9', '9', '9', '0', '1', '2022-04-04 16:18:10', 'fxz', 'fxz', '2022-04-04 16:18:10');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `remark`, `system_flag`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517894240063803393, 'data_permission_type', '数据权限', '数据权限', '1', '0', '2022-04-23 23:52:43', 'fxz', 'fxz', '2022-04-23 23:52:43');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint NOT NULL,
  `dict_id` bigint NOT NULL COMMENT '字典ID',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '值',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `sort_order` int NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT ' ' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标记',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510865497015984129, 1510865432566308866, '1', '系统类', 'dict_type', '系统类', 1, ' ', '0', '2022-04-04 14:23:00', 'fxz', 'fxz', '2022-04-04 14:23:25');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1510865563508285441, 1510865432566308866, '0', '业务类', 'dict_type', '业务类', 0, ' ', '0', '2022-04-04 14:23:16', 'fxz', 'fxz', '2022-04-04 14:23:16');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517894931306074113, 1517894240063803393, '1', '全部数据权限', 'data_permission_type', '全部数据权限', 1, ' ', '0', '2022-04-23 23:55:27', 'fxz', 'fxz', '2022-04-23 23:55:27');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517895362191118337, 1517894240063803393, '2', '指定部门数据权限', 'data_permission_type', '指定部门数据权限', 2, ' ', '0', '2022-04-23 23:57:10', 'fxz', 'fxz', '2022-04-23 23:55:27');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517895581628715009, 1517894240063803393, '3', '部门数据权限', 'data_permission_type', '部门数据权限', 3, ' ', '0', '2022-04-23 23:58:02', 'fxz', 'fxz', '2022-04-23 23:55:27');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517895701451591682, 1517894240063803393, '4', '部门及以下数据权限', 'data_permission_type', '部门及以下数据权限', 4, ' ', '0', '2022-04-23 23:58:31', 'fxz', 'fxz', '2022-04-23 23:55:27');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort_order`, `remark`, `del_flag`, `create_time`, `create_by`, `update_by`, `update_time`) VALUES (1517895786809872385, 1517894240063803393, '5', '仅本人数据权限', 'data_permission_type', '仅本人数据权限', 5, ' ', '0', '2022-04-23 23:58:51', 'fxz', 'fxz', '2022-04-23 23:55:27');
COMMIT;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint NOT NULL,
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `original` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '0-正常，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文件管理表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
BEGIN;
INSERT INTO `sys_file` (`id`, `file_name`, `bucket_name`, `original`, `type`, `file_size`, `del_flag`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1517878422349291521, '2eb7605827094bb8b9409f8efb795dd1.png', 'fxzcloud', '7493281_fxzcloud_1644239239.png', 'png', 20151, '0', '2022-04-23 22:49:51', '2022-04-23 22:49:51', 'fxz', 'fxz');
INSERT INTO `sys_file` (`id`, `file_name`, `bucket_name`, `original`, `type`, `file_size`, `del_flag`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1517878484760535041, '36f848152cef4db3be798ae7b23ddef5.png', 'fxzcloud', '7493281_fxzcloud_1644239239.png', 'png', 20151, '0', '2022-04-23 22:50:06', '2022-04-23 22:50:06', 'fxz', 'fxz');
INSERT INTO `sys_file` (`id`, `file_name`, `bucket_name`, `original`, `type`, `file_size`, `del_flag`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES (1517900762525290497, '2cf6a2a5a7704c67b733877ae3d8ac23.png', 'fxzcloud', '7493281_fxzcloud_1644239239.png', 'png', 20151, '0', '2022-04-24 00:18:38', '2022-04-24 00:18:38', 'fxz', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint NOT NULL COMMENT '上级菜单ID',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'title',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `perms` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识(多个用逗号分隔，如：user:list,user:create)',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应路由组件component',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应路由path',
  `redirect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '重定向',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `keep_alive` tinyint DEFAULT '1' COMMENT '是否缓存 0:否 1:是',
  `order_num` double(20,0) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `hidden` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '是否隐藏(1 隐藏 0 不隐藏)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100027 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (1, 0, '仪表盘', 'dashboard', 'dashboard', '0', 'RouteView', '/dashboard', NULL, 'code-sandbox', 1, 1, '2022-01-23 16:39:07', '2022-04-04 17:28:31', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (2, 1, '分析页', 'Analysis', 'dashboard', '0', 'dashboard/Analysis', '/dashboard/analysis', NULL, 'form', 1, 1, '2022-01-23 16:47:13', '2022-04-04 17:30:25', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (3, 1, '工作台', 'Workplace', 'dashboard', '0', 'dashboard/Workplace', '/dashboard/workplace', NULL, 'highlight', 1, 1, '2022-01-23 17:02:58', '2022-04-04 17:30:41', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (4, 0, '个人页', 'account', 'user', '0', 'RouteView', '/account', '', 'aliwangwang', 1, 2, '2022-01-23 17:04:07', '2022-04-04 17:29:00', NULL, 'fxz', '1');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (5, 4, '个人中心', 'center', 'user', '0', 'account/center', '/account/center', NULL, NULL, 1, 2, '2022-01-23 17:04:58', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (6, 4, '个人设置', 'settings', 'user', '0', 'account/settings/Index', '/account/settings', NULL, NULL, 1, 2, '2022-01-18 14:44:25', NULL, NULL, NULL, '1');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (7, 6, '基本设置', 'BaseSettings', 'user', '0', 'account/settings/BaseSetting', '/account/settings/base', NULL, NULL, 1, 2, '2022-01-18 14:48:57', NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (8, 6, '安全设置', 'SecuritySettings', 'user', '0', 'account/settings/Security', '/account/settings/security', NULL, NULL, 1, 2, '2022-01-18 14:49:39', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (9, 6, '个性化设置', 'CustomSettings', 'user', '0', 'account/settings/Custom', '/account/settings/custom', NULL, NULL, 1, 2, '2022-01-18 14:50:17', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (10, 6, '账户绑定', 'BindingSettings', 'user', '0', 'account/settings/Binding', '/account/settings/binding', NULL, NULL, 1, 2, '2022-01-18 14:50:58', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (11, 6, '新消息通知', 'NotificationSettings', 'user', '0', 'account/settings/Notification', '/account/settings/notification', NULL, NULL, 1, 2, '2022-01-18 14:51:31', NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (12, 0, '欢迎页', 'welcome', 'welcome', '0', 'index/welcome', '/welcome', '', 'twitter', 1, 0, '2022-01-18 16:22:11', '2022-04-04 17:34:43', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (20, 0, '系统管理', 'permissions', 'sys', '0', 'RouteView', '/permissions', NULL, 'rocket', 1, 3, '2022-01-23 16:52:53', '2022-04-05 16:17:42', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (21, 20, '菜单管理', 'menu', 'sys:menu', '0', 'modules/system/menu/menu', '/permissions/menu', NULL, 'idcard', 1, 0, '2022-01-23 16:55:48', '2022-04-04 17:31:22', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (29, 21, '添加菜单', 'saveMenu', 'sys:menu:save', '1', NULL, NULL, NULL, NULL, 1, 1, '2022-01-23 18:16:31', NULL, NULL, NULL, '1');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100006, 20, '用户管理', 'user', 'sys:user', '0', 'modules/system/user/userList.vue', '/permissions/user', NULL, 'user', 1, 1, '2022-02-27 09:35:00', '2022-04-05 20:24:14', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100007, 20, '角色管理', 'role', 'sys:role', '0', 'modules/system/role/RoleList.vue', '/permissions/role', NULL, 'woman', 1, 2, '2022-02-27 09:43:02', '2022-04-05 20:24:23', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100008, 20, '部门管理', 'dept', 'dept', '0', 'modules/system/dept/DeptList.vue', '/permissions/dept', NULL, 'contacts', 1, 3, '2022-02-27 10:15:46', '2022-04-05 20:24:37', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100009, 100006, '分页查看用户信息', 'sys:user:view', 'sys:user:view', '1', 'sys:user:view', '/sys/user/list', NULL, NULL, 1, 1, '2022-02-27 13:43:05', NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100010, 100006, '更新用户信息', 'sys:user:update', 'sys:user:update', '1', NULL, 'sys:user:update', NULL, NULL, 1, NULL, '2022-02-27 17:05:44', NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100011, 100006, '新增用户信息', 'sys:user:add', 'sys:user:add', '1', NULL, 'sys:user:add', NULL, NULL, 1, NULL, '2022-02-27 18:07:12', NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100012, 0, '系统工具', 'sysTool', 'sysTool', '0', 'RouteView', '/sysTool', NULL, 'shopping-cart', 1, 4, '2022-03-04 09:14:37', '2022-04-05 16:18:24', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100013, 100012, '代码生成器', 'genCode', 'sysTool:genCode', '0', 'modules/system/gen/CodeGenIndex.vue', '/sysTool/genCode', NULL, 'area-chart', 1, 1, '2022-03-04 09:19:37', '2022-04-04 17:33:00', NULL, 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100016, 100012, '数据源管理', 'DatasourceConfList', 'sysTool:datasourceConf', '0', 'modules/system/gen/datasource/DatasourceConfList.vue', '/sysTool/datasourceConf', NULL, 'box-plot', 1, 0, '2022-03-31 12:31:26', '2022-04-04 17:33:10', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100017, 0, '系统监控', 'monitor', 'sysMonitor', '0', 'RouteView', '/sysMonitor', NULL, 'aliwangwang', 1, 5, '2022-04-03 17:59:52', '2022-04-04 17:35:11', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100018, 100017, '定时任务', 'jobList', 'sysMonitor:job', '0', 'modules/system/monitor/JobList.vue', '/sysMonitor/job', NULL, 'loading', 1, 0, '2022-04-03 18:02:04', '2022-04-04 17:33:39', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100019, 20, '字典管理', 'dict', 'sys:dict', '0', 'modules/system/dict/DictList.vue', '/permissions/dict', NULL, 'folder-open', 1, 4, '2022-04-04 11:20:33', '2022-04-05 20:24:45', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100020, 100017, '调度日志', 'JobLogList', 'sysMonitor:jobLog', '0', 'modules/system/monitor/JobLogList.vue', '/sysMonitor/jobLog', NULL, 'hdd', 1, 1, '2022-04-04 15:55:43', '2022-04-04 17:34:06', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100021, 20, '文件管理', 'file', 'sys:file', '0', 'modules/system/file/FileList.vue', '/permissions/file', NULL, 'mail', 1, 5, '2022-04-04 22:47:38', '2022-04-04 22:49:20', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100022, 20, '岗位管理', 'post', 'sys:post', '0', 'modules/system/post/PostList.vue', '/permissions/post', NULL, 'coffee', 1, 4, '2022-04-05 20:17:47', '2022-04-05 20:24:58', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100023, 100012, '表单设计器', 'FormBuild', 'sysTool:formBuild', '0', 'modules/system/gen/FormBuild.vue', '/sysTool/formBuild', NULL, 'bg-colors', 1, 2, '2022-04-23 23:22:38', '2022-04-23 23:38:03', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100024, 20, '审计管理', 'oper', 'sys:oper', '0', 'RouteView', '/permissions/oper', NULL, 'credit-card', 1, 7, '2022-04-24 00:12:34', '2022-04-24 00:12:34', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100025, 100024, '登录日志', 'OperLogin', 'sys:oper:login', '0', 'modules/system/log/OperLogList.vue', '/sys/oper/login', NULL, 'logout', 1, 0, '2022-04-24 00:15:20', '2022-04-24 00:16:26', 'fxz', 'fxz', '0');
INSERT INTO `sys_menu` (`id`, `parent_id`, `title`, `name`, `perms`, `type`, `component`, `path`, `redirect`, `icon`, `keep_alive`, `order_num`, `create_time`, `update_time`, `create_by`, `update_by`, `hidden`) VALUES (100026, 100024, '操作日志', 'OperLogList', 'sys:oper:log', '0', 'modules/system/log/OperLogList.vue', '/sys/oper/log', NULL, 'stock', 1, 1, '2022-04-24 00:17:31', '2022-04-24 00:17:31', 'fxz', 'fxz', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户端ID',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源列表',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端密钥',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '域',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '认证类型',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '重定向地址',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色列表',
  `access_token_validity` int DEFAULT NULL COMMENT 'token 有效期',
  `refresh_token_validity` int DEFAULT NULL COMMENT '刷新令牌有效期',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '令牌扩展字段JSON',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自动放行',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('app', NULL, 'app', 'server', 'app,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('daemon', NULL, 'daemon', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('fxz', NULL, '123456', 'server', 'refresh_token,authorization_code,captcha', 'https://fxz.life', NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('open', NULL, 'open', 'server', 'refresh_token,authorization_code', 'https://fxz.life', NULL, NULL, NULL, '{\n	\"appName\":\"测试应用\"\n}', 'false', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('pig', NULL, 'pig', 'server', 'password,app,refresh_token,authorization_code,client_credentials', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login', NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('ruoyi', NULL, 'ruoyi', 'server', 'refresh_token,authorization_code', 'http://localhost:80/sso/login', NULL, 43200, 2592001, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('swagger', NULL, '123456', 'test', 'password', NULL, NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
INSERT INTO `sys_oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`, `create_time`, `update_time`, `create_by`, `update_by`) VALUES ('test', NULL, 'test', 'server', 'password,app,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `business_type` tinyint(1) DEFAULT NULL COMMENT '业务类型',
  `method` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `request_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `oper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人员',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求url',
  `oper_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主机地址',
  `oper_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求参数',
  `status` tinyint(1) DEFAULT NULL COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常信息',
  `time` bigint DEFAULT NULL COMMENT '执行时间',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1517900793491836930 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517873262470488066, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:29:21', 'anonymousUser', '2022-04-23 22:29:21');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517874824039227393, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:35:33', 'anonymousUser', '2022-04-23 22:35:33');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517874961989885953, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:36:06', 'anonymousUser', '2022-04-23 22:36:06');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517875079828856833, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:36:34', 'anonymousUser', '2022-04-23 22:36:34');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517875359362441218, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:37:41', 'anonymousUser', '2022-04-23 22:37:41');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517875595111686145, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:38:37', 'anonymousUser', '2022-04-23 22:38:37');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517876215528824834, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:41:05', 'anonymousUser', '2022-04-23 22:41:05');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517876871434084354, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:43:42', 'anonymousUser', '2022-04-23 22:43:41');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517876993928732674, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:44:11', 'anonymousUser', '2022-04-23 22:44:10');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517877130411384834, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:44:43', 'anonymousUser', '2022-04-23 22:44:43');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517877234337849346, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 613, 'anonymousUser', '2022-04-23 22:45:08', 'anonymousUser', '2022-04-23 22:45:08');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517877294932959233, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 636, 'anonymousUser', '2022-04-23 22:45:23', 'anonymousUser', '2022-04-23 22:45:23');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517878214878044161, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 823, 'anonymousUser', '2022-04-23 22:49:02', 'anonymousUser', '2022-04-23 22:49:02');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517878424391917569, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 1067, 'anonymousUser', '2022-04-23 22:49:52', 'anonymousUser', '2022-04-23 22:49:52');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517878485536481281, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 253, 'anonymousUser', '2022-04-23 22:50:06', 'anonymousUser', '2022-04-23 22:50:06');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517878639459049473, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 22:50:43', 'anonymousUser', '2022-04-23 22:50:43');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517882055593828354, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:04:18', 'anonymousUser', '2022-04-23 23:04:17');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517882152931041281, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:04:41', 'anonymousUser', '2022-04-23 23:04:40');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517882322338979841, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:05:21', 'anonymousUser', '2022-04-23 23:05:21');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517882933906329602, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:07:47', 'anonymousUser', '2022-04-23 23:07:46');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517886991689928705, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:23:54', 'anonymousUser', '2022-04-23 23:23:54');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517890600104185858, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:38:15', 'anonymousUser', '2022-04-23 23:38:14');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517893441833226242, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-23 23:49:32', 'anonymousUser', '2022-04-23 23:49:32');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517894241418563586, '字典管理', 1, 'com.fxz.system.controller.DictController.add()', 'POST', 'fxz', '/dict/add', '127.0.0.1', '', NULL, NULL, 174, 'anonymousUser', '2022-04-23 23:52:43', 'anonymousUser', '2022-04-23 23:52:43');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517894254861307905, '字典管理', 2, 'com.fxz.system.controller.DictController.update()', 'POST', 'fxz', '/dict/update', '127.0.0.1', '', NULL, NULL, 8, 'anonymousUser', '2022-04-23 23:52:46', 'anonymousUser', '2022-04-23 23:52:46');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517897494088732674, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-24 00:05:38', 'anonymousUser', '2022-04-24 00:05:38');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517900618702606337, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-24 00:18:03', 'anonymousUser', '2022-04-24 00:18:03');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517900763418677249, '文件管理', 1, 'com.fxz.system.controller.FileController.add()', 'POST', 'fxz', '/file/add', '127.0.0.1', '', NULL, NULL, 395, 'anonymousUser', '2022-04-24 00:18:38', 'anonymousUser', '2022-04-24 00:18:38');
INSERT INTO `sys_oper_log` (`id`, `title`, `business_type`, `method`, `request_method`, `oper_name`, `oper_url`, `oper_ip`, `oper_param`, `status`, `error_msg`, `time`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES (1517900793491836929, '用户登录', 4, NULL, 'POST', 'fxz', NULL, '127.0.0.1', 'fxz', 0, NULL, NULL, 'fxz', '2022-04-24 00:18:45', 'anonymousUser', '2022-04-24 00:18:45');
COMMIT;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '岗位排序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1511338847253417986 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='岗位信息表';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1, 'user', '员工', 2, '1', '打工人', '2022-03-19 10:05:15', 'admin', '2022-03-19 10:42:28', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (2, 'cto', 'cto', 0, '1', 'cto666', '2022-03-19 10:06:20', 'admin', '2022-03-19 10:06:20', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (3, 'boss', '董事长', -1, '1', '大boss', '2022-03-19 10:06:35', 'admin', '2022-03-19 10:42:44', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509530573596016641, '22s', '222s', 11, '1', '22s', '2022-03-31 21:58:29', 'admin', '2022-03-31 21:58:29', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509534285496426497, '22sas', '222ssss', 1122, '1', '22s', '2022-03-31 22:13:14', 'admin', '2022-03-31 22:13:14', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509534945046536193, '22sass', '222sssp', 1122, '1', '22ss', '2022-03-31 22:15:51', 'admin', '2022-03-31 22:15:51', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509535899309416450, 'aasw', 'ds', 11, '1', 'dd', '2022-03-31 22:19:39', 'admin', '2022-03-31 22:19:39', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509536193485316097, 'aasws', 'dss', 11, '1', 'dds', '2022-03-31 22:20:49', 'admin', '2022-03-31 22:20:49', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509536337576435713, 'aasws2', 'dss1', 11, '1', 'dds', '2022-03-31 22:21:23', 'admin', '2022-03-31 22:21:23', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1509536560256229377, 'aasws2s', 'dss1s', 11, '1', 'dds', '2022-03-31 22:22:16', 'admin', '2022-03-31 22:22:16', 'admin');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1511326356242804737, 'cto', 'cto', 0, '0', 'cto666', '2022-04-05 20:54:17', 'fxz', '2022-04-05 21:03:22', 'fxz');
INSERT INTO `sys_post` (`post_id`, `post_code`, `post_name`, `post_sort`, `del_flag`, `description`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1511338847253417985, 'ceo', 'ceo', 1, '0', 'ceo', '2022-04-05 21:43:55', 'fxz', '2022-04-05 21:43:55', 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `data_scope` tinyint(1) DEFAULT NULL COMMENT '数据权限范围',
  `data_scope_dept_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据范围(指定部门数组)',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_time`, `update_time`, `create_by`, `update_by`, `data_scope`, `data_scope_dept_ids`) VALUES (1, 'admin', '管理员', '2022-02-28 16:23:11', '2022-04-24 00:17:52', NULL, 'fxz', 1, NULL);
INSERT INTO `sys_role` (`role_id`, `role_name`, `remark`, `create_time`, `update_time`, `create_by`, `update_by`, `data_scope`, `data_scope_dept_ids`) VALUES (8, '游客', '游客,没啥权限', '2022-02-28 19:45:16', '2022-04-05 18:03:00', NULL, 'fxz', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `ROLE_ID` bigint NOT NULL,
  `MENU_ID` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 12);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 4);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 5);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 6);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 1);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 2);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (8, 3);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 2);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 3);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 4);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 5);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 6);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 7);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 12);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 20);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 21);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 29);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100006);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100009);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100010);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100011);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100008);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100007);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100012);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100013);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100014);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 1);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100015);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100016);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100017);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100018);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100019);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100020);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100021);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100022);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100023);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100024);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100025);
INSERT INTO `sys_role_menu` (`ROLE_ID`, `MENU_ID`) VALUES (1, 100026);
COMMIT;

-- ----------------------------
-- Table structure for sys_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_trade_log`;
CREATE TABLE `sys_trade_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` int DEFAULT NULL COMMENT '商品ID',
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_trade_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (141, 11, 'aaa', '下单并支付成功', '2022-01-25 11:21:02');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (142, 11, 'aaa', '打包完毕，开始物流配送！', '2022-01-25 11:21:02');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (145, 11, 'aaa', '下单并支付成功', '2022-01-25 12:59:31');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (146, 11, 'aaa', '打包完毕，开始物流配送！', '2022-01-25 12:59:31');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (147, 11, 'aaa', '下单并支付成功', '2022-01-25 13:01:11');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (148, 11, 'aaa', '打包完毕，开始物流配送！', '2022-01-25 13:01:11');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (149, 11, 'aaa', '下单并支付成功', '2022-01-25 13:06:30');
INSERT INTO `sys_trade_log` (`id`, `goods_id`, `goods_name`, `status`, `create_time`) VALUES (150, 11, 'aaa', '打包完毕，开始物流配送！', '2022-01-25 13:06:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近访问时间',
  `ssex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `create_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `dept_id`, `email`, `mobile`, `status`, `create_time`, `update_time`, `last_login_time`, `ssex`, `avatar`, `description`, `create_by`, `update_by`) VALUES (1, 'fxz', '{bcrypt}$2a$10$yMqLTL9t9TeRGp5fT7vENuxZaruaN.a/VlOpRGF7jBnKPAim6Xey.', 0, '2235602974@qq.com', '19806082431', '1', '2022-04-05 20:39:22', '2022-04-24 00:18:40', '2022-04-05 15:57:00', '0', '/system/file/fxzcloud/2cf6a2a5a7704c67b733877ae3d8ac23.png', 'FxzCloud的作者', NULL, 'fxz');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_post` (`user_id`, `post_id`) VALUES (1, 1511326356242804737);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `USER_ID` bigint NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`USER_ID`, `ROLE_ID`) VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for zipkin_annotations
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_annotations`;
CREATE TABLE `zipkin_annotations` (
  `trace_id_high` bigint NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint NOT NULL COMMENT 'coincides with zipkin_spans.trace_id',
  `span_id` bigint NOT NULL COMMENT 'coincides with zipkin_spans.id',
  `a_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'BinaryAnnotation.key or Annotation.value if type == -1',
  `a_value` blob COMMENT 'BinaryAnnotation.value(), which must be smaller than 64KB',
  `a_type` int NOT NULL COMMENT 'BinaryAnnotation.type() or -1 if Annotation',
  `a_timestamp` bigint DEFAULT NULL COMMENT 'Used to implement TTL; Annotation.timestamp or zipkin_spans.timestamp',
  `endpoint_ipv4` int DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_ipv6` binary(16) DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null, or no IPv6 address',
  `endpoint_port` smallint DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  `endpoint_service_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Null when Binary/Annotation.endpoint is null',
  UNIQUE KEY `trace_id_high` (`trace_id_high`,`trace_id`,`span_id`,`a_key`,`a_timestamp`) USING BTREE COMMENT 'Ignore insert on duplicate',
  KEY `trace_id_high_2` (`trace_id_high`,`trace_id`,`span_id`) USING BTREE COMMENT 'for joining with zipkin_spans',
  KEY `trace_id_high_3` (`trace_id_high`,`trace_id`) USING BTREE COMMENT 'for getTraces/ByIds',
  KEY `endpoint_service_name` (`endpoint_service_name`) USING BTREE COMMENT 'for getTraces and getServiceNames',
  KEY `a_type` (`a_type`) USING BTREE COMMENT 'for getTraces and autocomplete values',
  KEY `a_key` (`a_key`) USING BTREE COMMENT 'for getTraces and autocomplete values',
  KEY `trace_id` (`trace_id`,`span_id`,`a_key`) USING BTREE COMMENT 'for dependencies job'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_annotations
-- ----------------------------
BEGIN;
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -2566181379648875080, -2566181379648875080, 'lc', '', 6, 1638169865802000, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7881802119374865842, -7881802119374865842, 'lc', '', 6, 1638169867805000, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6145841007590615249, -6145841007590615249, 'sr', NULL, -1, 1638169875010503, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6145841007590615249, -6145841007590615249, 'ss', NULL, -1, 1638169875015540, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6145841007590615249, -6145841007590615249, 'ca', 0x01, 0, 1638169875010503, -1062721535, NULL, -5304, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6145841007590615249, -6145841007590615249, 'http.method', 0x474554, 6, 1638169875010503, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6145841007590615249, -6145841007590615249, 'http.path', 0x2F6163747561746F72, 6, 1638169875010503, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -8025310126578137703, -8025310126578137703, 'sr', NULL, -1, 1638169875020435, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -8025310126578137703, -8025310126578137703, 'ss', NULL, -1, 1638169875026622, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -8025310126578137703, -8025310126578137703, 'ca', 0x01, 0, 1638169875020435, -1062721535, NULL, -5302, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -8025310126578137703, -8025310126578137703, 'http.method', 0x474554, 6, 1638169875020435, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -8025310126578137703, -8025310126578137703, 'http.path', 0x2F6163747561746F72, 6, 1638169875020435, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7606622751437803094, -7606622751437803094, 'sr', NULL, -1, 1638169892123160, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7606622751437803094, -7606622751437803094, 'ss', NULL, -1, 1638169892126424, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7606622751437803094, -7606622751437803094, 'ca', 0x01, 0, 1638169892123160, -1062721535, NULL, -5301, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7606622751437803094, -7606622751437803094, 'http.method', 0x474554, 6, 1638169892123160, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7606622751437803094, -7606622751437803094, 'http.path', 0x2F6163747561746F72, 6, 1638169892123160, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 4592102201703596623, 4592102201703596623, 'sr', NULL, -1, 1638169894232100, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 4592102201703596623, 4592102201703596623, 'ss', NULL, -1, 1638169894234427, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 4592102201703596623, 4592102201703596623, 'ca', 0x01, 0, 1638169894232100, -1062721535, NULL, -5303, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 4592102201703596623, 4592102201703596623, 'http.method', 0x474554, 6, 1638169894232100, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 4592102201703596623, 4592102201703596623, 'http.path', 0x2F6163747561746F72, 6, 1638169894232100, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -6951861956747260916, 'cs', NULL, -1, 1638169938987665, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -6951861956747260916, 'cr', NULL, -1, 1638169939049919, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -6951861956747260916, 'http.method', 0x474554, 6, 1638169938987665, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -6951861956747260916, 'http.path', 0x2F617574682F75736572, 6, 1638169938987665, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, 3225804995639601053, 'cs', NULL, -1, 1638169939525241, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, 3225804995639601053, 'cr', NULL, -1, 1638169939573407, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, 3225804995639601053, 'http.method', 0x474554, 6, 1638169939525241, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, 3225804995639601053, 'http.path', 0x2F617574682F75736572, 6, 1638169939525241, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'sr', NULL, -1, 1638169939521100, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'ss', NULL, -1, 1638169939594973, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'ca', 0x01, 0, 1638169939521100, 2130706433, NULL, -5197, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'http.method', 0x474554, 6, 1638169939521100, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'http.path', 0x2F68656C6C6F, 6, 1638169939521100, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638169939521100, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'mvc.controller.method', 0x68656C6C6F, 6, 1638169939521100, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'cs', NULL, -1, 1638169939517799, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'cr', NULL, -1, 1638169939598483, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'http.method', 0x474554, 6, 1638169939517799, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -8520234267970190352, 'http.path', 0x2F68656C6C6F, 6, 1638169939517799, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'sr', NULL, -1, 1638169938984125, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'ss', NULL, -1, 1638169939611242, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'ca', 0x01, 0, 1638169938984125, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'http.method', 0x474554, 6, 1638169938984125, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'http.path', 0x2F68656C6C6F, 6, 1638169938984125, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638169938984125, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5328535706363524248, -5328535706363524248, 'mvc.controller.method', 0x68656C6C6F, 6, 1638169938984125, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3021340501933078410, 3021340501933078410, 'lc', '', 6, 1638171406675000, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -832826882313434463, -832826882313434463, 'lc', '', 6, 1638171416873000, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3831019749791502824, 3831019749791502824, 'sr', NULL, -1, 1638171433055254, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3831019749791502824, 3831019749791502824, 'ss', NULL, -1, 1638171433063336, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3831019749791502824, 3831019749791502824, 'ca', 0x01, 0, 1638171433055254, -1062721535, NULL, -3732, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3831019749791502824, 3831019749791502824, 'http.method', 0x474554, 6, 1638171433055254, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3831019749791502824, 3831019749791502824, 'http.path', 0x2F6163747561746F72, 6, 1638171433055254, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6953175459323808214, 6953175459323808214, 'sr', NULL, -1, 1638171443324234, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6953175459323808214, 6953175459323808214, 'ss', NULL, -1, 1638171443331556, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6953175459323808214, 6953175459323808214, 'ca', 0x01, 0, 1638171443324234, -1062721535, NULL, -3716, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6953175459323808214, 6953175459323808214, 'http.method', 0x474554, 6, 1638171443324234, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6953175459323808214, 6953175459323808214, 'http.path', 0x2F6163747561746F72, 6, 1638171443324234, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 8028521047333011932, 'cs', NULL, -1, 1638171534842575, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 8028521047333011932, 'cr', NULL, -1, 1638171534937773, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 8028521047333011932, 'http.method', 0x474554, 6, 1638171534842575, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 8028521047333011932, 'http.path', 0x2F617574682F75736572, 6, 1638171534842575, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'cs', NULL, -1, 1638171535296297, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'cr', NULL, -1, 1638171535351360, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'http.method', 0x474554, 6, 1638171535296297, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'http.path', 0x2F68656C6C6F, 6, 1638171535296297, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'sr', NULL, -1, 1638171534839111, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'ss', NULL, -1, 1638171535361900, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'ca', 0x01, 0, 1638171534839111, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'http.method', 0x474554, 6, 1638171534839111, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'http.path', 0x2F68656C6C6F, 6, 1638171534839111, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638171534839111, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 3592737260128299163, 'mvc.controller.method', 0x68656C6C6F, 6, 1638171534839111, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, -2874851072035824594, 'cs', NULL, -1, 1638171535303270, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, -2874851072035824594, 'cr', NULL, -1, 1638171535326023, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, -2874851072035824594, 'http.method', 0x474554, 6, 1638171535303270, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, -2874851072035824594, 'http.path', 0x2F617574682F75736572, 6, 1638171535303270, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'sr', NULL, -1, 1638171535299183, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'ss', NULL, -1, 1638171535349071, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'ca', 0x01, 0, 1638171535299183, 2130706433, NULL, -3679, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'http.method', 0x474554, 6, 1638171535299183, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'http.path', 0x2F68656C6C6F, 6, 1638171535299183, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638171535299183, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3592737260128299163, 1198070489411063301, 'mvc.controller.method', 0x68656C6C6F, 6, 1638171535299183, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -6092636913825039442, 'cs', NULL, -1, 1638171883694734, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -6092636913825039442, 'cr', NULL, -1, 1638171883707687, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -6092636913825039442, 'http.method', 0x474554, 6, 1638171883694734, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -6092636913825039442, 'http.path', 0x2F617574682F75736572, 6, 1638171883694734, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'cs', NULL, -1, 1638171883710609, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'cr', NULL, -1, 1638171883730378, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'http.method', 0x474554, 6, 1638171883710609, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'http.path', 0x2F68656C6C6F, 6, 1638171883710609, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'sr', NULL, -1, 1638171883694087, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'ss', NULL, -1, 1638171883731729, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'ca', 0x01, 0, 1638171883694087, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'http.method', 0x474554, 6, 1638171883694087, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'http.path', 0x2F68656C6C6F, 6, 1638171883694087, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638171883694087, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -5656661044141199813, 'mvc.controller.method', 0x68656C6C6F, 6, 1638171883694087, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -8489855318435819779, 'cs', NULL, -1, 1638171883712031, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -8489855318435819779, 'cr', NULL, -1, 1638171883725761, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -8489855318435819779, 'http.method', 0x474554, 6, 1638171883712031, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, -8489855318435819779, 'http.path', 0x2F617574682F75736572, 6, 1638171883712031, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'sr', NULL, -1, 1638171883712085, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'ss', NULL, -1, 1638171883730681, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'ca', 0x01, 0, 1638171883712085, 2130706433, NULL, -3481, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'http.method', 0x474554, 6, 1638171883712085, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'http.path', 0x2F68656C6C6F, 6, 1638171883712085, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638171883712085, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5656661044141199813, 1200834296849922203, 'mvc.controller.method', 0x68656C6C6F, 6, 1638171883712085, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8109846340536009304, 'cs', NULL, -1, 1638171885829877, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8109846340536009304, 'cr', NULL, -1, 1638171885841633, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8109846340536009304, 'http.method', 0x474554, 6, 1638171885829877, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8109846340536009304, 'http.path', 0x2F617574682F75736572, 6, 1638171885829877, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'sr', NULL, -1, 1638171885828194, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'ss', NULL, -1, 1638171886199373, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'ca', 0x01, 0, 1638171885828194, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'http.method', 0x505554, 6, 1638171885828194, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'http.path', 0x2F75736572, 6, 1638171885828194, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'mvc.controller.class', 0x55736572436F6E74726F6C6C6572, 6, 1638171885828194, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 8582406937137518271, 8582406937137518271, 'mvc.controller.method', 0x75706461746555736572, 6, 1638171885828194, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -7001130729477240016, -7001130729477240016, 'lc', '', 6, 1638172016343000, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5953620445964840203, 5953620445964840203, 'sr', NULL, -1, 1638172023984811, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5953620445964840203, 5953620445964840203, 'ss', NULL, -1, 1638172023991105, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5953620445964840203, 5953620445964840203, 'ca', 0x01, 0, 1638172023984811, -1062721535, NULL, -3318, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5953620445964840203, 5953620445964840203, 'http.method', 0x474554, 6, 1638172023984811, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5953620445964840203, 5953620445964840203, 'http.path', 0x2F6163747561746F72, 6, 1638172023984811, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5555067615528282343, 5555067615528282343, 'lc', '', 6, 1638172027446000, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -895604104681823013, 'cs', NULL, -1, 1638172033329855, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -895604104681823013, 'cr', NULL, -1, 1638172033352121, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -895604104681823013, 'http.method', 0x474554, 6, 1638172033329855, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -895604104681823013, 'http.path', 0x2F617574682F75736572, 6, 1638172033329855, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'sr', NULL, -1, 1638172033317109, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'ss', NULL, -1, 1638172033668958, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'ca', 0x01, 0, 1638172033317109, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'http.method', 0x505554, 6, 1638172033317109, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'http.path', 0x2F75736572, 6, 1638172033317109, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'mvc.controller.class', 0x55736572436F6E74726F6C6C6572, 6, 1638172033317109, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -6387757123946159492, -6387757123946159492, 'mvc.controller.method', 0x75706461746555736572, 6, 1638172033317109, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -6751089193175309411, 'cs', NULL, -1, 1638172034664994, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -6751089193175309411, 'cr', NULL, -1, 1638172034677582, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -6751089193175309411, 'http.method', 0x474554, 6, 1638172034664994, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -6751089193175309411, 'http.path', 0x2F617574682F75736572, 6, 1638172034664994, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'sr', NULL, -1, 1638172034664102, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'ss', NULL, -1, 1638172034693000, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'ca', 0x01, 0, 1638172034664102, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'http.method', 0x505554, 6, 1638172034664102, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'http.path', 0x2F75736572, 6, 1638172034664102, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'mvc.controller.class', 0x55736572436F6E74726F6C6C6572, 6, 1638172034664102, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5778378588885136564, -5778378588885136564, 'mvc.controller.method', 0x75706461746555736572, 6, 1638172034664102, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4917283278434034164, 'cs', NULL, -1, 1638172037192059, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4917283278434034164, 'cr', NULL, -1, 1638172037200850, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4917283278434034164, 'http.method', 0x474554, 6, 1638172037192059, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4917283278434034164, 'http.path', 0x2F617574682F75736572, 6, 1638172037192059, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'sr', NULL, -1, 1638172037191112, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'ss', NULL, -1, 1638172037208005, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'ca', 0x01, 0, 1638172037191112, 2130706433, NULL, -3264, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'http.method', 0x474554, 6, 1638172037191112, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'http.path', 0x2F68656C6C6F, 6, 1638172037191112, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638172037191112, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'mvc.controller.method', 0x68656C6C6F, 6, 1638172037191112, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4623739235853618020, 'cs', NULL, -1, 1638172036801136, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4623739235853618020, 'cr', NULL, -1, 1638172036824833, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4623739235853618020, 'http.method', 0x474554, 6, 1638172036801136, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4623739235853618020, 'http.path', 0x2F617574682F75736572, 6, 1638172036801136, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'cs', NULL, -1, 1638172037190102, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'cr', NULL, -1, 1638172037212833, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'http.method', 0x474554, 6, 1638172037190102, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, 4872798174086814728, 'http.path', 0x2F68656C6C6F, 6, 1638172037190102, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'sr', NULL, -1, 1638172036775310, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'ss', NULL, -1, 1638172037241653, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'ca', 0x01, 0, 1638172036775310, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'http.method', 0x474554, 6, 1638172036775310, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'http.path', 0x2F68656C6C6F, 6, 1638172036775310, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'mvc.controller.class', 0x54657374436F6E74726F6C6C6572, 6, 1638172036775310, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4279369022548599338, -4279369022548599338, 'mvc.controller.method', 0x68656C6C6F, 6, 1638172036775310, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 2161201663844554158, 2161201663844554158, 'sr', NULL, -1, 1638172042857098, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 2161201663844554158, 2161201663844554158, 'ss', NULL, -1, 1638172042859704, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 2161201663844554158, 2161201663844554158, 'ca', 0x01, 0, 1638172042857098, -1062721535, NULL, -3317, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 2161201663844554158, 2161201663844554158, 'http.method', 0x474554, 6, 1638172042857098, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 2161201663844554158, 2161201663844554158, 'http.path', 0x2F6163747561746F72, 6, 1638172042857098, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 9063703747363425883, 9063703747363425883, 'sr', NULL, -1, 1638172043861134, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 9063703747363425883, 9063703747363425883, 'ss', NULL, -1, 1638172043864526, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 9063703747363425883, 9063703747363425883, 'ca', 0x01, 0, 1638172043861134, -1062721535, NULL, -3257, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 9063703747363425883, 9063703747363425883, 'http.method', 0x474554, 6, 1638172043861134, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 9063703747363425883, 9063703747363425883, 'http.path', 0x2F6163747561746F72, 6, 1638172043861134, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 261522166580646438, 261522166580646438, 'sr', NULL, -1, 1638172053902101, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 261522166580646438, 261522166580646438, 'ss', NULL, -1, 1638172053904298, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 261522166580646438, 261522166580646438, 'ca', 0x01, 0, 1638172053902101, -1062721535, NULL, -3258, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 261522166580646438, 261522166580646438, 'http.method', 0x474554, 6, 1638172053902101, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 261522166580646438, 261522166580646438, 'http.path', 0x2F6163747561746F72, 6, 1638172053902101, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, 6868718754103691883, 'cs', NULL, -1, 1638172593497465, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, 6868718754103691883, 'cr', NULL, -1, 1638172593507775, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, 6868718754103691883, 'http.method', 0x474554, 6, 1638172593497465, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, 6868718754103691883, 'http.path', 0x2F617574682F75736572, 6, 1638172593497465, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'sr', NULL, -1, 1638172593497064, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'ss', NULL, -1, 1638172593525793, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'ca', 0x01, 0, 1638172593497064, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'http.method', 0x505554, 6, 1638172593497064, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'http.path', 0x2F75736572, 6, 1638172593497064, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'mvc.controller.class', 0x55736572436F6E74726F6C6C6572, 6, 1638172593497064, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5378421523613414783, -5378421523613414783, 'mvc.controller.method', 0x75706461746555736572, 6, 1638172593497064, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 8375295979555635969, 'cs', NULL, -1, 1638172595384801, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 8375295979555635969, 'cr', NULL, -1, 1638172595396921, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 8375295979555635969, 'http.method', 0x474554, 6, 1638172595384801, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 8375295979555635969, 'http.path', 0x2F617574682F75736572, 6, 1638172595384801, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'sr', NULL, -1, 1638172595384081, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'ss', NULL, -1, 1638172595464071, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'ca', 0x01, 0, 1638172595384081, NULL, 0x00000000000000000000000000000001, NULL, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'http.method', 0x474554, 6, 1638172595384081, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'http.path', 0x2F75736572, 6, 1638172595384081, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'mvc.controller.class', 0x55736572436F6E74726F6C6C6572, 6, 1638172595384081, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 3969593134840307654, 3969593134840307654, 'mvc.controller.method', 0x757365724C697374, 6, 1638172595384081, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -2442269197606816279, -2442269197606816279, 'lc', '', 6, 1638172780931000, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 1636879556027148046, 1636879556027148046, 'lc', '', 6, 1638172783690000, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5267234743012679838, 5267234743012679838, 'sr', NULL, -1, 1638172807175193, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5267234743012679838, 5267234743012679838, 'ss', NULL, -1, 1638172807183522, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5267234743012679838, 5267234743012679838, 'ca', 0x01, 0, 1638172807175193, -1062721535, NULL, -2434, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5267234743012679838, 5267234743012679838, 'http.method', 0x474554, 6, 1638172807175193, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 5267234743012679838, 5267234743012679838, 'http.path', 0x2F6163747561746F72, 6, 1638172807175193, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5216487376956510447, -5216487376956510447, 'sr', NULL, -1, 1638172809976512, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5216487376956510447, -5216487376956510447, 'ss', NULL, -1, 1638172809982302, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5216487376956510447, -5216487376956510447, 'ca', 0x01, 0, 1638172809976512, -1062721535, NULL, -2431, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5216487376956510447, -5216487376956510447, 'http.method', 0x474554, 6, 1638172809976512, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -5216487376956510447, -5216487376956510447, 'http.path', 0x2F6163747561746F72, 6, 1638172809976512, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6121297254844789231, 6121297254844789231, 'sr', NULL, -1, 1638179258730900, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6121297254844789231, 6121297254844789231, 'ss', NULL, -1, 1638179258736126, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6121297254844789231, 6121297254844789231, 'ca', 0x01, 0, 1638179258730900, -1062721535, NULL, -7316, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6121297254844789231, 6121297254844789231, 'http.method', 0x474554, 6, 1638179258730900, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, 6121297254844789231, 6121297254844789231, 'http.path', 0x2F6163747561746F72, 6, 1638179258730900, -1062715903, NULL, NULL, 'fxz-server-system');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4991943118875554767, -4991943118875554767, 'sr', NULL, -1, 1638179258834153, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4991943118875554767, -4991943118875554767, 'ss', NULL, -1, 1638179258838428, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4991943118875554767, -4991943118875554767, 'ca', 0x01, 0, 1638179258834153, -1062721535, NULL, -7309, '');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4991943118875554767, -4991943118875554767, 'http.method', 0x474554, 6, 1638179258834153, -1062715903, NULL, NULL, 'fxz-server-system-test');
INSERT INTO `zipkin_annotations` (`trace_id_high`, `trace_id`, `span_id`, `a_key`, `a_value`, `a_type`, `a_timestamp`, `endpoint_ipv4`, `endpoint_ipv6`, `endpoint_port`, `endpoint_service_name`) VALUES (0, -4991943118875554767, -4991943118875554767, 'http.path', 0x2F6163747561746F72, 6, 1638179258834153, -1062715903, NULL, NULL, 'fxz-server-system-test');
COMMIT;

-- ----------------------------
-- Table structure for zipkin_dependencies
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_dependencies`;
CREATE TABLE `zipkin_dependencies` (
  `day` date NOT NULL,
  `parent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `child` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `call_count` bigint DEFAULT NULL,
  `error_count` bigint DEFAULT NULL,
  PRIMARY KEY (`day`,`parent`,`child`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_dependencies
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for zipkin_spans
-- ----------------------------
DROP TABLE IF EXISTS `zipkin_spans`;
CREATE TABLE `zipkin_spans` (
  `trace_id_high` bigint NOT NULL DEFAULT '0' COMMENT 'If non zero, this means the trace uses 128 bit traceIds instead of 64 bit',
  `trace_id` bigint NOT NULL,
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `remote_service_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `debug` bit(1) DEFAULT NULL,
  `start_ts` bigint DEFAULT NULL COMMENT 'Span.timestamp(): epoch micros used for endTs query and to implement TTL',
  `duration` bigint DEFAULT NULL COMMENT 'Span.duration(): micros used for minDuration and maxDuration query',
  PRIMARY KEY (`trace_id_high`,`trace_id`,`id`) USING BTREE,
  KEY `trace_id_high` (`trace_id_high`,`trace_id`) USING BTREE COMMENT 'for getTracesByIds',
  KEY `name` (`name`) USING BTREE COMMENT 'for getTraces and getSpanNames',
  KEY `remote_service_name` (`remote_service_name`) USING BTREE COMMENT 'for getTraces and getRemoteServiceNames',
  KEY `start_ts` (`start_ts`) USING BTREE COMMENT 'for getTraces ordering and range'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of zipkin_spans
-- ----------------------------
BEGIN;
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -8025310126578137703, -8025310126578137703, 'get', '', NULL, NULL, 1638169875020435, 6187);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -7881802119374865842, -7881802119374865842, 'async', '', NULL, NULL, 1638169867805000, 697619);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -7606622751437803094, -7606622751437803094, 'get', '', NULL, NULL, 1638169892123160, 3264);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -7001130729477240016, -7001130729477240016, 'async', '', NULL, NULL, 1638172016343000, 691219);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -6387757123946159492, -6387757123946159492, 'put /user', '', NULL, NULL, 1638172033317109, 351849);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -6387757123946159492, -895604104681823013, 'get', '', -6387757123946159492, NULL, 1638172033329855, 22266);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -6145841007590615249, -6145841007590615249, 'get', '', NULL, NULL, 1638169875010503, 5037);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5778378588885136564, -6751089193175309411, 'get', '', -5778378588885136564, NULL, 1638172034664994, 12588);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5778378588885136564, -5778378588885136564, 'put /user', '', NULL, NULL, 1638172034664102, 28898);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5656661044141199813, -8489855318435819779, 'get', '', 1200834296849922203, NULL, 1638171883712031, 13730);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5656661044141199813, -6092636913825039442, 'get', '', -5656661044141199813, NULL, 1638171883694734, 12953);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5656661044141199813, -5656661044141199813, 'get /hello', '', NULL, NULL, 1638171883694087, 37642);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5656661044141199813, 1200834296849922203, 'get /hello', '', -5656661044141199813, NULL, 1638171883710609, 19769);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5378421523613414783, -5378421523613414783, 'put /user', '', NULL, NULL, 1638172593497064, 28729);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5378421523613414783, 6868718754103691883, 'get', '', -5378421523613414783, NULL, 1638172593497465, 10310);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5328535706363524248, -8520234267970190352, 'get', '', -5328535706363524248, NULL, 1638169939517799, 80684);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5328535706363524248, -6951861956747260916, 'get', '', -5328535706363524248, NULL, 1638169938987665, 62254);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5328535706363524248, -5328535706363524248, 'get /hello', '', NULL, NULL, 1638169938984125, 627117);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5328535706363524248, 3225804995639601053, 'get', '', -8520234267970190352, NULL, 1638169939525241, 48166);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -5216487376956510447, -5216487376956510447, 'get', '', NULL, NULL, 1638172809976512, 5790);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -4991943118875554767, -4991943118875554767, 'get', '', NULL, NULL, 1638179258834153, 4275);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -4279369022548599338, -4279369022548599338, 'get /hello', '', NULL, NULL, 1638172036775310, 466343);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -4279369022548599338, 4623739235853618020, 'get', '', -4279369022548599338, NULL, 1638172036801136, 23697);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -4279369022548599338, 4872798174086814728, 'get', '', -4279369022548599338, NULL, 1638172037190102, 22731);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -4279369022548599338, 4917283278434034164, 'get', '', 4872798174086814728, NULL, 1638172037192059, 8791);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -2566181379648875080, -2566181379648875080, 'async', '', NULL, NULL, 1638169865802000, 848862);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -2442269197606816279, -2442269197606816279, 'async', '', NULL, NULL, 1638172780931000, 974437);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, -832826882313434463, -832826882313434463, 'async', '', NULL, NULL, 1638171416873000, 702409);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 261522166580646438, 261522166580646438, 'get', '', NULL, NULL, 1638172053902101, 2197);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 1636879556027148046, 1636879556027148046, 'async', '', NULL, NULL, 1638172783690000, 731652);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 2161201663844554158, 2161201663844554158, 'get', '', NULL, NULL, 1638172042857098, 2606);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3021340501933078410, 3021340501933078410, 'async', '', NULL, NULL, 1638171406675000, 1077855);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3592737260128299163, -2874851072035824594, 'get', '', 1198070489411063301, NULL, 1638171535303270, 22753);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3592737260128299163, 1198070489411063301, 'get /hello', '', 3592737260128299163, NULL, 1638171535296297, 55063);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3592737260128299163, 3592737260128299163, 'get /hello', '', NULL, NULL, 1638171534839111, 522789);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3592737260128299163, 8028521047333011932, 'get', '', 3592737260128299163, NULL, 1638171534842575, 95198);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3831019749791502824, 3831019749791502824, 'get', '', NULL, NULL, 1638171433055254, 8082);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3969593134840307654, 3969593134840307654, 'get /user', '', NULL, NULL, 1638172595384081, 79990);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 3969593134840307654, 8375295979555635969, 'get', '', 3969593134840307654, NULL, 1638172595384801, 12120);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 4592102201703596623, 4592102201703596623, 'get', '', NULL, NULL, 1638169894232100, 2327);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 5267234743012679838, 5267234743012679838, 'get', '', NULL, NULL, 1638172807175193, 8329);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 5555067615528282343, 5555067615528282343, 'async', '', NULL, NULL, 1638172027446000, 730540);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 5953620445964840203, 5953620445964840203, 'get', '', NULL, NULL, 1638172023984811, 6294);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 6121297254844789231, 6121297254844789231, 'get', '', NULL, NULL, 1638179258730900, 5226);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 6953175459323808214, 6953175459323808214, 'get', '', NULL, NULL, 1638171443324234, 7322);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 8582406937137518271, 8109846340536009304, 'get', '', 8582406937137518271, NULL, 1638171885829877, 11756);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 8582406937137518271, 8582406937137518271, 'put /user', '', NULL, NULL, 1638171885828194, 371179);
INSERT INTO `zipkin_spans` (`trace_id_high`, `trace_id`, `id`, `name`, `remote_service_name`, `parent_id`, `debug`, `start_ts`, `duration`) VALUES (0, 9063703747363425883, 9063703747363425883, 'get', '', NULL, NULL, 1638172043861134, 3392);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
