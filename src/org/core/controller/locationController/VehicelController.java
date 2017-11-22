package org.core.controller.locationController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.core.domain.location.LocationClient;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationVehicel;
import org.core.service.location.UserService;
import org.core.service.location.VehicelService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VehicelController {
		@Autowired
		@Qualifier("vehicelService")
		private VehicelService vehicelService;
		@Autowired
		@Qualifier("userService")
		private UserService userService;
		//查询
		@RequestMapping(value="/LocationVehicel/LocationVehicelAck")
		 public String LocationVehicelAck(Integer pageIndex, @ModelAttribute LocationVehicel locationVehicel,Model model){
					PageModel pageModel = new PageModel();
					if(pageIndex != null){
						pageModel.setPageIndex(pageIndex);
			}
			/** 查询通道信息     */
			List<LocationVehicel> locationVehicels = vehicelService.findLocationVehicel(locationVehicel, pageModel);
			model.addAttribute("locationVehicels", locationVehicels);
			model.addAttribute("pageModel", pageModel);
			return "location/showLocationVehicels";
		}
		//删除
		@RequestMapping(value="/LocationVehicel/removeLocationVehicel")
		 public ModelAndView removeLocationVehicel(String ids,ModelAndView mv){
			// 分解id字符串
			String[] idArray = ids.split(",");
			for(String id : idArray){
				vehicelService.removeLocationVehicelById(id);
			}
				mv.setViewName("redirect:/LocationVehicel/LocationVehicelAck");
				return mv;
		}
		/*修改*/
		@RequestMapping(value="/LocationVehicel/updateLocationVehicel")
		 public ModelAndView updateLocationClient(
				 String flag,String vehicelid,
				 @ModelAttribute LocationVehicel locationVehicel,
				 Model model,ModelAndView mv,
				 HttpServletRequest request){
			if(flag.equals("1")){
				//修改前查询
				LocationVehicel getUpdate=vehicelService.getUpdate(vehicelid);
				model.addAttribute("getUpdate", getUpdate);
				//得到配件
				//1 分组  2客户
				List<LocationGroup> groupParts = userService.getGroupParts();
				model.addAttribute("groupParts",groupParts);
				List<LocationClient> clientParts = userService.getClientParts();
				model.addAttribute("clientParts",clientParts);
				
				mv.setViewName("location/showUpdateLocationVehicel");
			}else{
				
				//分组ids
				String[] Lgroupids = request.getParameterValues("cardIds");
				//外接设备 ids
				String[] wjsb = request.getParameterValues("ids");
				//执行修改
				vehicelService.UpdateVehicel(locationVehicel,Lgroupids,wjsb);
				mv.setViewName("redirect:/LocationVehicel/LocationVehicelAck");
			}
			return mv;
		}
		
		//添加
		@RequestMapping(value="/LocationVehicel/addLocationVehicel")
		 public ModelAndView addLocationClient(
				 String flag,
				 @ModelAttribute LocationVehicel locationVehicel,
				 Model model,ModelAndView mv,
				 HttpServletRequest request){
			if(flag.equals("1")){
				//得到配件
				//1 分组  2客户
			List<LocationGroup> groupParts = userService.getGroupParts();
			model.addAttribute("groupParts",groupParts);
			List<LocationClient> clientParts = userService.getClientParts();
			model.addAttribute("clientParts",clientParts);
			mv.setViewName("location/showAddLocationVehicel");
			}else{
				//分组ids
				String[] Lgroupids = request.getParameterValues("cardIds");
				//外接设备 ids
				String[] wjsb = request.getParameterValues("ids");
				//执行添加
				vehicelService.addLvehicel(locationVehicel,wjsb,Lgroupids);
				mv.setViewName("redirect:/LocationVehicel/LocationVehicelAck");
			}
			return mv;
	}
		
		
		
}
