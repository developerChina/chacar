package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarPassageway;
import org.core.util.tag.PageModel;

/**   
 * @Description: 出入口服务层接口
 */
public interface CarPassagewayService {
	/**
	 * 添加出入口
	 */
	String save(CarPassageway entity);
	
	/**
	 * 根据id删除出入口
	 */
	void deleteById(int id);
	/**
	 * 修改出入口
	 */
	void update(CarPassageway entity);
	/**
	 * 根据id查询出入口
	 */
	CarPassageway selectById(int id);
	
	/**
	 * 查询出入口(page信息为空不分页)
	 */
	List<CarPassageway> selectByPage(CarPassageway entity,PageModel pageModel);
	
	/**
	 * 查询全部出入口
	 */
	List<CarPassageway> selectAll();
	
	/**
	 * 查找停车场所有的出入口
	 */
	List<CarPassageway> selectByParkid(int parkid);
	
}
