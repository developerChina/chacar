package org.core.dao.car.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.car.CarInfo;
import org.core.util.BeanUtil;

/**
 * @Description: 动态SQL语句提供类
 */
public class CarInfoProvider {	
	private String exceptFields="tableName,id";
	public String save(CarInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(CarInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				}
			}
		}.toString();
	}

	public String update(CarInfo entity) {
		return new SQL() {
			{
				UPDATE(CarInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}

	public String selectByPage(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(CarInfo.tableName);
				if(params.get("entity") != null){
					CarInfo entity = (CarInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getCompany() != null && !entity.getCompany().equals("")){
						WHERE("  company LIKE CONCAT('%',#{entity.company},'%') ");
					}
					if(entity.getWorkNumber() != null && !entity.getWorkNumber().equals("")){
						WHERE("  workNumber LIKE CONCAT('%',#{entity.workNumber},'%') ");
					}
				}
				ORDER_BY("carno desc");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
	public String count(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(CarInfo.tableName);
				if(params.get("entity") != null){
					CarInfo entity = (CarInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getCompany() != null && !entity.getCompany().equals("")){
						WHERE("  company LIKE CONCAT('%',#{entity.company},'%') ");
					}
					if(entity.getWorkNumber() != null && !entity.getWorkNumber().equals("")){
						WHERE("  workNumber LIKE CONCAT('%',#{entity.workNumber},'%') ");
					}
				}
			}
		}.toString();
		return sql;
	}
	
}
