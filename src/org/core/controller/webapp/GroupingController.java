package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.ElevatorGroup;
import org.core.service.webapp.GroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理分组请求控制器
 * 
 */
@Controller
public class GroupingController {
	/**
	 * 自动注入GroupService
	 * */
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	
	
	/*
	 * 电梯分组的控制器
	 */
	@RequestMapping(value = "/Grouping/elevatorSplit")
	public ModelAndView elevatorSplit(Integer epageIndex,
			 @ModelAttribute ElevatorGroup elevatorGroup,
			 Model model,ModelAndView mv) {
		
		PageModel pageModel = new PageModel();
		if(epageIndex != null){
			pageModel.setPageIndex(epageIndex);
		}
		
		List<ElevatorGroup> elevatorGroups = groupService.findElevatorGroup(elevatorGroup, pageModel);
		
		for (ElevatorGroup myE : elevatorGroups) {
			  
			String selectids = myE.getEgssxj();
			List<Elevator> addElevators =groupService.getElevatorByEGid(selectids);
			for (Elevator myelevator : addElevators) {
				
				myE.getOrderItems().add(myelevator);
			}
			
		}
		model.addAttribute("elevatorGroups", elevatorGroups);
		model.addAttribute("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("group/showElevatorGroup");
		// 返回ModelAndView
		return mv;
	}
	
	//批量删除
	@RequestMapping(value = "/Grouping/removeEgroup")
	public ModelAndView removeEgroup(String ids,ModelAndView mv) {
		
		String[] idArry = ids.split(",");
		for (String id : idArry) {
			groupService.EgroupDelByEGid(id);
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/Grouping/elevatorSplit");
		// 返回ModelAndView
		return mv;
	}
	//添加分组
	@RequestMapping(value = "/Grouping/addEGroup")
	public ModelAndView addEGroup(String flag,String ids,Model model,ModelAndView mv,
			String egname) {
		if(flag.equals("1")){
			//执行查询所有‘下级单位’的方法 并跳到对应界面！
			List<Elevator> egElevators= groupService.selectEGSubordinate();
			model.addAttribute("egElevators",egElevators);
			// 设置客户端跳转到查询请求
			mv.setViewName("group/addEGroup");
			// 返回ModelAndView
			return mv;
		}else{
			groupService.addEGroup(ids,egname);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/Grouping/elevatorSplit");
			// 返回ModelAndView
			return mv;
		}
		
	}
	
	
	
	
	/*
	 *  修改电梯组
	 */
	@RequestMapping(value = "/Grouping/updateEG")
	public ModelAndView floorSplit(String id,String flag,Model model,
			@ModelAttribute ElevatorGroup elevatorGroup,
			ModelAndView mv) {
		//System.out.println(id);
		
		if(flag.equals("1")){
			ElevatorGroup groupById = groupService.selectEGbyId(id);
			List<Elevator> egElevators= groupService.selectEGSubordinate();
			model.addAttribute("egElevators",egElevators);
			model.addAttribute("groupById", groupById);
			// 设置客户端跳转到查询请求
			mv.setViewName("group/showUpdateEG");
		}else{
			 groupService.updateEG(elevatorGroup);
			 mv.setViewName("redirect:/Grouping/elevatorSplit");
		}
		
		return mv;
	}
	
	
	
	
	
}
