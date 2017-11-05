/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:58:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `card_access`
-- ----------------------------
DROP TABLE IF EXISTS `card_access`;
CREATE TABLE `card_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'card权限id',
  `cardno` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  `channel` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '通道',
  `floor` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电梯',
  `room` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '门禁',
  `park` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '停车场',
  `validate` datetime DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of card_access
-- ----------------------------
INSERT INTO `card_access` VALUES ('14', 'www', 'www', 'www', 'www', 'wwwww', '2017-11-02 00:00:00');
