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
	private String cardNation;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证民族' ,
	private Date cardBirthday;   //date NULL DEFAULT NULL COMMENT '身份证出生日期' ,
	private String cardAddress;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证地址' ,
	private String cardPhoto;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证照片' ,
	private String photo1;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片1' ,
	private String telephone;   //varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客电话' ,
	private String company;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访客单位' ,
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
}