package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.util.tag.PageModel;

public interface AccessGroupService {
	/**
	 * 门禁分组
	 * */
	//获得所有AccessGroup分组，返回对象集合
	List<AccessGroup> findAccessGroup(AccessGroup accessGroup, PageModel pageModel);
	//删除
	void removeAccessGroupById(String id);
	//查询所有的门禁
	List<Access> selectAGSubordinate();
	//添加门禁分组
	void addAGroup(String ids, String agname);
	//根据id查找下级
	List<Access> getAccessById(String selectids);
	//修改前查询一遍
	AccessGroup selectAGbyId(String id);
	//修改
	void updateAG(AccessGroup accessGroup);
	
}
