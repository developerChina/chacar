package org.core.service.visitor.impl;

import java.util.List;

import org.core.dao.visitor.VisitorInfoDao;
import org.core.domain.visitor.VisitorInfo;
import org.core.service.visitor.VisitorInfoService;
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
@Service("visitorInfoService")
public class VisitorInfoServiceImpl implements VisitorInfoService{
	@Autowired
	private VisitorInfoDao dao;
	@Override
	public void save(VisitorInfo entity) {
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(VisitorInfo entity) {
		dao.update(entity);
	}

	@Override
	public VisitorInfo selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<VisitorInfo> selectByPage(VisitorInfo entity) {
		return dao.selectByPage(entity);
	}

	@Override
	public VisitorInfo selectOneBycardID(String cardID) {
		return dao.selectOneBycardID(cardID);
	}

	@Override
	public void saveOrUpdate(VisitorInfo entity) {
		if(StringUtils.isNotBlank(entity.getVisitorID())){
			dao.update(entity);
			return;
		}
		if(StringUtils.isNotBlank(entity.getCardID())){
			VisitorInfo exits=dao.selectOneBycardID(entity.getCardID());
			if(exits!=null){
				entity.setVisitorID(exits.getVisitorID());
				dao.update(entity);
			}else{
				dao.save(entity);
			}
		}
	}
	 
}
