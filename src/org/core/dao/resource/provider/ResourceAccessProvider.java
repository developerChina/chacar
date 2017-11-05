package org.core.dao.resource.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.resource.ResourceAccess;
import org.core.util.BeanUtil;

/**
 * @Description: 动态SQL语句提供类
 */
public class ResourceAccessProvider {	

	public String save(ResourceAccess entity) {
		return new SQL() {
			{
				INSERT_INTO(ResourceAccess.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,id,resource");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(ResourceAccess entity) {
		return new SQL() {
			{
				UPDATE(ResourceAccess.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,id,resource");
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
				FROM(ResourceAccess.tableName);
				if(params.get("entity") != null){
//					ResourceAccess entity = (ResourceAccess) params.get("entity");
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
				FROM(ResourceAccess.tableName);
				if(params.get("entity") != null){
//					ResourceAccess entity = (ResourceAccess) params.get("entity");
//					if(dept.getName() != null && !dept.getName().equals("")){
//						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
//					}
				}
			}
		}.toString();
		return sql;
	}

}
