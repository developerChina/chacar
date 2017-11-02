/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-02 11:29:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blacklist_inf`
-- ----------------------------
DROP TABLE IF EXISTS `blacklist_inf`;
CREATE TABLE `blacklist_inf` (
  `blacklistID` int(10) NOT NULL auto_increment COMMENT '主键',
  `BlacklistName` varchar(50) NOT NULL COMMENT '被拉黑的姓名',
  `Company` varchar(50) NOT NULL COMMENT '单位',
  `idNumber` varchar(50) NOT NULL COMMENT '身份证',
  `Reason` varchar(100) NOT NULL COMMENT '事由',
  PRIMARY KEY  (`blacklistID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blacklist_inf
-- ----------------------------
INSERT INTO `blacklist_inf` VALUES ('5', '2', '2', '2', '2');
INSERT INTO `blacklist_inf` VALUES ('6', '张三丰', '武当', '1234567890', '太强');
INSERT INTO `blacklist_inf` VALUES ('8', '高原', 'HLC', '12345667', '丑拒');
INSERT INTO `blacklist_inf` VALUES ('9', '令狐冲', '华山派大弟子衡山派掌门', '1234567', '武功高怕被打');
INSERT INTO `blacklist_inf` VALUES ('10', '王宝强', '个体', '123456', '太绿了');
INSERT INTO `blacklist_inf` VALUES ('12', '梅超风', '黄药师', '888', '九阴白骨爪太阴险');
INSERT INTO `blacklist_inf` VALUES ('14', '张海兴', '北京天外天', '15272519880716031X', '测试一哈');
