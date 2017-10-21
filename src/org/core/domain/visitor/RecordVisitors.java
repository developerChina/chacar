package org.core.domain.visitor;

import java.io.Serializable;
import java.util.Date;
/**
 * table="record_visitors" <br/>
 * 访问记录表——来访人员列表
 */
public class RecordVisitors implements Serializable{
	public static final String tableName = "record_visitors";
	private static final long serialVersionUID = 1L;
	private String recordVID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录访客列表ID' ,
	private String recordID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
	
	private String visitorID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访客人员ID' ,
	private String cardNo;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证物理卡号' ,
	private String cardID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号' ,
	private String cardName;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证姓名' ,
	private String cardSex;   // varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证性别' ,
	private String cardNation;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证名族' ,
	private Date cardBirthday;   // date NULL DEFAULT NULL COMMENT '身份证出生日期' ,
	private String cardAddress;   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证地址' ,
	private String cardPhoto;   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证照片' ,
	private String photo1;   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片1' ,
	private String telephone;   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客电话' ,
	private String company;   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客单位' ,
	
	private int visitStatus;   // tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已经访问完成(0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除)' ,
	private String visitReason;   // varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访问事由' ,
	private int isAudit;   // tinyint(4) NOT NULL COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
	private String auditContent;   // varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人审核意见' ,
	
	public String getRecordVID() {
		return recordVID;
	}
	public void setRecordVID(String recordVID) {
		this.recordVID = recordVID;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getVisitorID() {
		return visitorID;
	}
	public void setVisitorID(String visitorID) {
		this.visitorID = visitorID;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardSex() {
		return cardSex;
	}
	public void setCardSex(String cardSex) {
		this.cardSex = cardSex;
	}
	public String getCardNation() {
		return cardNation;
	}
	public void setCardNation(String cardNation) {
		this.cardNation = cardNation;
	}
	public Date getCardBirthday() {
		return cardBirthday;
	}
	public void setCardBirthday(Date cardBirthday) {
		this.cardBirthday = cardBirthday;
	}
	public String getCardAddress() {
		return cardAddress;
	}
	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}
	public String getCardPhoto() {
		return cardPhoto;
	}
	public void setCardPhoto(String cardPhoto) {
		this.cardPhoto = cardPhoto;
	}
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getVisitStatus() {
		return visitStatus;
	}
	public void setVisitStatus(int visitStatus) {
		this.visitStatus = visitStatus;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public int getIsAudit() {
		return isAudit;
	}
	public void setIsAudit(int isAudit) {
		this.isAudit = isAudit;
	}
	public String getAuditContent() {
		return auditContent;
	}
	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}
}