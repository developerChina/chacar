package org.core.domain.queuing;

import java.util.Date;

/**
 * table="logis_vip" <br>
 *  普通队列的统计实体
 */
@SuppressWarnings("serial")
public class QueuingVip implements java.io.Serializable {
	public static final String tableName = "logis_vip";
	
	private int id;  				//int(11) 			VIP队列的主键
	private int island_no;  		//int(11) 			卸货岛主键
	private String car_code;  		//varchar(25) 		VIP车牌
	private int queue_number;  		//int(11) 			VIP队列排序
	private Date comein_time;  		//date				驶入时间
	private Date goout_time;  		//date 				驶出时间
	private String remarks;  		//varchar(50) 		排列原因
	
	//表外字段
	private Island vpartsI;			//卸货岛实体
	private String vagueiname;		//卸货岛名称用来模糊查询
	private String supplier;		//供货商名称
	
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
	public Island getVpartsI() {
		return vpartsI;
	}
	public void setVpartsI(Island vpartsI) {
		this.vpartsI = vpartsI;
	}
	public String getVagueiname() {
		return vagueiname;
	}
	public void setVagueiname(String vagueiname) {
		this.vagueiname = vagueiname;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	
	
	
	
}
