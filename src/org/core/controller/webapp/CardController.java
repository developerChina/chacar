package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.CardAccess;
import org.core.service.webapp.CardService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class CardController {
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("cardService")
	private CardService cardService;
	
	@RequestMapping(value="/card/cardAck")
	 public ModelAndView cardAck(Integer pageIndex,ModelAndView mv,@ModelAttribute CardAccess cardAccess){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<CardAccess> cards = cardService.selectByPage(cardAccess, pageModel);
		mv.addObject("cards", cards);
		mv.addObject("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("card/card");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * */
	@RequestMapping(value="/card/addEmpCard")
	 public ModelAndView addEmpCard(
			 String flag,
			 @ModelAttribute CardAccess cardAccess,
			 ModelAndView mv){
		if(flag.equals("1")){
			
			mv.setViewName("card/addEmpCard");
		}else{
			cardService.save(cardAccess); 
			mv.setViewName("redirect:/card/cardAck");
		}
		// 返回
		return mv;
		
	}
	
	/**
	 * 处理添加员工请求
	 * */
	@RequestMapping(value="/card/addDepCard")
	 public ModelAndView addDepCard(
			 String flag,
			 @ModelAttribute CardAccess cardAccess,
			 ModelAndView mv){
		if(flag.equals("1")){
			 
			mv.setViewName("card/addDepCard");
		}else{
			cardService.save(cardAccess);
			mv.setViewName("redirect:/card/cardAck");
		}
		// 返回
		return mv;
	}
	
	
	@RequestMapping(value="/card/removeCard")
	 public ModelAndView removeDept(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除部门
			cardService.deleteById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/card/cardAck");
		// 返回ModelAndView
		return mv;
	}
}