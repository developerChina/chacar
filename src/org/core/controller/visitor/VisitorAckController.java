package org.core.controller.visitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Blacklist;
import org.core.domain.webapp.Reson;
import org.core.service.visitor.VisitorInfoService;
import org.core.service.visitor.VisitorService;
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

	@Autowired
	@Qualifier("visitorService")
	private VisitorService visitorService;
	@Autowired
	@Qualifier("visitorInfoService")
	private VisitorInfoService visitorInfoService;

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

	/**
	 * 访问事由
	 * 
	 * @param request
	 * @param response
	 * @param mv
	 * @param recordid
	 * @param pageIndex
	 * @param model
	 * @param blacklist
	 * @return
	 */
	@RequestMapping(value = "/visitor/blackAck")
	public ModelAndView blackAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid, Integer pageIndex, Model model, @ModelAttribute Blacklist blacklist) {

		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询黑名单的信息 */
		List<Blacklist> blacks = visitorService.findBlacklist(pageModel, blacklist);
		model.addAttribute("blacks", blacks);
		model.addAttribute("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitorManage/visitorMain");
		// 返回ModelAndView
		return mv;
	}

	// 1_2.取消拉黑
	@RequestMapping(value = " /visitor/cancelblack")
	public ModelAndView cancelBlack(@ModelAttribute Blacklist blacklist, ModelAndView mv) {
		int id = blacklist.getBlacklistID();
		// 执行取消黑名单
		visitorService.remove(id);

		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/visitor/blackAck");
		// 返回ModelAndView
		return mv;
	}

	// 1_3 手动添加到黑名单
	@RequestMapping(value = "/visitor/blackManual")
	public ModelAndView blackManual(ModelAndView mv, String flag, @ModelAttribute Blacklist blacklist) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("visitorManage/addManualBlack");
		} else {
			// 执行添加操作
			visitorService.addBlacklist(blacklist);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/visitor/blackAck");
		}
		// 返回ModelAndView
		return mv;
	}

	// 1_4 自动添加到黑名单（将访客拉黑）
	@RequestMapping(value = "visitor/blackAutomatic")
	public ModelAndView blackAutomatic(ModelAndView mv, Model model, Integer pageIndex,
			@ModelAttribute VisitorInfo entity) {

		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}

		List<VisitorInfo> selectByPage = visitorService.selectByPage(pageModel, entity);
		model.addAttribute("selectByPage", selectByPage);
		model.addAttribute("pageModel", pageModel);

		// 设置跳转到显示页面
		mv.setViewName("visitorManage/showVisitor");
		return mv;
	}

	@RequestMapping(value = "/visitor/confirm")
	public ModelAndView confirm(ModelAndView mv, String id, Model model) {
		VisitorInfo selectById = visitorInfoService.selectById(id);
		model.addAttribute("selectById", selectById);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitorManage/addVBlack");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 访问事由
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