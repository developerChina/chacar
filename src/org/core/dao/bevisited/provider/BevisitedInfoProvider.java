package org.core.dao.bevisited.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.bevisited.BevisitedInfo;
import org.core.util.BeanUtil;
import org.core.util.GenId;;

/**
 * @Description: 动态SQL语句提供类
 */
public class BevisitedInfoProvider {		

	public String save(BevisitedInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(BevisitedInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,bevisitedID");
				entity.setBevisitedID(GenId.UUID());
				VALUES("bevisitedID", "#{bevisitedID}");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}

	public String update(BevisitedInfo entity) {
		return new SQL() {
			{
				UPDATE(BevisitedInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,bevisitedID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" bevisitedID = #{bevisitedID} ");
			}
		}.toString();
	}

	public String selectByPage(BevisitedInfo entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(BevisitedInfo.tableName);
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
