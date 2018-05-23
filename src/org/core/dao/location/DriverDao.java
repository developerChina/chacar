package org.core.dao.location;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.DriverSql;
import org.core.domain.location.LocationDriver;

public interface DriverDao {
	@SelectProvider(type=DriverSql.class,method="countD")
	int countD(Map<String, Object> params);
	
	@SelectProvider(type=DriverSql.class,method="pageSelectD")
	List<LocationDriver> pageSelectD(Map<String, Object> params);

	@SelectProvider(type=DriverSql.class,method="addD")
	void addD(LocationDriver locationDriver);
	
	@Select(" select * from "+LocationDriver.tableName+" as d WHERE d.vehicleCode = #{vehicleCode}")
	LocationDriver addValidate(String vehicleCode);

	@Delete(" delete from "+LocationDriver.tableName+" where id = #{id} ")
	void delDriver(Integer id);
	
	@Select(" select * from "+LocationDriver.tableName+" as d WHERE d.id = #{id}")
	LocationDriver getById(Integer id);

	@SelectProvider(type=DriverSql.class,method="upDriver")
	void upDriver(LocationDriver locationDriver);

}
