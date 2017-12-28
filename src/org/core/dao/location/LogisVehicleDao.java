package org.core.dao.location;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.LogisVehicleSqlProvider;
import org.core.domain.location.LocationLogisVehicle;

public interface LogisVehicleDao {
	@SelectProvider(type=LogisVehicleSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	@SelectProvider(type=LogisVehicleSqlProvider.class,method="selectByPagegy")
	List<LocationLogisVehicle> selectByPagegy(Map<String, Object> gy);
	

}
