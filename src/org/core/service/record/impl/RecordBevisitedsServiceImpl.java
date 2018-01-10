package org.core.service.record.impl;

import java.util.Date;
import java.util.List;

import org.core.dao.visitor.RecordBevisitedsDao;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.webapp.Employee;
import org.core.service.record.RecordBevisitedsService;
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
@Service("recordBevisitedsService")
public class RecordBevisitedsServiceImpl implements RecordBevisitedsService{
	@Autowired
	private RecordBevisitedsDao dao;
	@Override
	public String save(RecordBevisiteds entity) {
		String uuid=GenId.UUID();
		entity.setRecordBVID(uuid);
		dao.save(entity);
		return uuid;
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
	@Override
	public RecordBevisiteds selectBevisitedByRecordId(String recordid) {
		return dao.selectBevisitedByRecordId(recordid);		
	}
	@Override
	public void deleteByRecordID(String recordid) {
		dao.deleteByRecordID(recordid);
	}
	@Override
	public List<Employee> selectBycardNo(String cardNo, Date optDate) {
		return dao.selectBycardNo(cardNo,optDate);
	}

	@Override
	public List<RecordBevisiteds> selectByEntity(RecordBevisiteds entity) {
		return dao.selectByEntity(entity);
	}
}
