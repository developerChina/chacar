package org.core.domain.temporary;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * table="temporary_info" <br/>
 * 临时定位仪实体
 */
@SuppressWarnings("serial")
public class TemporaryInfo  implements java.io.Serializable{
	public static final String tableName = "temporary_info";
	private int id;  			//int(20)临时定位仪发放表主键id
	private String name;  		//varchar(50) 车主名称
	private String carno; 		//varchar(50) 车牌号
	private String contacts; 	//varchar(50) 联系人
	private String tel;  		//varchar(50) 联系方式
	private String gps;  		//varchar(50) GPS号码
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cominDate;   //datetime 进厂时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date outDate;  	//datetime 出厂时间
	private int type;  			//int(20) 状态（private String 办理业务中2离开）
	private String remarks;		//备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCominDate() {
		return cominDate;
	}
	public void setCominDate(Date cominDate) {
		this.cominDate = cominDate;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	@Override
	public String toString() {
		return "TemporaryInfo [id=" + id + ", name=" + name + ", carno=" + carno + ", contacts=" + contacts + ", tel="
				+ tel + ", gps=" + gps + ", cominDate=" + cominDate + ", outDate=" + outDate + ", type=" + type
				+ ", remarks=" + remarks + "]";
	}
	
	
}
