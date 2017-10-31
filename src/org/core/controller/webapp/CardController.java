package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class CardController {
	
	@RequestMapping(value="/card/cardAck")
	 public ModelAndView cardAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/card");
		// 返回ModelAndView
		return mv;
	}
}