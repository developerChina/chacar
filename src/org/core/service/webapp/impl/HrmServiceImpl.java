package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.DeptDao;
import org.core.dao.webapp.EmployeeDao;
import org.core.dao.webapp.JobDao;
import org.core.dao.webapp.UserDao;
import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.domain.webapp.User;
import org.core.service.webapp.HrmService;
import org.core.util.StringUtils;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 人事管理系统服务层接口实现类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DeptDao deptDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private JobDao jobDao;
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		List<User> users = userDao.selectByPage(params);
		 
		return users;
	}
	
	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
		
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
		
	}
	
	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addUser(User user) {
		userDao.save(user);
		
	}
	
	/*****************部门服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		
		return deptDao.selectAllDept();
	}
	
	/**
	 * HrmServiceImpl接口findDept方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("dept", dept);
		int recordCount = deptDao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Dept> depts = deptDao.selectByPage(params);
		return depts;
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeDeptById(Integer id) {
		deptDao.deleteById(id);
		
	}

	/**
	 * HrmServiceImpl接口addDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addDept(Dept dept) {
		deptDao.save(dept);
		
	}
	
	/**
	 * HrmServiceImpl接口findDeptById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Dept findDeptById(Integer id) {
		
		return deptDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口modifyDept方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDept(Dept dept) {
		deptDao.update(dept);
		
	}
	/*****************员工服务接口实现*************************************/
	/**
	 * HrmService接口findEmployee方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("employee", employee);
		
		int recordCount = employeeDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Employee> employees = employeeDao.selectByPage(params);
	    return employees;
	}
	/**
	 * HrmService接口removeEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeEmployeeById(Integer id) {
		employeeDao.deleteById(id);
		
	}
	/**
	 * HrmService接口findEmployeeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {
		
		return employeeDao.selectById(id);
	}
	
	/**
	 * HrmService接口addEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addEmployee(Employee employee) {
		employeeDao.save(employee);
		
	}
	
	/**
	 * HrmService接口modifyEmployee方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyEmployee(Employee employee) {
		employeeDao.update(employee);
	}
	
	/*****************职位接口实现*************************************/

	/**
	 * HrmService接口findAllJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		
		return jobDao.selectAllJob();
	}

	/**
	 * HrmService接口findJob方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("job", job);
		int recordCount = jobDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Job> jobs = jobDao.selectByPage(params);
		 
		return jobs;
	}
	
	/**
	 * HrmService接口removeJobById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeJobById(Integer id) {
		jobDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addJob(Job job) {
		jobDao.save(job);
		
	}
	
	/**
	 * HrmService接口findJobById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {
		
		return jobDao.selectById(id);
	}
	
	/**
	 * HrmService接口modifyJob方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyJob(Job job) {
		jobDao.update(job);
		
	}

	@Override
	public List<Map<String, Object>> getEmployeees(String name, String phone) {
		List<Map<String, Object>> listMap=new ArrayList<>();
		List<Employee> list=new ArrayList<>();
		
		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(phone)){
			list=employeeDao.getEmployeeesBy_name_phone(name,phone);
		}else{
			if(StringUtils.isNotBlank(name)){
				list=employeeDao.getEmployeeesBy_name(name);
			}
			if(StringUtils.isNotBlank(phone)){
				list=employeeDao.getEmployeeesBy_phone(phone);
			}
		}
				
		for (Employee employee : list) {
			Map<String, Object> map=new HashMap<>();
			map.put("id", employee.getId());
			map.put("dept", employee.getDept().getName());
			map.put("job", employee.getJob().getName());
			map.put("name", employee.getName());
			map.put("phone", hidePhonePart(employee.getPhone()));
			listMap.add(map);
		}
		return listMap;
	}

	private String hidePhonePart(String phone) {
		if(phone.length()>7){
			return phone.substring(0,3)+"****"+phone.substring(7);
		}else{
			return phone;
		}
	}

	@Override
	public void saveOrUpdateDept(Dept dept) {
		Dept exits=deptDao.selectByName(dept.getName());
		if(exits!=null){
			deptDao.update(dept);
		}else{
			deptDao.save(dept);
		}
	}

	@Override
	public void saveOrUpdateJob(Job job) {
		 Job exits=jobDao.selectByName(job.getName());
		 if(exits!=null){
			 jobDao.update(job);
		}else{
			jobDao.save(job);
		}
	}

	@Override
	public List<Employee> findEmployeeByCardNo_carstatus(String cardNo, int carstatus) {
		return employeeDao.findEmployeeByCardNo_carstatus(cardNo,carstatus);
	}

}
