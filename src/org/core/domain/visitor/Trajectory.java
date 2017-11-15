package org.core.domain.visitor;

import java.io.Serializable;
/**
 * table="trajectory" <br/>
 * 访问轨迹记录
 */
@SuppressWarnings("serial")
public class Trajectory implements Serializable{
	public static final String tableName = "trajectory";
	private String id;   //varchar(32)  COMMENT '访问轨迹id' ,
	private String controllerSN;   //varchar(20)  COMMENT '控制器sn' ,
	private String cardNo;   //varchar(10)  COMMENT '访问卡号' ,
	private String doorNo;   //varchar(10)  COMMENT '门编号' ,
	private String ifvalid;   //varchar(16)  COMMENT '进出' ,
	private String optInOut;   //varchar(6)  COMMENT '操作' ,
	private String optDate;   //varchar(20)  COMMENT '操作时间' ,
	private String optDesc;   //varchar(100)  COMMENT '操作描述' ,
	
	//表外字段
	private RecordVisitors recordVisitors; //根据cardno查询出访问人的信息
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getControllerSN() {
		return controllerSN;
	}
	public void setControllerSN(String controllerSN) {
		this.controllerSN = controllerSN;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getIfvalid() {
		return ifvalid;
	}
	public void setIfvalid(String ifvalid) {
		this.ifvalid = ifvalid;
	}
	public String getOptInOut() {
		return optInOut;
	}
	public void setOptInOut(String optInOut) {
		this.optInOut = optInOut;
	}
	public String getOptDate() {
		return optDate;
	}
	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}
	public String getOptDesc() {
		return optDesc;
	}
	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}
	public RecordVisitors getRecordVisitors() {
		return recordVisitors;
	}
	public void setRecordVisitors(RecordVisitors recordVisitors) {
		this.recordVisitors = recordVisitors;
	}
}