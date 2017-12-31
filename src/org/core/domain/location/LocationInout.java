package org.core.domain.location;

import org.springframework.format.annotation.DateTimeFormat;

public class LocationInout {
	private Integer id;
	private String vehicleCode;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date cominDate;	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date outDate;	
	private Integer vehicleType;
	public LocationInout() {
		super();
		// TODO Auto-generated constructor stub
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
	public java.util.Date getCominDate() {
		return cominDate;
	}
	public void setCominDate(java.util.Date cominDate) {
		this.cominDate = cominDate;
	}
	public java.util.Date getOutDate() {
		return outDate;
	}
	public void setOutDate(java.util.Date outDate) {
		this.outDate = outDate;
	}
	public Integer getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}
	@Override
	public String toString() {
		return "LocationInout [id=" + id + ", vehicleCode=" + vehicleCode + ", cominDate=" + cominDate + ", outDate="
				+ outDate + ", vehicleType=" + vehicleType + "]";
	}
	
	
	
}
