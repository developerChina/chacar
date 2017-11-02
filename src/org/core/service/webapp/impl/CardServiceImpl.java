package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.CardDao;
import org.core.domain.webapp.CardAccess;
import org.core.service.webapp.CardService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 卡片授权服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("cardService")
public class CardServiceImpl implements CardService{
	@Autowired
	private CardDao dao;
	@Override
	public String save(CardAccess entity) {		
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(CardAccess entity) {
		dao.update(entity);
	}

	@Override
	public CardAccess selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<CardAccess> selectByPage(CardAccess entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<CardAccess> depts = dao.selectByPage(params);
		return depts;
	}

}
