package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AccessGroupDao;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.service.webapp.AccessGroupService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("accessgroupService")
public class AccessGroupServiceImpl implements AccessGroupService {
			/**
			 * 自动注入分组持久层Dao对象
			 * */
			@Autowired
			private AccessGroupDao accessGroupDao;
			@Transactional(readOnly=true)
			/*
			 * 门禁分组
			 * */
			//查询
			@Override
			public List<AccessGroup> findAccessGroup(AccessGroup accessGroup, PageModel pageModel) {
				/** 当前需要分页的总数据条数  */
				Map<String,Object> sgy = new HashMap<>();
				sgy.put("accessGroup", accessGroup);
				int recordCount = accessGroupDao.count(sgy);
				pageModel.setRecordCount(recordCount);
				if(recordCount > 0){
			        /** 开始分页查询数据：查询第几页的数据 */
				    sgy.put("pageModel", pageModel);
			    }
				List<AccessGroup> accessGroups = accessGroupDao.selectByPagesgy(sgy);
				return accessGroups;
			}
			//删除
			@Override
			public void removeAccessGroupById(String id) {
				accessGroupDao.deleteByagID(id);
			}
			//查询所有门禁
			@Override
			public List<Access> selectAGSubordinate() {
				return accessGroupDao.selectAGSubordinate();
			}
			//添加分组
			@Override
			public void addAGroup(String ids, String agname) {
				String uuid=GenId.UUID();
				accessGroupDao.addAGroup(ids, agname,uuid);
			}
			//根据id查找所属下级
			@Override
			public List<Access> getAccessById(String selectids) {
				String[] idArry = selectids.split(",");
				List<Access> addList = new ArrayList<>();
				for (String id : idArry) {
					Access addAccess =accessGroupDao.getAccessByid(Integer.parseInt(id));
					  addList.add(addAccess);
				}
				return addList;
				}
			//修改前查询一遍
			@Override
			public AccessGroup selectAGbyId(String id) {
				// TODO Auto-generated method stub
				return accessGroupDao.selectAGbyId(id);
			}
			@Override
			public void updateAG(AccessGroup accessGroup) {
				// TODO Auto-generated method stub
				accessGroupDao.updateAG(accessGroup);
			}
			
	}
