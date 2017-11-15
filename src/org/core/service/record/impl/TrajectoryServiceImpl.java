package org.core.service.record.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.visitor.TrajectoryDao;
import org.core.domain.visitor.Trajectory;
import org.core.service.record.TrajectoryService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("trajectoryService")
public class TrajectoryServiceImpl implements TrajectoryService{
	@Autowired
	private TrajectoryDao dao;
	
	@Override
	public List<Trajectory> selectByPage(Trajectory entity, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Trajectory> entitys = dao.selectByPage(params);
		return entitys;
	}

}
