package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.ResonDao;
import org.core.domain.webapp.Reson;
import org.core.service.webapp.ResonService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("resonService")
public class ResonServiceImpl implements ResonService {
	@Autowired
	private ResonDao resonDao;
	@Transactional(readOnly=true)
	@Override
	public List<Reson> findReson(Reson reson, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("reson", reson);
		int recordCount = resonDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Reson> resons = resonDao.selectByPagegy(gy);
		 
		return resons;
	}

	@Override
	public void removeResonById(Integer id) {
		// TODO Auto-generated method stub
		resonDao.deleteByResonID(id);
	}

	@Override
	public void modifyReson(Reson reson) {
		// TODO Auto-generated method stub
		resonDao.update(reson);
	}

	@Override
	public Reson findResonById(Integer rid) {
		// TODO Auto-generated method stub
		return resonDao.selectByResonID(rid);
	}

	@Override
	public void addReson(Reson reson) {
		// TODO Auto-generated method stub
		resonDao.save(reson);
	}

	@Override
	public List<Reson> findAll() {
		return resonDao.findAll();
	}

}
