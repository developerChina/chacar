package org.core.dao.queuing.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.util.BeanUtil;

public class QueuingAuthorityProvider {

	private String exceptFields="tableName,id,vpartsI,hpartsI,vagueiname";
	
/*
	卸货岛
*/	
	public String countI(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(Island.tableName);
				if(params.get("island") != null){
					Island island = (Island) params.get("island");
					if(island.getIname() != null && !island.getIname().equals("")){
						WHERE(" iname LIKE CONCAT('%',#{island.iname},'%') ");				
					}
				}
			}
		}.toString();
		return sql;
	}
	public String pageSelectI(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(Island.tableName);
				if(params.get("island") != null){
					Island island = (Island) params.get("island");
					if(island.getIname() != null && !island.getIname().equals("")){
						WHERE(" iname LIKE CONCAT('%',#{island.iname},'%') ");				
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	
	public String addI(Island entity) {
		return new SQL() {
			{
				INSERT_INTO(Island.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}
	
	public String UpdI(Island entity) {
		return new SQL() {
			{
				UPDATE(Island.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
	
/*
	VIP 队列
*/	
	public String countV(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(QueuingVip.tableName);
				if(params.get("queuingVip") != null){
					QueuingVip queuingVip = (QueuingVip) params.get("queuingVip");
					if(queuingVip.getCar_code() != null && !queuingVip.getCar_code().equals("")){
						WHERE(" car_code LIKE CONCAT('%',#{queuingVip.car_code},'%') ");				
					}
					if(queuingVip.getVagueiname() != null && !queuingVip.getVagueiname().equals("")){
						WHERE(" island_no in ("+ queuingVip.getVagueiname() +" ) ");				
					}
					
				}
			}
		}.toString();
		return sql;
	}
	public String pageSelectV(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(QueuingVip.tableName);
				if(params.get("queuingVip") != null){
					QueuingVip queuingVip = (QueuingVip) params.get("queuingVip");
					if(queuingVip.getCar_code() != null && !queuingVip.getCar_code().equals("")){
						WHERE(" car_code LIKE CONCAT('%',#{queuingVip.car_code},'%') ");				
					}
					if(queuingVip.getVagueiname() != null && !queuingVip.getVagueiname().equals("")){
						WHERE(" island_no in ("+ queuingVip.getVagueiname() +" ) ");				
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	
	public String addV(QueuingVip entity) {
		return new SQL() {
			{
				INSERT_INTO(QueuingVip.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
				} 
			}
		}.toString();
	}
	
	
	
	public String UpdV(QueuingVip entity) {
		return new SQL() {
			{
				UPDATE(QueuingVip.tableName);
				Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					SET(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
	
	
	
	
	
/*
	历史记录
*/	
		public String countH(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(History.tableName);
				if(params.get("history") != null){
					History history = (History) params.get("history");
					if(history.getCar_code() != null && !history.getCar_code().equals("")){
						WHERE(" car_code LIKE CONCAT('%',#{history.car_code},'%') ");				
					}
					if(history.getVagueiname() != null && !history.getVagueiname().equals("")){
						WHERE(" island_no in ("+ history.getVagueiname() +" ) ");				
					}
				}
			}
		}.toString();
		return sql;
		}
		public String pageSelectH(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(History.tableName);
				if(params.get("history") != null){
					History history = (History) params.get("history");
					if(history.getCar_code() != null && !history.getCar_code().equals("")){
						WHERE(" car_code LIKE CONCAT('%',#{history.car_code},'%') ");				
					}
					if(history.getVagueiname() != null && !history.getVagueiname().equals("")){
						WHERE(" island_no in ("+ history.getVagueiname() +" ) ");				
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
		}
	
/*
	其他业务支持
*/	
		public String addH(History entity) {
			return new SQL() {
				{
					INSERT_INTO(History.tableName);
					Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
					for (Map.Entry<String, Object> entry : map.entrySet()) { 
						VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
					} 
				}
			}.toString();
		}
		
		
		
		
		public String addO(Ordinary entity) {
			return new SQL() {
				{
					INSERT_INTO(History.tableName);
					Map<String, Object> map=BeanUtil.getFiledsInfo(entity,exceptFields);
					for (Map.Entry<String, Object> entry : map.entrySet()) { 
						VALUES(entry.getKey(), "#{"+entry.getKey()+"}");
					} 
				}
			}.toString();
		}
		
		
		
		
		
		
		
		
		
		
	
}