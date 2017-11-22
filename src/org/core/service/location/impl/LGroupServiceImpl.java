package org.core.service.location.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.LGroupDao;
import org.core.domain.location.LocationGroup;
import org.core.service.location.LGroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Description: 定位系统 分组服务层接口实现类
 * @see (LGroupService)
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("lGroupService")
public class LGroupServiceImpl implements LGroupService {
	@Autowired
	private LGroupDao lGroupDao;
	//查询
	@Override
	public List<LocationGroup> selectAll(PageModel pageModel, LocationGroup locationGroup) {
		Map<String,Object> params = new HashMap<>();
		params.put("locationGroup", locationGroup);
		int recordCount = lGroupDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<LocationGroup> locationGroups = lGroupDao.selectByPage(params);
		return locationGroups;
	}
    //添加
	@Override
	public void addLocationGroup(LocationGroup locationGroup) {
		//最小回传时间 30
		locationGroup.setMintime(30);
		//最大回传时间 3600
		locationGroup.setMaxtime(60);
		//创建时间
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);	
		locationGroup.setCreatetime(time);
		//执行保存
		lGroupDao.save(locationGroup);
	}
	//删除
	@Override
	public void delLocationGroup(int Lgroup) {
		
		lGroupDao.delLocationGroup(Lgroup);
	}
	//修改前查一遍
	@Override
	public LocationGroup getUpdate(int lgroup) {
		return lGroupDao.getUpdate(lgroup);
	}
	//修改
	@Override
	public void updateLgroup(LocationGroup locationGroup) {
		
		lGroupDao.updateLgroup(locationGroup);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
