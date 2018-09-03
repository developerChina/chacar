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
	
	//2018-08-27新增字段
	private String tel;			//联系电话
	private String idNumber;	//身份证号
	private String workNumber;	//工号
	private String company;		//所在单位	
	private String team;		//班组
	private String job;			//岗位及职务
	private String model;		//车辆品牌型号
	private String attribute;	//车辆属性
	private String colour;		//颜色
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	
}