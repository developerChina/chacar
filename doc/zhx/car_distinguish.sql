/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:58:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car_distinguish`
-- ----------------------------
DROP TABLE IF EXISTS `car_distinguish`;
CREATE TABLE `car_distinguish` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '相机id',
  `no` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '相机编号',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '相机名称',
  `ip` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '相机ip',
  `action` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '相机执行类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='识别仪信息表';

-- ----------------------------
-- Records of car_distinguish
-- ----------------------------
INSERT INTO `car_distinguish` VALUES ('1', '0101', '01出口相机', '192.168.1.101', 'com.test.car');
INSERT INTO `car_distinguish` VALUES ('2', '0102', '01入口相机', '192.168.1.102', 'com.test.car');
