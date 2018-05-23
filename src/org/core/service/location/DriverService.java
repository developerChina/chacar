package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationDriver;
import org.core.util.tag.PageModel;

public interface DriverService {

	List<LocationDriver> selectIByPage(LocationDriver locationDriver, 
			PageModel pageModel);

	void addD(LocationDriver locationDriver);

	String addValidate(String vehicleCode);

	void delDriver(Integer id);

	LocationDriver getById(Integer id);

	void upDriver(LocationDriver locationDriver);

}
