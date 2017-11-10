package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Accessj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ajid;//门禁授权id
	private String ajname;//门禁授权名称
	private String ajempid;//门禁授权所属员工
	private String ajgroupid;//门禁授权所属门禁分组
	/*
	 *  授权里的门禁分组集合
	 */
	private Set<AccessGroup> agroups = new HashSet<AccessGroup>();
	
	private Employee ajEmployee = new Employee();
	
	public String getAjid() {
		return ajid;
	}
	public void setAjid(String ajid) {
		this.ajid = ajid;
	}
	public String getAjname() {
		return ajname;
	}
	public void setAjname(String ajname) {
		this.ajname = ajname;
	}
	public String getAjempid() {
		return ajempid;
	}
	public void setAjempid(String ajempid) {
		this.ajempid = ajempid;
	}
	public String getAjgroupid() {
		return ajgroupid;
	}
	public void setAjgroupid(String ajgroupid) {
		this.ajgroupid = ajgroupid;
	}
	public Set<AccessGroup> getAgroups() {
		return agroups;
	}
	public void setAgroups(Set<AccessGroup> agroups) {
		this.agroups = agroups;
	}
	public Employee getAjEmployee() {
		return ajEmployee;
	}
	public void setAjEmployee(Employee ajEmployee) {
		this.ajEmployee = ajEmployee;
	}
	
	
	
	
}
