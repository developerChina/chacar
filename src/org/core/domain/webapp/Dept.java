package org.core.domain.webapp;


public class Dept implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;		// id
	private String name;	// 部门名称
	private Integer pid;	//上级部门id
	private String pname;	// 上级部门名称
	private String remark;	// 详细描述
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	public String getRemark(){
		return this.remark;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}

}