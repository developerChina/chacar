package org.core.controller.visitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Blacklist;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.Reson;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.visitor.VisitorInfoService;
import org.core.service.visitor.VisitorService;
import org.core.service.webapp.AccessService;
import org.core.service.webapp.ElevatorService;
import org.core.service.webapp.PassagewayService;
import org.core.service.webapp.ResonService;
import org.core.util.AControlUtil;
import org.core.util.GenId;
import org.core.util.ImageUtils;
import org.core.util.JsonUtils;
import org.core.util.LadderControlUtil;
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
	
	@Autowired
	@Qualifier("visitorService")
	private VisitorService visitorService;//黑名单
	
	@Autowired
	@Qualifier("passagewayService")
	private PassagewayService passagewayService;// 通道
	@Autowired
	@Qualifier("elevatorService")
	private ElevatorService elevatorService;//电梯
	@Autowired
	@Qualifier("accessService")
	private AccessService accessService;//门禁
	
	/**
	 * 访问事由
	 */
	@Autowired
	@Qualifier("resonService")
	private ResonService resonService;
	/**		
	 * 单访客登记
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardSingleVisitor")
	public ModelAndView forwardSingleVisitor(HttpServletRequest request,HttpServletResponse response,ModelAndView mv){
		//访问事由
		List<Reson> resons=resonService.findAll();
		mv.addObject("resonStr", JsonUtils.toJson(resons));
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
	 * 根据身份证号码校验访客登记
	 * @param cardid
	 * @return
	 */
	@RequestMapping(value="/visitor/validateSingleVisitor")
	@ResponseBody
	public Object validateSingleVisitor(HttpServletRequest request,HttpServletResponse response,String cardid){
		//查询黑名单
		List<Blacklist> blacklist=visitorService.selectBlackByCardId(cardid);
		//查询正在访问
		List<RecordVisitors> recordVisitors=recordVisitorsService.selectRecordInfoBycardID_status(cardid, 3);
		boolean bool=true;
		String message="";
		if(blacklist.size()>0){
			bool=false;
			message=message+"你已经上黑名单。";
		}
		
		if(recordVisitors.size()>0){
			bool=false;
			message=message+"你上次访问没有正常签离。";
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", bool);
		map.put("message", message);
		return map;
	}
	
	/**
	 * 根据身份证号码签离
	 * @param cardid
	 * @return
	 */
	@RequestMapping(value="/visitor/visitorSignOut")
	@ResponseBody
	public Object visitorSignOut(HttpServletRequest request,HttpServletResponse response,String cardid){
		boolean bool=true;
		String message="签离成功";
		try {
			//查询正在访问
			List<RecordVisitors> recordVisitors=recordVisitorsService.selectRecordInfoBycardID_status(cardid, 3);
			//删除硬件权限
			List<Passageway> passList=passagewayService.selectAll();
			List<Elevator> egElevators=elevatorService.selectAll();
			List<Access> agAccesss=accessService.selectAll();
			
			for (RecordVisitors entity : recordVisitors) {
				entity.setVisitStatus(4);
				recordVisitorsService.update(entity);
				//授权-》去掉 权限
				GrantAuthorization(agAccesss,passList,egElevators,entity.getCardNo(),0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			bool=false;
			
			message=e.getMessage();
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", bool);
		map.put("message", message);
		return map;
	}
    //门禁,通道  int authority[] = { 1, 1, 1, 1 };
	public static void GrantAuthorization(List<Access> agAccesss,List<Passageway> passList,List<Elevator> egElevators,String cardno,int flag) {
		int authority[] = { 0, 0, 0, 0 };
		int lay[] = { 0, 0, 0, 0, 0 };
		if(flag==1) {
			authority[0]=1;
			authority[1]=1;
			authority[2]=1;
			authority[3]=1;
			lay[0]=255;
			lay[1]=255;
			lay[2]=255;
			lay[3]=255;
			lay[4]=255;
		}
		for(Access ac:agAccesss) {
		   AControlUtil.AddUserCard(Long.valueOf(ac.getCsn()), ac.getCip(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29, (byte) 0x12,
					(byte) 0x31, authority);
		}
		for(Passageway pa:passList) {
			   AControlUtil.AddUserCard(Long.valueOf(pa.getControllerSN()), pa.getControllerIP(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29, (byte) 0x12,
						(byte) 0x31, authority);
		}
		for(Elevator ea:egElevators) {
			LadderControlUtil.LadderControlUserCard(Long.valueOf(ea.getControllerSN()), ea.getControllerIP(), Long.valueOf(cardno), 1, (byte) 0x20, (byte) 0x29,
					(byte) 0x12, (byte) 0x31, lay[0], lay[1], lay[2], lay[3], lay[4]);
		}
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
