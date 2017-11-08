package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.GroupDao;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.ElevatorGroup;
import org.core.service.webapp.GroupService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("groupService")
public class GroupServiceImpl implements GroupService {
			/**
			 * 自动注入分组持久层Dao对象
			 * */
			@Autowired
			private GroupDao groupDao;
			/**
			 * GroupServiceImpl接口findElevatorGroup方法实现
			 * @see { GroupService }
			 * */
			@Override
			public List<ElevatorGroup> findElevatorGroup(ElevatorGroup elevatorGroup, PageModel pageModel) {
				// TODO Auto-generated method stub
				
				Map<String,Object> params = new HashMap<>();
				params.put("elevatorGroup", elevatorGroup);
				int recordCount = groupDao.count(params);
				pageModel.setRecordCount(recordCount);
				if(recordCount > 0){
			        /** 开始分页查询数据：查询第几页的数据 */
				    params.put("pageModel", pageModel);
			    }
				
				List<ElevatorGroup> elevatorGroups = groupDao.selectByPage(params);
				return elevatorGroups;
			}
			/**
			 * GroupServiceImpl接口EgroupDelByEGid方法实现
			 * @see { GroupService }
			 * */
			@Override
			public void EgroupDelByEGid(String id) {
				// TODO Auto-generated method stub
				groupDao.EgroupDelByEGid(id);
			}
			
			
			/**
			 * GroupServiceImpl接口selectEGSubordinate方法实现
			 * @see { GroupService }
			 * */
			@Override
			public List<Elevator> selectEGSubordinate() {
				
				return groupDao.selectEGSubordinate();
			}
			/**
			 * GroupServiceImpl接口addEGroup方法实现
			 * @see { GroupService }
			 * */
			@Override
			public void addEGroup(String ids, String egname) {
				// TODO Auto-generated method stub
					String uuid=GenId.UUID();
					groupDao.addEtoEG(ids,egname,uuid);
				}
			
			
			/**
			 * GroupServiceImpl接口getElevatorByEGid方法实现
			 * @see { GroupService }
			 * */
			@Override
			public List<Elevator> getElevatorByEGid(String selectids) {
				// TODO Auto-generated method stub
				String[] idArry = selectids.split(",");
				List<Elevator> addList = new ArrayList<>();
				for (String id : idArry) {
					  Elevator addElevator =groupDao.getElevatorByEGid(Integer.parseInt(id));
					  addList.add(addElevator);
				}
				return addList;
			}
			/**
			 * GroupServiceImpl接口selectEGbyId方法实现
			 * @see { GroupService }
			 * 修改前查询一遍
			 * */
			@Override
			public ElevatorGroup selectEGbyId(String id) {
				// TODO Auto-generated method stub
				return groupDao.selectbyId(id);
			}
			/**
			 * GroupServiceImpl接口updateEG方法实现
			 * @see { GroupService }
			 * 修改
			 * */
			@Override
			public void updateEG(ElevatorGroup elevatorGroup) {
				// TODO Auto-generated method stub
				groupDao.updateEG(elevatorGroup);
			}
		
				
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
}
