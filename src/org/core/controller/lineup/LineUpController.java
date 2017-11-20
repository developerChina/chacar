package org.core.controller.lineup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 排队叫号
 * 
 * 
 */
@Controller
public class LineUpController {

	@RequestMapping(value = "/lineup/forwardLineUp")
	public ModelAndView forwardLineUp(ModelAndView mv) {
		// 设置客户端跳转到查询请求
		mv.setViewName("lineup/lineup");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value = "/lineup/configLineUp")
	public ModelAndView configLineUp(ModelAndView mv) {
		// 设置客户端跳转到查询请求
		mv.setViewName("lineup/configLineUp");
		// 返回ModelAndView
		return mv;
	}
}
