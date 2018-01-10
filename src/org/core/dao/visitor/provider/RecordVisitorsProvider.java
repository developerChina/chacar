package org.core.dao.visitor.provider;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.RecordVisitors;
import org.core.util.BeanUtil;
import org.core.util.StringUtils;;

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
				if(StringUtils.isNotBlank(entity.getRecordID())){
					WHERE(" recordID in ("+entity.getRecordID()+") ");
				}
				if(StringUtils.isNotBlank(entity.getCardName())){
					WHERE(" cardName LIKE CONCAT ('%',#{cardName},'%') ");
				}
				if(StringUtils.isNotBlank(entity.getCardID())){
					WHERE(" cardID LIKE CONCAT ('%',#{cardID},'%') ");
				}
				if(StringUtils.isNotBlank(entity.getTelephone())){
					WHERE(" telephone LIKE CONCAT ('%',#{telephone},'%') ");
				}
			}
		}.toString();
		return sql;
	}
	
	public String selectCountByStatus(@Param("whereStatus")String whereStatus,@Param("startDate")Date startDate,@Param("endDate")Date endDate) {
		return "SELECT count(DISTINCT(cardID)) from record_visitors where visitStatus "+whereStatus+" and inDate BETWEEN #{startDate} AND #{endDate}";
	}

	public String selectCountByStatusGtDate(@Param("whereStatus")String whereStatus,@Param("startDate")Date startDate) {
		return "SELECT count(DISTINCT(cardID)) from record_visitors where visitStatus "+whereStatus+" and inDate >= #{startDate} ";
	}
	
	public String selectCountByStatusLtDate(@Param("whereStatus")String whereStatus,@Param("endDate")Date endDate) {
		return "SELECT count(DISTINCT(cardID)) from record_visitors where visitStatus "+whereStatus+" and inDate <= #{endDate} ";
	}
	
	public String selectCountByStatusEqDate(@Param("whereStatus")String whereStatus,@Param("date")Date date) {
		return "SELECT count(DISTINCT(cardID)) from record_visitors where visitStatus "+whereStatus+" and DATE_FORMAT(inDate,'%Y-%m-%d') = DATE_FORMAT(#{date},'%Y-%m-%d') ";
	}
}
