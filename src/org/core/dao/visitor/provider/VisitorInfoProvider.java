package org.core.dao.visitor.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.VisitorInfo;
import org.core.util.BeanUtil;
import org.core.util.StringUtils;;

/**
 * @Description: 动态SQL语句提供类
 */
public class VisitorInfoProvider {		

	public String save(VisitorInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(VisitorInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
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
	
	public String selectByPage(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(VisitorInfo.tableName);
				if(params.get("entity") != null){
					VisitorInfo entity = (VisitorInfo) params.get("entity");
					if(StringUtils.isNotBlank(entity.getCardName())){
						WHERE("  cardName LIKE CONCAT ('%',#{entity.cardName},'%') ");
					}
					if(StringUtils.isNotBlank(entity.getCardID())){
						WHERE("  cardID LIKE CONCAT ('%',#{entity.cardID},'%') ");
					}
					if(StringUtils.isNotBlank(entity.getTelephone())){
						WHERE(" telephone LIKE CONCAT ('%',#{entity.telephone},'%') ");
					}
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
				FROM(VisitorInfo.tableName);
				if(params.get("entity") != null){
					VisitorInfo entity = (VisitorInfo) params.get("entity");
					if(StringUtils.isNotBlank(entity.getCardName())){
						WHERE("  cardName LIKE CONCAT ('%',#{entity.cardName},'%') ");
					}
					if(StringUtils.isNotBlank(entity.getCardID())){
						WHERE("  cardID LIKE CONCAT ('%',#{entity.cardID},'%') ");
					}
				}
			}
		}.toString();
		return sql;
	}

}
