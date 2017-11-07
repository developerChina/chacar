 package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.service.webapp.AccessGroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理门禁分组请求控制器
 * 
 */
@Controller
public class AccessGroupController {
	/**
	 * 自动注入GroupService
	 * */
	@Autowired 
	@Qualifier("accessgroupService")
	private AccessGroupService accessgroupService;
	//查询
	@RequestMapping(value = "/accessGroup/floorSplit")
	public String floorSplit(Integer pageIndex, @ModelAttribute AccessGroup accessGroup,Model model) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
			}
		List<AccessGroup> accessGroups = accessgroupService.findAccessGroup(accessGroup, pageModel);
		for (AccessGroup ags : accessGroups) {
			String selectids=ags.getAgssxj();
			List<Access> saveaccesss = accessgroupService.getAccessById(selectids);
			for (Access access : saveaccesss) {
				//把查到的门禁集合挨个添加到门禁组的一个集合中
				ags.getOrderItems().add(access);
			}
		}
		model.addAttribute("accessGroups", accessGroups);
		model.addAttribute("pageModel", pageModel);
		return "group/showaccessgroup";
	}
	//删除
	@RequestMapping(value="/accessGroup/removeAccessGroup")
	 public ModelAndView removeAccessGroup(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			accessgroupService.removeAccessGroupById(id);
		}
		mv.setViewName("redirect:/accessGroup/floorSplit");
		return mv;
	}
	//添加门禁分组
	@RequestMapping(value="/accessGroup/addaccessGroup")
		public ModelAndView addaccessGroup(String flag,String ids,Model model,ModelAndView mv,String agname){
			if(flag.equals("1")){
				List<Access> agAccesss=accessgroupService.selectAGSubordinate();
				model.addAttribute("agAccesss", agAccesss);
				mv.setViewName("/group/addAGroup");
				return mv;
			}else{
				accessgroupService.addAGroup(ids,agname);
				mv.setViewName("redirect:/accessGroup/floorSplit");
				return mv;
			}
	}
	/*
	 *  修改门禁组
	 */
	@RequestMapping(value = "/accessGroup/updateAG")
	public ModelAndView updateAG(String id,String flag,Model model,
			@ModelAttribute AccessGroup accessGroup,
			ModelAndView mv) {
		//System.out.println(id);
		
		if(flag.equals("1")){
			AccessGroup accessgroupById = accessgroupService.selectAGbyId(id);
			List<Access> agAlevators= accessgroupService.selectAGSubordinate();
			model.addAttribute("agAlevators",agAlevators);
			model.addAttribute("accessgroupById", accessgroupById);
			// 设置客户端跳转到查询请求
			mv.setViewName("group/showUpdateAG");
			
			
		}else{
			 accessgroupService.updateAG(accessGroup);
			 mv.setViewName("redirect:/accessGroup/floorSplit");
			
		}
		return mv;
	}

	
	
	
}
