/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : visitoros

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-06 00:59:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `CARD_ID` varchar(18) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `POST_CODE` varchar(50) DEFAULT NULL,
  `TEL` varchar(16) DEFAULT NULL,
  `PHONE` varchar(11) NOT NULL,
  `QQ_NUM` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT '1',
  `PARTY` varchar(10) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `RACE` varchar(100) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `SPECIALITY` varchar(20) DEFAULT NULL,
  `HOBBY` varchar(100) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CARDNO` varchar(50) DEFAULT NULL,
  `CARNO` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '1', '8', '爱丽丝', '152624198605126548', '广州天河', '510000', '020-77777777', '13902001111', '36750066', '251425887@qq.com', '1', '党员', '2017-11-01 00:00:00', '满', '本科', '美声', '唱歌', '四大天王', '2016-03-14 11:35:18', '44444', '222');
INSERT INTO `employee_inf` VALUES ('2', '2', '1', '杰克', '22623', '43234', '42427424', '42242', '4247242', '42424', '251425887@qq.com', '2', null, null, null, null, null, null, null, '2016-03-14 11:35:18', '345', null);
INSERT INTO `employee_inf` VALUES ('3', '1', '2', 'bb', '432801197711251038', '广州', '510000', '020-99999999', '13907351532', '36750064', '36750064@qq.com', '1', '党员', '1977-11-25 00:00:00', '汉', '本科', '计算机', '爬山', '无', '2016-07-14 09:54:52', '456', null);
INSERT INTO `employee_inf` VALUES ('4', '5', '5', '333', '152725198807160351', '3', '100000', '01065478954', '13566665555', '364990240', '333@123.com', '1', '33', '2017-11-01 00:00:00', '33', '333', '33', '33', '333', '2017-11-01 20:20:22', '33333', null);
