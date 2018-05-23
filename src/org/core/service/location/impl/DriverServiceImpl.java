package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.DriverDao;
import org.core.domain.location.LocationDriver;
import org.core.service.location.DriverService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("driverService")
public class DriverServiceImpl implements DriverService{

	@Autowired
	private DriverDao driverDao;

	@Override
	public List<LocationDriver> selectIByPage(LocationDriver locationDriver, PageModel pageModel) {
		Map<String,Object> params = new HashMap<>();
		params.put("locationDriver", locationDriver);
		int recordCount = driverDao.countD(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<LocationDriver> pageListD = driverDao.pageSelectD(params);
		return pageListD;
	}

	@Override
	public void addD(LocationDriver locationDriver) {
		driverDao.addD(locationDriver);	
	}

	@Override
	public String addValidate(String vehicleCode) {
		LocationDriver entity = driverDao.addValidate(vehicleCode);
		String str = "";
		if(entity!=null){
			str="此车辆已经添加，请勿重复添加";
		}
		return str;
	}

	@Override
	public void delDriver(Integer id) {
		driverDao.delDriver(id);
	}

	@Override
	public LocationDriver getById(Integer id) {
		return driverDao.getById(id);
	}

	@Override
	public void upDriver(LocationDriver locationDriver) {
		driverDao.upDriver(locationDriver);
	}
	
	
}
