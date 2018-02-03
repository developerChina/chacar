package org.core.controller.locationController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.core.domain.location.LocationInout;
import org.core.service.location.InoutService;
import org.core.util.DateUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class InoutController {
	@Autowired
	@Qualifier("inoutService")
	private InoutService inoutService;
	//查询
	@RequestMapping(value="/Inout/selectInout")
	public String selectInout(Integer pageIndex,
			 @ModelAttribute LocationInout locationInout,
			 HttpServletRequest request,Model model){
		
		String pageParam="";
		if(locationInout.getVehicleCode()!=null&&!"".equals(locationInout.getVehicleCode())){
			pageParam+="&vehicleCode="+locationInout.getVehicleCode();
		}
		if(locationInout.getVehicleType()!=null){
			pageParam+="&vehicleType="+locationInout.getVehicleType();
		}
		if(locationInout.getSupplier()!=null && !locationInout.getSupplier().equals("")){
			pageParam+="&supplier="+locationInout.getSupplier();
		}
		model.addAttribute("targetSupplier", locationInout.getSupplier());
		
		String sDate=request.getParameter("sDate");
		if(sDate!=null && !"".equals(sDate)){
			pageParam+="&sDate="+sDate;
		}
		Date startDate=null;
		try {
			startDate=DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate=request.getParameter("eDate");
		if(eDate!=null && !"".equals(eDate)){
			pageParam+="&eDate="+eDate;
		}
		Date endDate=null;
		try {
			endDate=DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cominDate", sDate);
		model.addAttribute("outDate", eDate);
		model.addAttribute("pageParam", pageParam);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<LocationInout> locationInouts = inoutService.findLocationAlarm(locationInout, pageModel,startDate,endDate);
		model.addAttribute("locationInouts", locationInouts);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("model", locationInout);
		
		return "location/showInout";
	}
	
	
}
