package org.core.domain.webapp;

import java.io.Serializable;

public class Elevator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer elevatorID;	    //主键id
	private String  elevatorName;	//电梯名
	private String  controllerSN;	//控制器SN
	private Integer controllerIP;   //控制器IP
	private Integer floorNumber;    //楼层编号
	
	
	
	// 无参数构造器
	public Elevator() {
		super();
		// TODO Auto-generated constructor stub
	}
	// setter和getter方法
	
	
	public String getElevatorName() {
		return elevatorName;
	}

	public Integer getElevatorID() {
		return elevatorID;
	}


	public void setElevatorID(Integer elevatorID) {
		this.elevatorID = elevatorID;
	}


	public void setElevatorName(String elevatorName) {
		this.elevatorName = elevatorName;
	}
	public String getControllerSN() {
		return controllerSN;
	}



	public void setControllerSN(String controllerSN) {
		this.controllerSN = controllerSN;
	}



	public Integer getControllerIP() {
		return controllerIP;
	}



	public void setControllerIP(Integer controllerIP) {
		this.controllerIP = controllerIP;
	}



	public Integer getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}



	@Override
	public String toString() {
		return "Elevator [elevatorID=" + elevatorID + ", elevatorName=" + elevatorName + ", controllerSN="
				+ controllerSN + ", controllerIP=" + controllerIP + ", floorNumber=" + floorNumber + "]";
	}
	
	
	
	
	
	
	
}
