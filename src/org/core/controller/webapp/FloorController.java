package org.core.controller.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.webapp.Access;
import org.core.service.webapp.AccessService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//门禁
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
			 String flag,@ModelAttribute Access access,
			 HttpServletResponse response,
			 ModelAndView mv,Model model){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddAccess");
		}else{
				accessService.addAccess(access);
				mv.setViewName("redirect:/floor/floorAck");
		}
			return mv;
		}
	@ResponseBody
	@RequestMapping(value="/floor/addValidate")
	public Object addValidate(HttpServletRequest request,
			 HttpServletResponse response){
		Access access=new Access();
		access.setCip(request.getParameter("cip"));
		access.setCsn(request.getParameter("csn"));
		access.setFloorno(Integer.parseInt(request.getParameter("fno")));
		access.setAcno(Integer.parseInt(request.getParameter("cno")));
		List<Access> testList = accessService.getList(access);
		//定义一个判断量
		String test="";
		for (Access testA : testList) {
			//判断楼层是否一样
			if(testA.getAcno().equals(access.getAcno())){
				test+="门编号重复";
			}
			if(!testA.getFloorno().equals(access.getFloorno())){
				test="楼层不一致";
			}
		}
		Map<String,Object> map = new HashMap<>();
		if(!"".equals(test)){
			map.put("status", false);
			map.put("message", test);
		}else{
			map.put("status", true);
			map.put("message", "验证通过");
		}
		return map;
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="/floor/delValidate")
	public Object delValidate(HttpServletRequest request,
			 HttpServletResponse response){
		String myids =request.getParameter("ids");
		System.out.println("到这了"+myids);
		return null;
	}*/
	
}