package org.core.service.bevisited;

import java.util.List;
import java.util.Map;

import org.core.domain.bevisited.BevisitedInfo;

/**
 * Service-》 被访人控制接口
 */
public interface BevisitedInfoService {
	/**
	 * 添加被访人
	 */
	void save(BevisitedInfo entity);
	
	/**
	 * 根据id删除被访人
	 */
	void deleteById(String id);
	/**
	 * 修改被访人
	 */
	void update(BevisitedInfo entity);
	/**
	 * 根据id查询被访人
	 */
	BevisitedInfo selectById(String id);
	
	/**
	 * 查询被访人(page信息为空不分页)
	 */
	List<BevisitedInfo> selectByPage(BevisitedInfo entity);

	/**
	 * 获取被访人和部门树关系
	 * @return
	 */
	List<Map<String, Object>> getBevisitedTree();
	
}
