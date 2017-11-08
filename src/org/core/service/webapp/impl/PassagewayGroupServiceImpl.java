package org.core.service.webapp.impl;
/**
 * 通道分组
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.PassagewayGroupDao;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
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
		passagewayGroupDao.deletePassagewayGroupById(id);
	}
	//查询下级
	@Override
	public List<Passageway> getPassagewayById(String selectids) {
		String[] idArry = selectids.split(",");
		List<Passageway> addList = new ArrayList<>();
		for (String id : idArry) {
			Passageway addPassageway =passagewayGroupDao.getPassagewayByid(Integer.parseInt(id));
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
		passagewayGroupDao.addPGroup(ids, pgname,uuid);
	}
	@Override
	public PassagewayGroup selectPGbyId(String id) {
		// TODO Auto-generated method stub
		return passagewayGroupDao.selectPGbyId(id);
	}
	@Override
	public void updatePG(PassagewayGroup passagewayGroup) {
		// TODO Auto-generated method stub
		passagewayGroupDao.updatePG(passagewayGroup);
	}
}
