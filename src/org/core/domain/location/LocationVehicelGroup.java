package org.core.domain.location;

import java.io.Serializable;

/**   
 * @Description: 定位系统 设备所属分组表 table=vehicelgroup
 * @author WW GY	   
 * @version V1.0   
 */
public class LocationVehicelGroup implements Serializable{

	private static final long serialVersionUID = 1L;

	private String 	vehicelId;			//varchar(50) 		设备的id
	private int 	groupId;			//int(50) 			分组id
	private int 	vgid;				//int(50) 			设备分组表主键
	
	public LocationVehicelGroup() {
		super();
		
	}

	public String getVehicelId() {
		return vehicelId;
	}

	public void setVehicelId(String vehicelId) {
		this.vehicelId = vehicelId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getVgid() {
		return vgid;
	}

	public void setVgid(int vgid) {
		this.vgid = vgid;
	}
	
	
	
	
	
	
	
	
	
	
}
