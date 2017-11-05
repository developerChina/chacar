/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 01:00:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `resource_access`
-- ----------------------------
DROP TABLE IF EXISTS `resource_access`;
CREATE TABLE `resource_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源权限id',
  `resource_id` int(11) NOT NULL COMMENT '资源ID',
  `user_id` int(11) NOT NULL COMMENT '资源所属用户id',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of resource_access
-- ----------------------------
INSERT INTO `resource_access` VALUES ('19', '1', '2', '2017-11-05 11:29:08');
INSERT INTO `resource_access` VALUES ('20', '2', '2', '2017-11-05 11:29:08');
INSERT INTO `resource_access` VALUES ('21', '3', '2', '2017-11-05 11:29:08');
INSERT INTO `resource_access` VALUES ('22', '7', '2', '2017-11-05 11:29:08');
INSERT INTO `resource_access` VALUES ('26', '2', '1', '2017-11-05 11:34:20');
INSERT INTO `resource_access` VALUES ('27', '1', '1', '2017-11-05 11:34:20');
