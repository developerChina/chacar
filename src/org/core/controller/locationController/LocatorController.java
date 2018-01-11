package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationLocator;
import org.core.service.location.LocatorService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocatorController {
	@Autowired
	@Qualifier("locatorService")
	private LocatorService locatorService;
	//查询
	@RequestMapping(value="/Locator/selectLocator")
	public String selectLocator(Integer pageIndex,
			 @ModelAttribute LocationLocator locationLocator,
			 Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<LocationLocator> locationLocators = locatorService.findLocationLocator(locationLocator, pageModel);
		model.addAttribute("locationLocators", locationLocators);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("model", locationLocator);
		
		String pageParam="";
		if(locationLocator.getName()!=null){
			pageParam+="&name="+locationLocator.getName();
		}
		if(locationLocator.getDeviceid()!=null){
			pageParam+="&deviceid="+locationLocator.getDeviceid();
		}
		if(locationLocator.getSim()!=null){
			pageParam+="&sim="+locationLocator.getSim();
		}
		model.addAttribute("pageParam", pageParam);
		return "location/showLocationLocator";
	}
	//删除
	@RequestMapping(value="/Locator/removeLocationLocator")
	 public ModelAndView removeLocationClient(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			 
				// 根据id删除通道
			locatorService.removeLocationLocatorById(id);
			
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/Locator/selectLocator");
		// 返回ModelAndView
		return mv;
	}
	
	/*修改*/
	@RequestMapping(value="/Locator/updateLocationLocator")
	 public ModelAndView updateLocationLocator(String flag,@ModelAttribute LocationLocator locationLocator,ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询通道
			LocationLocator ggy = locatorService.findLocationLocatorById(locationLocator.getId());
			// 设置Model数据
			mv.addObject("locationLocator", ggy);
			// 返回修改通道页面
			mv.setViewName("location/showUpdateLocationLocator");
			
		}else{
			// 执行修改操作
			locatorService.modifyLocationLocator(locationLocator);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/Locator/selectLocator");
		}
		// 返回
		return mv;
	}
	//添加
	@RequestMapping(value="/Locator/addLocationLocator")
	 public ModelAndView addLocationLocator(
			 String flag,
			 @ModelAttribute LocationLocator locationLocator,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("location/showAddLocationLocator");
		}else{
			// 执行添加操作
			locatorService.addLocationLocator(locationLocator);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/Locator/selectLocator");
		}
		// 返回
		return mv;
	}
}