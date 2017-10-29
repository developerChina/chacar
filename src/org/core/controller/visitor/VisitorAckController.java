package org.core.controller.visitor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访客短信确认
 * */
@Controller
public class VisitorAckController {
	
	@RequestMapping(value="/visitor/forwardVisitorAck")
	 public ModelAndView forwardVisitorAck(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,String recordid){
		mv.addObject("recordid", recordid);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/visitor-ack");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/visitor/queryVisitorAck")
	 public ModelAndView queryVisitorAck(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,String recordid){
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/queryVisitor");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/visitor/blackAck")
	 public ModelAndView blackAck(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,String recordid){
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/black");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/visitor/reasonAck")
	 public ModelAndView reasonAck(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,String recordid){
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/reason");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value="/visitor/visitorCardAck")
	 public ModelAndView visitorCardAck(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,String recordid){
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/visitorCard");
		// 返回ModelAndView
		return mv;
	}
}