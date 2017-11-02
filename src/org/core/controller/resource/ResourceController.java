package org.core.controller.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.resource.ResourceInfo;
import org.core.service.resource.ResourceInfoService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class ResourceController {
	/**
	 * 自动注入resourceInfoService
	 * */
	@Autowired
	@Qualifier("resourceInfoService")
	private ResourceInfoService resourceInfoService;
	
	@RequestMapping(value="/resource/resourcesAck")
	 public ModelAndView resourcesAck(Integer pageIndex,ModelAndView mv,@ModelAttribute ResourceInfo resourceInfo){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<ResourceInfo> resources = resourceInfoService.selectByPage(resourceInfo, pageModel);
		mv.addObject("resources", resources);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("resource/resources");
		return mv;
	}
	
	@RequestMapping(value="/resource/selectAll")
	@ResponseBody
	public Object selectAll(HttpServletRequest request, HttpServletResponse response){
		List<ResourceInfo> resources= resourceInfoService.selectAll();
		return resources;
	}
	
	@RequestMapping(value="/resource/addResource")
	 public ModelAndView addResource(
			 String flag,
			 @ModelAttribute ResourceInfo resourceInfo,
			 ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("resource/resourceAdd");
		}else{
			resourceInfoService.save(resourceInfo); 
			mv.setViewName("redirect:/resource/resourcesAck");
		}
		return mv;
	}
	
	@RequestMapping(value="/resource/updateResource")
	 public ModelAndView updateResource(
			 String flag,
			 @ModelAttribute ResourceInfo resourceInfo,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询部门
			ResourceInfo target = resourceInfoService.selectById(resourceInfo.getId());
			// 设置Model数据
			mv.addObject("resource", target);
			// 设置跳转到修改页面
			mv.setViewName("resource/resourceUpdate");
		}else{
			// 执行修改操作
			resourceInfoService.update(resourceInfo);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/resource/resourcesAck");
		}
		// 返回
		return mv;
	}
	
	@RequestMapping(value="/resource/removeResource")
	 public ModelAndView removeResource(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			resourceInfoService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/resource/resourcesAck");
		return mv;
	}
}