package org.core.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向訪客最終首页
 * */
@Controller
public class FinalAckController {
	
	@RequestMapping(value="/finalAck")
	 public ModelAndView finalAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/visitor-final");
		// 返回ModelAndView
		return mv;
	}
}