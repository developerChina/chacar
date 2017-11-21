package org.core.controller.visitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.visitor.RecordVisitors;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Passageway;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.webapp.AccessService;
import org.core.service.webapp.ElevatorService;
import org.core.service.webapp.PassagewayService;
import org.core.util.DateStyle;
import org.core.util.DateUtil;
import org.core.util.StringUtils;
import org.core.util.VisitorEntryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 打印
 * */
@Controller
public class PrintAllController {
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
	
	@RequestMapping(value="/visitor/forwardAllPrint")
	 public ModelAndView forwardAllPrint(HttpServletRequest request,HttpServletResponse response,ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/all-print");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 根据身份证号码获取已审核访问记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/visitor/selectRecordInfo")
	@ResponseBody
	public Object selectRecordInfo(HttpServletRequest request,HttpServletResponse response) {
		List<Map<String, Object>> list=new ArrayList<>();
		String cardid=request.getParameter("cardid");
		List<RecordVisitors> rvs= recordVisitorsService.selectRecordInfoBycardID_status(cardid,2);
		for (RecordVisitors rv : rvs) {
			String recordid=rv.getRecordID();
			RecordBevisiteds rb=recordBevisitedsService.selectBevisitedByRecordId(recordid);
			Map<String, Object> map=new HashMap<>();
			map.put("visitor", rv);
			map.put("bevisited", rb);
			map.put("date", DateUtil.DateToString(new Date(),DateStyle.YYYY_MM_DD_CN));
			list.add(map);
		}
		return list;
	}
	
	/**
	 *  根据省份证号码获取单据信息，并且打印
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/visitor/printRecordInfo")
	@ResponseBody
	public Object printRecordInfo(HttpServletRequest request,HttpServletResponse response) {
		String cardid=request.getParameter("cardid");
		String cardno=request.getParameter("cardno");
		
		List<RecordVisitors> rvs= recordVisitorsService.selectRecordInfoBycardID_status(cardid,2);
		for (RecordVisitors rv : rvs) {
			//修改记录单状态
			if(rv.getIsAudit()==1){
				rv.setVisitStatus(3);//0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除
			}else{
				rv.setVisitStatus(5);//0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除
			}
			rv.setCardNo(cardno);//设置物理卡
			rv.setInDate(new Date());//授权时间
			recordVisitorsService.update(rv);
		}
		
		//授权所有正在访问的权限
		List<RecordVisitors> rvss= recordVisitorsService.selectRecordInfoBycardID_status(cardid,3);
		List<Passageway> td=new ArrayList<>();
		List<Elevator> dt=new ArrayList<>();
		List<Access> mj=new ArrayList<>();
		for (RecordVisitors rv : rvss) {
			//发送权限
			if(rv.getIsAudit()==1){
				RecordBevisiteds rb=recordBevisitedsService.selectBevisitedByRecordId(rv.getRecordID());
				//通道授权
				String channels=rb.getBevisitedChannel();
				if(StringUtils.isNotBlank(channels)){
					td.addAll(passagewayService.selectByIds(channels));
				}
				//梯控授权
				String floors=rb.getBevisitedFloor();
				if(StringUtils.isNotBlank(floors)){
					dt.addAll(elevatorService.selectByIds(floors));
				}
				//门禁授权
				String door=rb.getBevisitedDoor();
				if(StringUtils.isNotBlank(door)){
					mj.add(accessService.findAccessById(Integer.parseInt(door)));
				}
			}
		}
		VisitorEntryUtil.inPermissionControl(cardno, mj, dt, td);
		
		return true;
	}
	
}
