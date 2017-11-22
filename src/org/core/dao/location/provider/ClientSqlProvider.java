package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.CLIENTTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationClient;

public class ClientSqlProvider {
	
	
	//分页动态查询
	public String selectByPagegy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(CLIENTTABLE);
				if(gy.get("locationClient")!=null){
					LocationClient locationClient=(LocationClient) gy.get("locationClient");
					if(locationClient.getBeacon()!=null && !"".equals(locationClient.getBeacon())){
						WHERE(" beacon LIKE CONCAT('%',#{locationClient.beacon},'%')");
					}
					if(locationClient.getCompyname()!=null && !"".equals(locationClient.getCompyname())){
						WHERE(" compyname LIKE CONCAT('%',#{locationClient.compyname},'%')");
					}
					if(locationClient.getOfficeaddrs()!=null && !"".equals(locationClient.getOfficeaddrs())){
						WHERE(" officeaddrs LIKE CONCAT('%',#{locationClient.officeaddrs},'%')");
					}
					if(locationClient.getPhone()!=null && !"".equals(locationClient.getPhone())){
						WHERE(" phone LIKE CONCAT('%',#{locationClient.phone},'%')");
					}
				}
			}
		}.toString();
		if(gy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	//动态查询总数量
	public String count(Map<String, Object> gy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(CLIENTTABLE);
				if(gy.get("locationClient")!=null){
					LocationClient locationClient=(LocationClient) gy.get("locationClient");
					if(locationClient.getBeacon()!=null && !"".equals(locationClient.getBeacon())){
						WHERE(" beacon LIKE CONCAT('%',#{locationClient.beacon},'%')");
					}
					if(locationClient.getCompyname()!=null && !"".equals(locationClient.getCompyname())){
						WHERE(" compyname LIKE CONCAT('%',#{locationClient.compyname},'%')");
					}
					if(locationClient.getOfficeaddrs()!=null && !"".equals(locationClient.getOfficeaddrs())){
						WHERE(" officeaddrs LIKE CONCAT('%',#{locationClient.officeaddrs},'%')");
					}
					if(locationClient.getPhone()!=null && !"".equals(locationClient.getPhone())){
						WHERE(" phone LIKE CONCAT('%',#{locationClient.phone},'%')");
					}
				}
			}
		}.toString();
	}
	//动态更新
		public String modifyLocationClient(LocationClient locationClient){
		return new SQL(){
			{
				UPDATE(CLIENTTABLE);
				if(locationClient.getBeacon() != null){
					SET(" beacon = #{beacon} ");
				}
				if(locationClient.getCompyname() != null){
					SET(" compyname = #{compyname} ");
				}
				if(locationClient.getOfficeaddrs()!= null){
					SET(" officeaddrs = #{officeaddrs} ");
				}
				if(locationClient.getPhone() !=null){
					SET(" phone = #{phone}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
		//插入。。。
		public String addLocationClient(LocationClient locationClient){
			
			return new SQL(){
				{
					INSERT_INTO(CLIENTTABLE);
					if(locationClient.getBeacon() !=null && ! "".equals(locationClient.getBeacon()));{
						VALUES("beacon","#{beacon}");
					}
					if(locationClient.getCompyname() != null && !"".equals(locationClient.getCompyname())){
						VALUES("compyname", "#{compyname}");
					}
					if(locationClient.getOfficeaddrs()!=null && !"".equals(locationClient.getOfficeaddrs())){
						VALUES("officeaddrs","#{officeaddrs}");
					}
					if(locationClient.getPhone() !=null && !"".equals(locationClient.getPhone())){
						VALUES("phone","#{phone}");
					}
					if(locationClient.getId() !=null && !"".equals(locationClient.getId())){
						VALUES("id","#{id}");
					}
					
				}
			}.toString();
		}
}
