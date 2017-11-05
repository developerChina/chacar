/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:59:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `NAME` varchar(50) NOT NULL COMMENT '部门名称',
  `PID` int(11) DEFAULT NULL COMMENT '上级部门id',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '部门描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', null, '技术部', null);
INSERT INTO `dept_inf` VALUES ('2', '运营部', null, '运营部', null);
INSERT INTO `dept_inf` VALUES ('3', '财务部', null, '财务部', null);
INSERT INTO `dept_inf` VALUES ('5', '总公办', null, '总公办', null);
INSERT INTO `dept_inf` VALUES ('6', '市场部', null, '市场部', null);
INSERT INTO `dept_inf` VALUES ('7', '教学部', '1', '教学部', null);
INSERT INTO `dept_inf` VALUES ('24', '技术1部', '1', '技术1部', null);
