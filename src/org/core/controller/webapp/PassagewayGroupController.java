package org.core.controller.webapp;
import java.util.List;

import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.service.webapp.PassagewayGroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
/*
 *  通道的分组控制器
 */
public class PassagewayGroupController {
	/**
	 * 自动注入GroupService
	 * */
	@Autowired
	@Qualifier("passagewayGroupService")
	private PassagewayGroupService passagewayGroupService;
	//查询
	@RequestMapping(value = "/passagewayGroup/doorSplit")
	public String doorSplit(Integer pageIndex, @ModelAttribute PassagewayGroup passagewayGroup,Model model) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
			}
		List<PassagewayGroup> passagewayGroups = passagewayGroupService.findPassagewayGroup(passagewayGroup, pageModel);
		for (PassagewayGroup ags : passagewayGroups) {
			String selectids=ags.getPgssxj();
			List<Passageway> saveaccesss = passagewayGroupService.getPassagewayById(selectids);
			for (Passageway passageway : saveaccesss) {
				ags.getOrderItems().add(passageway);
			}
		}
		model.addAttribute("passagewayGroups", passagewayGroups);
		model.addAttribute("pageModel", pageModel);
		return "group/showpassagewaygroup";
	}
	//删除
	@RequestMapping(value = "/passagewayGroup/removepassagewayGroup")
	public ModelAndView removepassagewayGroup(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			passagewayGroupService.removePassagewayGroupById(id);
		}
		mv.setViewName("redirect:/passagewayGroup/doorSplit");
		return mv;
	}
	//添加通道分组
	@RequestMapping(value="/passagewayGroup/addpassagewayGroup")
	public ModelAndView addpassagewayGroup(String flag,String ids,Model model,ModelAndView mv,String pgname){
		if(flag.equals("1")){
			List<Passageway> pgPassageways=passagewayGroupService.selectPGSubordinate();
			model.addAttribute("pgPassageways", pgPassageways);
			mv.setViewName("/group/addPGroup");
			return mv;
		}else{
			passagewayGroupService.addPGroup(ids,pgname);
			mv.setViewName("redirect:/passagewayGroup/doorSplit");
			return mv;
		}
	}
	//修改
	@RequestMapping(value="/passagewayGroup/UpdatePG")
	public ModelAndView  UpdatePG(String id,String flag,Model model, @ModelAttribute PassagewayGroup passagewayGroup,ModelAndView mv){
		if(flag.equals("1")){
			PassagewayGroup passagewayGroupByid = passagewayGroupService.selectPGbyId(id);
			List<Passageway> pgGroups=passagewayGroupService.selectPGSubordinate();
			model.addAttribute("passagewayGroupByid", passagewayGroupByid);
			model.addAttribute("pgGroups", pgGroups);
			mv.setViewName("group/showUpdatePG");
		}else{
			passagewayGroupService.updatePG(passagewayGroup);
			mv.setViewName("redirect:/passagewayGroup/doorSplit");
		}
		return mv;
	}
}
