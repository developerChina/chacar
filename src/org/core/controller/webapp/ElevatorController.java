package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class ElevatorController {
	
	@RequestMapping(value="/elevator/elevatorAck")
	 public ModelAndView elevatorAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("dept/elevator");
		// 返回ModelAndView
		return mv;
	}
}