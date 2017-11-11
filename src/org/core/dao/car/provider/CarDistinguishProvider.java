package org.core.dao.car.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.car.CarDistinguish;
import org.core.util.BeanUtil;
import org.core.util.StringUtils;

/**
 * @Description: 动态SQL语句提供类
 */
public class CarDistinguishProvider {	
	private String exceptFields="tableName,id";
	public String save(CarDistinguish entity) {
		return new SQL() {
			{
				INSERT_INTO(CarDistinguish.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(CarDistinguish entity) {
		return new SQL() {
			{
				UPDATE(CarDistinguish.tableName);
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
				FROM(CarDistinguish.tableName);
				if(params.get("entity") != null){
//					CarDistinguish entity = (CarDistinguish) params.get("entity");
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
				FROM(CarDistinguish.tableName);
				if(params.get("entity") != null){
//					CarDistinguish entity = (CarDistinguish) params.get("entity");
//					if(dept.getName() != null && !dept.getName().equals("")){
//						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
//					}
				}
			}
		}.toString();
		return sql;
	}

	public String selectByIds(String ids) {
		String where="";
		if(StringUtils.isNotBlank(ids)){
			where=" id in ( "+ ids +" )";
		}else{
			where=" 1=0 ";
		}
		String sql ="select * from " +CarDistinguish.tableName +" where "+ where;
		return sql;
	}
	
	public String selectFillterIds(String ids) {
		String where="";
		if(StringUtils.isNotBlank(ids)){
			where="1=1";
			for (String id : ids.split(",")) {
				where=where+" and id <> "+id;
			}
		}else{
			where=" 1=1 ";
		}
		String sql ="select * from " +CarDistinguish.tableName +" where "+ where;
		return sql;
	}
}
