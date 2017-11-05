package org.core.domain.car;

/**
 * table="car_distinguish" <br/>
 * 车辆识别仪（相机）实体
 */
@SuppressWarnings("serial")
public class CarDistinguish implements java.io.Serializable{
	public static final String tableName = "car_distinguish";
	private int id;    // int(11) NOT NULL COMMENT '相机id' ,
	private String no;    // varchar(20) CHARACTER NOT NULL COMMENT '相机编号' ,
	private String name;    // varchar(50) CHARACTER NOT NULL COMMENT '相机名称' ,
	private String ip;    // varchar(20) CHARACTER NOT NULL COMMENT '相机ip' ,
	private String action;    // varchar(100) CHARACTER NULL DEFAULT NULL COMMENT '相机执行类' ,
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}