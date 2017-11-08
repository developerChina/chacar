/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-07 15:42:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `access_info`
-- ----------------------------
DROP TABLE IF EXISTS `access_info`;
CREATE TABLE `access_info` (
  `accessid` int(20) NOT NULL AUTO_INCREMENT COMMENT '门禁id',
  `accessname` varchar(20) NOT NULL COMMENT '门禁名称',
  `csn` varchar(20) NOT NULL COMMENT '控制器SN',
  `cip` varchar(20) NOT NULL COMMENT '控制器IP',
  `acno` varchar(20) NOT NULL COMMENT '门禁编号',
  PRIMARY KEY (`accessid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of access_info
-- ----------------------------
INSERT INTO `access_info` VALUES ('17', '褚晓东', '1', '1', '编号11');
INSERT INTO `access_info` VALUES ('18', '高源', '1', '1', '编号12');
INSERT INTO `access_info` VALUES ('20', '魏巍', '2', '2', '编号44');
