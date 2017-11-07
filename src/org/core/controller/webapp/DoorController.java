package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Passageway;
import org.core.service.webapp.PassagewayService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//通道
/**
 * 转向首页
 * */
@Controller
public class DoorController { 
	@Autowired
	@Qualifier("passagewayService")
	private PassagewayService passagewayService;
	
	@RequestMapping(value="/door/doorAck")
	 public String doorAck(Integer pageIndex, @ModelAttribute Passageway passageway,Model model){
				PageModel pageModel = new PageModel();
				if(pageIndex != null){
					pageModel.setPageIndex(pageIndex);
		}
		/** 查询通道信息     */
		List<Passageway> passageways = passagewayService.findPassageway(passageway, pageModel);
		model.addAttribute("passageways", passageways);
		model.addAttribute("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		
		// 返回ModelAndView
		return "dept/door";

	}
			//删除
	@RequestMapping(value="/door/removePassageway")
	 public ModelAndView removePassageway(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			int count=passagewayService.selectPassagewayGroupByid(id);
			if(count>0){
				
			}else{
				// 根据id删除通道
				passagewayService.removePassagewayById(Integer.parseInt(id));
			}
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/door/doorAck");
		// 返回ModelAndView
		return mv;
	}
	
	/*修改*/
	@RequestMapping(value="/door/updatePassageway")
	 public ModelAndView updatePassageway(String flag,@ModelAttribute Passageway passageway,ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询通道
			Passageway ggy = passagewayService.findPassagewayById(passageway.getPassagewayID());
			// 设置Model数据
			mv.addObject("passageway", ggy);
			// 返回修改通道页面
			mv.setViewName("dept/showUpdatePassageway");
			
		}else{
			// 执行修改操作
			passagewayService.modifyPassageway(passageway);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/door/doorAck");
		}
		// 返回
		return mv;
	}
	//添加
	@RequestMapping(value="/door/addPassageway")
	 public ModelAndView addPassageway(
			 String flag,
			 @ModelAttribute Passageway passageway,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddPassageway");
		}else{
			// 执行添加操作
			passagewayService.addPassageway(passageway);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/door/doorAck");
		}
		// 返回
		return mv;
	
}}