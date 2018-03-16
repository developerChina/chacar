package org.core.service.webapp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.webapp.EmployeeDao;
import org.core.dao.webapp.TrajectoryEmpDao;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.TrajectoryEmp;
import org.core.service.webapp.TrajectoryEmpService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("trajectoryEmpService")
public class TrajectoryEmpServiceImpl implements TrajectoryEmpService {
	@Autowired
	private TrajectoryEmpDao dao;
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public List<TrajectoryEmp> selectTrajectory(TrajectoryEmp entity, PageModel pageModel) {
		
		//Osnd按部门查
		String vague = entity.getTrajectoryDept();
		if(vague!=null && !"".equals(vague)){
			String nos = "";
			 List<Employee> vagueE =dao.vagueDept(vague);
			if(vagueE!=null&&vagueE.size()>0){
				for (Employee Single:vagueE) {
					nos+=Single.getCardno()+",";
				}
				//System.out.println(nos); 
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println("查出东西没有1"+nos);
				entity.setTrajectoryDept(nos);
			}else{
				//System.out.println("查出东西没有2"+nos);
				entity.setTrajectoryDept("");
			}
		}
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<TrajectoryEmp> entitys = dao.selectByPage(params);
		for (TrajectoryEmp trajectoryEmp : entitys) {
			List<Employee> emps=employeeDao.getEmployeeesBy_cardno(trajectoryEmp.getCardno());
			if(emps.size()==0){
				Employee e=new Employee();
				e.setName("cardno="+trajectoryEmp.getCardno()+"不存在");
				trajectoryEmp.setEmployees(e);	
			}else{
				Employee e=emps.get(0);
				for (int i=1;i<emps.size();i++) {
					e.setName(e.getName()+","+emps.get(i).getName());
				}
				trajectoryEmp.setEmployees(e);
			}
		}
		return entitys;
	}

	@Override
	public void deleteTrajectory(String id) {
		dao.deleteTrajectory(id);
	}
	 

}
