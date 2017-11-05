/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:59:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `depart_info`
-- ----------------------------
DROP TABLE IF EXISTS `depart_info`;
CREATE TABLE `depart_info` (
  `deptID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '部门ID',
  `deptName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '部门名称',
  `supDeptID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '上级部门ID',
  `supDeptName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '上级部门名称',
  PRIMARY KEY (`deptID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='公司部门表';

-- ----------------------------
-- Records of depart_info
-- ----------------------------
INSERT INTO `depart_info` VALUES ('1', '行政部', '0', '总部');
INSERT INTO `depart_info` VALUES ('2', '人事部', '0', '总部');
INSERT INTO `depart_info` VALUES ('3', '研发部', '0', '总部');
INSERT INTO `depart_info` VALUES ('4', '研发1部', '3', '研发部');
