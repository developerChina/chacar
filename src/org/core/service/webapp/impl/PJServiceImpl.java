package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.EmployeeDao;
import org.core.dao.webapp.PJDao;
import org.core.dao.webapp.PassagewayDao;
import org.core.dao.webapp.PassagewayGroupDao;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.MiddletoPG;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;
import org.core.service.webapp.PJService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("pJService")
public class PJServiceImpl implements PJService {
	@Autowired
	private PJDao pJDao;
	@Autowired
	private PassagewayGroupDao passagewayGroupDao;
	@Autowired
	private PassagewayDao passagewayDao;
	@Override
	@Transactional(readOnly=true)
	public int selectPJG(String id) {
		// TODO Auto-generated method stub
		return pJDao.selectPJG(id);
	}
	//查询所有分组
	@Override
	@Transactional(readOnly=true)
	public List<PassagewayGroup> selectAll() {
		// TODO Auto-generated method stub
		return pJDao.findPGAll();
	}
	//查询授权表并分页
	@Override
	public List<Passagewayj> selectPJ(Passagewayj passagewayj, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("passagewayj", passagewayj);
		int recordCount = pJDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<Passagewayj> passagewayjs = pJDao.selectByPagegy(gy);
		return passagewayjs;
	}
	//根据员工id和通道id删除
	@Override
	public void removePassagewayjByID(String ids) {
		String[] idArrays = ids.split(","); 
		for (String myrp : idArrays) {
			String[] idss = myrp.split(";");
			//员工id
			String myempid = null;
			//通道id
			String mypid=null;
			for (int i = 0; i < idss.length; i+=2) {
				myempid = idss[i];
			}
			for (int i = 1; i < idss.length; i+=2) {
				mypid=idss[i];
			}
			pJDao.removePassagewayjByID(myempid,mypid);
		}
	}
	//查自己
	@Override
	public Passagewayj selectPjByid(String id) {
		// TODO Auto-generated method stub
		return pJDao.selectPjByid(id);
	}
	//修改
	@Override
	public void updatePj(Passagewayj passagewayj) {
		// TODO Auto-generated method stub
		pJDao.updatePj(passagewayj);
	}
	@Override
	public void savePJ(Passagewayj passagewayj) {
		// TODO Auto-generated method stub
		String uuid=GenId.UUID();
		passagewayj.setPjid(uuid);
		pJDao.savePJ(passagewayj);
		
	}
	//查询的先来通道组
	@Override
	public PassagewayGroup selectPGbyId(String selectEGs) {
		return passagewayGroupDao.selectPGbyId(selectEGs);
	}
	
	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public List<Employee> findEmployeeByIds(String ids) {
		String[] idArry = ids.split(",");
		List<Employee> seList = new ArrayList<Employee>();
		for (String id : idArry) {
			Employee myAEmp=employeeDao.selectById(Integer.parseInt(id));
			seList.add(myAEmp);
		}
		return seList;
	}
	
	@Override
	public void savePJNew(String[] empids, String pjname, String pjgroup) {
		for (String myempid : empids) {
			Employee myAEmp=employeeDao.selectById(Integer.parseInt(myempid));
			String[] idArry = pjgroup.split(",");
			for (String groupid : idArry) {
				 List<MiddletoPG> mymiddletoPG =passagewayGroupDao.getMiddle(groupid);
				for (MiddletoPG middletoPG : mymiddletoPG) {
					 Passagewayj myPass= new Passagewayj();
						myPass.setPjgroupid(groupid);
						myPass.setPjempid(myempid);
						myPass.setPjname(pjname);
						String uuid=GenId.UUID();
						myPass.setPjid(uuid);
						//通道id
						myPass.setPassagewayjid(middletoPG.getPassagewayid());
						//员工卡号
						myPass.setPjempno(myAEmp.getCardno());
						pJDao.savePJ(myPass);
				}
				
			}
		}
		
	}
	
	@Override
	public Employee selectPjEmpbyId(String selectEmps) {
		Employee myAEmp=employeeDao.selectById(Integer.parseInt(selectEmps));
		return myAEmp;
	}
	
	@Override
	public Passageway selecPbypid(String Danpid) {
		// TODO Auto-generated method stub
		return passagewayDao.selectBypassagewayID(Integer.parseInt(Danpid));
	}
	@Override
	public Employee selectempbyid(String myempid) {
		// TODO Auto-generated method stub
		return employeeDao.selectById(Integer.parseInt(myempid));
	}

	
	
	
}
