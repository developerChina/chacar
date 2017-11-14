package org.core.service.webapp.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.webapp.AccessGroupDao;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.MiddletoAG;
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
				List<Accessj> delAjList = accessGroupDao.getAJlist(id);
				if(delAjList.size()>0){
					
				}else{
					accessGroupDao.deleteByagID(id);
					accessGroupDao.deleteAgMiddleByagID(id);
				}
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
					accessGroupDao.addAGroup(agname,uuid);
					String[] idArry = ids.split(",");
					for (String id : idArry) {
						
						accessGroupDao.addAGrouptoMiddle(uuid,id);
					}
					
			}
			//根据id查找所属下级
			/*String[] idArry = selectids.split(",");
			List<Access> addList = new ArrayList<>();
			for (String id : idArry) {
				Access addAccess =accessGroupDao.getAccessByid(Integer.parseInt(id));
				  addList.add(addAccess);
			}
			return addList;*/
			@Override
			public List<Access> getAccessById(String selectids) {
				List<MiddletoAG> MiddletoAGList = accessGroupDao.getMiddle(selectids);
				List<Access> addList = new ArrayList<>();
				for (MiddletoAG mymiddletoAG : MiddletoAGList) {
					Access addAccess =accessGroupDao.getAccessByid(Integer.parseInt(mymiddletoAG.getAccessid()));
					addList.add(addAccess);
				}
				return addList;
			}
			//修改前查询一遍
			@Override
			public AccessGroup selectAGbyId(String id) {
				
				AccessGroup selectAGbyId = accessGroupDao.selectAGbyId(id);
				List<MiddletoAG> MiddletoAGList = accessGroupDao.getMiddle(id);
				for (MiddletoAG mymiddletoAG : MiddletoAGList) {
					Access addAccess =accessGroupDao.getAccessByid(Integer.parseInt(mymiddletoAG.getAccessid()));
					selectAGbyId.getOrderItems().add(addAccess);
				}
				
				return selectAGbyId;
			}
			
			@Override
			public void updateAG(AccessGroup accessGroup,String aids) {
				//第一步 修改分组表里的名称 根据 分组表的主键id：agid
					accessGroupDao.updateAG(accessGroup);
				//第二步 1将分组表的主键id：agid 拿出来用
					String myagid = accessGroup.getAgid();
					  //2删除中间表 where 表中字段=#{myagid}
					accessGroupDao.upDelMiddletoAG(myagid);
				//第三步 1 拆分字符串  你有可能选了多个门禁到组内 （选择门禁就代表你传上来多个门禁id）
					String[] idArry = aids.split(",");
					  //2 向中间表 重新添加
					for (String aid : idArry) {
						accessGroupDao.addAGrouptoMiddle(myagid,aid);
					}	
			}
			
			@Override
			public List<Access> getAbyGroupid(String selectids) {
				List<MiddletoAG> MiddletoAGList = accessGroupDao.getMiddle(selectids);
				List<Access> selectAList= new ArrayList<Access>();
				for (MiddletoAG mymiddletoAG : MiddletoAGList) {
					Access addAccess =accessGroupDao.getAccessByid(Integer.parseInt(mymiddletoAG.getAccessid()));
					selectAList.add(addAccess);
				}
				return selectAList;
			}
			
			
			
			
			
			
	}
