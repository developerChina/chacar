/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 01:00:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `visitor_info`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_info`;
CREATE TABLE `visitor_info` (
  `visitorID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访客人员ID',
  `cardNo` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证物理卡号',
  `cardID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `cardName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证姓名',
  `cardSex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证性别',
  `cardNation` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证名族',
  `cardBirthday` date DEFAULT NULL COMMENT '身份证出生日期',
  `cardAddress` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证地址',
  `cardPhoto` text COLLATE utf8_bin COMMENT '身份证照片',
  `photo1` text COLLATE utf8_bin COMMENT '照片1',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '访客电话',
  `company` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访客单位',
  PRIMARY KEY (`visitorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访客信息表';

-- ----------------------------
-- Records of visitor_info
-- ----------------------------
INSERT INTO `visitor_info` VALUES ('41169f5f584e4ea1ad051473b063c989', null, '150207199510088612', '魏巍', '男', '汉', null, '内蒙古包头市九原区奶业公司三队27号', 0x6361726450686F746F2F3135303230373139393531303038383631322E6A7067, 0x70686F746F312F34616265656430306631663934653662626235343137393934346263306539642E6A7067, '', '');
INSERT INTO `visitor_info` VALUES ('5348f101e45642d282d34ace3daaceba', null, '15043019920928247X', '崔美生', '男', '汉', null, '内蒙古赤峰市敖汉旗丰收乡贺杖子村烧西村民组', 0x6361726450686F746F2F3135303433303139393230393238323437582E6A7067, '', '13723382346', '北京天外天有限公司');
INSERT INTO `visitor_info` VALUES ('aaf5b3c55ea345e8a0aec04d5cfd1ec6', null, '612726198711080611', '张少云', '男', '汉', null, '陕西省定边县砖井镇徐坑村4组37号', 0x6361726450686F746F2F3631323732363139383731313038303631312E6A7067, '', '13723382349', '北京星海');
INSERT INTO `visitor_info` VALUES ('dcef20cd349c4332bbc2d60cdd4a1dcd', null, '15272519880716031X', '张海兴', '男', '汉', null, '内蒙古鄂尔多斯市鄂托克旗棋盘井镇第六居委会41栋7号', 0x6361726450686F746F2F3135323732353139383830373136303331582E6A7067, 0x70686F746F312F3135323732353139383830373136303331582E6A7067, '13723382349', '北京天外天');
