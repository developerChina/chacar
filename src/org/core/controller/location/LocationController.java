package org.core.controller.location;

import javax.servlet.http.HttpSession;

import org.core.util.GlobleConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class LocationController {
	@RequestMapping(value="/location/login")
	 public ModelAndView login(HttpSession session,ModelAndView mv){
		session.removeAttribute(GlobleConstants.USER_SESSION);
		mv.setViewName("location/location");
		return mv;
	}
	
	@RequestMapping(value="/location/illegal")
	 public ModelAndView illegal(HttpSession session,ModelAndView mv){
		session.removeAttribute(GlobleConstants.USER_SESSION);
		mv.setViewName("location/illegal");
		return mv;
	}
}