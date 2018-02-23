package org.core.service.location.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.location.InoutDao;
import org.core.dao.queuing.QueuingDao;
import org.core.domain.car.CarDistinguish;
import org.core.domain.location.LocationInout;
import org.core.service.location.InoutService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("inoutService")
public class InoutServiceImpl implements InoutService {
	@Autowired
	private InoutDao inoutDao;
	@Autowired
	private QueuingDao queuingDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LocationInout> findLocationAlarm(LocationInout locationInout, PageModel pageModel,Date startDate, Date endDate) {
		
		String vSupplier = locationInout.getSupplier();
		if(vSupplier!=null && !"".equals(vSupplier)){
			String nos = "";
			List<String> carList = queuingDao.vagueCar_code(vSupplier);
			if(carList!=null&&carList.size()>0){
				
				for (String code : carList) {
					nos+="'"+code+"'"+",";
				}
				//System.out.println(nos);
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println(nos); 
				locationInout.setSupplier(nos);
			}else{
				locationInout.setSupplier("000000");
			}
		}
		/** 当前需要分页的总数据条数  */
		Map<String,Object> gy = new HashMap<>();
		gy.put("locationInout", locationInout);
		gy.put("startDate", startDate);
		gy.put("endDate", endDate);
		int recordCount = inoutDao.count(gy);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    gy.put("pageModel", pageModel);
	    }
		List<LocationInout> locationInouts = inoutDao.selectByPagegy(gy);
		for (LocationInout entity : locationInouts) {
			List<String> SupplierList = queuingDao.getSupplier(entity.getVehicleCode());
			if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
				entity.setSupplier(SupplierList.get(0));
			}else{
				entity.setSupplier("");
			}
			
			if(entity.getServerInIp()!=null&&!"".equals(entity.getServerInIp())){
					CarDistinguish camera = inoutDao.getCamera(entity.getServerInIp());
					if(camera!=null){
					    entity.setServerInName(camera.getName());
					}
				}
			if(entity.getServerOutIp()!=null&&!"".equals(entity.getServerOutIp())){
				CarDistinguish camera = inoutDao.getCamera(entity.getServerOutIp());
				if(camera!=null){
					entity.setServerOutName(camera.getName());
				}
			}
			
		}
		return locationInouts;
	}
	@Override
	public LocationInout selectNewRecord(String car_code) {
		return inoutDao.selectNewRecord(car_code);
	}

}
