package org.core.controller.visitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.webapp.Reson;
import org.core.service.webapp.ResonService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 访客短信确认
 */
@Controller
public class VisitorAckController {

	@RequestMapping(value = "/visitor/forwardVisitorAck")
	public ModelAndView forwardVisitorAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		mv.addObject("recordid", recordid);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/visitor-ack");
		// 返回ModelAndView
		return mv;
	}

	@RequestMapping(value = "/visitor/queryVisitorAck")
	public ModelAndView queryVisitorAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/queryVisitor");
		// 返回ModelAndView
		return mv;
	}

	@RequestMapping(value = "/visitor/blackAck")
	public ModelAndView blackAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/black");
		// 返回ModelAndView
		return mv;
	}

	/*
	 * =============================================================================
	 */
	/*
	 * =============================================================================
	 */
	/* ====================================高源=================================== */
	/* ====================================访问事由======================== */
	/*
	 * =============================================================================
	 */
	/*
	 * =============================================================================
	 */
	@Autowired
	@Qualifier("resonService")
	private ResonService resonService;

	@RequestMapping(value = "/visitor/reasonAck")
	public String reasonAck(Integer pageIndex, @ModelAttribute Reson reson, Model model) {

		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		// 查询事由
		List<Reson> resons = resonService.findReson(reson, pageModel);
		model.addAttribute("resons", resons);
		model.addAttribute("pageModel", pageModel);
		// 设置客户端跳转到查询请求

		// 返回ModelAndView

		return "employee/reason";

	}

	// 删除
	@RequestMapping(value = "/visitor/removeReson")
	public ModelAndView removeReson(String ids, ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			// 根据id删除通道
			resonService.removeResonById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/visitor/reasonAck");
		// 返回ModelAndView
		return mv;
	}

	/* 修改 */
	@RequestMapping(value = "/visitor/updateReson")
	public ModelAndView updateReson(String flag, @ModelAttribute Reson reson, ModelAndView mv) {
		if (flag.equals("1")) {
			// 根据id查询通道
			Reson ggy = resonService.findResonById(reson.getRid());
			// 设置Model数据
			mv.addObject("reson", ggy);
			// 返回修改通道页面
			mv.setViewName("dept/showUpdateReson");

		} else {
			// 执行修改操作
			resonService.modifyReson(reson);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/visitor/reasonAck");
		}
		// 返回
		return mv;
	}

	// 添加
	@RequestMapping(value = "/visitor/addReson")
	public ModelAndView addReson(String flag, @ModelAttribute Reson reson, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddReson");
		} else {
			// 执行添加操作
			resonService.addReson(reson);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/visitor/reasonAck");
		}
		// 返回
		return mv;

	}

	@RequestMapping(value = "/visitor/visitorCardAck")
	public ModelAndView visitorCardAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		// 设置客户端跳转到查询请求
		mv.setViewName("employee/visitorCard");
		// 返回ModelAndView
		return mv;
	}
	
	@RequestMapping(value = "/visitor/trajectoryAck")
	public ModelAndView visitortrajectoryAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/trajectory");
		// 返回ModelAndView
		return mv;
	}
}