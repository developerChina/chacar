package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarPark;
import org.core.util.tag.PageModel;

/**   
 * @Description: 停车场服务层接口
 */
public interface CarParkService {
	/**
	 * 添加停车场
	 */
	String save(CarPark entity);
	
	/**
	 * 根据id删除停车场
	 */
	void deleteById(int id);
	/**
	 * 修改停车场
	 */
	void update(CarPark entity);
	/**
	 * 根据id查询停车场
	 */
	CarPark selectById(int id);
	
	/**
	 * 查询停车场(page信息为空不分页)
	 */
	List<CarPark> selectByPage(CarPark entity,PageModel pageModel);
	
	/**
	 * 查询全部停车场
	 */
	List<CarPark> selectAll();
}
