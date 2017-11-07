package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.ElevatorGroup;
import org.core.domain.webapp.Elevatorj;
import org.core.util.tag.PageModel;

/**   
 * @Description: 电梯分组授权的接口
 * 
 */
public interface EJService {
	//用来判断 是否已经授权的 查询
	int selectEmp(String id);
	// 无参 查询所有电梯分组
	List<ElevatorGroup> findEGALL();
	//保存授权表
	void saveEj(Elevatorj elevatorj);
	//首页查询 带分页的查电梯授权表
	List<Elevatorj> selectEJAll(PageModel pageModel, Elevatorj elevatorj);
	
	
	//先来 电梯组
	List<ElevatorGroup> selectEGbyId(String selectEGs);
	
	//删除
	void EjDelByEjid(String id);
	//修改前查
	Elevatorj selectEjByid(String id);
	//修改
	void updateEj(Elevatorj elevatorj);
	
}
