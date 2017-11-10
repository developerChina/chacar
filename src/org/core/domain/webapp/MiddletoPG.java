package org.core.domain.webapp;

import java.io.Serializable;

public class MiddletoPG implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pmiddleid;
	private String pgroupid;
	private String passagewayid;
	public Integer getPmiddleid() {
		return pmiddleid;
	}
	public void setPmiddleid(Integer pmiddleid) {
		this.pmiddleid = pmiddleid;
	}
	public String getPgroupid() {
		return pgroupid;
	}
	public void setPgroupid(String pgroupid) {
		this.pgroupid = pgroupid;
	}
	public String getPassagewayid() {
		return passagewayid;
	}
	public void setPassagewayid(String passagewayid) {
		this.passagewayid = passagewayid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MiddletoPG() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MiddletoPG [pmiddleid=" + pmiddleid + ", pgroupid=" + pgroupid + ", passagewayid=" + passagewayid + "]";
	}
	
	
	
}
