package org.core.service.bevisited.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.bevisited.BevisitedInfoDao;
import org.core.dao.bevisited.DepartInfoDao;
import org.core.dao.webapp.DeptDao;
import org.core.dao.webapp.EmployeeDao;
import org.core.domain.bevisited.BevisitedInfo;
import org.core.domain.bevisited.DepartInfo;
import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Employee;
import org.core.service.bevisited.BevisitedInfoService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("bevisitedInfoService")
public class BevisitedInfoServiceImpl implements BevisitedInfoService{
	@Autowired
	private BevisitedInfoDao dao;
	@Autowired
	private DepartInfoDao departInfoDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public String save(BevisitedInfo entity) {
		String uuid=GenId.UUID();
		entity.setBevisitedID(uuid);
		dao.save(entity);
		return uuid;
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(BevisitedInfo entity) {
		dao.update(entity);
	}

	@Override
	public BevisitedInfo selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<BevisitedInfo> selectByPage(BevisitedInfo entity) {
		return dao.selectByPage(entity);
	}

	@Override
	public List<Map<String, Object>> getBevisitedTree_Old() {
		List<Map<String, Object>> list=new ArrayList<>();
		//追加组织
		List<DepartInfo> departInfos=departInfoDao.selectAll();
		Map<String, Object> map=null;
		for (DepartInfo departInfo : departInfos) {
			map=new HashMap<>();
			map.put("id", departInfo.getDeptID());
			map.put("name", departInfo.getDeptName());
			map.put("pid", departInfo.getSupDeptID());
			map.put("pname", departInfo.getSupDeptName());
			map.put("ischeck", false);
			list.add(map);
		}
		//追加被访人
		List<BevisitedInfo> bevisitedInfos=dao.selectAll();
		for (BevisitedInfo bevisitedInfo : bevisitedInfos) {
			map=new HashMap<>();
			map.put("id", bevisitedInfo.getBevisitedID());
			map.put("name", bevisitedInfo.getBevisitedName());
			map.put("pid", bevisitedInfo.getDeptID());
			map.put("pname", bevisitedInfo.getDeptName());
			map.put("ischeck", true);
			map.put("door", bevisitedInfo.getBevisitedDoor());
			map.put("floor", bevisitedInfo.getBevisitedFloor());
			map.put("room", bevisitedInfo.getBevisitedRoom());
			map.put("channel", bevisitedInfo.getBevisitedChannel());
			map.put("tel", bevisitedInfo.getBevisitedTel());
			if("0".equals(bevisitedInfo.getBevisitedStatus())){
				map.put("status", "正常");
			}else{
				map.put("status", "其他");
			}
			list.add(map);
		}
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> getBevisitedTree() {
		List<Map<String, Object>> list=new ArrayList<>();
		//追加组织
		List<Dept> depts=deptDao.selectAllDept();
		Map<String, Object> map=null;
		for (Dept dept : depts) {
			map=new HashMap<>();
			map.put("id", "d_"+dept.getId());
			map.put("name", dept.getName());
			map.put("pid", "d_"+dept.getPid());
			if(dept.getPid()!=null){
				Dept pdept=deptDao.selectById(dept.getPid());
				if(pdept!=null){
					map.put("pname", pdept.getName());
				}
			}
			map.put("open", true);
			map.put("nocheck", true);
			map.put("icon", "5.png");
			list.add(map);
		}
		//追加员工
		Map<String,Object> params = new HashMap<>();
		params.put("employee", new Employee());
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		pageModel.setRecordCount(Integer.MAX_VALUE);
		pageModel.setPageSize(Integer.MAX_VALUE);
		params.put("pageModel", pageModel);
		List<Employee> employees=employeeDao.selectByPage(params);
		for (Employee employee : employees) {
			map=new HashMap<>();
			map.put("id", "r_"+employee.getId());
			map.put("name", employee.getName());
			map.put("pid", "d_"+employee.getDept().getId());
			map.put("pname", employee.getDept().getName());
			map.put("icon", "3.png");
			map.put("tel", employee.getPhone());
			list.add(map);
		}
		return list;
	}

	@Override
	public BevisitedInfo selectOneByTel(String tel) {
		return dao.selectOneByTel(tel);
	}
	 
}
