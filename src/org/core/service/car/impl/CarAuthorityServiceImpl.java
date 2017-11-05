package org.core.service.car.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.car.CarAuthorityDao;
import org.core.dao.car.CarParkDao;
import org.core.dao.car.CarPassagewayDao;
import org.core.domain.car.CarAuthority;
import org.core.domain.car.CarPark;
import org.core.domain.car.CarPassageway;
import org.core.service.car.CarAuthorityService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 停车场服务层接口
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("carAuthorityService")
public class CarAuthorityServiceImpl implements CarAuthorityService{
	@Autowired
	private CarAuthorityDao dao;
	@Autowired
	private CarParkDao parkdao;
	@Autowired
	private CarPassagewayDao passagewaydao;
	@Override
	public String save(CarAuthority entity) {
		dao.save(entity);
		return "";
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public void update(CarAuthority entity) {
		dao.update(entity);
	}

	@Override
	public CarAuthority selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	public List<CarAuthority> selectByPage(CarAuthority entity,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<CarAuthority> entitys = dao.selectByPage(params);
		// 设置表外字段
		for (CarAuthority carAuthority : entitys) {
			int passageway_id= carAuthority.getPassageway_id();
			CarPassageway carPassageway=passagewaydao.selectById(passageway_id);
			carAuthority.setCarPassageway(carPassageway);//设置出入口
			if(carPassageway!=null){
				CarPark carPark=parkdao.selectById(carPassageway.getPark_id());
				carAuthority.setCarPark(carPark);//设置停车场
			}
		}
		return entitys;
	}

	@Override
	public List<CarAuthority> selectAll() {
		return dao.selectAll();
	}

	@Override
	public void saveOrUpdate(CarAuthority carAuthority) {
		String carno=carAuthority.getCarno();
		int passagewayid=carAuthority.getPassageway_id();
		CarAuthority exist=dao.selectByCarno_Passagewayid(carno,passagewayid);
		if(exist!=null){
			carAuthority.setId(exist.getId());
			dao.update(carAuthority);
		}else{
			dao.save(carAuthority);
		}
	}

}
