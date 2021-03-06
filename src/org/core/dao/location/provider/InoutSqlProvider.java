package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.INOUT;

import java.util.Date;
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
					/*if(locationInout.getCominDate()!=null && !"".equals(locationInout.getCominDate())){
						WHERE(" DATE_FORMAT(cominDate,'%m-%d-%Y')=DATE_FORMAT(#{locationInout.cominDate},'%m-%d-%Y') ");
					}
					if(locationInout.getOutDate()!=null && !"".equals(locationInout.getOutDate())){
						WHERE("  DATE_FORMAT(outDate,'%m-%d-%Y')=DATE_FORMAT(#{locationInout.outDate},'%m-%d-%Y') ");
					}*/
					if(locationInout.getVehicleCode()!=null && !"".equals(locationInout.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationInout.vehicleCode},'%')");
					}
					if(locationInout.getVehicleType()!=null && locationInout.getVehicleType()!=-1){
						WHERE(" vehicleType LIKE CONCAT('%',#{locationInout.vehicleType},'%')");
					}
					if(locationInout.getSupplier() != null && !locationInout.getSupplier().equals("")){
						WHERE(" vehicleCode in ("+ locationInout.getSupplier() +" ) ");				
					}
					Date startDate = (Date) gy.get("startDate");
					Date endDate = (Date) gy.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate} "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate} "); }
					}
					
				}
				ORDER_BY("cominDate desc");
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
					/*if(locationInout.getCominDate()!=null && !"".equals(locationInout.getCominDate())){
						WHERE(" DATE_FORMAT(cominDate,'%m-%d-%Y')=DATE_FORMAT(#{locationInout.cominDate},'%m-%d-%Y') ");
					}
					if(locationInout.getOutDate()!=null && !"".equals(locationInout.getOutDate())){
						WHERE("  DATE_FORMAT(outDate,'%m-%d-%Y')=DATE_FORMAT(#{locationInout.outDate},'%m-%d-%Y') ");
					}*/
					if(locationInout.getVehicleCode()!=null && !"".equals(locationInout.getVehicleCode())){
						WHERE(" vehicleCode LIKE CONCAT('%',#{locationInout.vehicleCode},'%')");
					}
					if(locationInout.getVehicleType()!=null && locationInout.getVehicleType()!=-1){
						WHERE(" vehicleType LIKE CONCAT('%',#{locationInout.vehicleType},'%')");
					}
					if(locationInout.getSupplier() != null && !locationInout.getSupplier().equals("")){
						WHERE(" vehicleCode in ("+ locationInout.getSupplier() +" ) ");				
					}
					Date startDate = (Date) gy.get("startDate");
					Date endDate = (Date) gy.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate} "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate} "); }
					}
				}
			}
		}.toString();
	}
}
