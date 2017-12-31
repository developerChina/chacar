package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.LogisVehicleDao;
import org.core.domain.location.LocationLogisVehicle;
import org.core.service.location.LogisVehicleService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("logisVehicleService")
public class LogisVehicleServiceImpl implements LogisVehicleService {
	
	@Autowired
	private LogisVehicleDao logisVehicleDao;

	@Transactional(readOnly=true)
	@Override
	public List<LocationLogisVehicle> findLocationLogisVehicle(LocationLogisVehicle locationLogisVehicle,
			PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationLogisVehicle", locationLogisVehicle);
		
		int recordCount = logisVehicleDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationLogisVehicle> locationLogisVehicles = logisVehicleDao.selectByPagegy(gy);
		 
		return locationLogisVehicles;
	}


}
