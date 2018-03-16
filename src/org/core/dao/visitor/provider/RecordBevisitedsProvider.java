package org.core.dao.visitor.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.util.BeanUtil;
import org.core.util.StringUtils;;

/**
 * @Description: 动态SQL语句提供类
 */
public class RecordBevisitedsProvider {		

	public String save(RecordBevisiteds entity) {
		return new SQL() {
			{
				INSERT_INTO(RecordBevisiteds.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 	
			}
		}.toString();
	}

	public String update(RecordBevisiteds entity) {
		return new SQL() {
			{
				UPDATE(RecordBevisiteds.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,recordBVID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" recordBVID = #{recordBVID} ");
			}
		}.toString();
	}

	public String selectByPage(RecordBevisiteds entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(RecordBevisiteds.tableName);
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
	
	public String selectByEntity(RecordBevisiteds entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(RecordBevisiteds.tableName);
				if(StringUtils.isNotBlank(entity.getBevisitedName())){
					WHERE(" bevisitedName LIKE CONCAT ('%',#{bevisitedName},'%') ");
				}
				if(StringUtils.isNotBlank(entity.getBevisitedTel())){
					WHERE(" bevisitedTel LIKE CONCAT ('%',#{bevisitedTel},'%') ");
				}
			}
		}.toString();
		return sql;
	}

	public String getbevisitedIDByrecordIDs(String recordIDs) {
		String sql = "SELECT DISTINCT(bevisitedID) from record_bevisiteds  where recordID in ("+recordIDs+") ";
		return sql;
	}
	
}
