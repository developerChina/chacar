/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-10 15:16:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `passagewaygroup_info`
-- ----------------------------
DROP TABLE IF EXISTS `passagewaygroup_info`;
CREATE TABLE `passagewaygroup_info` (
  `pgid` varchar(100) NOT NULL COMMENT '通道分组id',
  `pgname` varchar(20) NOT NULL COMMENT '通道分组名称',
  PRIMARY KEY (`pgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passagewaygroup_info
-- ----------------------------
