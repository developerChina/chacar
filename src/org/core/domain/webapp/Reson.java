package org.core.domain.webapp;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Reson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer rid;
	private String content;
	private String rtime;
	public Reson() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Reson [rid=" + rid + ", content=" + content + ", rtime=" + rtime + "]";
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	
	

}
