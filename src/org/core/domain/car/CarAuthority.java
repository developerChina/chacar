package org.core.domain.car;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * table="car_authority" <br/>
 * 车辆授权实体
 */
@SuppressWarnings("serial")
public class CarAuthority implements java.io.Serializable{
	public static final String tableName = "car_authority";
	private int id;    //int(11) NOT NULL COMMENT '车辆授权id' ,
	private String carno;    //varchar(10) CHARACTER NOT NULL COMMENT '车牌号' ,
	private int passageway_id;    //int(11) NOT NULL COMMENT '出入口id' ,
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validate;    //datetime NOT NULL COMMENT '有效期' ,
	
	////表外字段
	private CarPassageway carPassageway; //出入口实体 （表外字段）
	private CarPark carPark; //停车场实体 （表外字段）
	private String name;    //车主姓名 （表外字段）
	//根据车场查询	授权车辆
	private String park_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public int getPassageway_id() {
		return passageway_id;
	}
	public void setPassageway_id(int passageway_id) {
		this.passageway_id = passageway_id;
	}
	public Date getValidate() {
		return validate;
	}
	public void setValidate(Date validate) {
		this.validate = validate;
	}
	public CarPassageway getCarPassageway() {
		return carPassageway;
	}
	public void setCarPassageway(CarPassageway carPassageway) {
		this.carPassageway = carPassageway;
	}
	public CarPark getCarPark() {
		return carPark;
	}
	public void setCarPark(CarPark carPark) {
		this.carPark = carPark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPark_id() {
		return park_id;
	}
	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}
	 
}