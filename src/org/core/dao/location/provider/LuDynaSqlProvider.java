package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.USERTABLE;
import static org.core.domain.location.LocationConstants.USERGOUPTABLE;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationUser;

public class LuDynaSqlProvider {
			//分页查询 定位系统 分组表
			//条数
		public String count(Map<String, Object> params){
		return new SQL(){
			{	
				SELECT("count(*)");
				FROM(USERTABLE);
				if(params.get("locationUser")!=null){
					LocationUser locationUser = (LocationUser) params.get("locationUser");
					//用户名称
					if(locationUser.getUserName() != null && !locationUser.getUserName().equals("")){
						WHERE(" userName LIKE CONCAT('%',#{locationUser.userName},'%') ");
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
				FROM(USERTABLE);
				if(params.get("locationUser")!=null){
					LocationUser locationUser = (LocationUser) params.get("locationUser");
					//用户名称
					if(locationUser.getUserName() != null && !locationUser.getUserName().equals("")){
						WHERE(" userName LIKE CONCAT('%',#{locationUser.userName},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
			return sql;
		}
		
		
		//添加到用户和分组的 中间 表 
		public String addUserAndGroup(String myGroupid, String uuid){
			return new SQL(){
				{
					INSERT_INTO(USERGOUPTABLE);
					if(myGroupid != null && !myGroupid.equals("")){
						VALUES("groupid", "#{arg0}");
					}
					if(uuid != null && !uuid.equals("")){
						VALUES("userid", "#{arg1}");
					}
					
				}
			}.toString();
		}
		
		//添加 用户表
		public String addLuser(LocationUser locationUser){
			return new SQL(){
				{
					INSERT_INTO(USERTABLE);
					if(locationUser.getId() !=null && !locationUser.getId().equals("")){
						VALUES("id", "#{id}");
					}
					if(locationUser.getClientID() != null && !locationUser.getClientID().equals("")){
						VALUES("clientID", "#{clientID}");
					}
					if(locationUser.getUserName() != null && !locationUser.getUserName().equals("")){
						VALUES("userName", "#{userName}");
					}
					if(locationUser.getRePwd() != null && !locationUser.getRePwd().equals("")){
						VALUES("rePwd", "#{rePwd}");
					}
					if(locationUser.getUserPwd() != null && !locationUser.getUserPwd().equals("")){
						VALUES("userPwd", "#{userPwd}");
					}
					if(locationUser.getUserType() != 0){
						VALUES("userType", "#{userType}");
					}
					if(locationUser.getOverduetime() != null && !locationUser.getOverduetime().equals("")){
						VALUES("overduetime", "#{overduetime}");
					}
					if(locationUser.getGroupCount() >=0){
						VALUES("groupCount", "#{groupCount}");
					}
					if(locationUser.getVhcCount() >=0){
						VALUES("vhcCount", "#{vhcCount}");
					}
					if(locationUser.getUserCount() >=0){
						VALUES("userCount", "#{userCount}");
					}
				}
			}.toString();
		}
		//修改 
		public String updateUser(LocationUser locationUser){
			return new SQL(){
				{
					UPDATE(USERTABLE);
					if(locationUser.getClientID() != null && !locationUser.getClientID().equals("")){
						SET(" clientID = #{clientID} ");
					}
					if(locationUser.getUserName() != null && !locationUser.getUserName().equals("")){
						SET(" userName = #{userName} ");
					}
					if(locationUser.getUserType() != 0){
						SET(" userType = #{userType} ");
					}
					if(locationUser.getOverduetime() != null && !locationUser.getOverduetime().equals("")){
						SET(" overduetime = #{overduetime} ");
					}
					if(locationUser.getGroupCount() >=0){
						SET(" groupCount = #{groupCount} ");
					}
					if(locationUser.getVhcCount() >=0){
						SET(" vhcCount = #{vhcCount} ");
					}
					if(locationUser.getUserCount() >=0){
						SET(" userCount = #{userCount} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
		
		
		
		
		
		
		
}
