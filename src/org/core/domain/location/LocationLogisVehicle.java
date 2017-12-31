package org.core.domain.location;

import java.io.Serializable;

public class LocationLogisVehicle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String driverID;		//车主 ID（对应 driver 表）
	private String vehicleTypeID;	//车辆类型 ID（对应 vehicletype 表）
	private String colorID;			//颜色类型 ID（对应 color 表）
	private String vehicleCode;		//车牌号码
	private String sIM;				//SIM 卡号码
	private String deviceId;		//设备 ID
	private String clientId;		//客户 ID（对应 client 表）
	private Integer deviceType;		//设备类型   0：临时  1：固定
	public LocationLogisVehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getVehicleTypeID() {
		return vehicleTypeID;
	}
	public void setVehicleTypeID(String vehicleTypeID) {
		this.vehicleTypeID = vehicleTypeID;
	}
	public String getColorID() {
		return colorID;
	}
	public void setColorID(String colorID) {
		this.colorID = colorID;
	}
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getsIM() {
		return sIM;
	}
	public void setsIM(String sIM) {
		this.sIM = sIM;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Integer getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LocationLogisVehicle [id=" + id + ", driverID=" + driverID + ", vehicleTypeID=" + vehicleTypeID
				+ ", colorID=" + colorID + ", vehicleCode=" + vehicleCode + ", sIM=" + sIM + ", deviceId=" + deviceId
				+ ", clientId=" + clientId + ", deviceType=" + deviceType + "]";
	}
	
	
}
