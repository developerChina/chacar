/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-07 15:42:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accessgroup_info`
-- ----------------------------
DROP TABLE IF EXISTS `accessgroup_info`;
CREATE TABLE `accessgroup_info` (
  `agid` varchar(100) NOT NULL COMMENT '门禁分组id',
  `agname` varchar(20) NOT NULL COMMENT '门禁分组名称',
  `agssxj` varchar(500) NOT NULL COMMENT '门禁分组所属下级',
  PRIMARY KEY (`agid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of accessgroup_info
-- ----------------------------
INSERT INTO `accessgroup_info` VALUES ('0be4cd628a96443086cc0761f59a173f', 'A门', '17');
INSERT INTO `accessgroup_info` VALUES ('6674d1f183a5419d94dccd3cca5a4b59', 'B门', '18');
INSERT INTO `accessgroup_info` VALUES ('eb08b875cdb04c41b46c5d68bbae8013', 'D门', '20');
INSERT INTO `accessgroup_info` VALUES ('f2deed1cf98447dd954675dd113b84ef', 'sgfsdg', '17,20');
