/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-07 15:45:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `elevator_inf`
-- ----------------------------
DROP TABLE IF EXISTS `elevator_inf`;
CREATE TABLE `elevator_inf` (
  `ElevatorID` int(50) NOT NULL auto_increment COMMENT '电梯表主键',
  `ElevatorName` varchar(50) NOT NULL COMMENT '电梯名称',
  `ControllerSN` varchar(50) NOT NULL COMMENT '控制器SN',
  `ControllerIP` varchar(10) NOT NULL COMMENT '控制器IP',
  `FloorNumber` varchar(10) NOT NULL COMMENT '电梯表楼层编号',
  PRIMARY KEY  (`ElevatorID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elevator_inf
-- ----------------------------
INSERT INTO `elevator_inf` VALUES ('1', '大厅A电梯', '1', '1', '1');
INSERT INTO `elevator_inf` VALUES ('2', '大厅B电梯', '2', '2', '1');
INSERT INTO `elevator_inf` VALUES ('3', '大厅C电梯', '3', '3', '1');
INSERT INTO `elevator_inf` VALUES ('4', '大厅D电梯', '4圣达菲', '电风扇', '1圣达菲');
INSERT INTO `elevator_inf` VALUES ('5', '阿斯顿', '爱迪生', '阿斯顿', 'ad');
