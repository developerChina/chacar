/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2018-10-19 15:30:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `temporary_info`
-- ----------------------------
DROP TABLE IF EXISTS `temporary_info`;
CREATE TABLE `temporary_info` (
  `id` int(20) NOT NULL auto_increment COMMENT '临时定位仪发放表主键id',
  `name` varchar(50) default NULL COMMENT '车主名称',
  `carno` varchar(50) default NULL COMMENT '车牌号',
  `contacts` varchar(50) default NULL COMMENT '联系人',
  `tel` varchar(50) default NULL COMMENT '联系方式',
  `gps` varchar(50) default NULL COMMENT 'GPS号码',
  `cominDate` datetime default NULL COMMENT '进厂时间',
  `outDate` datetime default NULL COMMENT '出厂时间',
  `remarks` varchar(500) default NULL COMMENT '备注',
  `type` int(20) default NULL COMMENT '状态（1办理业务中2离开）',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temporary_info
-- ----------------------------
