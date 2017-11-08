package org.core.domain.webapp;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Passagewayj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pjid;
	private String pjname;
	private String pjemp;
	private String pjcard;
	private String pjgroup;
	private String pjpassageway;
	/*
	 *  授权里的通道分组集合
	 */
	private Set<PassagewayGroup> pgroups = new HashSet<PassagewayGroup>();
	public Passagewayj() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Passagewayj [pjid=" + pjid + ", pjname=" + pjname + ", pjemp=" + pjemp + ", pjcard=" + pjcard
				+ ", pjgroup=" + pjgroup + ", pjpassageway=" + pjpassageway + "]";
	}
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
	public String getPjemp() {
		return pjemp;
	}
	public void setPjemp(String pjemp) {
		this.pjemp = pjemp;
	}
	public String getPjcard() {
		return pjcard;
	}
	public void setPjcard(String pjcard) {
		this.pjcard = pjcard;
	}
	public String getPjgroup() {
		return pjgroup;
	}
	public void setPjgroup(String pjgroup) {
		this.pjgroup = pjgroup;
	}
	public String getPjpassageway() {
		return pjpassageway;
	}
	public void setPjpassageway(String pjpassageway) {
		this.pjpassageway = pjpassageway;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public Set<PassagewayGroup> getPgroups() {
		return pgroups;
	}
	public void setPgroups(Set<PassagewayGroup> pgroups) {
		this.pgroups = pgroups;
	}
	
}
