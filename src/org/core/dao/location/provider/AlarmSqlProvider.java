package org.core.dao.location.provider;

import static org.core.domain.location.LocationConstants.ALARM;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationAlarm;

public class AlarmSqlProvider {
	//分页动态查询
		public String selectByPagegy(Map<String, Object> gy){
			String sql=new SQL(){
				{
					SELECT("*");
					FROM(ALARM);
					if(gy.get("locationAlarm")!=null){
						LocationAlarm locationAlarm=(LocationAlarm) gy.get("locationAlarm");
						if(locationAlarm.getCstate()!=null && !"".equals(locationAlarm.getCstate())){
							WHERE(" cstate LIKE CONCAT('%',#{locationAlarm.cstate},'%')");
						}
						if(locationAlarm.getDirect()!=null && !"".equals(locationAlarm.getDirect())){
							WHERE(" direct LIKE CONCAT('%',#{locationAlarm.direct},'%')");
						}
						if(locationAlarm.getDistance()!=null && locationAlarm.getDistance()!=0.0){
							WHERE(" distance = CONCAT(=#{locationAlarm.distance}=)");
						}
						if(locationAlarm.getGpstime()!=null && !"".equals(locationAlarm.getGpstime())){
							WHERE(" gpstime = CONCAT(#{locationAlarm.gpstime})");
						}
						if(locationAlarm.getHandleidea()!=null && !locationAlarm.getHandleidea().equals("")){
							WHERE(" handleidea LIKE CONCAT('%',#{locationAlarm.handleidea},'%')");
						}
						if(locationAlarm.getHandletype()!=null && locationAlarm.getHandletype() !=-1){
							WHERE(" handletype LIKE CONCAT('%',#{locationAlarm.handletype},'%')");
						}
						if(locationAlarm.getIstate()!=null && !"".equals(locationAlarm.getIstate())){
							WHERE(" istate LIKE CONCAT('%',#{locationAlarm.istate},'%')");
						}
						if(locationAlarm.getLat()!=null && locationAlarm.getLat()!=0.0){
							WHERE(" lat = CONCAT(#{locationAlarm.lat})");
						}
						if(locationAlarm.getLng()!=null && locationAlarm.getLng()!=0.0){
							WHERE(" lng = CONCAT(#{locationAlarm.lng})");
						}
						if(locationAlarm.getPosinfo()!=null && !"".equals(locationAlarm.getPosinfo())){
							WHERE(" posinfo LIKE CONCAT('%',#{locationAlarm.posinfo},'%')");
						}
						if(locationAlarm.getRecvtime()!=null && !"".equals(locationAlarm.getRecvtime())){
							WHERE(" recvtime = CONCAT(#{locationAlarm.recvtime})");
						}
						if(locationAlarm.getTotaldistance()!=null && locationAlarm.getTotaldistance()!=0.0){
							WHERE(" totaldistance = CONCAT(#{locationAlarm.totaldistance})");
						}
						if(locationAlarm.getVehicleCode()!=null && !"".equals(locationAlarm.getVehicleCode())){
							WHERE(" vehicleCode LIKE CONCAT('%',#{locationAlarm.vehicleCode},'%')");
						}
						if(locationAlarm.getVeo()!=null && locationAlarm.getVeo()!=0.0){
							WHERE(" veo = CONCAT(#{locationAlarm.veo})");
						}
					}
				}
			}.toString();
			if(gy.get("pageModel")!=null){
				sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			return sql;
		}
		//动态查询总数量
		public String count(Map<String, Object> gy){
			return new SQL(){
				{
						
					SELECT("count(*)");
					FROM(ALARM);
					if(gy.get("locationAlarm")!=null){
						LocationAlarm locationAlarm=(LocationAlarm) gy.get("locationAlarm");
						if(locationAlarm.getCstate()!=null && !"".equals(locationAlarm.getCstate())){
							WHERE(" cstate LIKE CONCAT('%',#{locationAlarm.cstate},'%')");
						}
						if(locationAlarm.getDirect()!=null && !"".equals(locationAlarm.getDirect())){
							WHERE(" direct LIKE CONCAT('%',#{locationAlarm.direct},'%')");
						}
						if(locationAlarm.getDistance()!=null && locationAlarm.getDistance()!=0.0){
							WHERE(" distance = CONCAT(=#{locationAlarm.distance}=)");
						}
						if(locationAlarm.getGpstime()!=null && !"".equals(locationAlarm.getGpstime())){
							WHERE(" gpstime = CONCAT(#{locationAlarm.gpstime})");
						}
						if(locationAlarm.getHandleidea()!=null && !locationAlarm.getHandleidea().equals("")){
							WHERE(" handleidea LIKE CONCAT('%',#{locationAlarm.handleidea},'%')");
						}
						if(locationAlarm.getHandletype()!=null && locationAlarm.getHandletype() !=-1){
							WHERE(" handletype LIKE CONCAT('%',#{locationAlarm.handletype},'%')");
						}
						if(locationAlarm.getIstate()!=null && !"".equals(locationAlarm.getIstate())){
							WHERE(" istate LIKE CONCAT('%',#{locationAlarm.istate},'%')");
						}
						if(locationAlarm.getLat()!=null && locationAlarm.getLat()!=0.0){
							WHERE(" lat = CONCAT(#{locationAlarm.lat})");
						}
						if(locationAlarm.getLng()!=null && locationAlarm.getLng()!=0.0){
							WHERE(" lng = CONCAT(#{locationAlarm.lng})");
						}
						if(locationAlarm.getPosinfo()!=null && !"".equals(locationAlarm.getPosinfo())){
							WHERE(" posinfo LIKE CONCAT('%',#{locationAlarm.posinfo},'%')");
						}
						if(locationAlarm.getRecvtime()!=null && !"".equals(locationAlarm.getRecvtime())){
							WHERE(" recvtime = CONCAT(#{locationAlarm.recvtime})");
						}
						if(locationAlarm.getTotaldistance()!=null && locationAlarm.getTotaldistance()!=0.0){
							WHERE(" totaldistance = CONCAT(#{locationAlarm.totaldistance})");
						}
						if(locationAlarm.getVehicleCode()!=null && !"".equals(locationAlarm.getVehicleCode())){
							WHERE(" vehicleCode LIKE CONCAT('%',#{locationAlarm.vehicleCode},'%')");
						}
						if(locationAlarm.getVeo()!=null && locationAlarm.getVeo()!=0.0){
							WHERE(" veo = CONCAT(#{locationAlarm.veo})");
						}
					}
				}
			}.toString();
		}
}
