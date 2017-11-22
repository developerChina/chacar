package org.core.domain.location;

import java.io.Serializable;

/**   
 * @Description: 定位系统 用户所属分组表 table=usergroup
 * @author WW GY	   
 * @version V1.0   
 */
public class LocationUserGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int    id;			//int(30)      表id主键
	private String userid;		//varchar(50)  用户ID
	private int    groupid;		//int(30)      用户所属分组ID
	
	public LocationUserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	
	
	
	
}
