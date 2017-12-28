package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.ElevatorDao;
import org.core.domain.webapp.Elevator;
import org.core.service.webapp.ElevatorService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("elevatorService")
public class ElevatorServiceImpl implements ElevatorService {

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private ElevatorDao elevatorDao;
	
	/*****************电梯接口实现*************************************/
	
	/**
	 * ElevatorServiceImpl接口findElevator方法实现
	 * @see { ElevatorService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Elevator> findElevator(Elevator elevator, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("elevator", elevator);
		int recordCount = elevatorDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Elevator> elevators = elevatorDao.selectByPage(params);
		return elevators;
	}

	/**
	 * ElevatorServiceImpl接口removeUserById方法实现
	 * @see { ElevatorService }
	 * */
	@Override
	public void removeElevatorByID(Integer id) {
		// TODO Auto-generated method stub
		elevatorDao.deleteById(id);
	}

	/**
	 * ElevatorServiceImpl接口addElevator方法实现
	 * @see { ElevatorService }
	 * */
	@Override
	public void addElevator(Elevator elevator) {
		// TODO Auto-generated method stub
		elevatorDao.save(elevator);
	}

	@Transactional(readOnly=true)
	@Override
	public Elevator findElevatorById(Integer elevatorID) {
		// TODO Auto-generated method stub
		return elevatorDao.selectById(elevatorID);
	}

	@Override
	public void modifyElevator(Elevator elevator) {
		// TODO Auto-generated method stub
		elevatorDao.update(elevator);
	}

	@Override
	public int selectEGisE(String id) {
		
		return elevatorDao.selectEGisE(id);
	}

	@Override
	public List<Elevator> selectByIds(String ids) {
		return elevatorDao.selectByIds(ids);
	}

	@Override
	public List<Elevator> selectBySN(String sn) {
		return elevatorDao.selectBySN(sn);
	}

	@Override
	public List<Elevator> selectAll() {
		return elevatorDao.selectAll();
	}
	
}
