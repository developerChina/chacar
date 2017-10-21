package org.core.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访客短信确认
 * */
@Controller
public class VisitorAckController {
	
	@RequestMapping(value="/visitor/forwardVisitorAck")
	 public ModelAndView forwardVisitorAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/visitor-ack");
		// 返回ModelAndView
		return mv;
	}
}