package org.core.domain.visitor;

import java.io.Serializable;
/**
 * table="record_bevisiteds" <br/>
 * 访问记录表——被访人员列表
 */
public class RecordBevisiteds implements Serializable{
	public static final String tableName = "record_bevisiteds";
	private static final long serialVersionUID = 1L;
	private String recordBVID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录被访人员列表ID' ,
	private String recordID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
	private String bevisitedID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人ID' ,
	private String bevisitedCardNo;    //varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人卡号' ,
	private String bevisitedName;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人姓名' ,
	private String deptID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人部门ID' ,
	private String deptName;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人部门名称' ,
	private String bevisitedPosition;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人职位' ,
	private String bevisitedStatus;   // tinyint(4) NOT NULL DEFAULT 0 COMMENT '被访人状态（0=正常，1=离职.......）' ,
	private String bevisitedTel;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人手机号码' ,
	private String bevisitedDoor;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人门禁' ,
	private String bevisitedChannel;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人通道' ,
	private String bevisitedFloor;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人楼层' ,
	private String bevisitedRoom;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人房间号' ,
	private String bevisitedAddress;   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访问详细地址' ,
	public String getRecordBVID() {
		return recordBVID;
	}
	public void setRecordBVID(String recordBVID) {
		this.recordBVID = recordBVID;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getBevisitedID() {
		return bevisitedID;
	}
	public void setBevisitedID(String bevisitedID) {
		this.bevisitedID = bevisitedID;
	}
	public String getBevisitedCardNo() {
		return bevisitedCardNo;
	}
	public void setBevisitedCardNo(String bevisitedCardNo) {
		this.bevisitedCardNo = bevisitedCardNo;
	}
	public String getBevisitedName() {
		return bevisitedName;
	}
	public void setBevisitedName(String bevisitedName) {
		this.bevisitedName = bevisitedName;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getBevisitedPosition() {
		return bevisitedPosition;
	}
	public void setBevisitedPosition(String bevisitedPosition) {
		this.bevisitedPosition = bevisitedPosition;
	}
	public String getBevisitedStatus() {
		return bevisitedStatus;
	}
	public void setBevisitedStatus(String bevisitedStatus) {
		this.bevisitedStatus = bevisitedStatus;
	}
	public String getBevisitedTel() {
		return bevisitedTel;
	}
	public void setBevisitedTel(String bevisitedTel) {
		this.bevisitedTel = bevisitedTel;
	}
	public String getBevisitedDoor() {
		return bevisitedDoor;
	}
	public void setBevisitedDoor(String bevisitedDoor) {
		this.bevisitedDoor = bevisitedDoor;
	}
	public String getBevisitedChannel() {
		return bevisitedChannel;
	}
	public void setBevisitedChannel(String bevisitedChannel) {
		this.bevisitedChannel = bevisitedChannel;
	}
	public String getBevisitedFloor() {
		return bevisitedFloor;
	}
	public void setBevisitedFloor(String bevisitedFloor) {
		this.bevisitedFloor = bevisitedFloor;
	}
	public String getBevisitedRoom() {
		return bevisitedRoom;
	}
	public void setBevisitedRoom(String bevisitedRoom) {
		this.bevisitedRoom = bevisitedRoom;
	}
	public String getBevisitedAddress() {
		return bevisitedAddress;
	}
	public void setBevisitedAddress(String bevisitedAddress) {
		this.bevisitedAddress = bevisitedAddress;
	}
}