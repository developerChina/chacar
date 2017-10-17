package org.core.domain.visitor;

import java.io.Serializable;
import java.util.Date;
/**
 * table="visitor_record" <br/>
 * 访问记录表
 */
public class VisitorRecord implements Serializable{
	public static final String tableName = "visitor_record";
	private static final long serialVersionUID = 1L;
	private String recordID;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
	private String recordTitle;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '访问记录标题' ,
	private Date recordTime;   //datetime NOT NULL COMMENT '访问记录申请时间' ,
	private int visitorNum;   //tinyint(4) NULL DEFAULT NULL COMMENT '来访人数' ,
	private int bevisitedNum;   //tinyint(4) NULL DEFAULT NULL COMMENT '被访人数' ,
	private int isOut;   //tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已经访问完成(0=申请中，1=正在访问，2=访问结束)' ,
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getRecordTitle() {
		return recordTitle;
	}
	public void setRecordTitle(String recordTitle) {
		this.recordTitle = recordTitle;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public int getVisitorNum() {
		return visitorNum;
	}
	public void setVisitorNum(int visitorNum) {
		this.visitorNum = visitorNum;
	}
	public int getBevisitedNum() {
		return bevisitedNum;
	}
	public void setBevisitedNum(int bevisitedNum) {
		this.bevisitedNum = bevisitedNum;
	}
	public int getIsOut() {
		return isOut;
	}
	public void setIsOut(int isOut) {
		this.isOut = isOut;
	}

}