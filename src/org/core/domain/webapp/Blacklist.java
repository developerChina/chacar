package org.core.domain.webapp;

import java.io.Serializable;

public class Blacklist implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer blacklistID;	    //主键id
	private String  blacklistName;		//被拉黑的姓名
	private String  company;			//单位
	private String  idNumber;			//身份证
	private String  reason;				//事由
	
	public Blacklist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getBlacklistID() {
		return blacklistID;
	}

	public void setBlacklistID(Integer blacklistID) {
		this.blacklistID = blacklistID;
	}

	public String getBlacklistName() {
		return blacklistName;
	}

	public void setBlacklistName(String blacklistName) {
		this.blacklistName = blacklistName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Blacklist [blacklistID=" + blacklistID + ", blacklistName=" + blacklistName + ", company=" + company
				+ ", idNumber=" + idNumber + ", reason=" + reason + "]";
	}

	
	
	
	
	
	
	
	
	
}
