package org.core.controller.locationController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.core.domain.location.LocationClient;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationUser;
import org.core.service.location.UserService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LUserController {
		@Autowired
		@Qualifier("userService")
		private UserService userService;
		
		//查询
	@RequestMapping(value="/location/LUserAck")
	public ModelAndView elevatorAck(Integer pageIndex,
			@ModelAttribute LocationUser locationUser,
			Model model,ModelAndView mv){
					
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			/** 查询定位里的 用户表    */
			List<LocationUser> pagingList = userService.selectAll(pageModel,locationUser);
					
			model.addAttribute("pagingList", pagingList);
			model.addAttribute("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			mv.setViewName("location/showLuser");
			// 返回ModelAndView
			return mv;
		}
		//添加
			@RequestMapping(value="/location/addLuserAck")
			 public ModelAndView addElevator(
					 String flag,
					 @ModelAttribute LocationUser locationUser,
					 Model model,ModelAndView mv,
					 HttpServletRequest request){
				if(flag.equals("1")){
					// 设置跳转到添加页面
					//得到配件
						//1 分组  2客户
					List<LocationGroup> groupParts = userService.getGroupParts();
					model.addAttribute("groupParts",groupParts);
					List<LocationClient> clientParts = userService.getClientParts();
					model.addAttribute("clientParts",clientParts);
					mv.setViewName("/location/showAddLuser");
				}else{
					// 执行添加操作
					String[] Lgroupids = request.getParameterValues("ids");
					
					userService.addLocationUser(locationUser,Lgroupids);
					
					mv.setViewName("redirect:/location/LUserAck");
				}
				// 返回
				return mv;
			}
		//删除
			//删除
			@RequestMapping(value="/location/delLuserAck")
			 public ModelAndView delLuser(
					 String Luser,
					 ModelAndView mv){
			
				userService.delLocationUser(Luser);
				mv.setViewName("redirect:/location/LUserAck");
				
				return mv;
			}
			
		//修改用户	
			@RequestMapping(value="/location/updateLuserAck")
			 public ModelAndView updateLgroup(
					 String flag,String Luser,
					 @ModelAttribute LocationUser locationUser,
					 Model model,ModelAndView mv,
					 HttpServletRequest request){
				if(flag.equals("1")){
					// 设置跳转到添加页面
					LocationUser getUpdate = userService.getUpdateUser(Luser);
					model.addAttribute("getUpdate",getUpdate);
						//得到配件
					List<LocationGroup> groupParts = userService.getGroupParts();
					model.addAttribute("groupParts",groupParts);
					List<LocationClient> clientParts = userService.getClientParts();
					model.addAttribute("clientParts",clientParts);
					
					mv.setViewName("/location/showUpdateLuser");
				}else{
					//修改 多个分组的id
					String[] Lgroupids = request.getParameterValues("ids");
					userService.updateUser(Lgroupids,locationUser);
					mv.setViewName("redirect:/location/LUserAck");
				}
				return mv;
			}	
			
			
			
			
}
