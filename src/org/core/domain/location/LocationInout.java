package org.core.domain.location;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
@SuppressWarnings("serial")
public class LocationInout implements java.io.Serializable{
	private Integer id;
	private String vehicleCode;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cominDate;	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date outDate;	
	private Integer vehicleType;
	
	private String supplier; 			//供应商
	
	private String serverInIp;  		//相机进ip
	private String serverInName;		//进场名称
	private String serverOutIp;  		//相机出ip
	private String serverOutName;		//出场名称
	
	private String ids;  				//主键集合。
	
	private String plant;				//进出厂的差值
	private String type;				//车辆类型 固定 临时
 	
	public LocationInout() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	
	public String getServerInName() {
		return serverInName;
	}
	public void setServerInName(String serverInName) {
		this.serverInName = serverInName;
	}
	public String getServerOutName() {
		return serverOutName;
	}
	public void setServerOutName(String serverOutName) {
		this.serverOutName = serverOutName;
	}
	public String getServerInIp() {
		return serverInIp;
	}
	public void setServerInIp(String serverInIp) {
		this.serverInIp = serverInIp;
	}
	public String getServerOutIp() {
		return serverOutIp;
	}
	public void setServerOutIp(String serverOutIp) {
		this.serverOutIp = serverOutIp;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
