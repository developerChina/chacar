package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.INOUT;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationInout;

public class InoutSqlProvider {
	//分页动态查询
	public String selectByPagegy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(INOUT);
				if(gy.get("locationInout")!=null){
					LocationInout locationInout=(LocationInout) gy.get("locationInout");
					if(locationInout.getCominDate()!=null && !"".equals(locationInout.getCominDate())){
						WHERE(" cominDate = CONCAT(#{locationInout.cominDate})");
					}
					if(locationInout.getOutDate()!=null && !"".equals(locationInout.getOutDate())){
						WHERE(" outDate = CONCAT(#{locationInout.outDate})");
					}
					if(locationInout.getVehicleCode()!=null && !"".equals(locationInout.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationInout.vehicleCode},'%')");
					}
					if(locationInout.getVehicleType()!=null && locationInout.getVehicleType()!=-1){
						WHERE(" vehicleType LIKE CONCAT('%',#{locationInout.vehicleType},'%')");
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
				FROM(INOUT);
				if(gy.get("locationInout")!=null){
					LocationInout locationInout=(LocationInout) gy.get("locationInout");
					if(locationInout.getCominDate()!=null && !"".equals(locationInout.getCominDate())){
						WHERE(" cominDate = CONCAT(#{locationInout.cominDate})");
					}
					if(locationInout.getOutDate()!=null && !"".equals(locationInout.getOutDate())){
						WHERE(" outDate = CONCAT(#{locationInout.outDate})");
					}
					if(locationInout.getVehicleCode()!=null && !"".equals(locationInout.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationInout.vehicleCode},'%')");
					}
					if(locationInout.getVehicleType()!=null && locationInout.getVehicleType()!=-1){
						WHERE(" vehicleType LIKE CONCAT('%',#{locationInout.vehicleType},'%')");
					}
				}
			}
		}.toString();
	}
}
