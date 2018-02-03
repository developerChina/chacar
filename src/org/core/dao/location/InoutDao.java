package org.core.dao.location;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.InoutSqlProvider;
import org.core.domain.car.CarDistinguish;
import org.core.domain.location.LocationConstants;
import org.core.domain.location.LocationInout;

public interface InoutDao {
	@SelectProvider(type=InoutSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	@SelectProvider(type=InoutSqlProvider.class,method="selectByPagegy")	
	List<LocationInout> selectByPagegy(Map<String, Object> gy);

	@Select(" select * from "+LocationConstants.INOUT+" where VehicleCode=#{carno} and DATE_FORMAT(cominDate,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y') and outDate is null order by cominDate DESC limit 1")
	LocationInout selectNewRecord(String carno);
	
	@Select(" select * from "+CarDistinguish.tableName+" where ip = #{serverIp}")
	CarDistinguish getCamera(String serverIp);

}
