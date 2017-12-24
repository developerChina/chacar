package org.core.service.webapp;

import java.util.List;
import java.util.Map;

import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.domain.webapp.User;
import org.core.util.tag.PageModel;

/**   
 * @Description: 人事管理服务层接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public interface HrmService {


	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname,String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param User 用户对象
	 * */
	void addUser(User user);
	
	/**
	 * 获得所有员工
	 * @param employee 查询条件
	 * @param pageModel 分页对象
	 * @return Dept对象的List集合
	 * */
	List<Employee> findEmployee(Employee employee,PageModel pageModel);
	
	/**
	 * 根据id删除员工
	 * @param id
	 * */
	void removeEmployeeById(Integer id);
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return 员工对象
	 * */
	Employee findEmployeeById(Integer id);
	
	/**
	 * 添加员工
	 * @param employee 员工对象
	 * */
	void addEmployee(Employee employee);
	
	/**
	 * 修改员工
	 * @param employee 员工对象
	 * */
	void modifyEmployee(Employee employee);
	
	/**
	 * 获得所有部门，分页查询
	 * @return Dept对象的List集合
	 * */
	List<Dept> findDept(Dept dept,PageModel pageModel);
	
	/**
	 * 获得所有部门
	 * @return Dept对象的List集合
	 * */
	List<Dept> findAllDept();
	
	/**
	 * 根据id删除部门
	 * @param id
	 * */
	public void removeDeptById(Integer id);
	
	/**
	 * 添加部门
	 * @param dept 部门对象
	 * */
	void addDept(Dept dept);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return 部门对象
	 * */
	Dept findDeptById(Integer id);
	
	/**
	 * 修改部门
	 * @param dept 部门对象
	 * */
	void modifyDept(Dept dept);
	
	/**
	 * 获得所有职位
	 * @return Job对象的List集合
	 * */
	List<Job> findAllJob();
	
	List<Job> findJob(Job job,PageModel pageModel);
	
	/**
	 * 根据id删除职位
	 * @param id
	 * */
	public void removeJobById(Integer id);
	
	/**
	 * 添加职位
	 * @param Job 部门对象
	 * */
	void addJob(Job job);
	
	/**
	 * 根据id查询职位
	 * @param id
	 * @return 职位对象
	 * */
	Job findJobById(Integer id);
	
	/**
	 * 修改职位
	 * @param dept 部门对象
	 * */
	void modifyJob(Job job);
	
	
	/**
	 * 根据用户名或电话查找员工
	 * @param name
	 * @param telphone
	 * @return
	 */
	List<Map<String, Object>> getEmployeees(String name, String phone);

	/**
	 * 修改或保存
	 * @param dept
	 */
	void saveOrUpdateDept(Dept dept);

	/**
	 * 修改或保存
	 * @param job
	 */
	void saveOrUpdateJob(Job job);

	/**
	 * 查詢物理卡員工
	 */
	List<Employee> findEmployeeByCardNo_carstatus(String cardNo, int carstatus);
	
}
