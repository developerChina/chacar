package org.core.service.visitor.impl;

import java.util.List;

import org.core.dao.visitor.VisitorRecordDao;
import org.core.domain.visitor.VisitorRecord;
import org.core.service.visitor.VisitorRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("visitorRecordService")
public class VisitorRecordServiceImpl implements VisitorRecordService{
	@Autowired
	private VisitorRecordDao dao;
	@Override
	public void save(VisitorRecord entity) {
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(VisitorRecord entity) {
		dao.update(entity);
	}

	@Override
	public VisitorRecord selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<VisitorRecord> selectByPage(VisitorRecord entity) {
		return dao.selectByPage(entity);
	}
	 
}
