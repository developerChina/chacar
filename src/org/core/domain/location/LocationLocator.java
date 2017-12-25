package org.core.domain.location;

import java.io.Serializable;
//维护定位仪实体类
public class LocationLocator implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;		//定位仪表主键
	private String name;	//定位仪名称
	private String sim;		//sim卡号
	private String deviceid;//设备id
	private String type;	//定位仪类型
	//toString
	@Override
	public String toString() {
		return "LocationLocator [id=" + id + ", name=" + name + ", sim=" + sim + ", deviceid=" + deviceid + ", type="
				+ type + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public LocationLocator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
