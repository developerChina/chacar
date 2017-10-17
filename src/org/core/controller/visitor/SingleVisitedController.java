package org.core.controller.visitor;

import java.util.Date;

import org.core.domain.visitor.VisitorInfo;
import org.core.service.visitor.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
		
/**
 * 单访客
 * */
@Controller
public class SingleVisitedController {
	@Autowired
	@Qualifier("visitorInfoService")
	private VisitorInfoService visitorInfoService;
	
	/**
	 * 跳转到单访客页面
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardSingleVisitor")
	public ModelAndView forwardSingleVisitor(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visitor");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 保存或者更新单访客，并且跳转到选择被访人选择页面
	 * @param visitorInfo
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardSingleVisited")
	public ModelAndView forwardSingleVisited(@ModelAttribute VisitorInfo visitorInfo,ModelAndView mv){
		visitorInfoService.saveOrUpdate(visitorInfo);
		//设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visited");
		//返回ModelAndView
		return mv;
	}
	
	
	
	/**
	 * 处理添加请求
	 */
	@RequestMapping(value = "/visitor/addtest")
	public ModelAndView addtest(@ModelAttribute VisitorInfo visitorInfo, ModelAndView mv) {
		visitorInfo.setVisitorID("338c763352d64442a7a4d4fa7b998fd9");
		visitorInfo.setCardNo("cardNo");;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证物理卡号' ,
		visitorInfo.setCardID("cardID");;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号' ,
		visitorInfo.setCardName("cardName");;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证姓名' ,
		visitorInfo.setCardSex("cardSex");;   //varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证性别' ,
		visitorInfo.setCardNation("cardNation");;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证名族' ,
		visitorInfo.setCardBirthday(new Date());;   //date NULL DEFAULT NULL COMMENT '身份证出生日期' ,
		visitorInfo.setCardAddress("cardAddress");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证地址' ,
		visitorInfo.setCardPhoto("cardPhoto");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证照片' ,
		visitorInfo.setPhoto1("photo1");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片1' ,
		visitorInfo.setRemarks("");
		// 执行添加操作
		visitorInfoService.update(visitorInfo);
//		visitorInfoService.save(visitorInfo);
		// 设置客户端跳转到查询请求
		// mv.setViewName("redirect:/visitor/single-visitor");
		// 返回
		return mv;
	}
}
