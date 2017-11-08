package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Elevator;
import org.core.util.tag.PageModel;

public interface ElevatorService {

	/**
	 * 获得所有电梯信息
	 * 分页对象 PageModel
	 * 查出的Elevator对象的集合List<Elevator>
	 * */
	List<Elevator> findElevator(Elevator elevator, PageModel pageModel);
	/**
	 * 根据id删除电梯
	 * @param id
	 * */
	void removeElevatorByID(Integer id);
	
	void addElevator(Elevator elevator);
	
	
	
	Elevator findElevatorById(Integer elevatorID);
	
	void modifyElevator(Elevator elevator);
	
	
	int selectEGisE(String id);
	
	

}
