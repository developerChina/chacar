package org.core.domain.visitor;

import java.io.Serializable;
import java.util.Date;
/**
 * table="visitor_info" <br/>
 * 访客信息表
 */
public class VisitorInfo implements Serializable{
	public static final String tableName = "visitor_info";
	private static final long serialVersionUID = 1L;
	private String visitorID;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访客人员ID' ,
	private String cardNo;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证物理卡号' ,
	private String cardID;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号' ,
	private String cardName;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证姓名' ,
	private String cardSex;   //varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证性别' ,
	private String cardNation;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证名族' ,
	private Date cardBirthday;   //date NULL DEFAULT NULL COMMENT '身份证出生日期' ,
	private String cardAddress;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证地址' ,
	private String cardPhoto;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证照片' ,
	private String photo1;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片1' ,
	private String photo2;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片2' ,
	private String photo3;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片3' ,
	private String telephone;   //varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客电话' ,
	private String company;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客单位' ,
	private String remarks;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客备注' ,
	private String reserve1;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段1' ,
	private String reserve2;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段2' ,
	private String reserve3;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段3' ,
	private String reserve4;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备用字段4' ,
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
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getReserve4() {
		return reserve4;
	}
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
}