package org.core.domain.queuing;
 
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * table="logis_history" <br>
 *  历史数据的统计实体 git
 */
@SuppressWarnings("serial")
public class History implements java.io.Serializable {
	public static final String tableName = "logis_history";
	
	private String id; 	 				//int(11) 		     历史数据的统计表的主键
	private int island_no; 	 			//int(11) 		     卸货岛编号
	private String car_code;  			//varchar(50)     历史表的车牌
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date comein_time; 			//date    		    驶入时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date goout_time;  			//date     	             驶出时间
	private String remarks;  			//varchar(50)     备注
	private int source;					//int(11) 		    数据来源 1_vip 2_普通表
	private String empname;				//varchar(50)     操作员
	
	//表外字段
	private Island hpartsI;				//卸货岛实体
	private String vagueiname;			//卸货岛名称用来模糊查询
	
	private String reduce;				//驶入驶出的差值
	private String supplier;			//供货商名称
	
	//业务需求新增字段
	private Date take_time;				//date				取号时间
	
	private Date inplant;				//进厂的时间
	private String plant;				//进出厂的差值
	private Date outplant;				//出厂的时间
	
	private String vehicleType;			//车辆类型 固定车辆 临时车辆 其他
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsland_no() {
		return island_no;
	}
	public void setIsland_no(int island_no) {
		this.island_no = island_no;
	}
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public Date getComein_time() {
		return comein_time;
	}
	public void setComein_time(Date comein_time) {
		this.comein_time = comein_time;
	}
	public Date getGoout_time() {
		return goout_time;
	}
	public void setGoout_time(Date goout_time) {
		this.goout_time = goout_time;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public Island getHpartsI() {
		return hpartsI;
	}
	public void setHpartsI(Island hpartsI) {
		this.hpartsI = hpartsI;
	}
	public String getVagueiname() {
		return vagueiname;
	}
	public void setVagueiname(String vagueiname) {
		this.vagueiname = vagueiname;
	}
	public String getReduce() {
		return reduce;
	}
	public void setReduce(String reduce) {
		this.reduce = reduce;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Date getTake_time() {
		return take_time;
	}
	public void setTake_time(Date take_time) {
		this.take_time = take_time;
	}
	public Date getInplant() {
		return inplant;
	}
	public void setInplant(Date inplant) {
		this.inplant = inplant;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public Date getOutplant() {
		return outplant;
	}
	public void setOutplant(Date outplant) {
		this.outplant = outplant;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
