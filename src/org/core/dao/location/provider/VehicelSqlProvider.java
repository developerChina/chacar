package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.VEHICELGROUPTABLE;
import static org.core.domain.location.LocationConstants.VEHICELTABLE;
import static org.core.domain.location.LocationConstants.WAIJIETABLE;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationVehicel;
import org.core.util.BeanUtil;

public class VehicelSqlProvider {
	/**
	 * 
	 * 动态查询
	 * */
	public String selectByPagegy(Map<String, Object> gy){
		String sql= new SQL(){
			{
				SELECT("*");
				FROM(VEHICELTABLE);
				if(gy.get("locationVehicel")!=null){
					LocationVehicel locationVehicel=(LocationVehicel) gy.get("locationVehicel");
					if(locationVehicel.getCarName()!=null && !"".equals(locationVehicel.getCarName())){
						WHERE(" carName LIKE CONCAT('%',#{locationVehicel.carName},'%')");
					}
					if(locationVehicel.getClientID()!=null && !"".equals(locationVehicel.getClientID())){
						WHERE(" clientID LIKE CONCAT('%',#{locationVehicel.clientID},'%')");
					}
					if(locationVehicel.getColorId()!=0 && !"".equals(locationVehicel.getColorId())){
						WHERE(" colorId LIKE CONCAT('%',#{locationVehicel.colorId},'%')");
					}
					if(locationVehicel.getGprs()!=null && !"".equals(locationVehicel.getGprs())){
						WHERE(" gprs LIKE CONCAT('%',#{locationVehicel.gprs},'%')");
					}
					if(locationVehicel.getMobileId()!=0 && !"".equals(locationVehicel.getMobileId())){
						WHERE(" mobileId LIKE CONCAT('%',#{locationVehicel.mobileId},'%')");
					}
					if(locationVehicel.getNumber()!=null && !"".equals(locationVehicel.getNumber())){
						WHERE(" number LIKE CONCAT('%',#{locationVehicel.number},'%')");
					}
					if(locationVehicel.getOverduetime()!=null && !"".equals(locationVehicel.getOverduetime())){
						WHERE(" overduetime LIKE CONCAT('%',#{locationVehicel.overduetime},'%')");
					}
					if(locationVehicel.getSim()!=null && !"".equals(locationVehicel.getSim())){
						WHERE(" sim LIKE CONCAT('%',#{locationVehicel.sim},'%')");
					}
					if(locationVehicel.getVedio()!=null && !"".equals(locationVehicel.getVedio())){
						WHERE(" vedio LIKE CONCAT('%',#{locationVehicel.vedio},'%')");
					}
					if(locationVehicel.getVehicleTypeId()!=0 && !"".equals(locationVehicel.getVehicleTypeId())){
						WHERE(" vehicleTypeId LIKE CONCAT('%',#{locationVehicel.vehicleTypeId},'%')");
					}
				}
			}
		}.toString();
		if(gy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	/**
	 * 
	 * 动态查询分页
	 * */
	public String count(Map<String, Object> gy){
		return new SQL(){
			{

				SELECT("count(*)");
				FROM(VEHICELTABLE);
				if(gy.get("locationVehicel")!=null){
					LocationVehicel locationVehicel=(LocationVehicel) gy.get("locationVehicel");
					if(locationVehicel.getCarName()!=null && !"".equals(locationVehicel.getCarName())){
						WHERE(" carName LIKE CONCAT('%',#{locationVehicel.carName},'%')");
					}
					if(locationVehicel.getClientID()!=null && !"".equals(locationVehicel.getClientID())){
						WHERE(" clientID LIKE CONCAT('%',#{locationVehicel.clientID},'%')");
					}
					if(locationVehicel.getColorId()!=0 && !"".equals(locationVehicel.getColorId())){
						WHERE(" colorId LIKE CONCAT('%',#{locationVehicel.colorId},'%')");
					}
					if(locationVehicel.getGprs()!=null && !"".equals(locationVehicel.getGprs())){
						WHERE(" gprs LIKE CONCAT('%',#{locationVehicel.gprs},'%')");
					}
					if(locationVehicel.getMobileId()!=0 && !"".equals(locationVehicel.getMobileId())){
						WHERE(" mobileId LIKE CONCAT('%',#{locationVehicel.mobileId},'%')");
					}
					if(locationVehicel.getNumber()!=null && !"".equals(locationVehicel.getNumber())){
						WHERE(" number LIKE CONCAT('%',#{locationVehicel.number},'%')");
					}
					if(locationVehicel.getOverduetime()!=null && !"".equals(locationVehicel.getOverduetime())){
						WHERE(" overduetime LIKE CONCAT('%',#{locationVehicel.overduetime},'%')");
					}
					if(locationVehicel.getSim()!=null && !"".equals(locationVehicel.getSim())){
						WHERE(" sim LIKE CONCAT('%',#{locationVehicel.sim},'%')");
					}
					if(locationVehicel.getVedio()!=null && !"".equals(locationVehicel.getVedio())){
						WHERE(" vedio LIKE CONCAT('%',#{locationVehicel.vedio},'%')");
					}
					if(locationVehicel.getVehicleTypeId()!=0 && !"".equals(locationVehicel.getVehicleTypeId())){
						WHERE(" vehicleTypeId LIKE CONCAT('%',#{locationVehicel.vehicleTypeId},'%')");
					}
				}
				
			}
		}.toString();
	}
	
	
	
	//保存到中间表   设备分组 
	public String addVehicelGroup(String groupid, String uuid){
		return new SQL(){
			{
				INSERT_INTO(VEHICELGROUPTABLE);
				if(groupid != null && !groupid.equals("")){
					VALUES("groupId", "#{arg0}");
				}
				if(uuid != null && !uuid.equals("")){
					VALUES("vehicelId", "#{arg1}");
				}
				
			}
		}.toString();
	}
	
	//保存到中间表 外接设备表 
	public String addWaiJie(String mywjsb, String uuid){
		return new SQL(){
			{
				INSERT_INTO(WAIJIETABLE);
				if(mywjsb != null && !mywjsb.equals("")){
					VALUES("wjsbid", "#{arg0}");
				}
				if(uuid != null && !uuid.equals("")){
					VALUES("vehicleid", "#{arg1}");
				}
				
			}
		}.toString();
	}
	private String exceptFields="tableName,myVgroups";
	//添加到自己表中
	public String save(LocationVehicel entity) {
		return new SQL() {
			{
				INSERT_INTO(VEHICELTABLE);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}
	
	
	
	//修改
	public String update(LocationVehicel entity) {
		return new SQL() {
			{
				UPDATE(VEHICELTABLE);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
