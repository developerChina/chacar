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
}