package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.ElevatorGroup;
import org.core.util.tag.PageModel;

public interface GroupService {
	//查询方法
	List<ElevatorGroup> findElevatorGroup(ElevatorGroup elevatorGroup, PageModel pageModel);
	// 单个/批量删除 电梯组
	void EgroupDelByEGid(String id);
	//添加的电梯分组
		//先查询所有的‘下级单位’ 
	List<Elevator> selectEGSubordinate();
		//再添加
	void addEGroup(String ids, String egname);
	//查询组内的单个 物体  根据组内的IDS分开查
	List<Elevator> getElevatorByEGid(String selectids);
	//修改前查询
	ElevatorGroup selectEGbyId(String id);
	//修改
	void updateEG(ElevatorGroup elevatorGroup);
	

}
