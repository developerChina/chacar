package org.core.service.car.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.core.dao.car.CarLogsDao;
import org.core.domain.car.CarDistinguish;
import org.core.domain.car.CarInfo;
import org.core.domain.car.CarLogs;
import org.core.service.car.CarLogsService;
import org.core.util.StringUtils;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 停车场出入记录服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("carLogsService")
public class CarLogsServiceImpl implements CarLogsService{
	
	@Autowired
	private CarLogsDao carLogsdao;
	
	@Override
	public List<CarLogs> selectCarLogs(CarLogs carLogs, PageModel pageModel,Date startDate, Date endDate) {
		
		String vSupplier = carLogs.getCarMaster();
		if(vSupplier!=null && !"".equals(vSupplier)){
			String nos = "";
			List<String> carList = carLogsdao.vagueCar_code(vSupplier);
			if(carList!=null&&carList.size()>0){
				for (String code : carList) {
					nos+="'"+code+"'"+",";
				}
				nos = nos.substring(0,nos.length() - 1);
				carLogs.setCarMaster(nos);
			}else{
				carLogs.setCarMaster("000000");
			}
		}
		
		Map<String,Object> params = new HashMap<>();
		List<CarDistinguish> ipList = carLogsdao.selectIp();
		String ips = "";
		for (CarDistinguish ip : ipList) {
			ips+="'"+ip.getIp()+"'"+",";
		}
		params.put("ips", ips.substring(0,ips.length() - 1));
		params.put("carLogs", carLogs);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int recordCount = carLogsdao.countCarLogs(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		
		List<CarLogs> carLogsList = carLogsdao.pageSelectCarLogs(params);
		for(CarLogs entity : carLogsList) {
			//车主
			if(StringUtils.isNotBlank(entity.getCacrno())){
				CarInfo carInfo=carLogsdao.selectByCarno(entity.getCacrno());
				if(carInfo!=null){
					entity.setCarMaster(carInfo.getName());
				}
			}
			//ip对应的相机名称
			if(StringUtils.isNotBlank(entity.getServerIp())){
				//根据相机ip 去识别仪那里找到名称
				CarDistinguish carServerIp = carLogsdao.selectByIp(entity.getServerIp());
				entity.setInIpName(carServerIp.getName());
				
				String relationId = carLogsdao.getRelationId(entity.getServerIp());
				CarDistinguish disById = carLogsdao.getDisById(relationId);
				if(entity.getShootTime()!=null&&!"".equals(entity.getShootTime())){
					if(disById!=null){
						CarLogs outIpName = carLogsdao.getOut(disById.getIp(),entity.getShootTime(),entity.getCacrno());
						if(outIpName!=null){
							if(StringUtils.isNotBlank(outIpName.getServerIp())){
								entity.setOutIp(outIpName.getServerIp());
								CarDistinguish outIp = carLogsdao.selectByIp(outIpName.getServerIp());
								entity.setOutIpName(outIp.getName());
								entity.setOutTime(outIpName.getShootTime());
							}
						}
					}
				}

			}
			if(entity.getShootTime()!=null&&entity.getOutTime()!=null){
				//String a = formatMiliLongToString(entity.getOutTime().getTime()-entity.getShootTime().getTime());
				String b = myDateToString(entity.getOutTime().getTime()-entity.getShootTime().getTime());
				entity.setPlant(b);
			}
		}
		return carLogsList;
	}

	
	//mili 2个日期的毫秒差
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
