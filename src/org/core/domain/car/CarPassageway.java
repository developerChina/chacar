package org.core.domain.car;

import java.util.List;

/**
 * table="car_passageway" <br/>
 * 出入口实体
 */
@SuppressWarnings("serial")
public class CarPassageway implements java.io.Serializable{
	public static final String tableName = "car_passageway";
	private int id;    //int(11) NOT NULL AUTO_INCREMENT COMMENT '出入口id' ,
	private int type;    //int(11) NOT NULL COMMENT '类型（0=入口，1=出口，2=入/出口）' ,
	private String name;    //varchar(50) CHARACTER NOT NULL COMMENT '识别仪名称' ,
	private int park_id;    //int(11) NOT NULL COMMENT '所属停车场' ,
	private String distinguish_ids;    //varchar(100) CHARACTER NOT NULL COMMENT '识别仪列表' , 

	////表外字段
	private CarPark carPark; //停车场实体（表外字段）
	private List<CarDistinguish> carDistinguishs; //识别仪列表（表外字段）
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPark_id() {
		return park_id;
	}
	public void setPark_id(int park_id) {
		this.park_id = park_id;
	}
	public String getDistinguish_ids() {
		return distinguish_ids;
	}
	public void setDistinguish_ids(String distinguish_ids) {
		this.distinguish_ids = distinguish_ids;
	}
	public CarPark getCarPark() {
		return carPark;
	}
	public void setCarPark(CarPark carPark) {
		this.carPark = carPark;
	}
	public List<CarDistinguish> getCarDistinguishs() {
		return carDistinguishs;
	}
	public void setCarDistinguishs(List<CarDistinguish> carDistinguishs) {
		this.carDistinguishs = carDistinguishs;
	}
}