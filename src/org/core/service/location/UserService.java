package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationClient;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationUser;
import org.core.util.tag.PageModel;
/**
 * @Description: 定位系统 用户服务层接口
 */
public interface UserService {
	/**
	 * @Description:分页查询 定位系统里的 用户
	 * @param pageModel 分页的条件
	 * @param locationUser  定位系统里的   用户对象  模糊查询条件  
	 * @return List<LocationUser> 定位系统里的 用户集合
	 * */
	List<LocationUser> selectAll(PageModel pageModel, LocationUser locationUser);
	/**
	 * @Description: 定位系统里 添加用户时得到的配件
	 * @param  void
	 * @return List<LocationGroup> 定位系统里的 分组集合
	 * */
	List<LocationGroup> getGroupParts();
	/**
	 * @Description: 定位系统里 添加用户时得到的配件
	 * @param  void
	 * @return List<LocationGroup> 定位系统里的 客户集合
	 * */
	List<LocationClient> getClientParts();
	/**
	 * @Description: 定位系统里 添加用户
	 * @param  LocationUser 用户的对象 前台传的值
	 * @return void
	 * */
	void addLocationUser(LocationUser locationUser,String[] Lgroupids);
	/**
	 * @Description: 定位系统里 删除用户
	 * @param  luser 用户的id 前台传的值
	 * @return void
	 * */
	void delLocationUser(String luser);
	/**
	 * @Description: 定位系统里 修改用户 查
	 * @param  luser 用户的id 前台传的值
	 * @return LocationUser 用户对象
	 * */
	LocationUser getUpdateUser(String luser);
	/**
	 * @Description: 定位系统里 修改用户 修
	 * @param  String[] Lgroupids, 分组的id数组 前台选的值
	 * @param  locationUser, user对象 用来接前台的值
	 * @return void
	 * */
	void updateUser(String[] Lgroupids, LocationUser locationUser);

	
	
	
}
