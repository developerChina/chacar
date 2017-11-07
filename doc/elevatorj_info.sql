/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-07 15:45:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `elevatorj_info`
-- ----------------------------
DROP TABLE IF EXISTS `elevatorj_info`;
CREATE TABLE `elevatorj_info` (
  `ejid` varchar(50) NOT NULL COMMENT '电梯授权id',
  `ejname` varchar(50) NOT NULL COMMENT '授权表名称',
  `ejemp` varchar(50) NOT NULL COMMENT '电梯授权所属员工',
  `ejcard` varchar(50) default NULL COMMENT '电梯授权所属员工卡号',
  `ejgroup` varchar(500) NOT NULL COMMENT '电梯授权所属电梯组',
  `ejelevator` varchar(50) default NULL COMMENT '电梯授权所属电梯',
  PRIMARY KEY  (`ejid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elevatorj_info
-- ----------------------------
INSERT INTO `elevatorj_info` VALUES ('d72b5a4df28e417cab9c2d053ef1309d', '测试修改', '1', null, '1759a7b3151248e6b1e5bcc792bdd754', null);
