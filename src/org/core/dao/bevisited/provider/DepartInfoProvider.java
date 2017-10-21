package org.core.dao.bevisited.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.bevisited.DepartInfo;
import org.core.util.BeanUtil;;

/**
 * @Description: 动态SQL语句提供类
 */
public class DepartInfoProvider {		

	public String save(DepartInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(DepartInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 		
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(DepartInfo entity) {
		return new SQL() {
			{
				UPDATE(DepartInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,deptID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" deptID = #{deptID} ");
			}
		}.toString();
	}

	public String selectByPage(DepartInfo entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(DepartInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					WHERE(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
			}
		}.toString();

		/**
		 * 分页后续再补 if(params.get("pageModel") != null){ sql += " limit
		 * #{pageModel.firstLimitParam} , #{pageModel.pageSize} "; }
		 */
		return sql;
	}

}
