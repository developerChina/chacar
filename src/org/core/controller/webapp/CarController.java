package org.core.controller.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class CarController {
	/**
	 * 车场分类
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carClass")
	 public ModelAndView carClass(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("car/carClass");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 车识别仪管理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carDistinguish")
	 public ModelAndView carDistinguish(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("car/carDistinguish");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 车辆绑定授权
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carAuthority")
	 public ModelAndView carAuthority(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("car/carAuthority");
		// 返回ModelAndView
		return mv;
	}
}