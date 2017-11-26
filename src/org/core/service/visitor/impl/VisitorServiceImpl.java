package org.core.service.visitor.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.visitor.VisitorDao;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Blacklist;
import org.core.service.visitor.VisitorService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("visitorService")
public class VisitorServiceImpl implements VisitorService {
	@Autowired
	private  VisitorDao visitorDao;
	
	@Override
	public List<Blacklist> findBlacklist(PageModel pageModel,Blacklist blacklist) {
		// TODO Auto-generated method stub
		
		Map<String,Object> params = new HashMap<>();
		params.put("blacklist", blacklist);
		int recordCount = visitorDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Blacklist> myBlacklist = visitorDao.selectByPage(params);
		return myBlacklist;
	}


	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		visitorDao.remove(id);
	}

	//实现手动添加黑名单
	@Override
	public void addBlacklist(Blacklist blacklist) {
		// TODO Auto-generated method stub
		visitorDao.addBlacklist(blacklist);
	}


	
	//实现将访客信息添加到黑名单！
	//查访客信息表！
	@Override
	public List<VisitorInfo> selectByPage(PageModel pageModel,
			VisitorInfo entity) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = visitorDao.entityCount(params);
		
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }

		List<VisitorInfo> myVisitorInfo = visitorDao.myselectByPage(params);
		return myVisitorInfo;
		
	}


	@Override
	public void removeByids(String ids) {
		
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			visitorDao.remove(Integer.parseInt(id));
		}
		
	}

	@Override
	public List<Blacklist> selectBlackByCardId(String cardid) {
		return visitorDao.selectBlackByCardId(cardid);
	}
	
}
