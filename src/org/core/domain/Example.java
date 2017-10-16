package org.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Example implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;			// id
	private String username;	// 用户名
	private Integer status;		// 状态
	private Date createDate;	// 建档日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}