package org.core.dao.temporary.provider;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.temporary.TemporaryInfo;
import org.core.util.BeanUtil;

public class TemporaryProvider {
//查询中使用的方法
	public String count(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(TemporaryInfo.tableName);
				if(params.get("entity") != null){
					TemporaryInfo entity = (TemporaryInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getContacts() != null && !entity.getContacts().equals("")){
						WHERE("  contacts LIKE CONCAT('%',#{entity.contacts},'%') ");
					}
					if(entity.getGps() != null && !entity.getGps().equals("")){
						WHERE("  gps LIKE CONCAT('%',#{entity.gps},'%') ");
					}
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate}  "); }
					}
				}
				WHERE(" type = 1  ");
			}
		}.toString();
		return sql;
	}
	
	public String selectByPage(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TemporaryInfo.tableName);
				if(params.get("entity") != null){
					TemporaryInfo entity = (TemporaryInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getContacts() != null && !entity.getContacts().equals("")){
						WHERE("  contacts LIKE CONCAT('%',#{entity.contacts},'%') ");
					}
					if(entity.getGps() != null && !entity.getGps().equals("")){
						WHERE("  gps LIKE CONCAT('%',#{entity.gps},'%') ");
					}
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate}  "); }
					}
				}
				WHERE(" type = 1  ");
				ORDER_BY(" cominDate desc");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
//办理中使用的方法
	public String countHandle(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(TemporaryInfo.tableName);
				if(params.get("entity") != null){
					TemporaryInfo entity = (TemporaryInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getContacts() != null && !entity.getContacts().equals("")){
						WHERE("  contacts LIKE CONCAT('%',#{entity.contacts},'%') ");
					}
					if(entity.getGps() != null && !entity.getGps().equals("")){
						WHERE("  gps LIKE CONCAT('%',#{entity.gps},'%') ");
					}
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate}  "); }
					}
				}
				WHERE(" type = 2  ");
			}
		}.toString();
		return sql;
	}
	
	public String selectByPageHandle(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TemporaryInfo.tableName);
				if(params.get("entity") != null){
					TemporaryInfo entity = (TemporaryInfo) params.get("entity");
					if(entity.getName() != null && !entity.getName().equals("")){
						WHERE("  name LIKE CONCAT('%',#{entity.name},'%') ");
					}
					if(entity.getCarno() != null && !entity.getCarno().equals("")){
						WHERE("  carno LIKE CONCAT('%',#{entity.carno},'%') ");
					}
					if(entity.getContacts() != null && !entity.getContacts().equals("")){
						WHERE("  contacts LIKE CONCAT('%',#{entity.contacts},'%') ");
					}
					if(entity.getGps() != null && !entity.getGps().equals("")){
						WHERE("  gps LIKE CONCAT('%',#{entity.gps},'%') ");
					}
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" cominDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" cominDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" cominDate <= #{endDate}  "); }
					}
				}
				WHERE(" type = 2  ");
				ORDER_BY(" cominDate desc");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
	
	//执行添加和修改
	//去掉的字段  
	private String exceptFields="tableName,id";
	
	//添加
	public String save(TemporaryInfo entity) {
		return new SQL() {
			{
				INSERT_INTO(TemporaryInfo.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				}
			}
		}.toString();
	}
	
	//修改
		public String update(TemporaryInfo entity) {
			return new SQL() {
				{
					UPDATE(TemporaryInfo.tableName);
					Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
					for (Map.Entry<String, Object> entry : map.entrySet()) { 
						SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}

	
}
