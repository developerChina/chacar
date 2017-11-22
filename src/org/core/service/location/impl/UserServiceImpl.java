package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.LGroupDao;
import org.core.dao.location.LUserDao;
import org.core.domain.location.LocationClient;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationUser;
import org.core.domain.location.LocationUserGroup;
import org.core.service.location.UserService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 定位系统 用户服务层接口实现类
 * @see (LGroupService)
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private LUserDao luserDao;
	@Autowired
	private LGroupDao lGroupDao;
	
	//查询
	@Override
	public List<LocationUser> selectAll(PageModel pageModel, LocationUser locationUser) {
		Map<String,Object> params = new HashMap<>();
		params.put("locationUser", locationUser);
		int recordCount = luserDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<LocationUser> locationUsers = luserDao.selectByPage(params);
		return locationUsers;
	}
	//添加时得到的配件 1分组的集合 2客户的集合
	@Override
	public List<LocationGroup> getGroupParts() {
		
		return luserDao.getGroupParts();
	}
	@Override
	public List<LocationClient> getClientParts() {

		return luserDao.getClientParts();
	}
	//添加 用户
	@Override
	public void addLocationUser(LocationUser locationUser, String[] Lgroupids) {
		//先保存用户与分组 的中间表中 
			//保证2张表里用户id用一个uuid
		String uuid = GenId.UUID();
		int UservhcCount= 0;
		int UseruserCount= 0;
		for (String myGroupid : Lgroupids) {
			//执行添加
			luserDao.addUserAndGroup(myGroupid,uuid);
			//根据id得分组对象 保存用户总车辆  == 用户的总用户数目
			LocationGroup addUser = lGroupDao.getUpdate(Integer.parseInt(myGroupid));
			UservhcCount  += addUser.getVhcCount();
			UseruserCount += addUser.getUserCount();
		}
		locationUser.setVhcCount(UservhcCount);
		locationUser.setUserCount(UseruserCount);
		locationUser.setId(uuid);
		//分组数量
		int ids = Lgroupids.length;
		locationUser.setGroupCount(ids);
		//执行添加
		luserDao.addLuser(locationUser);
	}
	//删除
	@Override
	public void delLocationUser(String luser) {

		//删除用户表里的相关数据
		luserDao.removeUser(luser);
		//删除用户分组表里的相关数据
		luserDao.removeUserGroup(luser);
	}
	//修改前查一遍
	@Override
	public LocationUser getUpdateUser(String luser) {
		//查用户表
		LocationUser getUpdateU =luserDao.getUpdateUser(luser);
		//查用户分组 中间表
		List<LocationUserGroup> getUpdateUG =luserDao.getUpdateUserGroup(luser);
		for (LocationUserGroup locationUserGroup : getUpdateUG) {
			//查分组表
			LocationGroup getUpdatG =	luserDao.grtGroup(locationUserGroup.getGroupid());
			//存对应的分组到用户对象里
			getUpdateU.getUserGroups().add(getUpdatG);
		}
		//查询客户表
		LocationClient getUpdateC=luserDao.getUpateUser(getUpdateU.getClientID());
		getUpdateU.setUserClient(getUpdateC);
		
		return getUpdateU;
	}
	//修改
	@Override
	public void updateUser(String[] Lgroupids, LocationUser locationUser) {
		//先修改中间表
			//执行删除
		luserDao.removeUserGroup(locationUser.getId());
		int UservhcCount= 0;
		int UseruserCount= 0;
		for (String myGroupid : Lgroupids) {
			//执行添加
			luserDao.addUserAndGroup(myGroupid,locationUser.getId());
			//根据id得分组对象 保存用户总车辆数目 == 用户的总用户数目
			LocationGroup addUser = lGroupDao.getUpdate(Integer.parseInt(myGroupid));
			UservhcCount  += addUser.getVhcCount();
			UseruserCount += addUser.getUserCount();
		}
		
		locationUser.setVhcCount(UservhcCount);
		locationUser.setUserCount(UseruserCount);
		//分组数量
		int ids = Lgroupids.length;
		locationUser.setGroupCount(ids);
		//执行保存
		luserDao.updateUser(locationUser);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

