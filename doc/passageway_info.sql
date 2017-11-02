/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-02 11:26:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `passageway_info`
-- ----------------------------
DROP TABLE IF EXISTS `passageway_info`;
CREATE TABLE `passageway_info` (
  `passagewayID` int(11) NOT NULL AUTO_INCREMENT,
  `passagewayName` varchar(20) NOT NULL,
  `ControllerSN` varchar(20) NOT NULL,
  `ControllerIP` varchar(20) NOT NULL,
  `ptype` varchar(5) NOT NULL,
  PRIMARY KEY (`passagewayID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passageway_info
-- ----------------------------
INSERT INTO `passageway_info` VALUES ('61', 'h', 'h', 'ggggg是', '1');
INSERT INTO `passageway_info` VALUES ('62', 'h', 'h', 'h', '0');
INSERT INTO `passageway_info` VALUES ('63', 'h', 'ggggggggggggggg', 'h', '1');
INSERT INTO `passageway_info` VALUES ('64', 'h', 'h', 'h', '1');
INSERT INTO `passageway_info` VALUES ('65', 'h', 'h', 'h', '1');
INSERT INTO `passageway_info` VALUES ('66', 'h', 'h', 'h', '0');
INSERT INTO `passageway_info` VALUES ('67', 'ff', 'ff', 'ff', '1');
INSERT INTO `passageway_info` VALUES ('68', '1', '1', '1', '1');
INSERT INTO `passageway_info` VALUES ('69', 'fff', 'fff', 'fff', '1');
INSERT INTO `passageway_info` VALUES ('70', 'bbb', 'bbb', 'bbb', '1');
INSERT INTO `passageway_info` VALUES ('71', 'e', 'e', 'e', '1');
INSERT INTO `passageway_info` VALUES ('72', 'a', 'a', 'a', '1');
INSERT INTO `passageway_info` VALUES ('73', 'as', 's', 's', '0');
INSERT INTO `passageway_info` VALUES ('74', 'gg', 'gg', 'gg', '1');
INSERT INTO `passageway_info` VALUES ('75', 'jjj', 'jfr', 'jfr', '1');
INSERT INTO `passageway_info` VALUES ('76', 'fff', 'fff', 'fff', '0');
