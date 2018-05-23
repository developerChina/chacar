package org.core.controller.locationController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.location.LocationDriver;
import org.core.service.location.DriverService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理车主信息的控制器
 */
@Controller
public class LogisDriverController {
	@Autowired
	@Qualifier("driverService")
	private DriverService driverService;
	
	/**
	 * 车主管理 跳向首页 分页查询
	 */
	@RequestMapping(value="/driver/DriverAck")
			 public ModelAndView IslandAck(Integer pageIndex,
					 @ModelAttribute LocationDriver locationDriver,
					 ModelAndView mv,Model model){
		String pageParam="";
		if(locationDriver.getVehicleCode()!=null&&!"".equals(locationDriver.getVehicleCode())){
			pageParam+="&vehicleCode="+locationDriver.getVehicleCode();
		}
		if(locationDriver.getName()!=null){
			pageParam+="&name="+locationDriver.getName();
		}
		model.addAttribute("targetSupplier", locationDriver.getName());
		model.addAttribute("vehicleCode", locationDriver.getVehicleCode());
		model.addAttribute("pageParam", pageParam);
		
	    PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<LocationDriver> pageListD = driverService.selectIByPage(locationDriver, pageModel);
		
		mv.addObject("pageListD", pageListD);
		mv.addObject("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("location/showDriver");
		// 返回ModelAndView
		return mv;
	}
	
			@RequestMapping(value="/driver/addDriver")
			 public ModelAndView IslandAdd(String flag,
					 @ModelAttribute LocationDriver locationDriver,
					 ModelAndView mv){		
		if(flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("/location/showAddD");
		}else{
			// 执行添加
			driverService.addD(locationDriver);
			mv.setViewName("redirect:/driver/DriverAck");
		}
			return mv;
		}
	
	//ajax验证车牌号不能重复
			@ResponseBody
			@RequestMapping(value="/driver/addDValidate")
			public Object validate(HttpServletRequest request,
					 HttpServletResponse response){
				
				String vehicleCode = request.getParameter("vehicleCode");
				Map<String,Object> map = new HashMap<>();
				String flag = driverService.addValidate(vehicleCode);
					if(!"".equals(flag)){
						map.put("status", false);
						map.put("message", flag);
					}else{
						map.put("status", true);
						map.put("message", "验证通过");
					}
				return map;
			}	
			
	//删除	
			@RequestMapping(value = "/driver/delDriverAck")
			public ModelAndView delRoomAck(Integer id, ModelAndView mv) {
				driverService.delDriver(id);
				mv.setViewName("redirect:/driver/DriverAck");
				return mv;
			}
	//修改   
			@RequestMapping(value = "/driver/upDriverAck")
			public ModelAndView VipUpd(String flag, Integer id, 
				@ModelAttribute LocationDriver locationDriver, 
				ModelAndView mv) {
				if (flag.equals("1")) {
					// 设置跳转到修改页
					LocationDriver entity = driverService.getById(id);
					mv.addObject("entity", entity);
					mv.setViewName("/location/showUpdD");
				} else {
					// 执行修改
					driverService.upDriver(locationDriver);
					mv.setViewName("redirect:/driver/DriverAck");
				}
				// 返回ModelAndView
				return mv;
			}

}
