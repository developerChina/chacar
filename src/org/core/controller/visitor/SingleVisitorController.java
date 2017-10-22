package org.core.controller.visitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.visitor.VisitorRecord;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.visitor.VisitorInfoService;
import org.core.util.GenId;
import org.core.util.ImageUtils;
import org.core.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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
	public ModelAndView forwardSingleVisitor(ModelAndView mv,String recordid){
		if(StringUtils.isNotBlank(recordid)){
			List<RecordVisitors> list=recordVisitorsService.selectVisitorByRecordId(recordid);
			if(list.size()>0){
				mv.addObject("modle",list.get(0));
			}
		}
		mv.addObject("recordid",recordid);
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
	@RequestMapping(value="/visitor/forwardSingleVisited")
	public ModelAndView forwardSingleVisited(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute RecordVisitors recordVisitors,ModelAndView mv){
		//存储或修改访客信息
		VisitorInfo visitorInfo=new VisitorInfo();
		BeanUtils.copyProperties(recordVisitors, visitorInfo);
		
		//base64转图片
		String uploadRootPath=request.getSession().getServletContext().getRealPath("/");
		System.out.println(uploadRootPath);
		String cardPhoto=ImageUtils.cardPhoto+GenId.UUID()+".jpg";
		String photo1=ImageUtils.photo1+GenId.UUID()+".jpg";
		if(StringUtils.isNotBlank(visitorInfo.getCardPhoto())){
			ImageUtils.generateImage(visitorInfo.getCardPhoto(), uploadRootPath+cardPhoto);
			visitorInfo.setCardPhoto(cardPhoto);
		}
		if(StringUtils.isNotBlank(visitorInfo.getPhoto1())){
			ImageUtils.generateImage(visitorInfo.getPhoto1(), uploadRootPath+photo1);
			visitorInfo.setPhoto1(photo1);
		}
		
		String visitorID =visitorInfoService.saveOrUpdate(visitorInfo);
		//存储访问记录
		VisitorRecord visitorRecord=new VisitorRecord();
		visitorRecord.setRecordID(recordVisitors.getRecordID());
		String recordID=visitorRecordService.saveOrUpdate(visitorRecord);
		//删除访问记录人员
		recordVisitorsService.deleteByRecordID(recordID);
		//存储访问记录访客信息
		recordVisitors.setRecordID(recordID);
		recordVisitors.setVisitorID(visitorID);
		recordVisitorsService.save(recordVisitors);
		//设置前台记录ID
		mv.addObject("recordid", recordID);
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
	public Object getVisitorBycardID(String cardid){
		VisitorInfo visitorInfo=visitorInfoService.selectOneBycardID(cardid);
		return visitorInfo;
	}
	
	
	/**
	 * 测试用例
	 */
	@RequestMapping(value = "/visitor/test")
	@ResponseBody
	public Object test(HttpServletRequest request,HttpServletResponse response) {
//		visitorInfo.setVisitorID("338c763352d64442a7a4d4fa7b998fd9");
//		visitorInfo.setCardNo("cardNo");;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证物理卡号' ,
//		visitorInfo.setCardID("cardID");;   //varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号' ,
//		visitorInfo.setCardName("cardName");;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证姓名' ,
//		visitorInfo.setCardSex("cardSex");;   //varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证性别' ,
//		visitorInfo.setCardNation("cardNation");;   //varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证名族' ,
//		visitorInfo.setCardBirthday(new Date());;   //date NULL DEFAULT NULL COMMENT '身份证出生日期' ,
//		visitorInfo.setCardAddress("cardAddress");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证地址' ,
//		visitorInfo.setCardPhoto("cardPhoto");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证照片' ,
//		visitorInfo.setPhoto1("photo1");;   //varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '照片1' ,
//		// 执行添加操作
//		visitorInfoService.update(visitorInfo);		
////		visitorInfoService.save(visitorInfo);
//		// 设置客户端跳转到查询请求
//		// mv.setViewName("redirect:/visitor/single-visitor");
//		// 返回
//		return mv;
		RecordVisitors entity=new RecordVisitors();
		entity.setRecordID("ab6b674d654b415794fe558c91d4b40b");
		entity.setVisitStatus(2);
		return recordVisitorsService.selectVisitorByRID_Statuts("36b045674c9e4f8eb1fc4189db61307e",2);
	}
	
}
