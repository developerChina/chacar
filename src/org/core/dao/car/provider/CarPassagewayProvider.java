package org.core.dao.car.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.car.CarPassageway;
import org.core.util.BeanUtil;

/**
 * @Description: 动态SQL语句提供类
 */
public class CarPassagewayProvider {	
	private String exceptFields="tableName,id,carPark,carDistinguishs";
	public String save(CarPassageway entity) {
		return new SQL() {
			{
				INSERT_INTO(CarPassageway.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(CarPassageway entity) {
		return new SQL() {
			{
				UPDATE(CarPassageway.tableName);
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
				FROM(CarPassageway.tableName);
				if(params.get("entity") != null){
//					CarPassageway entity = (CarPassageway) params.get("entity");
//					if(dept.getName() != null && !dept.getName().equals("")){
//						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
//					}
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
				FROM(CarPassageway.tableName);
				if(params.get("entity") != null){
//					CarPassageway entity = (CarPassageway) params.get("entity");
//					if(dept.getName() != null && !dept.getName().equals("")){
//						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
//					}
				}
			}
		}.toString();
		return sql;
	}

}
