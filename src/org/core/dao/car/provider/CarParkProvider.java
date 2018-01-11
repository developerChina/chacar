package org.core.dao.car.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.car.CarPark;
import org.core.util.BeanUtil;
import org.core.util.StringUtils;

/**
 * @Description: 动态SQL语句提供类
 */
public class CarParkProvider {	
	private String exceptFields="tableName,id";
	public String save(CarPark entity) {
		return new SQL() {
			{
				INSERT_INTO(CarPark.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(CarPark entity) {
		return new SQL() {
			{
				UPDATE(CarPark.tableName);
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
				FROM(CarPark.tableName);
				if(params.get("entity") != null){
					CarPark entity = (CarPark) params.get("entity");
					if(StringUtils.isNotBlank(entity.getName())){
						WHERE("  name LIKE CONCAT ('%',#{entity.name},'%') ");
					}
				}
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
				FROM(CarPark.tableName);
				if(params.get("entity") != null){
					CarPark entity = (CarPark) params.get("entity");
					if(StringUtils.isNotBlank(entity.getName())){
						WHERE("  name LIKE CONCAT ('%',#{entity.name},'%') ");
					}
				}
			}
		}.toString();
		return sql;
	}
	
}
