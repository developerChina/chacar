/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-10 15:16:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `passagewayj_info`
-- ----------------------------
DROP TABLE IF EXISTS `passagewayj_info`;
CREATE TABLE `passagewayj_info` (
  `pjid` varchar(50) NOT NULL COMMENT '通道授权id',
  `pjname` varchar(50) NOT NULL COMMENT '通道授权表名称',
  `pjempid` varchar(50) NOT NULL COMMENT '通道授权所属员工',
  `pjgroupid` varchar(505) NOT NULL COMMENT '通道授权所属通道组',
  `passagewayjid` varchar(50) NOT NULL COMMENT '通道id',
  `pjempno` varchar(50) DEFAULT NULL COMMENT '员工卡号',
  PRIMARY KEY (`pjid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passagewayj_info
-- ----------------------------
