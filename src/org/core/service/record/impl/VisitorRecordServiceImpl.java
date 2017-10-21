package org.core.service.record.impl;

import java.util.Date;
import java.util.List;

import org.core.dao.visitor.VisitorRecordDao;
import org.core.domain.visitor.VisitorRecord;
import org.core.service.record.VisitorRecordService;
import org.core.util.GenId;
import org.core.util.StringUtils;
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
	public String save(VisitorRecord entity) {
		String uuid=GenId.UUID();
		entity.setRecordID(uuid);
		entity.setRecordTime(new Date());
		dao.save(entity);
		return uuid;
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

	@Override
	public String saveOrUpdate(VisitorRecord entity) {
		String uuid=null;		
		if(StringUtils.isNotBlank(entity.getRecordID())){
			 update(entity);
			 uuid=entity.getRecordID();
		}else{
			uuid=save(entity);
		}
		return uuid;
	}
	
}
