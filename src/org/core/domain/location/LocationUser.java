package org.core.domain.location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**   
 * @Description: 定位系统用户表table=user
 * @author WW GY	   
 * @version V1.0   
 */
public class LocationUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String 	clientID;			//int(50) 	     所属客户Id
	private String  userName;			//varchar(50) 用户名称
	private String  rePwd;				//varchar(50) 用户密码
	private String  userPwd;			//varchar(50) 用户确认密码
	private int 	userType;			//int(50) 	    用户类型 -1普通监控员 1一级管理员 2二级管理员
	private String  overduetime;		//varchar(50) 用户过期时间
	private int 	groupCount;			//int(50) 	    用户分组总数 
	private int 	vhcCount;			//int(50) 	    用户车辆总数
	private String 	id;					//int(50) 	    用户主键
	
	private int     userCount;			//int(50)     用户表里的用户数量:是选择分组之后所有分组里的用户数量之和
	
	
	
	private Set<LocationGroup> userGroups = new HashSet<LocationGroup>();  
	
	private LocationClient userClient = new LocationClient();
	
	
	public LocationUser() {
		super();
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getOverduetime() {
		return overduetime;
	}

	public void setOverduetime(String overduetime) {
		this.overduetime = overduetime;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public int getVhcCount() {
		return vhcCount;
	}

	public void setVhcCount(int vhcCount) {
		this.vhcCount = vhcCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public Set<LocationGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<LocationGroup> userGroups) {
		this.userGroups = userGroups;
	}

	public LocationClient getUserClient() {
		return userClient;
	}

	public void setUserClient(LocationClient userClient) {
		this.userClient = userClient;
	}
	
	
	
	
	

}
