package org.core.controller.webapp;

import javax.servlet.http.HttpSession;

import org.core.domain.webapp.User;
import org.core.service.webapp.HrmService;
import org.core.util.GlobleConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class MainAckController {
	
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	
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
	public ModelAndView leftAck(Model model,HttpSession session,ModelAndView mv)
	{
		System.out.println("============");
		/** 查询用户信息     */
		User users = (User)session.getAttribute(GlobleConstants.USER_SESSION);
		System.out.println(users.getLoginname());
		model.addAttribute("authlist", users);
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