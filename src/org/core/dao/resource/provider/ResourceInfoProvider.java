package org.core.dao.resource.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.resource.ResourceInfo;
import org.core.util.BeanUtil;

/**
 * @Description: 动态SQL语句提供类
 */
public class ResourceInfoProvider {	

	public String save(ResourceInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(ResourceInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,id,resource");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
					System.out.println(entry.getKey()+"==="+entry.getValue());
				} 
			}
		}.toString();
	}

	public String update(ResourceInfo entity) {
		return new SQL() {
			{
				UPDATE(ResourceInfo.tableName);
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
				FROM(ResourceInfo.tableName);
				if(params.get("entity") != null){
//					ResourceInfo entity = (ResourceInfo) params.get("entity");
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
				FROM(ResourceInfo.tableName);
				if(params.get("entity") != null){
//					ResourceInfo entity = (ResourceInfo) params.get("entity");
//					if(dept.getName() != null && !dept.getName().equals("")){
//						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
//					}
				}
			}
		}.toString();
		return sql;
	}

}
