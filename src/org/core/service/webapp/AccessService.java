package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Access;
import org.core.util.tag.PageModel;

public interface AccessService {
	
	//获得所有通道，返回Access对象的集合
		List<Access> findAccess(Access access,PageModel pageModel);
	//删除
		void removeAccessById(Integer id);
	//修改
		void modifyAccess(Access access);
	//根据id查通道
		Access findAccessById(Integer accessid);
	//添加通道
		void addAccess(Access access);
	//根据id模糊查询
		int selectAccessGroupByid(String id);
	//添加时候的验证
		List<Access> getList(Access access);





}
