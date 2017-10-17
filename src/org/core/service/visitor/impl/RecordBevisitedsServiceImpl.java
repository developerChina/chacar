package org.core.service.visitor.impl;

import java.util.List;

import org.core.dao.visitor.RecordBevisitedsDao;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.service.visitor.RecordBevisitedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("RecordBevisitedsService")
public class RecordBevisitedsServiceImpl implements RecordBevisitedsService{
	@Autowired
	private RecordBevisitedsDao dao;
	@Override
	public void save(RecordBevisiteds entity) {
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(RecordBevisiteds entity) {
		dao.update(entity);
	}

	@Override
	public RecordBevisiteds selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<RecordBevisiteds> selectByPage(RecordBevisiteds entity) {
		return dao.selectByPage(entity);
	}
	 
}
