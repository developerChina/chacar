package org.core.service.visitor;

import java.util.List;

import org.core.domain.visitor.VisitorInfo;
import org.core.util.tag.PageModel;


/**
 * Service-》 访客控制接口
 */
public interface VisitorInfoService {
	/**
	 * 添加访客
	 */
	String save(VisitorInfo entity);
	
	/**
	 * 根据id删除访客
	 */
	void deleteById(String id);
	/**
	 * 修改访客
	 */
	void update(VisitorInfo entity);
	/**
	 * 根据id查询访客
	 */
	VisitorInfo selectById(String id);
	
	/**
	 * 查询访客(page信息为空不分页)
	 */
	List<VisitorInfo> selectByPage(VisitorInfo entity,PageModel pageModel);
	/**
	 * 根据身份证号查找访客
	 * @param entity
	 * @return
	 */
	VisitorInfo selectOneBycardID(String cardID);
	/**
	 * 更新或保存访客
	 * @param entity
	 */
	String saveOrUpdate(VisitorInfo entity);
}
