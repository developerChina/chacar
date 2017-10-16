package org.core.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 单访客
 * */
@Controller
public class SingleVisitedController {
	
	@RequestMapping(value="/visitor/forwardSingleVisited")
	 public ModelAndView forwardSingleVisited(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visited");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/visitor/forwardSingleVisitor")
	 public ModelAndView forwardSingleVisitor(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visitor");
		// 返回ModelAndView
		return mv;
	}
}
