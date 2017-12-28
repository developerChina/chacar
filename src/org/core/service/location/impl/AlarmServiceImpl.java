package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.AlarmDao;
import org.core.domain.location.LocationAlarm;
import org.core.service.location.AlarmService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmDao alarmDao;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<LocationAlarm> findLocationAlarm(LocationAlarm locationAlarm, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationAlarm", locationAlarm);
		int recordCount = alarmDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationAlarm> locationAlarms = alarmDao.selectByPagegy(gy);
		 
		return locationAlarms;
	}


	@Override
	public void modifyLocationLocationAlarm(String ids) {
		
		String[] idArry = ids.split(",");
		for (String id : idArry) {
			alarmDao.modifyLocationAlarm(Integer.parseInt(id));
		}	
		
	}

}
