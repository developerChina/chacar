package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ElevatorGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String egid;	//电梯分组id
	private String egname;	//电梯分组名称
	private String egssxj;	//电梯分组所属下级
	private Set<Elevator> orderItems = new HashSet<Elevator>();
	@Override
	public String toString() {
		return "ElevatorGroup [egid=" + egid + ", egname=" + egname + ", egssxj=" + egssxj + "]";
	}
	public ElevatorGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEgid() {
		return egid;
	}
	public void setEgid(String egid) {
		this.egid = egid;
	}
	public String getEgname() {
		return egname;
	}
	public void setEgname(String egname) {
		this.egname = egname;
	}
	public String getEgssxj() {
		return egssxj;
	}
	public void setEgssxj(String egssxj) {
		this.egssxj = egssxj;
	}
	
	
	
	public Set<Elevator> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<Elevator> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
		
}
