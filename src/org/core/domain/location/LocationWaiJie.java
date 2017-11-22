package org.core.domain.location;

import java.io.Serializable;

public class LocationWaiJie implements Serializable{

	/**   
	 * @Description: 外接设备表 table=WaiJie
	 * @author WW GY	   
	 * @version V1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	private String  wjsbid;   	  //varchar(50)  '外接设备id'
	private String  vehicleid;    //varchar(50)  '设备id' 
	private Integer id;   		  //int(50) 	 '外接设备表主键'
	public LocationWaiJie() {
		super();
		
	}
	@Override
	public String toString() {
		return "LocationWaiJie [wjsbid=" + wjsbid + ", vehicleid=" + vehicleid + ", id=" + id + "]";
	}
	public String getWjsbid() {
		return wjsbid;
	}
	public void setWjsbid(String  wjsbid) {
		this.wjsbid = wjsbid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
