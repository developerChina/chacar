package org.core.service.car.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.car.CarPassagewayDao;
import org.core.domain.car.CarPassageway;
import org.core.service.car.CarPassagewayService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 出入口服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("carPassagewayService")
public class CarPassagewayServiceImpl implements CarPassagewayService{
	@Autowired
	private CarPassagewayDao dao;
	@Override
	public String save(CarPassageway entity) {
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(CarPassageway entity) {
		dao.update(entity);
	}

	@Override
	public CarPassageway selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<CarPassageway> selectByPage(CarPassageway entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<CarPassageway> entitys = dao.selectByPage(params);
		return entitys;
	}

	@Override
	public List<CarPassageway> selectAll() {
		return dao.selectAll();
	}

	@Override
	public List<CarPassageway> selectByParkid(int parkid) {
		return dao.selectByParkid(parkid);
	}

}
