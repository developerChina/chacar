package org.core.service.car.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.car.CarDistinguishDao;
import org.core.domain.car.CarDistinguish;
import org.core.service.car.CarDistinguishService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 识别仪服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("carDistinguishService")
public class CarDistinguishServiceImpl implements CarDistinguishService{
	@Autowired
	private CarDistinguishDao dao;
	@Override
	public String save(CarDistinguish entity) {
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(CarDistinguish entity) {
		dao.update(entity);
	}

	@Override
	public CarDistinguish selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<CarDistinguish> selectByPage(CarDistinguish entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<CarDistinguish> entitys = dao.selectByPage(params);
		return entitys;
	}

	@Override
	public List<CarDistinguish> selectAll() {
		return dao.selectAll();
	}

	@Override
	public List<CarDistinguish> selectByIds(String ids) {
		return dao.selectByIds(ids);
	}
	
	@Override
	public List<CarDistinguish> selectFillterIds(String ids) {
		return dao.selectFillterIds(ids);
	}

}
