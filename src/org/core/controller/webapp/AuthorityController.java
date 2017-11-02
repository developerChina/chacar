package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class AuthorityController {
	
	@RequestMapping(value="/authority/authorityAck")
	 public ModelAndView authorityAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("user/authority");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 门禁绑定授权
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/authority/floorBindAck")
	 public ModelAndView floorBindAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("dept/floorBind");
		// 返回ModelAndView
		return mv;
	}
}