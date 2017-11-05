package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarDistinguish;
import org.core.util.tag.PageModel;

/**   
 * @Description: 识别仪服务层接口
 */
public interface CarDistinguishService {
	/**
	 * 添加识别仪
	 */
	String save(CarDistinguish entity);
	
	/**
	 * 根据id删除识别仪
	 */
	void deleteById(int id);
	/**
	 * 修改识别仪
	 */
	void update(CarDistinguish entity);
	/**
	 * 根据id查询识别仪
	 */
	CarDistinguish selectById(int id);
	
	/**
	 * 查询识别仪(page信息为空不分页)
	 */
	List<CarDistinguish> selectByPage(CarDistinguish entity,PageModel pageModel);
	
	/**
	 * 查询全部识别仪
	 */
	List<CarDistinguish> selectAll();
	/**
	 * 根据ids查询
	 */
	List<CarDistinguish> selectByIds(String ids);
	/**
	 * 过滤已被使用的识别仪
	 */
	List<CarDistinguish> selectFillterIds(String ids);
}
