package org.core.controller.visitor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.Trajectory;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Blacklist;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.Reson;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.TrajectoryService;
import org.core.service.visitor.VisitorInfoService;
import org.core.service.visitor.VisitorService;
import org.core.service.webapp.AccessGroupService;
import org.core.service.webapp.AccessService;
import org.core.service.webapp.ElevatorService;
import org.core.service.webapp.GroupService;
import org.core.service.webapp.PassagewayGroupService;
import org.core.service.webapp.PassagewayService;
import org.core.service.webapp.ResonService;
import org.core.util.DateUtil;
import org.core.util.PropUtil;
import org.core.util.StringUtils;
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
	@Autowired
	@Qualifier("recordVisitorsService")
	private RecordVisitorsService recordVisitorsService;
	@Autowired
	@Qualifier("recordBevisitedsService")
	private RecordBevisitedsService recordBevisitedsService;
	@Autowired
	@Qualifier("passagewayService")
	private PassagewayService passagewayService;// 通道
	@Autowired
	@Qualifier("elevatorService")
	private ElevatorService elevatorService;//电梯
	@Autowired
	@Qualifier("accessService")
	private AccessService accessService;//门禁
	
	@Autowired 
	@Qualifier("accessgroupService")
	private AccessGroupService accessgroupService;
	
	@Autowired
	@Qualifier("passagewayGroupService")
	private PassagewayGroupService passagewayGroupService;
	
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	
	@Autowired
	@Qualifier("trajectoryService")
	private TrajectoryService trajectoryService;//访问轨迹
	
	@RequestMapping(value = "/visitor/forwardVisitorAck")
	public ModelAndView forwardVisitorAck(HttpServletRequest request, HttpServletResponse response, ModelAndView mv,
			String recordid) {
		//短信申请
		if("11111111".equals(recordid) || recordid==null || "".equals(recordid)|| recordid.length()<31) {
			// 设置客户端跳转到查询请求
			mv.setViewName("visitor/visitor-sq");
			// 返回ModelAndView
			return mv;
		}
		// 添加访问记录id
		mv.addObject("recordid", recordid);
		
		
//		RecordBevisiteds rbv=recordBevisitedsService.selectBevisitedByRecordId(recordid);
//		//添加通道
//		List<Passageway> pws=passagewayService.selectAccessByCardNo(rbv.getBevisitedCardNo());
//		mv.addObject("pws", pws);
//		//添加楼层
//		List<Access> acces=accessService.selectAccessByCardNo(rbv.getBevisitedCardNo());
//		mv.addObject("acces", acces);
//		if(acces.size()>0){
//			//添加电梯
//			PageModel pageModel=new PageModel();
//			pageModel.setPageIndex(1);
//			pageModel.setRecordCount(Integer.MAX_VALUE);
//			pageModel.setPageSize(Integer.MAX_VALUE);
//			List<Elevator> elts=elevatorService.findElevator(new Elevator(), pageModel);
//			mv.addObject("elts", elts);
//		}
		
		
		//所有通道
		List<Passageway> passList=passagewayGroupService.selectPGSubordinate();
		mv.addObject("pws", passList);
		//所有门禁
		List<Access> agAccesss=accessgroupService.selectAGSubordinate();
		mv.addObject("acces", agAccesss);
		//所有电梯
		List<Elevator> egElevators= groupService.selectEGSubordinate();
		mv.addObject("elts", egElevators);
		//所有门禁分组
		List<AccessGroup> groups = accessgroupService.findAccessGroupAll();
		mv.addObject("groups", groups);
		
		if(agAccesss.size()>0 || agAccesss.size()>0){
			mv.addObject("isShow", "yes");
		}
		
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

	// 1_2.1取消拉黑batchDelete
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
	// 1_2.2批量取消拉黑	
	@RequestMapping(value = " /visitor/batchDelete")
	public ModelAndView delBlacklist(
			String ids,
			ModelAndView mv) {
		
		// 执行取消黑名单
		visitorService.removeByids(ids);

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
	public ModelAndView visitortrajectoryAck(
			Integer pageIndex, ModelAndView mv,
			Trajectory trajectory,
			HttpServletRequest request, 
			HttpServletResponse response) {
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		
		
		String cardName=request.getParameter("cardName");
		String cardID=request.getParameter("cardID");
		
		if(StringUtils.isNotBlank(cardName) || StringUtils.isNotBlank(cardID)){
			RecordVisitors entity=new RecordVisitors();
			entity.setCardName(cardName);
			entity.setCardID(cardID);
			List<RecordVisitors> rs=recordVisitorsService.selectByEntity(entity);
			StringBuffer cardNo=new StringBuffer();
			if(rs.size()==1){
				trajectory.setCardNo(rs.get(0).getCardNo());
			}else{
				for (RecordVisitors r : rs) {
					cardNo.append(",'").append(r.getCardNo()).append("'");
				}
				trajectory.setCardNo(cardNo.toString().substring(1));
			}
		}
		
		String sDate=request.getParameter("sDate");
		Date startDate=null;
		try {
			startDate=DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate=request.getParameter("eDate");
		Date endDate=null;
		try {
			endDate=DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Trajectory>  trajectorys=trajectoryService.selectByPage(trajectory, pageModel,startDate,endDate);
		mv.addObject("trajectorys", trajectorys);
		mv.addObject("pageModel", pageModel);
		mv.addObject("imgurl", PropUtil.getSysValue("serverPath")+request.getContextPath()+"/");
		mv.addObject("all", recordVisitorsService.selectCountByStatus("in(1,2,3,4,5)",startDate,endDate));
		mv.addObject("ing", recordVisitorsService.selectCountByStatus("=3",startDate,endDate));
		mv.addObject("ed", recordVisitorsService.selectCountByStatus(">3",startDate,endDate));
		mv.setViewName("visitor/trajectory");
		return mv;
	}
}