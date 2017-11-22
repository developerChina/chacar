package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationVehicel;
import org.core.util.tag.PageModel;

public interface VehicelService {
	//查询
	List<LocationVehicel> findLocationVehicel(LocationVehicel locationVehicel, PageModel pageModel);
	//删除
	void removeLocationVehicelById(String id);
	//添加
	void addLvehicel(LocationVehicel locationVehicel, String[] wjsb, String[] Lgroupids);
	//修改前查
	LocationVehicel getUpdate(String vehicelid);
	//执行修改
	void UpdateVehicel(LocationVehicel locationVehicel, String[] Lgroupids, String[] wjsb);

}
