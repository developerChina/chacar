/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:57:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car_authority`
-- ----------------------------
DROP TABLE IF EXISTS `car_authority`;
CREATE TABLE `car_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆授权id',
  `carno` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '车牌号',
  `passageway_id` int(11) NOT NULL COMMENT '出入口id',
  `validate` datetime DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车辆授权控制';

-- ----------------------------
-- Records of car_authority
-- ----------------------------
