CREATE TABLE `record_bevisiteds` (
`recordBVID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录被访人列表ID' ,
`recordID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
`bevisitedCardNo`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人卡号' ,
`bevisitedID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人ID' ,
`bevisitedName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人姓名' ,
`deptID`  varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人部门ID' ,
`deptName`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人部门名称' ,
`bevisitedPosition`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人职位' ,
`bevisitedStatus`  tinyint(4) NULL DEFAULT 0 COMMENT '被访人状态（0=正常，1=离职.......）' ,
`bevisitedTel`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人手机号码' ,
`bevisitedDoor`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人门禁' ,
`bevisitedChannel`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人通道' ,
`bevisitedFloor`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人楼层' ,
`bevisitedRoom`  varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人房间号' ,
`bevisitedAddress`  varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访问详细地址' ,
PRIMARY KEY (`recordBVID`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='访问记录表——被访人员列表'
ROW_FORMAT=COMPACT
;

