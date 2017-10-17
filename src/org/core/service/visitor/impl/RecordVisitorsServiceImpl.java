package org.core.service.visitor.impl;

import java.util.List;

import org.core.dao.visitor.RecordVisitorsDao;
import org.core.domain.visitor.RecordVisitors;
import org.core.service.visitor.RecordVisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("recordVisitorsService")
public class RecordVisitorsServiceImpl implements RecordVisitorsService{
	@Autowired
	private RecordVisitorsDao dao;
	@Override
	public void save(RecordVisitors entity) {
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(RecordVisitors entity) {
		dao.update(entity);
	}

	@Override
	public RecordVisitors selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<RecordVisitors> selectByPage(RecordVisitors entity) {
		return dao.selectByPage(entity);
	}
	 
}
