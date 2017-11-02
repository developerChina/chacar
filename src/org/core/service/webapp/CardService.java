package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.CardAccess;
import org.core.util.tag.PageModel;

/**   
 * @Description: 卡片授权服务层接口
 */
public interface CardService {
	/**
	 * 添加卡授权
	 */
	String save(CardAccess entity);
	
	/**
	 * 根据id删除卡授权
	 */
	void deleteById(int id);
	/**
	 * 修改卡授权
	 */
	void update(CardAccess entity);
	/**
	 * 根据id查询卡授权
	 */
	CardAccess selectById(int id);
	
	/**
	 * 查询卡授权(page信息为空不分页)
	 */
	List<CardAccess> selectByPage(CardAccess entity,PageModel pageModel);	
	
}
