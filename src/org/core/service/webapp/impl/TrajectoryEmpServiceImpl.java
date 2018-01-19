package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.TrajectoryEmpDao;
import org.core.domain.webapp.TrajectoryEmp;
import org.core.service.webapp.TrajectoryEmpService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("trajectoryEmpService")
public class TrajectoryEmpServiceImpl implements TrajectoryEmpService {
	@Autowired
	private TrajectoryEmpDao dao;

	@Override
	public List<TrajectoryEmp> selectTrajectory(TrajectoryEmp entity, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<TrajectoryEmp> entitys = dao.selectByPage(params);
		return entitys;
	}
	 

}
