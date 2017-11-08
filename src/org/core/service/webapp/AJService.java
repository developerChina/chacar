package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.Employee;
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
	
	/**
	 * 根据ids查询用户
	 * @param ids
	 * @return 用户对象的集合
	 * */
	List<Employee> findEmployeeByIds(String ids);
	/**
	 * 根据id 授权
	 * @param id ajname  ajgroup(分组的id 多个)
	 * @return 用户对象的集合
	 * */
	void saveAJNew(String[] myempid, String ajname, String ajgroup);
	/**
	 * 根据id 查employee
	 * @param id  员工id
	 * @return 员工的对象
	 * */
	Employee selectAjEmpbyId(String selectEmps);
	/**
	 * 修改
	 * 根据empid 查门禁授权表
	 * @param id  员工id
	 * @return 门禁授权的集合
	 * */
	List<Accessj> selectAjByEmpid(String id);
	/**
	 * 修改
	 * 根据empid 查员工表
	 * @param id  代表员工id
	 * @return 员工的对象
	 * */
	Employee selectEmployee(String id);
	
	
	
}
