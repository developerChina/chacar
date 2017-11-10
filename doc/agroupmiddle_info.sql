/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-08 17:10:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `agroupmiddle_info`
-- ----------------------------
DROP TABLE IF EXISTS `agroupmiddle_info`;
CREATE TABLE `agroupmiddle_info` (
  `amiddleid` int(10) NOT NULL auto_increment COMMENT '门禁分组主键',
  `agroupid` varchar(50) default NULL COMMENT '门禁分组的主键id',
  `accessid` varchar(50) default NULL COMMENT '门禁主键',
  PRIMARY KEY  (`amiddleid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of agroupmiddle_info
-- ----------------------------
INSERT INTO `agroupmiddle_info` VALUES ('5', 'f9090652eaf7445280ece18503ef7880', '1');
INSERT INTO `agroupmiddle_info` VALUES ('6', 'f9090652eaf7445280ece18503ef7880', '2');
INSERT INTO `agroupmiddle_info` VALUES ('7', 'f9090652eaf7445280ece18503ef7880', '3');
INSERT INTO `agroupmiddle_info` VALUES ('8', 'f9090652eaf7445280ece18503ef7880', '4');
INSERT INTO `agroupmiddle_info` VALUES ('9', 'b9ed17a781114351a506f20bb6bf5b67', '1');
INSERT INTO `agroupmiddle_info` VALUES ('10', 'b9ed17a781114351a506f20bb6bf5b67', '2');
INSERT INTO `agroupmiddle_info` VALUES ('11', 'b9ed17a781114351a506f20bb6bf5b67', '3');
INSERT INTO `agroupmiddle_info` VALUES ('12', 'b9ed17a781114351a506f20bb6bf5b67', '4');
