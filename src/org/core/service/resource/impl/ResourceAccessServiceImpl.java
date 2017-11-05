package org.core.service.resource.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.resource.ResourceAccessDao;
import org.core.domain.resource.ResourceAccess;
import org.core.service.resource.ResourceAccessService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 资源授权服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("resourceAccessService")
public class ResourceAccessServiceImpl implements ResourceAccessService{
	@Autowired
	private ResourceAccessDao dao;
	@Override
	public String save(ResourceAccess entity) {
		entity.setCreatedate(new Date());
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(ResourceAccess entity) {
		dao.update(entity);
	}

	@Override
	public ResourceAccess selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<ResourceAccess> selectByPage(ResourceAccess entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<ResourceAccess> entitys = dao.selectByPage(params);
		return entitys;
	}

	@Override
	public List<ResourceAccess> selectByUserid(int userid) {
		return dao.selectByUserid(userid);
	}

	@Override
	public void deleteByUserid(int userid) {
		dao.deleteByUserid(userid);
	}
	
	@Override
	public List<ResourceAccess> selectByResourceid(int resourceid) {
		return dao.selectByResourceid(resourceid);
	}

	@Override
	public ResourceAccess selectByResource_User(int userid, int resourceid) {
		return dao.selectByResource_User(userid,resourceid);
	}

}
