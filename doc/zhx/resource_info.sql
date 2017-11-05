/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 01:00:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `resource_info`
-- ----------------------------
DROP TABLE IF EXISTS `resource_info`;
CREATE TABLE `resource_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '资源名称',
  `pid` int(11) DEFAULT NULL COMMENT '上级资源',
  `pname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '上级资源名称',
  `path` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源路劲',
  `createdate` date DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资源图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource_info
-- ----------------------------
INSERT INTO `resource_info` VALUES ('1', '系统管理', null, null, null, null, null, null);
INSERT INTO `resource_info` VALUES ('2', '用户管理', '1', '系统管理', '/user/selectUser', '2017-11-01', null, null);
INSERT INTO `resource_info` VALUES ('3', '资源管理', '1', '系统管理', '/resource/resourcesAck', null, null, null);
INSERT INTO `resource_info` VALUES ('7', '员工系统', null, null, null, '2017-11-03', null, null);
