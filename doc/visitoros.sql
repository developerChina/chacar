/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-10-12 19:37:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `recordbevisiteds`
-- ----------------------------
DROP TABLE IF EXISTS `recordbevisiteds`;
CREATE TABLE `recordbevisiteds` (
`recordBVID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录被访人员列表ID' ,
`recordID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
`isAudit`  tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
`bevisitedTel`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人电话' ,
`bevisitedAdd`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人详细地址' ,
`bevisitedUserInfo`  text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '被访人信息' ,
`visitReason`  varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访事由' ,
`auditContent`  varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人审核意见' ,
PRIMARY KEY (`recordBVID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访问记录表——被访人员列表';

-- ----------------------------
-- Records of recordbevisiteds
-- ----------------------------

-- ----------------------------
-- Table structure for `recordvisitors`
-- ----------------------------
DROP TABLE IF EXISTS `recordvisitors`;
CREATE TABLE `recordvisitors` (
  `recordVID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录访客列表ID',
  `recordID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录ID',
  `recordIsOut` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经访问完成(0=申请中，1=正在访问，2=访问结束)',
  `visitorID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访客人员ID',
  `cardNo` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证物理卡号',
  `cardID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `cardName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证姓名',
  `cardSex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证性别',
  `cardNation` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证名族',
  `cardBirthday` date DEFAULT NULL COMMENT '身份证出生日期',
  `cardAddress` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证地址',
  `cardPhoto` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证照片',
  `photo1` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片1',
  `photo2` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片2',
  `photo3` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片3',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '访客电话',
  `company` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访客单位',
  `remarks` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访客备注',
  `reserve1` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段1',
  `reserve2` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段2',
  `reserve3` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段3',
  `reserve4` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段4',
  PRIMARY KEY (`recordVID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访问记录表——来访人员列表';

-- ----------------------------
-- Records of recordvisitors
-- ----------------------------

-- ----------------------------
-- Table structure for `visitorinfo`
-- ----------------------------
DROP TABLE IF EXISTS `visitorinfo`;
CREATE TABLE `visitorinfo` (
  `visitorID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访客人员ID',
  `cardNo` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证物理卡号',
  `cardID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `cardName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证姓名',
  `cardSex` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证性别',
  `cardNation` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证名族',
  `cardBirthday` date DEFAULT NULL COMMENT '身份证出生日期',
  `cardAddress` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证地址',
  `cardPhoto` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证照片',
  `photo1` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片1',
  `photo2` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片2',
  `photo3` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '照片3',
  `telephone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '访客电话',
  `company` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访客单位',
  `remarks` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访客备注',
  `reserve1` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段1',
  `reserve2` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段2',
  `reserve3` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段3',
  `reserve4` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备用字段4',
  PRIMARY KEY (`visitorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访客信息表';

-- ----------------------------
-- Records of visitorinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `visitorrecord`
-- ----------------------------
DROP TABLE IF EXISTS `visitorrecord`;
CREATE TABLE `visitorrecord` (
  `recordID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录ID',
  `recordTitle` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访问记录标题',
  `recordTime` datetime NOT NULL COMMENT '访问记录申请时间',
  `visitorNum` tinyint(4) DEFAULT NULL COMMENT '来访人数',
  `bevisitedNum` tinyint(4) DEFAULT NULL COMMENT '被访人数',
  `isOut` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经访问完成(0=申请中，1=正在访问，2=访问结束)',
  PRIMARY KEY (`recordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访问记录表';

-- ----------------------------
-- Records of visitorrecord
-- ----------------------------
