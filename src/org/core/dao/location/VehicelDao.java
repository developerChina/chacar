package org.core.dao.location;

import static org.core.domain.location.LocationConstants.VEHICELTABLE;
import static org.core.domain.location.LocationConstants.VEHICELGROUPTABLE;
import static org.core.domain.location.LocationConstants.GROUPTABLE;
import static org.core.domain.location.LocationConstants.WAIJIETABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.VehicelSqlProvider;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationVehicel;
import org.core.domain.location.LocationVehicelGroup;
import org.core.domain.location.LocationWaiJie;

public interface VehicelDao {
	//count
	@SelectProvider(type=VehicelSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	//查询
	@SelectProvider(type=VehicelSqlProvider.class,method="selectByPagegy")
	List<LocationVehicel> selectByPagegy(Map<String, Object> gy);
	//删除
	@Delete(" delete from "+VEHICELTABLE+" where id = #{id} ")
	void removeLocationVehicelById(String id);
	//添加 到设备分组表中
	@SelectProvider(type=VehicelSqlProvider.class,method="addVehicelGroup")
	void addVehicelGroup(String groupid, String uuid);
	//添加 到设备外接设备表中
	@SelectProvider(type=VehicelSqlProvider.class,method="addWaiJie")
	void addWaiJie(String mywjsb, String uuid);
	//保存自己
	@SelectProvider(type=VehicelSqlProvider.class,method="save")
	void save(LocationVehicel locationVehicel);
	//修改前查一遍
	@Select(" select * from "+VEHICELTABLE+" where id = #{vehicelid} ")
	LocationVehicel getUpdate(String vehicelid);
	@Select(" select * from "+VEHICELGROUPTABLE+" where vehicelId = #{vehicelid} ")
	List<LocationVehicelGroup> getUpdateVgroup(String vehicelid);
	@Select(" select * from "+GROUPTABLE+" where id = #{groupId} ")
	LocationGroup getGroup(int groupId);
	@Select(" select * from "+WAIJIETABLE+" where vehicleid = #{vehicelid} ")
	List<LocationWaiJie> getUpdateVwaijie(String vehicelid);
	
	//修改 
		//1先删除分组设备
	@Delete(" delete from "+VEHICELGROUPTABLE+" where vehicelId = #{vehicelid} ")
	void removeVgroup(String vehicelid);
		//2再删除分组设备
	@Delete(" delete from "+WAIJIETABLE+" where vehicleid = #{vehicelid} ")
	void removeVwaijie(String vehicelid);
		//3...重新添加
		//修改
	@SelectProvider(type=VehicelSqlProvider.class,method="update")
	void UpdateVehicel(LocationVehicel locationVehicel);
	
	
	
	
}
