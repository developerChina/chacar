package org.core.service.location.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.core.dao.location.InoutDao;
import org.core.dao.queuing.QueuingDao;
import org.core.domain.car.CarDistinguish;
import org.core.domain.location.LocationDriver;
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
			List<String>  driverList = queuingDao.driverCar_code(vSupplier);
			if(carList!=null&&carList.size()>0){
				for (String code : carList) {
					nos+="'"+code+"'"+",";
				}
			if(driverList!=null&&driverList.size()>0){
					for (String drivercode : driverList) {
						nos+="'"+drivercode+"'"+",";
					}
				}	
				nos = nos.substring(0,nos.length() - 1);
				locationInout.setSupplier(nos);
			}else{
				if(driverList!=null&&driverList.size()>0){
					for (String drivercode : driverList) {
						nos+="'"+drivercode+"'"+",";
					}
					nos = nos.substring(0,nos.length() - 1);
					locationInout.setSupplier(nos);
				}else{
					locationInout.setSupplier("000000");
				}
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
			//相机进口名称
			if(entity.getServerInIp()!=null&&!"".equals(entity.getServerInIp())){
					CarDistinguish camera = inoutDao.getCamera(entity.getServerInIp());
					if(camera!=null){
					    entity.setServerInName(camera.getName());
					}
				}
			//相机出口名称
			if(entity.getServerOutIp()!=null&&!"".equals(entity.getServerOutIp())){
				CarDistinguish camera = inoutDao.getCamera(entity.getServerOutIp());
				if(camera!=null){
					entity.setServerOutName(camera.getName());
				}
			}
			//进出的时间差
			if(entity.getCominDate()!=null&&entity.getOutDate()!=null){
				String a = myDateToString(entity.getOutDate().getTime()-entity.getCominDate().getTime());
				entity.setPlant(a);
			}
			
			//供应商信息 查定位仪表的车主信息（根据车牌号）
			List<String> SupplierList = queuingDao.getSupplier(entity.getVehicleCode());
			//查询车主表 根据车牌获取对象
			LocationDriver driver = queuingDao.getDriver(entity.getVehicleCode());
			//车辆类型  查定位仪表的车辆类型（根据车牌号）
			List<String> vehicleTypeList = queuingDao.getVehicleTypeList(entity.getVehicleCode());
			if(SupplierList!=null && SupplierList.size()>0 ){
				//如果能查出来说明在定位仪中存在 去车主表里查一下
				//如果不为空则是 修改车 跟换车主
				if(driver!=null){
					if(driver.getOptdate()!=null&&entity.getCominDate()!=null){
						// 进入时间  小于  操作时间  将供应商显示为 车主表中的供应商
						if(entity.getCominDate().before(driver.getOptdate())){
							entity.setSupplier(driver.getName());
							if(driver.getType()==1){
								//修改车
								entity.setType(driver.getCartType());
							}
						}else{
							//显示定位仪表中字段
							entity.setSupplier(SupplierList.get(0));
							//显示定位仪表中车辆类型
							if(vehicleTypeList!=null && vehicleTypeList.size()>0 ){
								entity.setType(vehicleTypeList.get(0));
							}
						}
					}else{/* 操作时间或进入时间有空值  */
						entity.setSupplier("暂无供应商信息");
						entity.setType("暂无车辆类型");
					}
				}else{/* 在车主表中未查到值  */
					//显示定位仪表中字段
					entity.setSupplier(SupplierList.get(0));
					//显示定位仪表中车辆类型
					if(vehicleTypeList!=null && vehicleTypeList.size()>0 ){
						entity.setType(vehicleTypeList.get(0));
					}
				}
			}else{/* 在定位仪表中无此车辆信息  */
				//查询车主表，有值则此车为删除车 此车将不再通过车辆识别仪
				//记录表中将无此车辆信息，就无需判断时间
				if(driver!=null){
					entity.setSupplier(driver.getName());
					if(driver.getType()==0){
						//删除车
						entity.setType("已经注销");
					}
				}else{
					entity.setSupplier("暂无供应商信息");
					entity.setType("暂无车辆类型");
				}
			}
			
			
		}
		return locationInouts;
	}
	@Override
	public LocationInout selectNewRecord(String car_code) {
		return inoutDao.selectNewRecord(car_code);
	}

	public static String formatMiliLongToString(Long mili) {
		if (0 == mili || null == mili) {
			return "00:00:00";
		}
		Date date = new Date(mili);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("UTC+8"));
		return format.format(date);
	}
	
	public static String myDateToString(Long mili) {
		int hh = (int) (mili/(60*60*1000));
		int mm = (int) ((mili-(60*60*1000*hh))/(60*1000));
		int ss = (int) ((mili-(60*60*1000*hh)-(mm*60*1000))/1000);
		String str = (hh>=10?hh:("0"+hh))+":"+(mm>=10?mm:("0"+mm))+":"+(ss>=10?ss:("0"+ss));
		return str;
	}
	
	
}
