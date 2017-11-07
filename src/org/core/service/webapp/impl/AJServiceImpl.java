package org.core.service.webapp.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AJDao;
import org.core.dao.webapp.AccessGroupDao;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.service.webapp.AJService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("aJService")
public class AJServiceImpl implements AJService {
	@Autowired
	private AJDao aJDao;
	//查询所有权限表
	@Autowired
	private AccessGroupDao accessGroupDao;
	@Transactional(readOnly=true)
	@Override
	public int selectAJG(String id) {
		// TODO Auto-generated method stub
		return aJDao.selectAJG(id);
	}
	//查询所有门禁分组
	@Transactional(readOnly=true)
	@Override
	public List<AccessGroup> findAGAll() {
		// TODO Auto-generated method stub
		return aJDao.findAGAll();
	}
	//查询授权并分页
	@Override
	public List<Accessj> selectAJ(Accessj accessj, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("accessj", accessj);
		int recordCount = aJDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Accessj> accessjs = aJDao.selectByPagegy(gy);
		return accessjs;
	}
	@Override
	public void removeAccessjByID(String  id) {
		// TODO Auto-generated method stub
		aJDao.removeAccessjByID(id);
	}
	@Override
	public void saveAJ(Accessj accessj) {
		// TODO Auto-generated method stub
		//uuid
		String uuid=GenId.UUID();
		accessj.setAjid(uuid);
		aJDao.saveAJ(accessj);
		
	}
	@Override
	public Accessj selectAjByid(String id) {
		// TODO Auto-generated method stub
		return aJDao.selectAjByid(id);
	}
	//修改
	@Override
	public void updateAj(Accessj accessj) {
		// TODO Auto-generated method stub
		aJDao.updateAj(accessj);
	}
	//查询里的先来门禁组
	@Override
	public List<AccessGroup> selectPGbyId(String selectEGs) {
		// TODO Auto-generated method stub
		String[] idArry = selectEGs.split(",");
		List<AccessGroup> addList = new ArrayList<>();
		for (String id : idArry) {
			AccessGroup myAG=accessGroupDao.selectAGbyId(id);
			addList.add(myAG);
		}
		return addList;
	}
	
}
