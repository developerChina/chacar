package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarInfo;
import org.core.util.tag.PageModel;

/**   
 * @Description: 车辆服务层接口
 */
public interface CarInfoService {
	/**
	 * 添加车辆
	 */
	String save(CarInfo entity);
	
	/**
	 * 根据id删除车辆
	 */
	void deleteById(int id);
	/**
	 * 修改车辆
	 */
	void update(CarInfo entity);
	/**
	 * 根据id查询车辆
	 */
	CarInfo selectById(int id);
	
	/**
	 * 查询车辆(page信息为空不分页)
	 */
	List<CarInfo> selectByPage(CarInfo entity,PageModel pageModel);
	
	/**
	 * 查询全部车辆
	 */
	List<CarInfo> selectAll();
	/**
	 * 保存或者修改
	 */
	void saveOrUpdateDept(CarInfo car);

	
	
	/**
	 * 只执行保存，通过ajax校验车牌号是否重复
	 */
	void saveCar(CarInfo carInfo);
	/**
	 * 只执行修改，车牌号在jsp控制不可修改
	 */
	void updateCar(CarInfo carInfo);
	/**
	 * 添加时ajax校验车牌号是否重复
	 */
	String addValidate(String carno);
}
