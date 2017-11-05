package org.core.domain.resource;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * table="resource_info" <br/>
 * 资源实体
 */
@SuppressWarnings("serial")
public class ResourceInfo implements java.io.Serializable{
	public static final String tableName = "resource_info";
	private int id;    // int(11) NOT NULL AUTO_INCREMENT COMMENT '资源id' ,
	private String name;    // varchar(50) CHARACTER COMMENT '资源名称' ,
	private String pid;    // int(11) NULL DEFAULT NULL COMMENT '上级资源' ,
	private String path;    // varchar(50) CHARACTER  COMMENT '资源路劲' ,
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdate;    // date NULL DEFAULT NULL COMMENT '创建时间' , 

	////表外字段
	private ResourceInfo resource;    // 上级资源名称 ,（表外字段）
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
}