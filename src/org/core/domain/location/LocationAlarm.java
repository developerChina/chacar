package org.core.domain.location;

import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

public class LocationAlarm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;	//主键id
	private String vehicleCode; //车牌号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date gpstime;	//gps时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date recvtime;//接受时间
	private Float lng;//经度
	private Float lat;//维度
	private Float veo;//速度
	private Integer direct;//方向
	private Integer istate;//状态
	private String cstate;//状态描述
	private String posinfo;//位置
	private Float distance;//里程
	private Float totaldistance;//总里程
	private Integer handletype;//处理状态
	private String handleidea;//处理意见

	public LocationAlarm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public java.util.Date getGpstime() {
		return gpstime;
	}

	public void setGpstime(java.util.Date gpstime) {
		this.gpstime = gpstime;
	}

	public java.util.Date getRecvtime() {
		return recvtime;
	}

	public void setRecvtime(java.util.Date recvtime) {
		this.recvtime = recvtime;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getVeo() {
		return veo;
	}

	public void setVeo(Float veo) {
		this.veo = veo;
	}

	public Integer getDirect() {
		return direct;
	}

	public void setDirect(Integer direct) {
		this.direct = direct;
	}

	public Integer getIstate() {
		return istate;
	}

	public void setIstate(Integer istate) {
		this.istate = istate;
	}

	public String getCstate() {
		return cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	public String getPosinfo() {
		return posinfo;
	}

	public void setPosinfo(String posinfo) {
		this.posinfo = posinfo;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getTotaldistance() {
		return totaldistance;
	}

	public void setTotaldistance(Float totaldistance) {
		this.totaldistance = totaldistance;
	}

	public Integer getHandletype() {
		return handletype;
	}

	public void setHandletype(Integer handletype) {
		this.handletype = handletype;
	}

	public String getHandleidea() {
		return handleidea;
	}

	public void setHandleidea(String handleidea) {
		this.handleidea = handleidea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LocationAlarm [id=" + id + ", vehicleCode=" + vehicleCode + ", gpstime=" + gpstime + ", recvtime="
				+ recvtime + ", lng=" + lng + ", lat=" + lat + ", veo=" + veo + ", direct=" + direct + ", istate="
				+ istate + ", cstate=" + cstate + ", posinfo=" + posinfo + ", distance=" + distance + ", totaldistance="
				+ totaldistance + ", handletype=" + handletype + ", handleidea=" + handleidea + "]";
	}


	
	
}
