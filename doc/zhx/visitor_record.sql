/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 01:00:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `visitor_record`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_record`;
CREATE TABLE `visitor_record` (
  `recordID` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '访问记录ID',
  `recordTitle` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '访问记录标题',
  `recordTime` datetime NOT NULL COMMENT '访问记录申请时间',
  `visitorNum` tinyint(4) DEFAULT NULL COMMENT '来访人数',
  `bevisitedNum` tinyint(4) DEFAULT NULL COMMENT '被访人数',
  PRIMARY KEY (`recordID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='访问记录表';

-- ----------------------------
-- Records of visitor_record
-- ----------------------------
INSERT INTO `visitor_record` VALUES ('0643837302d14ea7abe44fd367313cf3', null, '2017-10-29 13:23:32', '0', '0');
INSERT INTO `visitor_record` VALUES ('66cdd520c5ab40d3814508675788ea86', null, '2017-10-23 00:33:08', '0', '0');
INSERT INTO `visitor_record` VALUES ('825e95ed48f342938c850a5d1cc05fce', null, '2017-10-23 00:38:28', '0', '0');
INSERT INTO `visitor_record` VALUES ('bb6f831eb6254536a1e0f170d19c893d', null, '2017-10-23 00:33:08', '0', '0');
INSERT INTO `visitor_record` VALUES ('ca229ff310cb40599f80762f9a9124b9', null, '2017-10-29 13:23:32', '0', '0');
INSERT INTO `visitor_record` VALUES ('df8e998bc6414461bf1b5a4161406450', null, '2017-10-29 13:36:42', '0', '0');
