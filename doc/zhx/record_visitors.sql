/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:59:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `record_visitors`
-- ----------------------------
DROP TABLE IF EXISTS `record_visitors`;
CREATE TABLE `record_visitors` (
  `recordVID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录访客列表ID',
  `recordID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录ID',
  `visitorID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '访客人员ID',
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
  `visitStatus` tinyint(4) NOT NULL DEFAULT '0' COMMENT '访问单状态(0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除)',
  `visitReason` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `isAudit` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态（0=未审核，1=同意，2=拒绝）',
  `auditContent` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`recordVID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='访问记录表——来访人员列表';

-- ----------------------------
-- Records of record_visitors
-- ----------------------------
INSERT INTO `record_visitors` VALUES ('357391919f9341e792b906c15d4b66e2', 'bb6f831eb6254536a1e0f170d19c893d', null, 'hhhhhh', '612726198711080611', '张少云', '男', '汉', null, '陕西省定边县砖井镇徐坑村4组37号', 0x6361726450686F746F2F3631323732363139383731313038303631312E6A7067, '', '13723382349', '北京星海', '3', '', '1', '刚刚到公司，可以访问');
INSERT INTO `record_visitors` VALUES ('7b812dba7af94f77909b19851a0e06f4', '66cdd520c5ab40d3814508675788ea86', null, '顶顶顶顶顶', '612726198711080611', '张少云', '男', '汉', null, '陕西省定边县砖井镇徐坑村4组37号', 0x6361726450686F746F2F3631323732363139383731313038303631312E6A7067, '', '13723382349', '北京星海', '3', '', '2', '呜呜呜哇哇哇');
INSERT INTO `record_visitors` VALUES ('a0e71f9a5b3c400481e6da0c1f394229', 'df8e998bc6414461bf1b5a4161406450', '5348f101e45642d282d34ace3daaceba', null, '15043019920928247X', '崔美生', '男', '汉', null, '内蒙古赤峰市敖汉旗丰收乡贺杖子村烧西村民组', 0x6361726450686F746F2F3135303433303139393230393238323437582E6A7067, 0x70686F746F312F36653430616661613962353634303265393531616231393263643839383630642E6A7067, '13723382346', '北京天外天有限公司', '2', null, '1', '同意');
INSERT INTO `record_visitors` VALUES ('ae07cb14b99a475cbb4f422358290fef', '0643837302d14ea7abe44fd367313cf3', null, '90a29216', '15043019920928247X', '崔美生', '男', '汉', null, '内蒙古赤峰市敖汉旗丰收乡贺杖子村烧西村民组', 0x6361726450686F746F2F3135303433303139393230393238323437582E6A7067, 0x70686F746F312F61333662633736393439633434366630623463343963666131383533633934392E6A7067, '13723382346', '北京天外天有限公司', '3', '送餐', '1', '哇哇哇');
INSERT INTO `record_visitors` VALUES ('b2060eecb66f49189dc13df257e3dc14', '825e95ed48f342938c850a5d1cc05fce', 'dcef20cd349c4332bbc2d60cdd4a1dcd', '22222222', '15272519880716031X', '张海兴', '男', '汉', null, '内蒙古鄂尔多斯市鄂托克旗棋盘井镇第六居委会41栋7号', 0x6361726450686F746F2F3135323732353139383830373136303331582E6A7067, 0x70686F746F312F3135323732353139383830373136303331582E6A7067, '13723382349', '北京天外天', '3', null, '1', '');
INSERT INTO `record_visitors` VALUES ('c2d69d60f6104f869f2e00bcf0c0d81e', 'df8e998bc6414461bf1b5a4161406450', '41169f5f584e4ea1ad051473b063c989', null, '150207199510088612', '魏巍', '男', '汉', null, '内蒙古包头市九原区奶业公司三队27号', 0x6361726450686F746F2F3135303230373139393531303038383631322E6A7067, 0x70686F746F312F34616265656430306631663934653662626235343137393934346263306539642E6A7067, '', '', '2', null, '1', '同意');
INSERT INTO `record_visitors` VALUES ('c3ac633118034c8c88e8308f460dfe1e', '825e95ed48f342938c850a5d1cc05fce', 'aaf5b3c55ea345e8a0aec04d5cfd1ec6', '呜呜呜呜', '612726198711080611', '张少云', '男', '汉', null, '陕西省定边县砖井镇徐坑村4组37号', 0x6361726450686F746F2F3631323732363139383731313038303631312E6A7067, '', '13723382349', '北京星海', '3', null, '1', '');
INSERT INTO `record_visitors` VALUES ('d2bb254fac68417584621fb5590d6be7', 'ca229ff310cb40599f80762f9a9124b9', null, '90a29216', '15043019920928247X', '崔美生', '男', '汉', null, '内蒙古赤峰市敖汉旗丰收乡贺杖子村烧西村民组', 0x6361726450686F746F2F3135303433303139393230393238323437582E6A7067, 0x70686F746F312F61333662633736393439633434366630623463343963666131383533633934392E6A7067, '13723382346', '北京天外天有限公司', '3', '送餐', '2', '哇哇哇哇');
