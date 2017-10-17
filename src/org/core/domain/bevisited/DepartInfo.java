package org.core.domain.bevisited;

import java.io.Serializable;
/**
 * table="depart_info" <br/>
 * 公司部门表
 */
public class DepartInfo implements Serializable{
	public static final String tableName = "depart_info";
	private static final long serialVersionUID = 1L;
	private String deptID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门ID' ,
	private String deptName;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '部门名称' ,
	private String supDeptID;   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上级部门ID' ,
	private String supDeptName;   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上级部门名称' ,
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSupDeptID() {
		return supDeptID;
	}
	public void setSupDeptID(String supDeptID) {
		this.supDeptID = supDeptID;
	}
	public String getSupDeptName() {
		return supDeptName;
	}
	public void setSupDeptName(String supDeptName) {
		this.supDeptName = supDeptName;
	}
}