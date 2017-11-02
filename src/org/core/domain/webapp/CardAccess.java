package org.core.domain.webapp;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * table="card_access" <br/>
 * 卡片权限实体
 */
@SuppressWarnings("serial")
public class CardAccess implements java.io.Serializable{
	public static final String tableName = "card_access";
	private int id;    //int(11) NOT NULL AUTO_INCREMENT COMMENT 'card权限id' ,
	private String cardno;    //varchar(20) CHARACTER COMMENT '用户id' ,
	private String channel;    //varchar(500) CHARACTER COMMENT '通道' ,
	private String floor;    //varchar(500) CHARACTER COMMENT '电梯' ,
	private String room;    //varchar(500) CHARACTER COMMENT '门禁' ,
	private String park;    //varchar(500) CHARACTER COMMENT '停车场' ,
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validate;    //datetime NULL DEFAULT NULL COMMENT '有效期' ,
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public Date getValidate() {
		return validate;
	}
	public void setValidate(Date validate) {
		this.validate = validate;
	}

}