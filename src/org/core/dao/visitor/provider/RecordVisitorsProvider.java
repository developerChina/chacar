package org.core.dao.visitor.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.RecordVisitors;
import org.core.util.BeanUtil;;

/**
 * @Description: 动态SQL语句提供类
 */
public class RecordVisitorsProvider {		

	public String save(RecordVisitors entity) {
		return new SQL() {
			{
				INSERT_INTO(RecordVisitors.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 		
			}
		}.toString();
	}

	public String update(RecordVisitors entity) {
		return new SQL() {
			{
				UPDATE(RecordVisitors.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID,recordVID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE("recordVID = #{recordVID} ");
			}
		}.toString();
	}

	public String selectByEntity(RecordVisitors entity) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(RecordVisitors.tableName);
				if(entity.getCardName() != null && !entity.getCardName().equals("")){
					WHERE(" cardName LIKE CONCAT ('%',#{cardName},'%') ");
				}
				if(entity.getCardID() != null && !entity.getCardID().equals("")){
					WHERE(" cardID LIKE CONCAT ('%',#{cardID},'%') ");
				}
			}
		}.toString();
		return sql;
	}

}
