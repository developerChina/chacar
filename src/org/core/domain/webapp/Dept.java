package org.core.domain.webapp;


public class Dept implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;		// id
	private String name;	// 部门名称
	private Integer pid;	//上级部门id
	private String remark;	// 详细描述
	private Dept dept;	// 上级部门
	// 无参数构造器
	public Dept() {
		super();
	}
	// setter和getter方法
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
}