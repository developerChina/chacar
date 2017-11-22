package org.core.service.location.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.VehicelDao;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationVehicel;
import org.core.domain.location.LocationVehicelGroup;
import org.core.domain.location.LocationWaiJie;
import org.core.service.location.VehicelService;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("vehicelService")
public class VehicelServiceImpl implements VehicelService {
	@Autowired
	private VehicelDao vehicelDao;
	@Transactional(readOnly=true)
	@Override
	public List<LocationVehicel> findLocationVehicel(LocationVehicel locationVehicel, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationVehicel", locationVehicel);
		int recordCount = vehicelDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationVehicel> locationVehicels = vehicelDao.selectByPagegy(gy);
		 
		return locationVehicels;
	}
	//删除
	@Override
	public void removeLocationVehicelById(String id) {
		vehicelDao.removeLocationVehicelById(id);
		//将与其关联的俩张表删除
		vehicelDao.removeVgroup(id);
		vehicelDao.removeVwaijie(id);
	}
	
	@Override
	public void addLvehicel(LocationVehicel locationVehicel, String[] wjsb, String[] Lgroupids) {
		//用来当设备主键 
		String uuid = GenId.UUID();
		//外接设备    保存到设备与外接设备表中
		for (String mywjsb : wjsb) {
			vehicelDao.addWaiJie(mywjsb,uuid);
		}
		
		//分组id 与设备保存在中间表
		for (String groupid : Lgroupids) {
			vehicelDao.addVehicelGroup(groupid,uuid);
		}
		//保存自己
		locationVehicel.setId(uuid);
		vehicelDao.save(locationVehicel);
	}
	//修改前查
	@Override
	public LocationVehicel getUpdate(String vehicelid) {
		//查到自己
		LocationVehicel updateVehicel = vehicelDao.getUpdate(vehicelid);
		//查中间表   分组和设备
		List<LocationVehicelGroup> UpdateVgroup = vehicelDao.getUpdateVgroup(vehicelid);
		for (LocationVehicelGroup locationVehicelGroup : UpdateVgroup) {
			LocationGroup updateVgroup=vehicelDao.getGroup(locationVehicelGroup.getGroupId());
			updateVehicel.getMyVgroups().add(updateVgroup);
		}
		//查中间表   外接设备和设备
		String ids = "";
		List<LocationWaiJie> UpdateVwaijie = vehicelDao.getUpdateVwaijie(vehicelid);
		for (LocationWaiJie locationWaiJie : UpdateVwaijie) {
			ids+=locationWaiJie.getWjsbid()+",";
		}
		ids = ids.substring(0,ids.length()-1);
		updateVehicel.setWaijieids(ids);
		return updateVehicel;
	}
	//执行修改
	@Override
	public void UpdateVehicel(LocationVehicel locationVehicel, String[] Lgroupids, String[] wjsb) {
		//设备表的id
		String Vehicelid = locationVehicel.getId();
		//先将分组表和设备表的中间表删除
		vehicelDao.removeVgroup(Vehicelid);
		//再将外接设备表和设备表的中间表删除
		vehicelDao.removeVwaijie(Vehicelid);
		//删除之后重新添加 添加里的方法
		for (String mywjsb : wjsb) {
			vehicelDao.addWaiJie(mywjsb,Vehicelid);
		}
		for (String groupid : Lgroupids) {
			vehicelDao.addVehicelGroup(groupid,Vehicelid);
		}
		
		//修改自己
		
		vehicelDao.UpdateVehicel(locationVehicel);
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
