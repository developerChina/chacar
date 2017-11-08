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
	private String ajemp;//门禁授权所属员工
	private String ajcard;//门禁授权所属员工卡号
	private String ajgroup;//门禁授权所属门禁分组
	private String ajaccess;//门禁授权所属门禁
	/*
	 *  授权里的门禁分组集合
	 */
	private Set<AccessGroup> agroups = new HashSet<AccessGroup>();

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
	public String getAjemp() {
		return ajemp;
	}
	public void setAjemp(String ajemp) {
		this.ajemp = ajemp;
	}
	public String getAjcard() {
		return ajcard;
	}
	public void setAjcard(String ajcard) {
		this.ajcard = ajcard;
	}
	public String getAjgroup() {
		return ajgroup;
	}
	public void setAjgroup(String ajgroup) {
		this.ajgroup = ajgroup;
	}
	public String getAjaccess() {
		return ajaccess;
	}
	public void setAjaccess(String ajaccess) {
		this.ajaccess = ajaccess;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Accessj [ajid=" + ajid + ", ajname=" + ajname + ", ajemp=" + ajemp + ", ajcard=" + ajcard + ", ajgroup="
				+ ajgroup + ", ajaccess=" + ajaccess + "]";
	}
	public Accessj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Set<AccessGroup> getAgroups() {
		return agroups;
	}
	public void setAgroups(Set<AccessGroup> agroups) {
		this.agroups = agroups;
	}
	
}
