/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-07 15:42:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `passageway_info`
-- ----------------------------
DROP TABLE IF EXISTS `passageway_info`;
CREATE TABLE `passageway_info` (
  `passagewayID` int(11) NOT NULL AUTO_INCREMENT COMMENT '通道id',
  `passagewayName` varchar(20) NOT NULL COMMENT '通道名称',
  `ControllerSN` varchar(20) NOT NULL COMMENT '控制器SN',
  `ControllerIP` varchar(20) NOT NULL COMMENT '控制器IP',
  `ptype` varchar(5) NOT NULL COMMENT '通道种类',
  PRIMARY KEY (`passagewayID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passageway_info
-- ----------------------------
INSERT INTO `passageway_info` VALUES ('5', '嚎哭深渊', 'HK001', 'HK001', '0');
INSERT INTO `passageway_info` VALUES ('6', 'wo', '111', '222', '0');
INSERT INTO `passageway_info` VALUES ('7', 'ca', '33', '33', '0');
