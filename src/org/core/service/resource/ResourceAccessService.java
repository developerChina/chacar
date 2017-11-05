package org.core.service.resource;

import java.util.List;

import org.core.domain.resource.ResourceAccess;
import org.core.util.tag.PageModel;

/**   
 * @Description: 资源授权服务层接口
 */
public interface ResourceAccessService {
	/**
	 * 添加资源授权
	 */
	String save(ResourceAccess entity);
	
	/**
	 * 根据id删除资源授权
	 */
	void deleteById(int id);
	/**
	 * 修改资源授权
	 */
	void update(ResourceAccess entity);
	/**
	 * 根据id查询资源授权
	 */
	ResourceAccess selectById(int id);
	
	/**
	 * 查询资源授权(page信息为空不分页)
	 */
	List<ResourceAccess> selectByPage(ResourceAccess entity,PageModel pageModel);
	

	/**
	 * 查询用户资源授权
	 */
	List<ResourceAccess> selectByUserid(int userid);
	
	/**
	 * 删除用户资源授权
	 */
	void deleteByUserid(int userid);
	
	/**
	 * 查询资源授权的用户
	 */
	List<ResourceAccess> selectByResourceid(int resourceid);
	
	/**
	 * 查询资源授权(根据用户和资源)
	 */
	ResourceAccess selectByResource_User(int userid,int resourceid);
}
