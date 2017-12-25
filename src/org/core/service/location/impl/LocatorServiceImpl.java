package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.LocatorDao;
import org.core.domain.location.LocationLocator;
import org.core.service.location.LocatorService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("locatorService")
public class LocatorServiceImpl implements LocatorService {
	
	
	@Autowired
	private LocatorDao locatorDao;
	
	
	//查询
	@Transactional(readOnly=true)
	@Override
	public List<LocationLocator> findLocationLocator(LocationLocator locationLocator, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationLocator", locationLocator);
		int recordCount = locatorDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationLocator> locationLocators = locatorDao.selectByPagegy(gy);
		 
		return locationLocators;
	}
	//删除
	@Override
	public void removeLocationLocatorById(String id) {
		locatorDao.removeLocationLocatorById(id);
		}
	//根据id查询
	@Override
	public LocationLocator findLocationLocatorById(Integer id) {
		return locatorDao.findLocationLocatorById(id);
		}
	//修改
	@Override
	public void modifyLocationLocator(LocationLocator locationLocator) {
		locatorDao.modifyLocationLocator(locationLocator);		
		}
	//添加
	@Override
	public void addLocationLocator(LocationLocator locationLocator) {
		locatorDao.addLocationLocator(locationLocator);
	}

}
