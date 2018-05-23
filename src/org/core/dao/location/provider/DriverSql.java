package org.core.dao.location.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationDriver;
import org.core.util.BeanUtil;

public class DriverSql {
	
	private String exceptFields="tableName";
	
	
	public String countD(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(LocationDriver.tableName);
				if(params.get("locationDriver") != null){
					LocationDriver locationDriver = (LocationDriver) params.get("locationDriver");
					if(locationDriver.getName() != null && !locationDriver.getName().equals("")){
						WHERE(" name LIKE CONCAT('%',#{locationDriver.name},'%') ");				
					}
					if(locationDriver.getVehicleCode() != null && !locationDriver.getVehicleCode().equals("")){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationDriver.vehicleCode},'%') ");				
					}
				}
			}
		}.toString();
		return sql;
	}
	public String pageSelectD(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(LocationDriver.tableName);
				if(params.get("locationDriver") != null){
					LocationDriver locationDriver = (LocationDriver) params.get("locationDriver");
					if(locationDriver.getName() != null && !locationDriver.getName().equals("")){
						WHERE(" name LIKE CONCAT('%',#{locationDriver.name},'%') ");				
					}
					if(locationDriver.getVehicleCode() != null && !locationDriver.getVehicleCode().equals("")){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationDriver.vehicleCode},'%') ");				
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	
	
	//添加
	public String addD(LocationDriver entity) {
		return new SQL() {
			{
				INSERT_INTO(LocationDriver.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}
	
	
	
	public String upDriver(LocationDriver entity) {
		return new SQL() {
			{
				UPDATE(LocationDriver.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
	
	
	
	
}
