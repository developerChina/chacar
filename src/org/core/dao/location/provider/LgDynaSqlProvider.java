package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.GROUPTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationGroup;

public class LgDynaSqlProvider {
	//分页查询 定位系统 分组表
			//条数
	public String count(Map<String, Object> params){
		return new SQL(){
			{	
				SELECT("count(*)");
				FROM(GROUPTABLE);
				if(params.get("locationGroup")!=null){
					LocationGroup locationGroup = (LocationGroup) params.get("locationGroup");
					//分组名称
					if(locationGroup.getGroupName() != null && !locationGroup.getGroupName().equals("")){
						WHERE(" groupName LIKE CONCAT('%',#{locationGroup.groupName},'%') ");
					}
				}
			}
		}.toString();
	}
		//集合
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(GROUPTABLE);
				if(params.get("locationGroup")!=null){
					LocationGroup locationGroup = (LocationGroup) params.get("locationGroup");
					//分组名称
					if(locationGroup.getGroupName() != null && !locationGroup.getGroupName().equals("")){
						WHERE(" groupName LIKE CONCAT('%',#{locationGroup.groupName},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	
	//添加
	public String saveLgroup(LocationGroup locationGroup){
		return new SQL(){
			{
				INSERT_INTO(GROUPTABLE);
				if(locationGroup.getGroupName() != null && !locationGroup.getGroupName().equals("")){
					VALUES("groupName", "#{groupName}");
				}
				if(locationGroup.getCreatetime() != null && !locationGroup.getCreatetime().equals("")){
					VALUES("createtime", "#{createtime}");
				}
				if(locationGroup.getMintime() > 0){
					VALUES("mintime", "#{mintime}");
				}
				if(locationGroup.getMaxtime() >0 ){
					VALUES("maxtime", "#{maxtime}");
				}
				if(locationGroup.getUserCount() >0 ){
					VALUES("userCount", "#{userCount}");
				}
				if(locationGroup.getVhcCount() >0 ){
					VALUES("vhcCount", "#{vhcCount}");
				}
				if(locationGroup.getSeeNext() >=0 ){
					VALUES("seeNext", "#{seeNext}");
				}
			}
		}.toString();
	}
	
	
	public String updateLgroup(LocationGroup locationGroup){
		return new SQL(){
			{
				UPDATE(GROUPTABLE);
				if(locationGroup.getGroupName() != null && !locationGroup.getGroupName().equals("")){
					SET(" groupName = #{groupName} ");
				}
				if(locationGroup.getUserCount() >0 ){
					SET(" userCount = #{userCount} ");
				}
				if(locationGroup.getVhcCount() >0 ){
					SET(" vhcCount = #{vhcCount} ");
				}
				if(locationGroup.getSeeNext() >=0  ){
					SET(" seeNext = #{seeNext} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
	
	
	
	
	
	
	
	
}
