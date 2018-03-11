package org.core.dao.car.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.car.CarLogs;
public class carLogsProvider {
	
	public String countCarLogs(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(CarLogs.tableName);
				if(params.get("carLogs") != null){
					CarLogs carLogs = (CarLogs) params.get("carLogs");
					//车牌号
					if(carLogs.getCacrno() != null && !carLogs.getCacrno().equals("")){
						WHERE(" cacrno LIKE CONCAT('%',#{carLogs.cacrno},'%') ");				
					}
					if(carLogs.getCarMaster() != null && !carLogs.getCarMaster().equals("")){
						WHERE(" cacrno in ("+ carLogs.getCarMaster() +" )  ");				
					}
				}
				if(params.get("ips") != null){
					WHERE(" serverIp in ("+ (String) params.get("ips") +")  ");				
				}
				ORDER_BY("shootTime desc");
			}
		}.toString();
		return sql;
	}
	
	public String pageSelectCarLogs(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CarLogs.tableName);
				if(params.get("carLogs") != null){
					CarLogs carLogs = (CarLogs) params.get("carLogs");
					//车牌号
					if(carLogs.getCacrno() != null && !carLogs.getCacrno().equals("")){
						WHERE(" cacrno LIKE CONCAT('%',#{carLogs.cacrno},'%') ");				
					}
					if(carLogs.getCarMaster() != null && !carLogs.getCarMaster().equals("")){
						WHERE(" cacrno in ("+ carLogs.getCarMaster() +" )  ");				
					}
				}
				if(params.get("ips") != null){
					WHERE(" serverIp in ("+ (String) params.get("ips") +")  ");				
				}
				ORDER_BY("shootTime desc");
			}
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	
	
	
	
	
	
	
	
	
	
	
}
