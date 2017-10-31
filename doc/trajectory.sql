/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : chacar

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-10-31 16:54:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `trajectory`
-- ----------------------------
DROP TABLE IF EXISTS `trajectory`;
CREATE TABLE `trajectory` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `controllerSN` varchar(20) DEFAULT NULL,
  `cardNo` varchar(10) DEFAULT NULL,
  `doorNo` varchar(10) DEFAULT NULL,
  `ifvalid` varchar(16) DEFAULT NULL,
  `optInOut` varchar(6) DEFAULT NULL,
  `optDate` varchar(20) DEFAULT NULL,
  `optDesc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trajectory
-- ----------------------------
INSERT INTO `trajectory` VALUES ('071de95efff64c2fbc0d981231049eb5', '173100788', '13942026', '4', '进门', '禁止', '2017-10-31 16:47:57', '刷卡禁止通过: 没有权限');
INSERT INTO `trajectory` VALUES ('2a47e7eb65ad49e38773600c153d06cf', '173100788', '13942026', '128', '进门', '通过', '2017-10-31 16:49:33', '刷卡开门');
INSERT INTO `trajectory` VALUES ('32225653c1bb4b03a5e1545534854826', '173100788', '13942026', '4', '进门', '禁止', '2017-10-31 16:48:41', '刷卡禁止通过: 没有权限');
INSERT INTO `trajectory` VALUES ('9d0836be415c4f129ee5388cf06444e8', '173100788', '13942026', '128', '进门', '通过', '2017-10-31 16:51:01', '刷卡开门');
INSERT INTO `trajectory` VALUES ('abe0f32e7b5e4ffc8693b078eda9d569', '173100788', '13942026', '128', '进门', '通过', '2017-10-31 16:51:10', '刷卡开门');
INSERT INTO `trajectory` VALUES ('d26927d799074e9db2d52a16732c4c25', '173100788', '13942026', '4', '进门', '禁止', '2017-10-31 16:47:37', '刷卡禁止通过: 没有权限');
