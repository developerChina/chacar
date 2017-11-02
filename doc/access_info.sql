/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-02 11:25:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `access_info`
-- ----------------------------
DROP TABLE IF EXISTS `access_info`;
CREATE TABLE `access_info` (
  `accessid` int(20) NOT NULL AUTO_INCREMENT,
  `accessname` varchar(20) NOT NULL,
  `csn` varchar(20) NOT NULL,
  `cip` varchar(20) NOT NULL,
  PRIMARY KEY (`accessid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of access_info
-- ----------------------------
INSERT INTO `access_info` VALUES ('3', '3', '3', 'gggg');
INSERT INTO `access_info` VALUES ('4', '4', '4', '4');
INSERT INTO `access_info` VALUES ('5', '5', '5', '5');
INSERT INTO `access_info` VALUES ('6', '6', '6', '6');
INSERT INTO `access_info` VALUES ('7', '7', '7', '7');
INSERT INTO `access_info` VALUES ('8', '8', '8', '8');
INSERT INTO `access_info` VALUES ('9', '9', '9', '9');
INSERT INTO `access_info` VALUES ('10', '0', '0', '0');
INSERT INTO `access_info` VALUES ('11', '1', '2', '2');
INSERT INTO `access_info` VALUES ('12', '是', '是', '是');

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

-- ----------------------------
-- Table structure for `reson_info`
-- ----------------------------
DROP TABLE IF EXISTS `reson_info`;
CREATE TABLE `reson_info` (
  `content` text NOT NULL,
  `rid` int(3) NOT NULL AUTO_INCREMENT,
  `rtime` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reson_info
-- ----------------------------
INSERT INTO `reson_info` VALUES ('的说法', '10', '');
INSERT INTO `reson_info` VALUES ('水电费', '11', '');
INSERT INTO `reson_info` VALUES ('第三方', '12', '');
INSERT INTO `reson_info` VALUES ('地方撒', '13', '');
INSERT INTO `reson_info` VALUES ('其他是是是', '14', '');
INSERT INTO `reson_info` VALUES ('jjj', '15', '4');
INSERT INTO `reson_info` VALUES ('找高原', '16', '1');
INSERT INTO `reson_info` VALUES ('找高原', '17', '1');
