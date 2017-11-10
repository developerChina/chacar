package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;
import org.core.util.tag.PageModel;

public interface PJService {
	//查询权限表所有
	int selectPJG(String id);
	//查询所有分组
	List<PassagewayGroup> selectAll();
	//查询授权表
	List<Passagewayj> selectPJ(Passagewayj passagewayj, PageModel pageModel);
	//删除通道授权
	void removePassagewayjByID(String ids);
	//查自己
	Passagewayj selectPjByid(String id);
	//修改
	void updatePj(Passagewayj passagewayj);
	//添加
	void savePJ(Passagewayj passagewayj);
	//查询先来通道组
	PassagewayGroup selectPGbyId(String selectEGs);
	
	List<Employee> findEmployeeByIds(String ids);
	
	void savePJNew(String[] empids, String pjname, String pjgroup);
	
	Employee selectPjEmpbyId(String selectEmps);
	/**
	 * @param danpid 授权表里的通道id
	 * */
	Passageway selecPbypid(String Danpid);
	
	Employee selectempbyid(String myempid);

}
