package org.core.dao.visitor.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.VisitorRecord;
import org.core.util.BeanUtil;;

/**
 * @Description: 动态SQL语句提供类
 */
public class VisitorRecordProvider {		

	public String save(VisitorRecord entity) {
		return new SQL() {
			{
				INSERT_INTO(VisitorRecord.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 	
			}
		}.toString();
	}

	public String update(VisitorRecord entity) {
		return new SQL() {
			{
				UPDATE(VisitorRecord.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,recordID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" recordID = #{recordID} ");
			}
		}.toString();
	}

	public String selectByPage(VisitorRecord entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(VisitorRecord.tableName);
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
