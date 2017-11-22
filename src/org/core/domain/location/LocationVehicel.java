package org.core.domain.location;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**   
 * @Description: 定位系统设备表(车辆)table=vehicel
 * @author WW GY	   
 * @version V1.0  
 */
@SuppressWarnings("serial")
public class LocationVehicel implements Serializable{
	
	
	private String 	carName;				//varchar(50) 车牌号码 手动输入  
	private String 	clientID;				//int(50) 所属客户Id	  选择 
	private String 	gprs;					//varchar(50) 设备Id  手动输入 
	private String 	sim;					//varchar(50) SIM号码  手动输入
	private int 	mobileId;				//int(50) 车机类型	   选择
	private int 	vehicleTypeId;			//int(50) 车辆类型	   选择
	private String  number;					//varchar(50) 数据号码 选择 数据转发类型
	private String  vedio;					//varchar(50) 语音号码 手动输入
	private int 	colorId;				//int(20) 车辆颜色	    选择
	private String  overduetime;			//varchar(50) 过期时间  选择
	private String  id;						//varchar(50) 设备表主键
	
	
	//存分组的集合
	private Set<LocationGroup> myVgroups = new HashSet<LocationGroup>();
	//存放外接设备的字符串
	private String waijieids ;
	
	public LocationVehicel() {
		super();
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getGprs() {
		return gprs;
	}

	public void setGprs(String gprs) {
		this.gprs = gprs;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public int getMobileId() {
		return mobileId;
	}

	public void setMobileId(int mobileId) {
		this.mobileId = mobileId;
	}

	public int getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVedio() {
		return vedio;
	}

	public void setVedio(String vedio) {
		this.vedio = vedio;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getOverduetime() {
		return overduetime;
	}

	public void setOverduetime(String overduetime) {
		this.overduetime = overduetime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	

	public Set<LocationGroup> getMyVgroups() {
		return myVgroups;
	}

	public void setMyVgroups(Set<LocationGroup> myVgroups) {
		this.myVgroups = myVgroups;
	}

	public String getWaijieids() {
		return waijieids;
	}

	public void setWaijieids(String waijieids) {
		this.waijieids = waijieids;
	}
		
	
	
	
	
}
