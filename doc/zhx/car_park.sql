/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:58:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `car_park`
-- ----------------------------
DROP TABLE IF EXISTS `car_park`;
CREATE TABLE `car_park` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '停车场id',
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '车场名称',
  `no` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '车场编号',
  `num` int(11) NOT NULL COMMENT '停车位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='停车场信息表';

-- ----------------------------
-- Records of car_park
-- ----------------------------
INSERT INTO `car_park` VALUES ('1', '内部停车场01', '01', '200');
INSERT INTO `car_park` VALUES ('2', '内部停车场02', '02', '100');
