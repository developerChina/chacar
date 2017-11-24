package org.core.domain.location;

import java.io.Serializable;
/**   
 * @Description: 定位系统客户表 table=client
 * @author WW GY	   
 * @version V1.0   
 */
public class LocationClient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String compyname;    	//varchar(50) 企业名称
	private String phone;		 	//varchar(50) 企业联系电话
	private String officeaddrs; 	//varchar(50) 办公地址
	private String beacon;			//varchar(50) 法人代表  
	private String id;				//varchar(50) 客户管理表主键
	
	public LocationClient() {
		super();
	}
	public String getCompyname() {
		return compyname;
	}
	public void setCompyname(String compyname) {
		this.compyname = compyname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOfficeaddrs() {
		return officeaddrs;
	}
	public void setOfficeaddrs(String officeaddrs) {
		this.officeaddrs = officeaddrs;
	}
	public String getBeacon() {
		return beacon;
	}
	public void setBeacon(String beacon) {
		this.beacon = beacon;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	
}
