package org.core.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 打印
 * */
@Controller
public class PrintAllController {
	
	@RequestMapping(value="/visitor/forwardAllPrint")
	 public ModelAndView forwardAllPrint(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/all-print");
		// 返回ModelAndView
		return mv;
	}
}
