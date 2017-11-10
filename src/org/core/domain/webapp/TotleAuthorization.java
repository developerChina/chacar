package org.core.domain.webapp;

public class TotleAuthorization {
	private static final long serialVersionUID = 1L;
	private String id;
	private String userids;
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	private String carno;
	private String mjids;
	private String dtids;
	private String tdids;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getMjids() {
		return mjids;
	}
	public void setMjids(String mjids) {
		this.mjids = mjids;
	}
	public String getDtids() {
		return dtids;
	}
	public void setDtids(String dtids) {
		this.dtids = dtids;
	}
	public String getTdids() {
		return tdids;
	}
	public void setTdids(String tdids) {
		this.tdids = tdids;
	}
	
	
}
