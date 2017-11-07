package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.PassagewayDao;
import org.core.domain.webapp.Passageway;
import org.core.service.webapp.PassagewayService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("passagewayService")
public class PassagewayServiceImpl implements PassagewayService {
	@Autowired
	private PassagewayDao passagewayDao;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Passageway> findPassageway(Passageway passageway, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("passageway", passageway);
		int recordCount = passagewayDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Passageway> passageways = passagewayDao.selectByPagegy(gy);
		 
		return passageways;
	}
	//删除
	@Override
	public void removePassagewayById(Integer id) {
		// TODO Auto-generated method stub
		passagewayDao.deleteBypassagewayID(id);
	}
	//更新
	@Override
	public void modifyPassageway(Passageway passageway) {
		// TODO Auto-generated method stub
		passagewayDao.update(passageway);
	}
	//根据id查询
	@Transactional(readOnly=true)
	@Override
	public Passageway findPassagewayById(Integer passagewayID) {
		// TODO Auto-generated method stub
		return passagewayDao.selectBypassagewayID(passagewayID);
	}
	//添加
	@Override
	public void addPassageway(Passageway passageway) {
		// TODO Auto-generated method stub
		passagewayDao.save(passageway);
		
	}
	//根据id模糊查询
	@Override
	public int selectPassagewayGroupByid(String id) {
		// TODO Auto-generated method stub
		return passagewayDao.selectPassagewayGroupByid(id);
	}
	

	}
