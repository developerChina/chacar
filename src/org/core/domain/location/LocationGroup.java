package org.core.domain.location;

import java.io.Serializable;
/**   
 * @Description: 定位系统分组表table=group
 * @author WW GY	   
 * @version V1.0   
 */
public class LocationGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String 	groupName;			//varchar(50) 分组名称
	private String 	createtime;			//varchar(50) 创建时间
	private int 	mintime;			//int(50) 最小回传时间
	private int 	maxtime;			//int(50) 最大回传时间
	private int 	userCount;			//int(50) 用户数量
	private int 	vhcCount;			//int(50) 车辆数量
	private int 	clientID;			//int(50) 客户id
	private int 	id;					//int(50) 分组表 主键
	private int 	seeNext;			//int(50) 上级可见 0不可见 1可见
	public LocationGroup() {
		super();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getMintime() {
		return mintime;
	}

	public void setMintime(int mintime) {
		this.mintime = mintime;
	}

	public int getMaxtime() {
		return maxtime;
	}

	public void setMaxtime(int maxtime) {
		this.maxtime = maxtime;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getVhcCount() {
		return vhcCount;
	}

	public void setVhcCount(int vhcCount) {
		this.vhcCount = vhcCount;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeeNext() {
		return seeNext;
	}

	public void setSeeNext(int seeNext) {
		this.seeNext = seeNext;
	}
	
	
	
	
	
}
