package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationGroup;
import org.core.util.tag.PageModel;
/**
 * @Description: 定位系统 分组服务层接口
 */
public interface LGroupService {
	/**
	 * @Description:分页查询 定位系统里的 分组
	 * @param pageModel 分页的条件
	 * @param locationGroup  定位系统里的 分组对象  模糊查询条件  
	 * @return List<LocationGroup> 定位系统里的 分组集合
	 * */
	List<LocationGroup> selectAll(PageModel pageModel, LocationGroup locationGroup);
	/**
	 * @Description:添加分组
	 * @param locationGroup  定位系统里的对象 保存对象  
	 * @return void
	 * */
	void addLocationGroup(LocationGroup locationGroup);
	/**
	 * @Description: 删除分组
	 * @param Lgroup 分组的id  
	 * @return void
	 * */
	void delLocationGroup(int Lgroup);
	/**
	 * @Description: 修改分组 先查单个对象
	 * @param Lgroup 分组的id  
	 * @return LocationGroup 分组对象
	 * */
	LocationGroup getUpdate(int lgroup);
	/**
	 * @Description: 修改分组 修改对象
	 * @param 	locationGroup 分组的对象  
	 * @return  void
	 * */
	void updateLgroup(LocationGroup locationGroup);

}
