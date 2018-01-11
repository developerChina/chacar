package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AJDao;
import org.core.dao.webapp.AccessDao;
import org.core.dao.webapp.AccessGroupDao;
import org.core.dao.webapp.ElevatorDao;
import org.core.dao.webapp.EmployeeDao;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.MiddletoAG;
import org.core.service.webapp.AJService;
import org.core.util.AControlUtil;
import org.core.util.DTConstants;
import org.core.util.GenId;
import org.core.util.LadderControlUtil;
import org.core.util.O2MoreOnlyMap;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("aJService")
public class AJServiceImpl implements AJService {
	@Autowired
	private AJDao aJDao;
	@Autowired
	ElevatorDao elevatorDao;
	@Autowired
	private EmployeeDao employeeDao;
	// 查询所有权限表
	@Autowired
	private AccessGroupDao accessGroupDao;

	@Autowired
	private AccessDao accessDao;

	@Transactional(readOnly = true)
	@Override
	public int selectAJG(String id) {
		return aJDao.selectAJG(id);
	}

	// 查询所有门禁分组
	@Transactional(readOnly = true)
	@Override
	public List<AccessGroup> findAGAll() {
		return aJDao.findAGAll();
	}

	@Override
	public Access getAccessById(String accessid) {

		return accessDao.selectByaccessid(Integer.parseInt(accessid));
	}

	// 查询授权并分页
	@Override
	public List<Accessj> selectAJ(Accessj accessj, PageModel pageModel) {
		String vague = accessj.getPganame();
		if(vague!=null&& !"".equals(vague)){
			List<Access>  vagueList = aJDao.getPlist(vague);
			String myids="";
			if(vagueList!=null&&vagueList.size()>0){
				for (Access access : vagueList) {
					myids+=access.getAccessid()+",";
				}
				myids = myids.substring(0,myids.length() - 1);
				accessj.setPganame(myids);
			}else{
				accessj.setPganame("000000");
			}
			
		}
		
		String empName = accessj.getAjEmpName();
		if(empName!=null&& !"".equals(empName)){
			List<Employee>  vagueList = aJDao.getEmp(empName);
			String myids="";
			if(vagueList!=null&&vagueList.size()>0){
				for (Employee employee : vagueList) {
					myids+=employee.getId()+",";
				}
				myids = myids.substring(0,myids.length() - 1);
				accessj.setAjEmpName(myids);
			}else{
				accessj.setAjEmpName("000000");
			}
		}
		
		/** 当前需要分页的总数据条数 */
		Map<String, Object> gy = new HashMap<>();
		gy.put("accessj", accessj);
		int recordCount = aJDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			gy.put("pageModel", pageModel);
		}
		List<Accessj> accessjs = aJDao.selectByPagegy(gy);
		return accessjs;
	}

	@Override
	public void saveAJ(Accessj accessj) {
		// uuid
		String uuid = GenId.UUID();
		accessj.setAjid(uuid);
		aJDao.saveAJ(accessj);

	}

	@Override
	public Accessj selectAjByid(String id) {
		return aJDao.selectAjByid(id);
	}

	// 修改
	@Override
	public void updateAj(Accessj accessj) {
		aJDao.updateAj(accessj);
	}

	// 查询里的先来门禁组
	@Override
	public AccessGroup selectPGbyId(String selectEGs) {
		AccessGroup myAG = accessGroupDao.selectAGbyId(selectEGs);
		return myAG;
	}

	// 查询员工集合 根据IDS
	@Override
	public List<Employee> findEmployeeByIds(String ids) {
		String[] idArry = ids.split(",");
		List<Employee> seList = new ArrayList<Employee>();
		for (String id : idArry) {
			Employee myAEmp = employeeDao.selectById(Integer.parseInt(id));
			seList.add(myAEmp);
		}
		return seList;
	}

	@Override
	public void removeAccessjByID(String ids) {
		// 分解id字符串
		String[] idArrayto = ids.split(",");
		for (String idto : idArrayto) {
			String[] idArray = idto.split(";");
			String myempid = null;
			String accessid = null;
			for (int i = 0; i < idArray.length; i += 2) {
				myempid = idArray[i];
			}
			for (int i = 1; i < idArray.length; i += 2) {
				accessid = idArray[i];
			}
			// 删除授权
			GrantAuthorization(myempid,1);
			aJDao.removeAccessjByID(myempid, accessid);
		}
	}
	// 添加授权
	public void saveAJNew(String[] empids, String ajname, String ajgroup) {
		for (String myempid : empids) {
			Employee selectById = employeeDao.selectById(Integer.parseInt(myempid));
			String[] idArry = ajgroup.split(",");
			for (String groupid : idArry) {
				List<MiddletoAG> middle = accessGroupDao.getMiddle(groupid);
				for (MiddletoAG middletoAG : middle) {
					Accessj accessj = new Accessj();
					accessj.setAjgroupid(groupid);
					accessj.setAjempid(myempid);
					accessj.setAjname(ajname);
					String uuid = GenId.UUID();
					accessj.setAjid(uuid);
					accessj.setAjaccessid(middletoAG.getAccessid());
					accessj.setAjempno(selectById.getCardno());
					aJDao.saveAJ(accessj);
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
			List<Access> la = aJDao.getGrantAuthorization(Integer.parseInt(empid));
			O2MoreOnlyMap<Integer, String> moreDTMap = new O2MoreOnlyMap<>();// 某个人的“去重”后的所有 电梯  权限
			O2MoreOnlyMap<String, Integer> moreMJMap = new O2MoreOnlyMap<>();// 某个人的“去重”后的所有  门禁 权限
			for (Access ac : la) {
				moreDTMap.put(ac.getFloorno(), ac.getCsn() + "," + ac.getCip() + "," + ac.getAjempno() + "," + ac.getAcno());
				moreMJMap.put(ac.getCsn() + "," + ac.getCip() + "," + ac.getAjempno() + "," +ac.getFloorno(), ac.getAcno());
			}
			//System.out.println("授权==dianti==" + moreDTMap.getAll());
			//System.out.println("授权==mengj==" + moreMJMap.getAll());
			InitMJGrant(moreMJMap,opt);
			InitDTGrant(moreDTMap,opt);
		}
	}
	/**
	 * {433104923,192.168.1.5,3918980220,14  -->  1}
	 * @param moreMap
	 * @param opt
	 */
	public void InitMJGrant(O2MoreOnlyMap<String,Integer> moreMap,int opt) {
		int authority[] = { 0, 0, 0, 0 };
		long cardno = 0;
		String sn="",ip="";
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {
				String[] key = moreMap.getkey(i).split(",");
				sn=key[0];
				ip=key[1];
				cardno = Long.valueOf(key[2]);
				for (Iterator<Integer> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					authority[it.next() - 1] = opt==1?0:1;
				}
				//System.out.println("授权====" + authority[0]+"  "+ authority[1]+"  "+ authority[2]+"  "+ authority[3]);
				AControlUtil.AddUserCard(Long.valueOf(sn),ip,Long.valueOf(cardno),(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31,authority);
				authority[0]=0; authority[1]=0; authority[2]=0;authority[3]=0;
			}
		}
	}
	/**
	 * 
	 * {14 --> 433104923,192.168.1.5,3918980220,1}
	 * @param moreMap
	 * @param opt
	 */
	public void InitDTGrant(O2MoreOnlyMap<Integer, String> moreMap,int opt) {
		int layOne = 0, layTwo = 0, layThree = 0, layFour = 0, layFive = 0;
		long cardno = 0;
		if(moreMap !=null &&  moreMap.getSize()>0) {
			for (int i = 0; i < moreMap.getSize(); i++) {// 一个控制器，一层，一个人
				Integer key = moreMap.getkey(i);
				if (key >= 1 && key <= 8) {
					layOne = opt==1?0:(layOne + DTConstants.getFloor(key));
				}
				if (key >= 9 && key <= 16) {
					layTwo = opt==1?0:(layTwo + DTConstants.getFloor(key));
				}
				if (key >= 17 && key <= 24) {
					layThree = opt==1?0:(layThree + DTConstants.getFloor(key));
				}
				if (key >= 25 && key <= 32) {
					layFour =opt==1?0:( layFour + DTConstants.getFloor(key));
				}
				if (key >= 33 && key <= 40) {
					layFive = opt==1?0:(layFive + DTConstants.getFloor(key));
				}
				for (Iterator<String> it = moreMap.getvalue(i).iterator(); it.hasNext();) {
					String[] value = it.next().split(",");
					cardno = Long.valueOf(value[2]);
					if(cardno!=0) break;
				}
			}
			//System.out.println("==dianti==" + layOne+"  "+ layTwo+"  "+layThree+"  "+layFour+"  "+layFive);
			GrantDianTi(layOne, layTwo, layThree, layFour, layFive, cardno);
		}
	}
	
	public void GrantDianTi(int layOne, int layTwo, int layThree, int layFour, int layFive, Long cardno) {
		List<Elevator> elevators = elevatorDao.selectByPage(new HashMap<String, Object>());// 所有电梯
		for (Elevator el : elevators) {
			LadderControlUtil.LadderControlUserCard(Long.valueOf(el.getControllerSN()), el.getControllerIP(), cardno, 1,
					(byte) 0x20, (byte) 0x29, (byte) 0x12, (byte) 0x31, layOne, layTwo, layThree, layFour, layFive);
		}
	}

	@Override
	public Employee selectAjEmpbyId(String selectEmps) {
		Employee myAEmp = employeeDao.selectById(Integer.parseInt(selectEmps));
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

	@Override
	public List<MiddletoAG> selectAGinMiddle(String selectEGs) {

		return accessGroupDao.getMiddle(selectEGs);
	}

}
