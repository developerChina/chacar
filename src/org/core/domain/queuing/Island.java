package org.core.domain.queuing;
/**
 * table="logis_island" <br>
 *  卸货岛的统计实体
 */
@SuppressWarnings("serial")
public class Island implements java.io.Serializable{
	public static final String tableName = "logis_island";
	
	private int id;  					//int(11)  		卸货岛的主键id
	private int no;  					//int(11)  		卸货岛的编号
	private String iname;  				//varchar(50) 	卸货岛的名称
	private String cameraip;  			//varchar(50) 	识别仪的ip
	private String camera_content;  	//varchar(150) 	识别仪的内容
	private String big_screenip;  		//varchar(50) 	大屏幕的ip
	private String big_content;  		//varchar(150) 	大屏幕的内容
	private String small_screenip;  	//varchar(50) 	小屏幕的ip
	private String small_content;  		//varchar(150) 	小屏幕的内容
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getCameraip() {
		return cameraip;
	}
	public void setCameraip(String cameraip) {
		this.cameraip = cameraip;
	}
	public String getBig_screenip() {
		return big_screenip;
	}
	public void setBig_screenip(String big_screenip) {
		this.big_screenip = big_screenip;
	}
	public String getSmall_screenip() {
		return small_screenip;
	}
	public void setSmall_screenip(String small_screenip) {
		this.small_screenip = small_screenip;
	}
	public String getCamera_content() {
		return camera_content;
	}
	public void setCamera_content(String camera_content) {
		this.camera_content = camera_content;
	}
	public String getBig_content() {
		return big_content;
	}
	public void setBig_content(String big_content) {
		this.big_content = big_content;
	}
	public String getSmall_content() {
		return small_content;
	}
	public void setSmall_content(String small_content) {
		this.small_content = small_content;
	}
	@Override
	public String toString() {
		return "Island [id=" + id + ", no=" + no + ", iname=" + iname + ", cameraip=" + cameraip + ", camera_content="
				+ camera_content + ", big_screenip=" + big_screenip + ", big_content=" + big_content
				+ ", small_screenip=" + small_screenip + ", small_content=" + small_content + "]";
	}
	
	
	
	
}
