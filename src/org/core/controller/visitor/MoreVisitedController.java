package org.core.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 多访客
 * */
@Controller
public class MoreVisitedController {
	/**
	 * 访客信息
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardMoreVisitor")
	 public ModelAndView forwardMoreVisitor(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/more-visitor");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 被访对象
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardMoreVisited")
	 public ModelAndView forwardMoreVisited(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/more-visited");
		// 返回ModelAndView
		return mv;
	}
	

}
