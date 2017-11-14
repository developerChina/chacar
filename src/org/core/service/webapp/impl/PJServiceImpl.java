package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import org.core.util.AControlUtil;
import org.core.util.GenId;
import org.core.util.O2MoreOnlyMap;
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
		return pJDao.selectPJG(id);
	}
	//查询所有分组
	@Override
	@Transactional(readOnly=true)
	public List<PassagewayGroup> selectAll() {
		return pJDao.findPGAll();
	}
	//查询授权表并分页
	@Override
	public List<Passagewayj> selectPJ(Passagewayj passagewayj, PageModel pageModel) {
		String vague = passagewayj.getPganame();
		if(vague!=null&& !"".equals(vague)){
			List<Passageway>  vagueList = pJDao.getPlist(vague);
			String myids="";
			for (Passageway passageway : vagueList) {
				myids+=passageway.getPassagewayID()+",";
			}
			myids = myids.substring(0,myids.length() - 1);
			passagewayj.setPganame(myids);
		}
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
			// 删除授权
			GrantAuthorization(myempid,1);
			pJDao.removePassagewayjByID(myempid,mypid);
		}
	}
	//查自己
	@Override
	public Passagewayj selectPjByid(String id) {
		return pJDao.selectPjByid(id);
	}
	//修改
	@Override
	public void updatePj(Passagewayj passagewayj) {
		pJDao.updatePj(passagewayj);
	}
	@Override
	public void savePJ(Passagewayj passagewayj) {
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
			// 授权
			GrantAuthorization(myempid,2);
		}
	}
	/**
	 * 公用 授权函数（电梯，门禁） 逻辑 查询 某个人-》权限 -》 某层 -》 某门禁
	 * 
	 * @param empids
	 */
	public void GrantAuthorization(String empid,int opt) {
		if (empid != null && !"".equals(empid)) {
			List<Passageway> la = pJDao.getGrantAuthorization(Integer.parseInt(empid));
			O2MoreOnlyMap<String, String> moreTDMap = new O2MoreOnlyMap<>();// 某个人的“去重”后的所有  门禁 权限
			for (Passageway ac : la) {
				moreTDMap.put(ac.getControllerSN() + "," + ac.getControllerIP()+","+ac.getPjempno(), ac.getPtype());// 0:出  1：进
			}
			System.out.println("授权==dianti==" + moreTDMap.getAll());
			InitTDGrant(moreTDMap,opt);
		}
	}
	/**
	 * {433104923,192.168.1.5,3918980220,14  -->  1}
	 * @param moreMap
	 * @param opt
	 */
	public void InitTDGrant(O2MoreOnlyMap<String,String> moreMap,int opt) {
		int authority[] = { 0, 0, 0, 0 };
		long cardno = 0;
		String sn="",ip="";
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {
				String[] key = moreMap.getkey(i).split(",");
				sn=key[0];
				ip=key[1];
				cardno = Long.valueOf(key[2]);
				for (Iterator<String> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					authority[Integer.parseInt(it.next())] = opt==1?0:1;
				}
				//System.out.println("授权====" + authority[0]+"  "+ authority[1]+"  "+ authority[2]+"  "+ authority[3]);
				AControlUtil.AddUserCard(Long.valueOf(sn),ip,Long.valueOf(cardno),(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31,authority);
				authority[0]=0; authority[1]=0; authority[2]=0;authority[3]=0;
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
		return passagewayDao.selectBypassagewayID(Integer.parseInt(Danpid));
	}
	@Override
	public Employee selectempbyid(String myempid) {
		return employeeDao.selectById(Integer.parseInt(myempid));
	}
}
