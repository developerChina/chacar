package org.core.service.webapp.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AJDao;
import org.core.dao.webapp.AccessGroupDao;
import org.core.dao.webapp.ElevatorDao;
import org.core.dao.webapp.EmployeeDao;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Employee;
import org.core.service.webapp.AJService;
import org.core.util.AControlUtil;
import org.core.util.GenId;
import org.core.util.LadderControlUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("aJService")
public class AJServiceImpl implements AJService {
	@Autowired
	private AJDao aJDao;
	@Autowired
	ElevatorDao elevatorDao;
	@Autowired
	private EmployeeDao employeeDao;
	//查询所有权限表
	@Autowired
	private AccessGroupDao accessGroupDao;
	@Transactional(readOnly=true)
	@Override
	public int selectAJG(String id) {
		// TODO Auto-generated method stub
		return aJDao.selectAJG(id);
	}
	//查询所有门禁分组
	@Transactional(readOnly=true)
	@Override
	public List<AccessGroup> findAGAll() {
		// TODO Auto-generated method stub
		return aJDao.findAGAll();
	}
	//查询授权并分页
	@Override
	public List<Accessj> selectAJ(Accessj accessj, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("accessj", accessj);
		int recordCount = aJDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Accessj> accessjs = aJDao.selectByPagegy(gy);
		return accessjs;
	}
	@Override
	public void removeAccessjByID(String  id) {
		// TODO Auto-generated method stub
		aJDao.removeAccessjByID(id);
	}
	@Override
	public void saveAJ(Accessj accessj) {
		// TODO Auto-generated method stub
		//uuid
		String uuid=GenId.UUID();
		accessj.setAjid(uuid);
		aJDao.saveAJ(accessj);
		
	}
	@Override
	public Accessj selectAjByid(String id) {
		// TODO Auto-generated method stub
		return aJDao.selectAjByid(id);
	}
	//修改
	@Override
	public void updateAj(Accessj accessj) {
		// TODO Auto-generated method stub
		aJDao.updateAj(accessj);
	}
	//查询里的先来门禁组
	@Override
	public List<AccessGroup> selectPGbyId(String selectEGs) {
		// TODO Auto-generated method stub
		String[] idArry = selectEGs.split(",");
		List<AccessGroup> addList = new ArrayList<>();
		for (String id : idArry) {
			AccessGroup myAG=accessGroupDao.selectAGbyId(id);
			addList.add(myAG);
		}
		return addList;
	}
	
	
	
	//查询员工集合 根据IDS 
	@Override
	public List<Employee> findEmployeeByIds(String ids) {
		// TODO Auto-generated method stub
		String[] idArry = ids.split(",");
		List<Employee> seList = new ArrayList<Employee>();
		for (String id : idArry) {
			Employee myAEmp=employeeDao.selectById(Integer.parseInt(id));
			seList.add(myAEmp);
		}
		return seList;
	}
	
	
	@Override
	public void saveAJNew(String[] empids, String ajname,String ajgroup) {
		String[] idArry = ajgroup.split(",");
		List<Elevator> elevators=elevatorDao.selectByPage(new HashMap<String, Object>());//所有电梯
		for (int i=0; i<empids.length; i++) { 
			Employee myAEmp=employeeDao.selectById(Integer.parseInt(empids[i]));//某个人
			for (String groupid : idArry) {
				Accessj accessj = new Accessj();
				accessj.setAjgroupid(groupid);
				accessj.setAjempid(empids[i]);
				accessj.setAjname(ajname);
				String uuid=GenId.UUID();
				accessj.setAjid(uuid);
				aJDao.saveAJ(accessj);
				/**
				 * 门禁下发权限
				 */
				String lay[]={"0","0","0","0","0","0","0","0",
					      "0","0","0","0","0","0","0","0",
					      "0","0","0","0","0","0","0","0",
					      "0","0","0","0","0","0","0","0",
					      "0","0","0","0","0","0","0","0"};
				
				List<Access> la=aJDao.getAccess(groupid);
				for(Access ac:la) {
					int authority[] = {0, 0, 0, 0};
					authority[(ac.getAcno()-1)]= 1;
					lay[(ac.getFloorno()-1)]="1";
					AControlUtil.AddUserCard(Long.valueOf(ac.getCsn()),ac.getCip(),Long.valueOf(myAEmp.getCardno()),(byte)0x20,(byte)0x29,(byte)0x12,(byte)0x31,authority);
				}
				/**
				 * 下发电梯
				 */
				for(Elevator el: elevators) {
					LadderControlUtil.LadderControlUserCard(Long.valueOf(el.getControllerSN()),el.getControllerIP(),Long.valueOf(myAEmp.getCardno()),1,(byte)0x20,(byte)0x29,(byte)0x12,(byte)0x31,
					lay[0]+lay[1]+lay[2]+lay[3]+lay[4]+lay[5]+lay[6]+lay[7],
					lay[8]+lay[9]+lay[10]+lay[11]+lay[12]+lay[13]+lay[14]+lay[15],
					lay[16]+lay[17]+lay[18]+lay[19]+lay[20]+lay[21]+lay[22]+lay[23], 
					lay[24]+lay[25]+lay[26]+lay[27]+lay[28]+lay[29]+lay[30]+lay[31],
					lay[32]+lay[33]+lay[34]+lay[35]+lay[36]+lay[37]+lay[38]+lay[39]);
				}
				/**
				 * select * from  agroupmiddle_info gm, access_info ai
                   where  gm.agroupid="948139c2ff4e4f1bb93ee79424e9741d" and ai.accessid =gm.accessid
                    ORDER BY ai.acno
				*/
			}
	    } 
	}

	@Override
	public Employee selectAjEmpbyId(String selectEmps) {
	
		Employee myAEmp=employeeDao.selectById(Integer.parseInt(selectEmps));
		return myAEmp;
	}
	
	@Override
	public List<Accessj> selectAjByEmpid(String id) {

		return aJDao.selectAjByEmpid(id);
	}
	@Override
	public Employee selectEmployee(String id) {

		return employeeDao.selectById(Integer.parseInt(id));
	}
	
	
	
	
	
	
	
	
	
	
	
}
