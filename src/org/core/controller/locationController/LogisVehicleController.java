package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationLogisVehicle;
import org.core.service.location.LogisVehicleService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LogisVehicleController {
	@Autowired
	@Qualifier("logisVehicleService")
	private LogisVehicleService logisVehicleService;
	@RequestMapping(value = "/logisVehicle/selectlogisVehicle")
	public String selectlogisVehicle(Integer pageIndex,  @ModelAttribute LocationLogisVehicle locationLogisVehicle,
			Model model) {
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		// 查询logis_vehicle信息
		List<LocationLogisVehicle> locationLogisVehicles = logisVehicleService.findLocationLogisVehicle(locationLogisVehicle, pageModel);
		// 设置Model数据
		model.addAttribute("locationLogisVehicles", locationLogisVehicles);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("model", locationLogisVehicle);
		
		String pageParam="";
		if(locationLogisVehicle.getDriverID()!=null){
			pageParam+="&driverID="+locationLogisVehicle.getDriverID();
		}
		if(locationLogisVehicle.getVehicleTypeID()!=null){
			pageParam+="&vehicleTypeID="+locationLogisVehicle.getVehicleTypeID();
		}
		if(locationLogisVehicle.getColorID()!=null){
			pageParam+="&colorID="+locationLogisVehicle.getColorID();
		}
		if(locationLogisVehicle.getVehicleCode()!=null){
			pageParam+="&vehicleCode="+locationLogisVehicle.getVehicleCode();
		}
		if(locationLogisVehicle.getDeviceType()!=null){
			pageParam+="&deviceType="+locationLogisVehicle.getDeviceType();
		}
		model.addAttribute("pageParam", pageParam);
		// 返回员工页面
		return "location/ShowLocationLogisVehicles";

	}
}
