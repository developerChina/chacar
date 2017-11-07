package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.util.tag.PageModel;

public interface AJService {
	//查询所有权限表
	int selectAJG(String id);
	//查询所有门禁分组
	List<AccessGroup> findAGAll();
	//查询门禁授权表
	List<Accessj> selectAJ(Accessj accessj, PageModel pageModel);
	//删除门禁授权
	void removeAccessjByID(String id);
	//添加授权
	void saveAJ(Accessj accessj);
	//查自己
	Accessj selectAjByid(String id);
	//修改
	void updateAj(Accessj accessj);
	//查询里的先来门禁组
	List<AccessGroup> selectPGbyId(String selectEGs);
	
}
