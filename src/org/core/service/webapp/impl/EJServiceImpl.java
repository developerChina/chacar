package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.EJDao;
import org.core.dao.webapp.GroupDao;
import org.core.domain.webapp.ElevatorGroup;
import org.core.domain.webapp.Elevatorj;
import org.core.service.webapp.EJService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 电梯授权的服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("eJService")
public class EJServiceImpl implements EJService {
			
	@Autowired
	private EJDao eJDao;
	
	@Autowired
	private GroupDao groupDao;
	/**
	 * EJServiceImpl接口selectEmp方法实现
	 * 用来判断 是否已经授权的 查询
	 * @see { EJService }
	 * */
	@Override
	public int selectEmp(String id) {
		
		return eJDao.selectEmp(id);
	}
	/**
	 * EJServiceImpl接口findEGALL方法实现
	 * 无参 查询所有电梯分组
	 * @see { EJService }
	 * */

	@Override
	public List<ElevatorGroup> findEGALL() {
		// TODO Auto-generated method stub
		return eJDao.findEGALL();
	}
	
	/**
	 * EJServiceImpl接口saveEj方法实现
	 * 保存授权表
	 * @see { EJService }
	 * */
	@Override
	public void saveEj(Elevatorj elevatorj) {
		// TODO Auto-generated method stub
		String uuid=GenId.UUID();
		elevatorj.setEjid(uuid);
		eJDao.saveEj(elevatorj);
	}
	
	/**
	 * EJServiceImpl接口selectEJAll方法实现
	 * 首页查询 带分页的查电梯授权表
	 * @see { EJService }
	 * */
	@Override
	public List<Elevatorj> selectEJAll(PageModel pageModel, Elevatorj elevatorj) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<>();
		params.put("elevatorj", elevatorj);
		int recordCount =eJDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<Elevatorj> myElevatorjs = eJDao.selectByPage(params);
		return myElevatorjs;
	}
	/**
	 * EJServiceImpl接口selectEGbyId方法实现
	 * 首页查询  电梯授权的 电梯分组
	 * @see { EJService }
	 * */
	@Override
	public List<ElevatorGroup> selectEGbyId(String selectEGs) {
		// TODO Auto-generated method stub
		String[] idArry = selectEGs.split(",");
		List<ElevatorGroup> addList = new ArrayList<>();
		for (String id : idArry) {
			ElevatorGroup myEG=groupDao.selectbyId(id);
			addList.add(myEG);
		}
		return addList;
	}
	
	/**
	 * EJServiceImpl接口EjDelByEjid方法实现
	 * 首页查询  电梯授权的 删除
	 * @see { EJService }
	 * */
	@Override
	public void EjDelByEjid(String id) {
		
		eJDao.EjDelByEjid(id);
	}
	/**
	 * EJServiceImpl接口selectEjByid方法实现
	 * 首页  电梯授权的 修改前 查询
	 * @see { EJService }
	 * */
	@Override
	public Elevatorj selectEjByid(String id) {
		
		return eJDao.selectEjByid(id);
	}
	/**
	 * EJServiceImpl接口updateEj方法实现
	 * 首页  电梯授权的 修改
	 * @see { EJService }
	 * */
	@Override
	public void updateEj(Elevatorj elevatorj) {
		// TODO Auto-generated method stub
		eJDao.updateEj(elevatorj);
	}
	
	
	
}
