package org.core.dao.location.provider;


import static org.core.domain.location.LocationConstants.VEHICLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationLogisVehicle;

public class LogisVehicleSqlProvider {
	//分页动态查询
	public String selectByPagegy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(VEHICLE);
				if(gy.get("locationLogisVehicle")!=null){
					LocationLogisVehicle locationLogisVehicle=(LocationLogisVehicle) gy.get("locationLogisVehicle");
					if(locationLogisVehicle.getClientId()!=null && !"".equals(locationLogisVehicle.getClientId())){
						WHERE(" clientId LIKE CONCAT('%',#{locationLogisVehicle.clientId},'%')");
					}
					if(locationLogisVehicle.getColorID()!=null && !"".equals(locationLogisVehicle.getColorID())){
						WHERE(" colorID LIKE CONCAT('%',#{locationLogisVehicle.colorID},'%')");
					}
					if(locationLogisVehicle.getDeviceId()!=null && !"".equals(locationLogisVehicle.getDeviceId())){
						WHERE(" deviceId LIKE CONCAT('%',#{locationLogisVehicle.deviceId},'%')");
					}
					if(locationLogisVehicle.getDeviceType()!=null && locationLogisVehicle.getDeviceType() !=-1){
						WHERE(" deviceType LIKE CONCAT('%',#{locationLogisVehicle.deviceType},'%')");
					}
					if(locationLogisVehicle.getDriverID()!=null && !locationLogisVehicle.getDriverID().equals("")){
						WHERE(" driverID LIKE CONCAT('%',#{locationLogisVehicle.driverID},'%')");
					}
					if(locationLogisVehicle.getsIM()!=null && ! locationLogisVehicle.getsIM().equals("")){
						WHERE(" sIM LIKE CONCAT('%',#{locationLogisVehicle.sIM},'%')");
					}
					if(locationLogisVehicle.getVehicleCode()!=null && !"".equals(locationLogisVehicle.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationLogisVehicle.vehicleCode},'%')");
					}
					if(locationLogisVehicle.getVehicleTypeID()!=null && !"".equals(locationLogisVehicle.getVehicleTypeID())){
						WHERE(" vehicleTypeID LIKE CONCAT('%',#{locationLogisVehicle.vehicleTypeID},'%')");
					}
				}
			}
		}.toString();
		if(gy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	//动态查询总数量
	public String count(Map<String, Object> gy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(VEHICLE);
				if(gy.get("locationLogisVehicle")!=null){
					LocationLogisVehicle locationLogisVehicle=(LocationLogisVehicle) gy.get("locationLogisVehicle");
					if(locationLogisVehicle.getClientId()!=null && !"".equals(locationLogisVehicle.getClientId())){
						WHERE(" clientId LIKE CONCAT('%',#{locationLogisVehicle.clientId},'%')");
					}
					if(locationLogisVehicle.getColorID()!=null && !"".equals(locationLogisVehicle.getColorID())){
						WHERE(" colorID LIKE CONCAT('%',#{locationLogisVehicle.colorID},'%')");
					}
					if(locationLogisVehicle.getDeviceId()!=null && !"".equals(locationLogisVehicle.getDeviceId())){
						WHERE(" deviceId LIKE CONCAT('%',#{locationLogisVehicle.deviceId},'%')");
					}
					if(locationLogisVehicle.getDeviceType()!=null && locationLogisVehicle.getDeviceType() !=-1){
						WHERE(" deviceType LIKE CONCAT('%',#{locationLogisVehicle.deviceType},'%')");
					}
					if(locationLogisVehicle.getDriverID()!=null && !locationLogisVehicle.getDriverID().equals("")){
						WHERE(" driverID LIKE CONCAT('%',#{locationLogisVehicle.driverID},'%')");
					}
					if(locationLogisVehicle.getsIM()!=null && ! locationLogisVehicle.getsIM().equals("")){
						WHERE(" sIM LIKE CONCAT('%',#{locationLogisVehicle.sIM},'%')");
					}
					if(locationLogisVehicle.getVehicleCode()!=null && !"".equals(locationLogisVehicle.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationLogisVehicle.vehicleCode},'%')");
					}
					if(locationLogisVehicle.getVehicleTypeID()!=null && !"".equals(locationLogisVehicle.getVehicleTypeID())){
						WHERE(" vehicleTypeID LIKE CONCAT('%',#{locationLogisVehicle.vehicleTypeID},'%')");
					}
				}
			}
		}.toString();
	}
}	
