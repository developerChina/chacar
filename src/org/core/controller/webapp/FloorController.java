package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Access;
import org.core.service.webapp.AccessService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class FloorController {
	@Autowired
	@Qualifier("accessService")
	private AccessService accessService;
	@RequestMapping(value="/floor/floorAck")
	 public String floorAck(Integer pageIndex, @ModelAttribute Access access,Model model){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
			}
		List<Access> accesss = accessService.findAccess(access, pageModel);
		model.addAttribute("accesss", accesss);
		model.addAttribute("pageModel", pageModel);
		// 返回ModelAndView
		return "dept/floor";
	}
		//删除
	@RequestMapping(value="/floor/removeAccess")
	public ModelAndView removeAccess(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除通道
			accessService.removeAccessById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/floor/floorAck");
		// 返回ModelAndView
		return mv;
	}
	//修改
	@RequestMapping(value="/floor/updateAccess")
	 public ModelAndView updateAccess(
			 
			 String flag,
			 @ModelAttribute Access access,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询通道
			Access sggy = accessService.findAccessById(access.getAccessid());
			// 设置Model数据
			mv.addObject("access", sggy);
			// 返回修改通道页面
			mv.setViewName("dept/showUpdateAccess");
			
		}else{ 
			// 执行修改操作
			accessService.modifyAccess(access);;
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/floor/floorAck");
		}
		// 返回
		return mv;
	}
	//添加
	@RequestMapping(value="/floor/addAccess")
	 public ModelAndView addAccess(
			 String flag,
			 @ModelAttribute Access access,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddAccess");
		}else{
			// 执行添加操作
			accessService.addAccess(access);;
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/floor/floorAck");
		}
		// 返回
		return mv;
	
}
}