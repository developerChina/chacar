package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarAuthority;
import org.core.util.tag.PageModel;

/**   
 * @Description: 车辆授权服务层接口
 */
public interface CarAuthorityService {
	/**
	 * 添加车辆授权
	 */
	String save(CarAuthority entity);
	
	/**
	 * 根据id删除车辆授权
	 */
	void deleteById(int id);
	/**
	 * 修改车辆授权
	 */
	void update(CarAuthority entity);
	/**
	 * 根据id查询车辆授权
	 */
	CarAuthority selectById(int id);
	
	/**
	 * 查询车辆授权(page信息为空不分页)
	 */
	List<CarAuthority> selectByPage(CarAuthority entity,PageModel pageModel);
	
	/**
	 * 查询全部车辆授权
	 */
	List<CarAuthority> selectAll();
	/**
	 * 根据车牌号和出入口id判断保存或修改
	 * @param carAuthority
	 */
	void saveOrUpdate(CarAuthority carAuthority);
}
