package org.core.service.resource.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.resource.ResourceInfoDao;
import org.core.domain.resource.ResourceInfo;
import org.core.service.resource.ResourceInfoService;
import org.core.util.tag.PageModel;	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 资源服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("resourceInfoService")
public class ResourceInfoServiceImpl implements ResourceInfoService{
	@Autowired
	private ResourceInfoDao dao;
	@Override
	public String save(ResourceInfo entity) {
		entity.setCreatedate(new Date());
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(ResourceInfo entity) {
		dao.update(entity);
	}

	@Override
	public ResourceInfo selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<ResourceInfo> selectByPage(ResourceInfo entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<ResourceInfo> entitys = dao.selectByPage(params);
		return entitys;
	}

	@Override
	public List<ResourceInfo> selectAll() {
		return dao.selectAll();
	}

}
