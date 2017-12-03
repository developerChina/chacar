package org.core.domain.car;

/**
 * table="car_info" <br/>
 * 车辆实体
 */
@SuppressWarnings("serial")
public class CarInfo implements java.io.Serializable{
	public static final String tableName = "car_info";
	private int id;    //int(11) COMMENT '车辆id' ,
	private String name;    //varchar(20)   COMMENT '车主姓名' ,
	private String carno;    //varchar(20)  COMMENT '车牌号' ,
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
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
}