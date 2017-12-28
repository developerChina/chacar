package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.InoutDao;
import org.core.domain.location.LocationInout;
import org.core.service.location.InoutService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("inoutService")
public class InoutServiceImpl implements InoutService {
	@Autowired
	private InoutDao inoutDao;
	@Transactional(readOnly=true)
	@Override
	public List<LocationInout> findLocationAlarm(LocationInout locationInout, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationInout", locationInout);
		
		int recordCount = inoutDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationInout> locationInouts = inoutDao.selectByPagegy(gy);
		 
		return locationInouts;
	}

}
