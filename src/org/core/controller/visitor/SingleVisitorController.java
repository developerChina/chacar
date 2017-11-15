package org.core.controller.visitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.visitor.VisitorInfoService;
import org.core.util.GenId;
import org.core.util.ImageUtils;
import org.core.util.JsonUtils;
import org.core.util.StringUtils;
import org.core.util.tag.PageModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
		
/**
 * 单访客
 * */
@Controller
public class SingleVisitorController {
	@Autowired		
	@Qualifier("visitorInfoService")
	private VisitorInfoService visitorInfoService;
	@Autowired
	@Qualifier("visitorRecordService")
	private VisitorRecordService visitorRecordService;
	@Autowired
	@Qualifier("recordVisitorsService")
	private RecordVisitorsService recordVisitorsService;
	@Autowired
	@Qualifier("recordBevisitedsService")
	private RecordBevisitedsService recordBevisitedsService;
	
	/**		
	 * 单访客登记
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardSingleVisitor")
	public ModelAndView forwardSingleVisitor(HttpServletRequest request,HttpServletResponse response,ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visitor");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 保存或者更新单访客，访问记录，记录访客名单，并且跳转到选择被访人选择页面
	 * @param visitorInfo
	 * @param mv
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/visitor/forwardSingleVisited")
	public ModelAndView forwardSingleVisited(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute RecordVisitors recordVisitors,ModelAndView mv){
		
		//存储或修改访客信息
		VisitorInfo visitorInfo=new VisitorInfo();
		BeanUtils.copyProperties(recordVisitors, visitorInfo);
		
		//base64转图片
		String uploadRootPath=request.getSession().getServletContext().getRealPath("/");
		String cardPhoto=ImageUtils.cardPhoto + visitorInfo.getCardID() + ".jpg";
		String photo1=ImageUtils.photo1 + GenId.UUID() + ".jpg";
		if(StringUtils.isNotBlank(visitorInfo.getCardPhoto())){
			ImageUtils.generateImage(visitorInfo.getCardPhoto(), uploadRootPath+cardPhoto);
			visitorInfo.setCardPhoto(cardPhoto);
			recordVisitors.setCardPhoto(cardPhoto);
		}
		if(StringUtils.isNotBlank(visitorInfo.getPhoto1())){
			ImageUtils.generateImage(visitorInfo.getPhoto1(), uploadRootPath+photo1);
			visitorInfo.setPhoto1(photo1);
			recordVisitors.setPhoto1(photo1);
		}		
		
		String visitorID =visitorInfoService.saveOrUpdate(visitorInfo);
		mv.addObject("recordVisitors", JsonUtils.toJson(recordVisitors).replaceAll("\"", "\'"));
		//设置客户端跳转到查询请求
		mv.setViewName("visitor/single-visited");
		//返回ModelAndView
		return mv;
	}
	
	/**
	 * 根据身份证号码查找对应访客信息
	 * @param cardid
	 * @return
	 */
	@RequestMapping(value="/visitor/getVisitorBycardID")
	@ResponseBody
	public Object getVisitorBycardID(HttpServletRequest request,HttpServletResponse response,String cardid){
		VisitorInfo visitorInfo=visitorInfoService.selectOneBycardID(cardid);
		return visitorInfo;
	}
	
	
	/**
	 * 访客列表查询
	 * @param model
	 * @param pageIndex
	 * @param dept
	 * @return
	 */
	@RequestMapping(value="/visitor/selectVisitor")
	 public String selectDept(Model model,Integer pageIndex,
			 @ModelAttribute VisitorInfo visitorInfo){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<VisitorInfo> visitors=visitorInfoService.selectByPage(visitorInfo,pageModel);
		model.addAttribute("visitors", visitors);
		model.addAttribute("pageModel", pageModel);
		return "visitor/visitors";
	}
	
}
