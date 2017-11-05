package org.core.domain.car;

/**
 * table="car_park" <br/>
 * 停车场实体
 */
@SuppressWarnings("serial")
public class CarPark implements java.io.Serializable{
	public static final String tableName = "car_park";
	private int id;    //int(11) NOT NULL AUTO_INCREMENT COMMENT '停车场id' ,
	private String no;    //varchar(20) CHARACTER  COMMENT '车场编号' ,
	private String name;    //varchar(50) CHARACTER  COMMENT '车场名称' ,
	private int num;    //int(11) NOT NULL COMMENT '停车位' , 
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	 
}