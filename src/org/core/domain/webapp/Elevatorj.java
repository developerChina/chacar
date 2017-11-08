package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * table="elevatorj_info" <br/>
 * 电梯授权表
 */
public class Elevatorj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ejid;			//电梯授权id' ,	
	private String ejname;        	//授权表名称' ,
	private String ejemp;   		//电梯授权所属员工' ,
	private String ejcard;			//电梯授权所属员工卡号' ,
	private String ejgroup;			//电梯授权所属电梯组' ,
	private String ejelevator;		//电梯授权所属电梯' ,
	
	/*
	 *  授权里的电梯分组集合
	 */
	private Set<ElevatorGroup> egroups = new HashSet<ElevatorGroup>();
	/*
	 *  授权里的电梯集合
	 */
	private Set<Elevator> elevatorS = new HashSet<Elevator>();
	
	
	
	public Elevatorj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEjid() {
		return ejid;
	}
	public void setEjid(String ejid) {
		this.ejid = ejid;
	}
	public String getEjname() {
		return ejname;
	}
	public void setEjname(String ejname) {
		this.ejname = ejname;
	}
	public String getEjemp() {
		return ejemp;
	}
	public void setEjemp(String ejemp) {
		this.ejemp = ejemp;
	}
	public String getEjcard() {
		return ejcard;
	}
	public void setEjcard(String ejcard) {
		this.ejcard = ejcard;
	}
	public String getEjgroup() {
		return ejgroup;
	}
	public void setEjgroup(String ejgroup) {
		this.ejgroup = ejgroup;
	}
	public String getEjelevator() {
		return ejelevator;
	}
	public void setEjelevator(String ejelevator) {
		this.ejelevator = ejelevator;
	}
	
	
	
	
	public Set<ElevatorGroup> getEgroups() {
		return egroups;
	}
	public void setEgroups(Set<ElevatorGroup> egroups) {
		this.egroups = egroups;
	}
	public Set<Elevator> getElevatorS() {
		return elevatorS;
	}
	public void setElevatorS(Set<Elevator> elevatorS) {
		this.elevatorS = elevatorS;
	}
	@Override
	public String toString() {
		return "Elevatorj [ejid=" + ejid + ", ejname=" + ejname + ", ejemp=" + ejemp + ", ejcard=" + ejcard
				+ ", ejgroup=" + ejgroup + ", ejelevator=" + ejelevator + "]";
	}
	
	
}
