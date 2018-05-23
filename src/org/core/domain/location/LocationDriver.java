package org.core.domain.location;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**   
 * @Description: 定位系统   车主信息  table=logis_vehicle_his
 */
@SuppressWarnings("serial")
public class LocationDriver  implements java.io.Serializable{
	public static final String tableName = "logis_vehicle_his";
	private int id;  					//int(11)  		车辆信息表的主键id
	private String name;  				//varchar(50)	车主信息
	private String vehicleCode;  		//varchar(50)   车牌号
	private String cartType;  			// 车的类型
	private int type;  					// 操作类型0删除1修改
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date optdate;  				// 操作时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public String getCartType() {
		return cartType;
	}
	public void setCartType(String cartType) {
		this.cartType = cartType;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getOptdate() {
		return optdate;
	}
	public void setOptdate(Date optdate) {
		this.optdate = optdate;
	}
	
}
