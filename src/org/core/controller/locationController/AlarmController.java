package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationAlarm;
import org.core.service.location.AlarmService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AlarmController {
	@Autowired
	@Qualifier("alarmService")
	private AlarmService alarmService;
	//查询
	@RequestMapping(value="/Alarm/selectAlarm")
	public String selectAlarm(Integer pageIndex,
			 @ModelAttribute LocationAlarm locationAlarm,
			 Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<LocationAlarm> locationAlarms = alarmService.findLocationAlarm(locationAlarm, pageModel);
		model.addAttribute("locationAlarms", locationAlarms);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("model", locationAlarm);
		
		String pageParam="";
		if(locationAlarm.getLat()!=null){
			pageParam+="&lat="+locationAlarm.getLat();
		}
		if(locationAlarm.getLng()!=null){
			pageParam+="&lng="+locationAlarm.getLng();
		}
		if(locationAlarm.getGpstime()!=null){
			pageParam+="&gpstime="+locationAlarm.getGpstime();
		}
		if(locationAlarm.getRecvtime()!=null){
			pageParam+="&recvtime="+locationAlarm.getRecvtime();
		}
		if(locationAlarm.getPosinfo()!=null){
			pageParam+="&posinfo="+locationAlarm.getPosinfo();
		}
		if(locationAlarm.getDistance()!=null){
			pageParam+="&distance="+locationAlarm.getDistance();
		}
		if(locationAlarm.getHandletype()!=null){
			pageParam+="&handletype="+locationAlarm.getHandletype();
		}
		if(locationAlarm.getHandleidea()!=null){
			pageParam+="&handleidea="+locationAlarm.getHandleidea();
		}
		if(locationAlarm.getVeo()!=null){
			pageParam+="&veo="+locationAlarm.getVeo();
		}
		model.addAttribute("pageParam", pageParam);
		return "location/showAlarm";
		
		
	}
	//修改
	@RequestMapping(value="/Alarm/updateAlarm")
	 public ModelAndView updateAlarm(String flag, String ids ,ModelAndView mv){
		if(flag.equals("1")){
			// 执行修改操作
			alarmService.modifyLocationLocationAlarm(ids);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/Alarm/selectAlarm");
		}
		// 返回
		return mv;
	}
}
