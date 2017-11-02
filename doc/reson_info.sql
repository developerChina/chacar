/*
Navicat MySQL Data Transfer

Source Server         : gaoyuandd
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-02 11:26:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `reson_info`
-- ----------------------------
DROP TABLE IF EXISTS `reson_info`;
CREATE TABLE `reson_info` (
  `content` text NOT NULL,
  `rid` int(3) NOT NULL AUTO_INCREMENT,
  `rtime` varchar(50) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reson_info
-- ----------------------------
INSERT INTO `reson_info` VALUES ('的说法', '10', '');
INSERT INTO `reson_info` VALUES ('水电费', '11', '');
INSERT INTO `reson_info` VALUES ('第三方', '12', '');
INSERT INTO `reson_info` VALUES ('地方撒', '13', '');
INSERT INTO `reson_info` VALUES ('其他是是是', '14', '');
INSERT INTO `reson_info` VALUES ('jjj', '15', '4');
INSERT INTO `reson_info` VALUES ('找高原', '16', '1');
INSERT INTO `reson_info` VALUES ('找高原', '17', '1');
