package org.core.domain.queuing;

import java.util.Date;

/**
 * table="logis_ordinary" <br>
 *  普通队列的统计实体
 */
@SuppressWarnings("serial")
public class Ordinary implements java.io.Serializable {
	public static final String tableName = "logis_ordinary";
	
	private int id;  				//int(11) 			普通队列表主键
	private int island_no;  		//int(11) 			卸货岛编号
	private String car_code;  		//varchar(50) 		普通队列的车牌
	private int queue_number;  		//int(11) 			普通队列排序
	private Date comein_time;  		//Date 				驶入时间
	private Date goout_time; 		//Date				驶出时间
	private String remarks;  		//varchar(50) 		备注
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getQueue_number() {
		return queue_number;
	}
	public void setQueue_number(int queue_number) {
		this.queue_number = queue_number;
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
	

	
	
}
