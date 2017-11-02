/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-02 11:29:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `elevator_inf`
-- ----------------------------
DROP TABLE IF EXISTS `elevator_inf`;
CREATE TABLE `elevator_inf` (
  `ElevatorID` int(50) NOT NULL auto_increment,
  `ElevatorName` varchar(50) NOT NULL,
  `ControllerSN` varchar(50) NOT NULL,
  `ControllerIP` int(10) NOT NULL,
  `FloorNumber` int(10) NOT NULL,
  PRIMARY KEY  (`ElevatorID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elevator_inf
-- ----------------------------
INSERT INTO `elevator_inf` VALUES ('1', '123', '123', '123', '123');
INSERT INTO `elevator_inf` VALUES ('2', '123', '123', '123', '123');
INSERT INTO `elevator_inf` VALUES ('3', '1', '1', '1', '1');
INSERT INTO `elevator_inf` VALUES ('4', '1', '1', '1', '1');
INSERT INTO `elevator_inf` VALUES ('5', '1', '1', '1', '1231');
INSERT INTO `elevator_inf` VALUES ('6', '1', '1vF', '1', '1231231');
INSERT INTO `elevator_inf` VALUES ('7', '1', '1', '1333', '1');
INSERT INTO `elevator_inf` VALUES ('8', '1', '1', '1', '1');
INSERT INTO `elevator_inf` VALUES ('11', '123', '1', '1', '123');
INSERT INTO `elevator_inf` VALUES ('12', '123', '1', '123', '123');
INSERT INTO `elevator_inf` VALUES ('13', '1', '1', '1', '123');
INSERT INTO `elevator_inf` VALUES ('14', '1', '123', '1', '123');
INSERT INTO `elevator_inf` VALUES ('15', '1', '1', '1', '1');
INSERT INTO `elevator_inf` VALUES ('16', '123', '123', '1', '1');
