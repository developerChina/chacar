package org.core.service.resource;

import java.util.List;

import org.core.domain.resource.ResourceInfo;
import org.core.util.tag.PageModel;

/**   
 * @Description: 资源服务层接口
 */
public interface ResourceInfoService {
	/**
	 * 添加资源
	 */
	String save(ResourceInfo entity);
	
	/**
	 * 根据id删除资源
	 */
	void deleteById(int id);
	/**
	 * 修改资源
	 */
	void update(ResourceInfo entity);
	/**
	 * 根据id查询资源
	 */
	ResourceInfo selectById(int id);
	
	/**
	 * 查询资源(page信息为空不分页)
	 */
	List<ResourceInfo> selectByPage(ResourceInfo entity,PageModel pageModel);
	
	/**
	 * 查询全部资源
	 */
	List<ResourceInfo> selectAll();
}
