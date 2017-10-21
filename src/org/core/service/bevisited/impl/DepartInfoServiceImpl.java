package org.core.service.bevisited.impl;

import java.util.List;

import org.core.dao.bevisited.DepartInfoDao;
import org.core.domain.bevisited.DepartInfo;
import org.core.service.bevisited.DepartInfoService;
import org.core.util.GenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("departInfoService")
public class DepartInfoServiceImpl implements DepartInfoService{
	@Autowired
	private DepartInfoDao dao;
	@Override
	public String save(DepartInfo entity) {
		String uuid=GenId.UUID();
		entity.setDeptID(uuid);
		dao.save(entity);
		return uuid;
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(DepartInfo entity) {
		dao.update(entity);
	}

	@Override
	public DepartInfo selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<DepartInfo> selectByPage(DepartInfo entity) {
		return dao.selectByPage(entity);
	}
	 
}
