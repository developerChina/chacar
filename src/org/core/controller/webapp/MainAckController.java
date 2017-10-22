package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class MainAckController {
	
	@RequestMapping(value="/loginAck")
	 public ModelAndView loginAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("loginForm");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/mainAck")
	 public ModelAndView mainAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("main");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value="/topAck")
	 public ModelAndView topAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("top");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value="/leftAck")
	 public ModelAndView leftAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("left");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value="/rightAck")
	 public ModelAndView rightAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("right");
		// 返回ModelAndView
		return mv;
	}
}