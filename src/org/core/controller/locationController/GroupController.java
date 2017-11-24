package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationGroup;
import org.core.service.location.LGroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GroupController {
		@Autowired
		@Qualifier("lGroupService")
		private LGroupService lGroupService;
		
		//查询
		@RequestMapping(value="/location/GroupAck")
		 public ModelAndView elevatorAck(Integer pageIndex,
				 @ModelAttribute LocationGroup locationGroup,
				 Model model,ModelAndView mv){
			
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			/** 查询定位里的 分组表    */
			List<LocationGroup> pagingList = lGroupService.selectAll(pageModel,locationGroup);
			
			model.addAttribute("pagingList", pagingList);
			model.addAttribute("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			mv.setViewName("location/showLgroup");
			// 返回ModelAndView
			return mv;
		}
		//添加
		@RequestMapping(value="/location/addLgroupAck")
		 public ModelAndView addElevator(
				 String flag,
				 @ModelAttribute LocationGroup locationGroup,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
				mv.setViewName("/location/showAddLgroup");
			}else{
				// 执行添加操作
				// 设置客户端跳转到查询请求
				lGroupService.addLocationGroup(locationGroup);
				mv.setViewName("redirect:/location/GroupAck");
			}
			// 返回
			return mv;
		}
		
		//删除
		@RequestMapping(value="/location/delLgroupAck")
		 public ModelAndView delLgroup(
				 int Lgroup,
				 ModelAndView mv){
		
				lGroupService.delLocationGroup(Lgroup);
				mv.setViewName("redirect:/location/GroupAck");
			
			return mv;
		}
		
		//修改
		@RequestMapping(value="/location/updateLgroupAck")
		 public ModelAndView updateLgroup(
				 String flag,String Lgroup,
				 @ModelAttribute LocationGroup locationGroup,
				 Model model,ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
				LocationGroup getUpdate = lGroupService.getUpdate(Integer.parseInt(Lgroup));
				model.addAttribute("getUpdate",getUpdate);
				mv.setViewName("/location/showUpdateLgroup");
			}else{
				lGroupService.updateLgroup(locationGroup);
				mv.setViewName("redirect:/location/GroupAck");
			}
			return mv;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
}
