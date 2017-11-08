/*
Navicat MySQL Data Transfer

Source Server         : MyTest
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2017-11-07 15:45:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `elevatorgroup_info`
-- ----------------------------
DROP TABLE IF EXISTS `elevatorgroup_info`;
CREATE TABLE `elevatorgroup_info` (
  `egid` varchar(100) NOT NULL COMMENT '电梯分组的id',
  `egname` varchar(20) NOT NULL COMMENT '电梯分组的名称',
  `egssxj` varchar(50) NOT NULL COMMENT '分组所属的电梯',
  PRIMARY KEY  (`egid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of elevatorgroup_info
-- ----------------------------
INSERT INTO `elevatorgroup_info` VALUES ('1759a7b3151248e6b1e5bcc792bdd754', '用来测试删除的分组', '1,2,3,4,5');
INSERT INTO `elevatorgroup_info` VALUES ('2fc0dec5cd294a278ac8d8eca3c768b8', '技术部的电梯分组', '2,4');
INSERT INTO `elevatorgroup_info` VALUES ('6bbab76878344ab9a625cfbe51ab7311', '财务部的电梯分组', '1,2,3');
