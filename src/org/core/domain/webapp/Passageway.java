package org.core.domain.webapp;

import java.io.Serializable;

public class Passageway implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer passagewayID; //通道id
	private String passagewayName; //通道名称
	private String ControllerSN; //控制SN
	private String ControllerIP; //控制IP
	private String ptype; //通道类型

	//toString
	@Override
	public String toString() {
		return "Passageway [passagewayID=" + passagewayID + ", passagewayName=" + passagewayName + ", ControllerSN="
				+ ControllerSN + ", ControllerIP=" + ControllerIP + ", ptype=" + ptype + "]";
	}
	
	//getset
		public Integer getPassagewayID() {
				return passagewayID;
			}
		
	public void setPassagewayID(Integer passagewayID) {
		this.passagewayID = passagewayID;
	}
	public String getPassagewayName() {
		return passagewayName;
	}
	public void setPassagewayName(String passagewayName) {
		this.passagewayName = passagewayName;
	}
	public String getControllerSN() {
		return ControllerSN;
	}
	public void setControllerSN(String controllerSN) {
		ControllerSN = controllerSN;
	}
	public String getControllerIP() {
		return ControllerIP;
	}
	public void setControllerIP(String controllerIP) {
		ControllerIP = controllerIP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//无参构造
	public Passageway() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
	

}
