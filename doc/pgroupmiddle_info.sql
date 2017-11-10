/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-10 15:16:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pgroupmiddle_info`
-- ----------------------------
DROP TABLE IF EXISTS `pgroupmiddle_info`;
CREATE TABLE `pgroupmiddle_info` (
  `pmiddleid` int(10) NOT NULL AUTO_INCREMENT COMMENT '通道分组主键',
  `pgroupid` varchar(50) NOT NULL COMMENT '通道分组的主键id',
  `passagewayid` varchar(50) NOT NULL COMMENT '通道主键',
  PRIMARY KEY (`pmiddleid`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pgroupmiddle_info
-- ----------------------------
