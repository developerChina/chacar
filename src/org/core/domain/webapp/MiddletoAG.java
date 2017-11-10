package org.core.domain.webapp;

import java.io.Serializable;

public class MiddletoAG implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer amiddleid;	//'门禁分组主键' ,
	
	private String agroupid;    //'门禁分组的主键id' ,
	private String accessid;    //'门禁主键' ,
	public Integer getAmiddleid() {
		return amiddleid;
	}
	public void setAmiddleid(Integer amiddleid) {
		this.amiddleid = amiddleid;
	}
	public String getAgroupid() {
		return agroupid;
	}
	public void setAgroupid(String agroupid) {
		this.agroupid = agroupid;
	}
	public String getAccessid() {
		return accessid;
	}
	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}
 
	
	
}
