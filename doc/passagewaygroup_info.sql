/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-07 15:42:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `passagewaygroup_info`
-- ----------------------------
DROP TABLE IF EXISTS `passagewaygroup_info`;
CREATE TABLE `passagewaygroup_info` (
  `pgid` varchar(100) NOT NULL COMMENT '通道分组id',
  `pgname` varchar(20) NOT NULL COMMENT '通道分组名称',
  `pgssxj` varchar(500) NOT NULL COMMENT '通道所属下级',
  PRIMARY KEY (`pgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of passagewaygroup_info
-- ----------------------------
INSERT INTO `passagewaygroup_info` VALUES ('4686c168565c41d68a8ebc3ba5eb65f9', 'C', '5');
INSERT INTO `passagewaygroup_info` VALUES ('55b4f89733aa4f17889dfa956906a8c3', '444', '5,6,7');
INSERT INTO `passagewaygroup_info` VALUES ('b3f7a0951e9d493cb9b5b916bcf723e8', '333', '6');
