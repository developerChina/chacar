package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class ResourcesController {
	
	@RequestMapping(value="/resource/resourcesAck")
	 public ModelAndView resourcesAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("user/resources");
		// 返回ModelAndView
		return mv;
	}
}