package org.core.domain.resource;

import java.util.Date;

import org.core.domain.webapp.User;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * table="resource_access" <br/>
 * 资源授权实体
 */
@SuppressWarnings("serial")
public class ResourceAccess implements java.io.Serializable{
	public static final String tableName = "resource_access";
	private int id;    //int(11) NOT NULL AUTO_INCREMENT COMMENT '资源权限id' ,
	private int resource_id;    //int(11) NOT NULL COMMENT '资源ID' ,
	private int user_id;    //int(11) NOT NULL COMMENT '资源所属用户id' ,
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdate;    // date NULL DEFAULT NULL COMMENT '创建时间' , 
	
	////表外字段
	private ResourceInfo resource; //资源实体（表外字段）
	private User user;//用户实体 （表外字段）
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResource_id() {
		return resource_id;
	}
	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public ResourceInfo getResource() {
		return resource;
	}
	public void setResource(ResourceInfo resource) {
		this.resource = resource;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}