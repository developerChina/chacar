package org.core.domain.webapp;

import java.io.Serializable;
import java.util.Date;
/**
 * table="trajectory_emp" <br/>
 * 员工访问轨迹记录
 */
@SuppressWarnings("serial")
public class TrajectoryEmp implements Serializable{
	public static final String tableName = "trajectory_emp";
	private String id;      // int(11) 员工出入轨迹id
	private String cardno;      // varchar(20) 员工卡号 
	private String optAction;      // varchar(20) 进出动作 
	private Date optTime;      // datetime  操作时间
	
	//表外字段
	private Employee employees; //查询当前被访问人的信息
	private Date startTime;      // datetime  查询开始时间条件
	private Date endTime;      // datetime  查询结束时间条件

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getOptAction() {
		return optAction;
	}

	public void setOptAction(String optAction) {
		this.optAction = optAction;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}