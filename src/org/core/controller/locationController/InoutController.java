package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationInout;
import org.core.service.location.InoutService;
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
			 Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<LocationInout> locationInouts = inoutService.findLocationAlarm(locationInout, pageModel);
		model.addAttribute("locationInouts", locationInouts);
		model.addAttribute("pageModel", pageModel);
		return "location/showInout";
	}}
