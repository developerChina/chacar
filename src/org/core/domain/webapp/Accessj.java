package org.core.domain.webapp;

import java.io.Serializable;

public class Accessj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ajid;		//门禁授权  id
	private String ajname;		//门禁授权  名称
	private String ajempid;		//门禁授权  员工
	private String ajgroupid;	//门禁授权  门禁分组
	
	private String ajaccessid;  //门禁授权  门禁
	private String ajempno;  	//门禁授权  员工卡号
	
	private	String pganame;		//用来模糊查询门禁名称
	private	String ajEmpName;	//用来模糊查询员工名称
	private	String ajphone;		//用来模糊查询员工电话
	
	private Integer dept_id;	//接收部门的查询条件
	private	String dept_string;	//接收根据dept查出员工id的条件
	/*
	 *  授权里的门禁分组集合
	 */
	private AccessGroup agroups = new AccessGroup();
	
	private Employee ajEmployee = new Employee();
	/*
	 *  授权里的门禁集合
	 */
	private Access accessList = new Access();
	
	
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
	
	public Employee getAjEmployee() {
		return ajEmployee;
	}
	public void setAjEmployee(Employee ajEmployee) {
		this.ajEmployee = ajEmployee;
	}
	
	public AccessGroup getAgroups() {
		return agroups;
	}
	public void setAgroups(AccessGroup agroups) {
		this.agroups = agroups;
	}
	public String getAjaccessid() {
		return ajaccessid;
	}
	public void setAjaccessid(String ajaccessid) {
		this.ajaccessid = ajaccessid;
	}
	public String getAjempno() {
		return ajempno;
	}
	public void setAjempno(String ajempno) {
		this.ajempno = ajempno;
	}
	public Access getAccessList() {
		return accessList;
	}
	public void setAccessList(Access accessList) {
		this.accessList = accessList;
	}
	public String getPganame() {
		return pganame;
	}
	public void setPganame(String pganame) {
		this.pganame = pganame;
	}
	public String getAjEmpName() {
		return ajEmpName;
	}
	public void setAjEmpName(String ajEmpName) {
		this.ajEmpName = ajEmpName;
	}
	public String getAjphone() {
		return ajphone;
	}
	public void setAjphone(String ajphone) {
		this.ajphone = ajphone;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_string() {
		return dept_string;
	}
	public void setDept_string(String dept_string) {
		this.dept_string = dept_string;
	}
	
	
	
}
