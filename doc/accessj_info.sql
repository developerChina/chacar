/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-07 15:42:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accessj_info`
-- ----------------------------
DROP TABLE IF EXISTS `accessj_info`;
CREATE TABLE `accessj_info` (
  `ajid` varchar(50) NOT NULL COMMENT '门禁授权id',
  `ajname` varchar(50) NOT NULL COMMENT '授权表名称',
  `ajemp` varchar(50) NOT NULL COMMENT '门禁授权所属员工',
  `ajcard` varchar(50) DEFAULT NULL COMMENT '门禁授权所属员工卡号',
  `ajgroup` varchar(500) NOT NULL COMMENT '门禁授权所属门禁组',
  `ajaccess` varchar(50) DEFAULT NULL COMMENT '门禁授权所属门禁',
  PRIMARY KEY (`ajid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessj_info
-- ----------------------------
