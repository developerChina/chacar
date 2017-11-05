/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:58:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car_passageway`
-- ----------------------------
DROP TABLE IF EXISTS `car_passageway`;
CREATE TABLE `car_passageway` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '出入口id',
  `type` int(11) NOT NULL COMMENT '类型（0=入口，1=出口，2=入/出口）',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '识别仪名称',
  `park_id` int(11) NOT NULL COMMENT '所属停车场',
  `distinguish_ids` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '识别仪列表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='出入口信息';

-- ----------------------------
-- Records of car_passageway
-- ----------------------------
