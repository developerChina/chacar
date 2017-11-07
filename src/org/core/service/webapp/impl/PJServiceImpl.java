package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.PJDao;
import org.core.dao.webapp.PassagewayGroupDao;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;
import org.core.service.webapp.PJService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("pJService")
public class PJServiceImpl implements PJService {
	@Autowired
	private PJDao pJDao;
	@Autowired
	private PassagewayGroupDao passagewayGroupDao;
	@Override
	@Transactional(readOnly=true)
	public int selectPJG(String id) {
		// TODO Auto-generated method stub
		return pJDao.selectPJG(id);
	}
	//查询所有分组
	@Override
	@Transactional(readOnly=true)
	public List<PassagewayGroup> selectAll() {
		// TODO Auto-generated method stub
		return pJDao.findPGAll();
	}
	//查询授权表并分页
	@Override
	public List<Passagewayj> selectPJ(Passagewayj passagewayj, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("passagewayj", passagewayj);
		int recordCount = pJDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Passagewayj> passagewayjs = pJDao.selectByPagegy(gy);
		return passagewayjs;
	}
	@Override
	public void removePassagewayjByID(String id) {
		// TODO Auto-generated method stub
		pJDao.removePassagewayjByID(id);
	}
	//查自己
	@Override
	public Passagewayj selectPjByid(String id) {
		// TODO Auto-generated method stub
		return pJDao.selectPjByid(id);
	}
	//修改
	@Override
	public void updatePj(Passagewayj passagewayj) {
		// TODO Auto-generated method stub
		pJDao.updatePj(passagewayj);
	}
	@Override
	public void savePJ(Passagewayj passagewayj) {
		// TODO Auto-generated method stub
		String uuid=GenId.UUID();
		passagewayj.setPjid(uuid);
		pJDao.savePJ(passagewayj);
		
	}
	//查询的先来通道组
	@Override
	public List<PassagewayGroup> selectPGbyId(String selectEGs) {
		// TODO Auto-generated method stub
		String[] idArry = selectEGs.split(",");
		List<PassagewayGroup> addList = new ArrayList<>();
		for (String id : idArry) {
			PassagewayGroup myAG=passagewayGroupDao.selectPGbyId(id);
			addList.add(myAG);
		}
		return addList;
	}

}
