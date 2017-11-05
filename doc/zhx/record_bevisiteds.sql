/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:59:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `record_bevisiteds`
-- ----------------------------
DROP TABLE IF EXISTS `record_bevisiteds`;
CREATE TABLE `record_bevisiteds` (
  `recordBVID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录被访人列表ID',
  `recordID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录ID',
  `bevisitedID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人ID',
  `bevisitedName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人姓名',
  `deptID` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人部门ID',
  `deptName` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人部门名称',
  `bevisitedPosition` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人职位',
  `bevisitedStatus` tinyint(4) DEFAULT '0' COMMENT '被访人状态（0=正常，1=离职.......）',
  `bevisitedTel` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人手机号码',
  `bevisitedDoor` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人门禁',
  `bevisitedChannel` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人通道',
  `bevisitedFloor` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人楼层',
  `bevisitedRoom` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '被访人房间号',
  `bevisitedAddress` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '被访问详细地址',
  PRIMARY KEY (`recordBVID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='访问记录表——被访人员列表';

-- ----------------------------
-- Records of record_bevisiteds
-- ----------------------------
INSERT INTO `record_bevisiteds` VALUES ('4b48b143121846c59530cb23c9be27eb', 'bb6f831eb6254536a1e0f170d19c893d', '5', '张三丰', '1', null, '1', '0', '13723382349', '1', '1', '1', '1', '5A-603');
INSERT INTO `record_bevisiteds` VALUES ('52cc7b9996114381b951b057bc3923c8', 'df8e998bc6414461bf1b5a4161406450', '6', '曹旭', '2', null, '2', '0', '13723382348', '2', '2', '2', '2', '4A-102');
INSERT INTO `record_bevisiteds` VALUES ('aef4e5e306f549ceb0026b994db8465f', 'ca229ff310cb40599f80762f9a9124b9', '6', '曹旭', '2', null, '2', '0', '13723382348', '2', '2', '2', '2', '4A-102');
INSERT INTO `record_bevisiteds` VALUES ('dfca125c5d4044eb850af25fff028bdf', '825e95ed48f342938c850a5d1cc05fce', '8', '马化腾', '4', null, '4', '0', '13723382346', '4', '4', '4', '4', '5A-2301');
INSERT INTO `record_bevisiteds` VALUES ('e321c34c17f846b1aeb344272a5c8115', '66cdd520c5ab40d3814508675788ea86', '6', '曹旭', '2', null, '2', '0', '13723382348', '2', '2', '2', '2', '4A-102');
INSERT INTO `record_bevisiteds` VALUES ('f821f6b01fff4d029b4133dcf3f5afa4', '0643837302d14ea7abe44fd367313cf3', '5', '张三丰', '1', null, '1', '0', '13723382349', '1', '1', '1', '1', '5A-603');
