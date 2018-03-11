package org.core.domain.car;
/**
 * table="car_logs" <br/>
 * 车辆授权实体
 */
@SuppressWarnings("serial")
public class CarLogs implements java.io.Serializable{
	public static final String tableName = "car_logs";
	
	private int id;				//主键id
	private String serverIp;	//相机ip
	private String cacrno;		//车牌号
	private String shootTime;	//通过时间

	//表外字段
	private String carMaster;	//车主
	private String outIp;		//另一个iP
	private String outTime;		//另一个时间
	private String inIpName;	//进入名称
	private String outIpName;	//驶出名称
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getCacrno() {
		return cacrno;
	}
	public void setCacrno(String cacrno) {
		this.cacrno = cacrno;
	}
	public String getShootTime() {
		return shootTime;
	}
	public void setShootTime(String shootTime) {
		this.shootTime = shootTime;
	}
	public String getCarMaster() {
		return carMaster;
	}
	public void setCarMaster(String carMaster) {
		this.carMaster = carMaster;
	}
	public String getOutIp() {
		return outIp;
	}
	public void setOutIp(String outIp) {
		this.outIp = outIp;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getInIpName() {
		return inIpName;
	}
	public void setInIpName(String inIpName) {
		this.inIpName = inIpName;
	}
	public String getOutIpName() {
		return outIpName;
	}
	public void setOutIpName(String outIpName) {
		this.outIpName = outIpName;
	}
	
	
	
}
