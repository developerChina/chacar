package org.core.domain.webapp;

import java.io.Serializable;

public class Passagewayj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pjid;
	private String pjname;
	private String pjempid;
	private String pjgroupid;
	private String passagewayjid;
	private String pjempno;
	
	//模糊查询用到的字段
	private String pganame;
	private String pEmpName;
	
	private	String pjphone;		//用来模糊查询员工电话
	
	private Integer dept_id;	//接收部门的查询条件
	private	String dept_string;	//接收根据dept查出员工id的条件
	/*
	 *  授权里的通道分组集合
	 */
	private PassagewayGroup pgroups = new PassagewayGroup();
	private Employee pjEmployee =new Employee();
	private Passageway pass = new Passageway();
	public String getPjid() {
		return pjid;
	}
	public void setPjid(String pjid) {
		this.pjid = pjid;
	}
	public String getPjname() {
		return pjname;
	}
	public void setPjname(String pjname) {
		this.pjname = pjname;
	}
	public String getPjempid() {
		return pjempid;
	}
	public void setPjempid(String pjempid) {
		this.pjempid = pjempid;
	}
	public String getPjgroupid() {
		return pjgroupid;
	}
	public void setPjgroupid(String pjgroupid) {
		this.pjgroupid = pjgroupid;
	}
	public PassagewayGroup getPgroups() {
		return pgroups;
	}
	public void setPgroups(PassagewayGroup pgroups) {
		this.pgroups = pgroups;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Passagewayj() {
		super();
	}
	public Employee getPjEmployee() {
		return pjEmployee;
	}
	public void setPjEmployee(Employee pjEmployee) {
		this.pjEmployee = pjEmployee;
	}
	
	public String getPassagewayjid() { 
		return passagewayjid;
	}
	public void setPassagewayjid(String passagewayjid) {
		this.passagewayjid = passagewayjid;
	}
	public String getPjempno() {
		return pjempno;
	}
	public void setPjempno(String pjempno) {
		this.pjempno = pjempno;
	}
	public Passageway getPass() {
		return pass;
	}
	public void setPass(Passageway pass) {
		this.pass = pass;
	}
	public String getPganame() {
		return pganame;
	}
	public void setPganame(String pganame) {
		this.pganame = pganame;
	}
	public String getpEmpName() {
		return pEmpName;
	}
	public void setpEmpName(String pEmpName) {
		this.pEmpName = pEmpName;
	}
	public String getPjphone() {
		return pjphone;
	}
	public void setPjphone(String pjphone) {
		this.pjphone = pjphone;
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
