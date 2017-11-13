package org.core.service.webapp.impl;
/**
 * 通道分组
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.PassagewayGroupDao;
import org.core.domain.webapp.MiddletoPG;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;
import org.core.service.webapp.PassagewayGroupService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("passagewayGroupService")
public class PassagewayGroupServiceImpl implements PassagewayGroupService {
	//注入DAO
	@Autowired
	private PassagewayGroupDao passagewayGroupDao;
	@Transactional(readOnly=true)
	@Override
	public List<PassagewayGroup> findPassagewayGroup(PassagewayGroup passagewayGroup, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> sgy = new HashMap<>();
		sgy.put("passagewayGroup", passagewayGroup);
		int recordCount = passagewayGroupDao.count(sgy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    sgy.put("pageModel", pageModel);
	    }
		List<PassagewayGroup> passagewayGroups = passagewayGroupDao.selectByPagesgy(sgy);
		return passagewayGroups;
	}
	//删除
	@Override
	public void removePassagewayGroupById(String id) {
		List<Passagewayj> pj= passagewayGroupDao.selectPJ(id);
		if(pj.size()>0){
			
		}else{
			passagewayGroupDao.deletePassagewayGroupById(id);
			passagewayGroupDao.deleteMiddleById(id);
		}
		
	}
	//查询下级
	@Override
	public List<Passageway> getPassagewayById(String selectids) {
		List<MiddletoPG> middletoPGList =passagewayGroupDao.selectMiddlePG(selectids);
		List<Passageway> addList = new ArrayList<Passageway>();
			for (MiddletoPG myMiddletoPG : middletoPGList) {
				Passageway addPassageway =passagewayGroupDao.getPassagewayByid(Integer.parseInt(myMiddletoPG.getPassagewayid()));
				addList.add(addPassageway);
			}
		return addList;
		}
	
	//查询所有通道
	@Override
	public List<Passageway> selectPGSubordinate() {
		return passagewayGroupDao.selectPGSubordinate();
	}
	//添加通道分组
	@Override
	public void addPGroup(String ids, String pgname) {
		String uuid=GenId.UUID();
		passagewayGroupDao.addPGroup(pgname,uuid);
		String[] idArry = ids.split(",");
		for (String id : idArry) {
			passagewayGroupDao.addaddPGrouptoMiddle(uuid,id);
		}
	}
	@Override
	public PassagewayGroup selectPGbyId(String id) {
		
		PassagewayGroup selectPGbyId = passagewayGroupDao.selectPGbyId(id);
		//执行一个查中间表的集合的方法
		List<MiddletoPG> selectMiddlePG = passagewayGroupDao.selectMiddlePG(id);
		for (MiddletoPG middletoPG : selectMiddlePG) {
			Passageway passagewayByid = passagewayGroupDao.getPassagewayByid(Integer.parseInt(middletoPG.getPassagewayid()));
			selectPGbyId.getOrderItems().add(passagewayByid);
		}
		return selectPGbyId;
	}
	//修改
	@Override
	public void updatePG(PassagewayGroup passagewayGroup,String pid) {
		passagewayGroupDao.updatePG(passagewayGroup);
		String mypgid = passagewayGroup.getPgid();
		passagewayGroupDao.upDelMiddletoPG(mypgid);
		String[] idArry = pid.split(",");
		for (String ppid : idArry) {
			passagewayGroupDao.addaddPGrouptoMiddle(mypgid,ppid);
		}	
	}
}
