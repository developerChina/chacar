package org.core.service.temporary.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.temporary.TemporaryDao;
import org.core.domain.temporary.TemporaryInfo;
import org.core.service.temporary.HandleService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("handleService")
public class HandleServiceImpl implements HandleService{

	@Autowired
	private TemporaryDao temporaryDao;

	@Override
	public List<TemporaryInfo> selectByPage(TemporaryInfo entity, PageModel pageModel, Date startDate, Date endDate) {

		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int recordCount = temporaryDao.countHandle(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<TemporaryInfo> entitys = temporaryDao.selectByPageHandle(params);
		return entitys;
		
	}

	//执行添加
	@Override
	public void save(TemporaryInfo entity) {
		//自动设置日期
		Date date = new Date();
		entity.setCominDate(date);
		//自动设置为状态2 发放状态
		entity.setType(2);
		temporaryDao.save(entity);
	}
	//执行修改
	@Override
	public void update(TemporaryInfo entity) {
		temporaryDao.update(entity);
	}
	//根据id获取单个实体
	@Override
	public TemporaryInfo getEntityById(int id) {
		TemporaryInfo entityAgain = temporaryDao.getEntityById(id);
		return entityAgain;
	}

	@Override
	public void deleteById(String ids) {
		String[] idArray = ids.split(",");
		for(String id : idArray){
			temporaryDao.deleteById(Integer.parseInt(id));
		}
		
	}

	@Override
	public void confirmById(Integer id) {
		TemporaryInfo entityAgain = temporaryDao.getEntityById(id);
		//自动设置日期
		Date date = new Date();
		entityAgain.setOutDate(date);
		//自动设置为状态1 回收状态
		entityAgain.setType(1);
		//执行修改
		temporaryDao.update(entityAgain);
	}
	
}
