package org.core.dao.visitor.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.VisitorInfo;
import org.core.util.BeanUtil;
import org.core.util.GenId;;

/**
 * @Description: 动态SQL语句提供类
 */
public class VisitorInfoProvider {		

	public String save(VisitorInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(VisitorInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,visitorID");
				entity.setVisitorID(GenId.UUID());
				VALUES("visitorID", "#{visitorID}");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(VisitorInfo entity) {
		return new SQL() {
			{
				UPDATE(VisitorInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,visitorID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" visitorID = #{visitorID} ");
			}
		}.toString();
	}

	public String selectByPage(VisitorInfo entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(VisitorInfo.tableName);
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
