package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AccessDao;
import org.core.domain.webapp.Access;
import org.core.service.webapp.AccessService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("accessService")
public class AccessServiceImpl implements AccessService{
	@Autowired 
	private AccessDao accessDao;
	@Transactional(readOnly=true)
	@Override
	public List<Access> findAccess(Access access, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> sgy = new HashMap<>();
		sgy.put("access", access);
		int recordCount = accessDao.count(sgy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    sgy.put("pageModel", pageModel);
	    }
		List<Access> accesss = accessDao.selectByPagesgy(sgy);
		 
		return accesss;
	}
	//删除
	@Override
	public void removeAccessById(Integer id) {
		// TODO Auto-generated method stub
		accessDao.deleteBypassagewayID(id);
	}
	//更新
	@Override
	public void modifyAccess(Access access) {
		// TODO Auto-generated method stub
		accessDao.update(access);
	}
	//根据id查询
	@Transactional(readOnly=true)
	@Override
	public Access findAccessById(Integer accessid) {
		// TODO Auto-generated method stub
		return accessDao.selectByaccessid(accessid);
	}
	//添加
	@Override
	public void addAccess(Access access) {
		// TODO Auto-generated method stub
		accessDao.save(access);
	}
	//根据id模糊查询
	@Override
	public int selectAccessGroupByid(String id) {
		return accessDao.selectAccessGroupByid(id);
	}

}
