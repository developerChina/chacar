package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationClient;
import org.core.util.tag.PageModel;

public interface ClientService {
	//获得所有客户
	List<LocationClient> findLocationClient(LocationClient locationClient, PageModel pageModel);
	//删除客户
	void removeLocationClientById(String id);
	//根据id查询客户
	LocationClient findLocationClientById(String id);
	//修改客户信息
	void modifyLocationClient(LocationClient locationClient);
	//添加客户信息
	void addLocationClient(LocationClient locationClient);

}
