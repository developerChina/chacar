package org.core.service.bevisited;

import java.util.List;

import org.core.domain.bevisited.DepartInfo;

/**
 * Service-》 被访人控制接口
 */
public interface DepartInfoService {
	/**
	 * 添加被访人
	 */
	void save(DepartInfo entity);
	
	/**
	 * 根据id删除被访人
	 */
	void deleteById(String id);
	/**
	 * 修改被访人
	 */
	void update(DepartInfo entity);
	/**
	 * 根据id查询被访人
	 */
	DepartInfo selectById(String id);
	
	/**
	 * 查询被访人(page信息为空不分页)
	 */
	List<DepartInfo> selectByPage(DepartInfo entity);
	
}
