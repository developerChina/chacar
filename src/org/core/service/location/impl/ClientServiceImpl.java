package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.ClientDao;
import org.core.domain.location.LocationClient;
import org.core.service.location.ClientService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("clientService")
public class ClientServiceImpl implements ClientService {
		/**
		 * 自动注入持久层Dao对象
		 * */
		@Autowired
		private ClientDao clientDao;
		
		//查询所有客户
		@Transactional(readOnly=true)
		@Override
		public List<LocationClient> findLocationClient(LocationClient locationClient, PageModel pageModel) {
			/** 当前需要分页的总数据条数  */
			Map<String,Object> gy = new HashMap<>();
			gy.put("locationClient", locationClient);
			int recordCount = clientDao.count(gy);
			pageModel.setRecordCount(recordCount);
			if(recordCount > 0){
		        /** 开始分页查询数据：查询第几页的数据 */
			    gy.put("pageModel", pageModel);
		    }
			List<LocationClient> locationClients = clientDao.selectByPagegy(gy);
			 
			return locationClients;
		}
		
		//删除客户
		@Override
		public void removeLocationClientById(String id) {
			clientDao.removeLocationClientById(id);
		}
		
		//根据id查询客户
		@Transactional(readOnly=true)
		@Override
		public LocationClient findLocationClientById(String id) {
			return clientDao.findLocationClientById(id);
		}
		
		//修改客户
		@Override
		public void modifyLocationClient(LocationClient locationClient) {
			clientDao.modifyLocationClient(locationClient);		
		}
		
		//添加客户
		@Override
		public void addLocationClient(LocationClient locationClient) {
			String uuid = GenId.UUID();
			locationClient.setId(uuid);
			clientDao.addLocationClient(locationClient);
		}
}
