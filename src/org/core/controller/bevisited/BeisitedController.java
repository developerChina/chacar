package org.core.controller.bevisited;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.bevisited.BevisitedInfo;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.visitor.VisitorRecord;
import org.core.domain.webapp.Employee;
import org.core.service.bevisited.BevisitedInfoService;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.visitor.VisitorInfoService;
import org.core.service.webapp.HrmService;
import org.core.util.DateStyle;
import org.core.util.DateUtil;
import org.core.util.HttpKit;
import org.core.util.JsonUtils;
import org.core.util.PropUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
		
/**
 * 多被访人
 * */
@Controller
public class BeisitedController {
	@Autowired
	@Qualifier("bevisitedInfoService")
	private BevisitedInfoService bevisitedInfoService;
	@Autowired
	@Qualifier("recordBevisitedsService")
	private RecordBevisitedsService recordBevisitedsService;
	@Autowired
	@Qualifier("recordVisitorsService")
	private RecordVisitorsService recordVisitorsService;
	@Autowired
	@Qualifier("visitorRecordService")
	private VisitorRecordService visitorRecordService;
	@Autowired		
	@Qualifier("visitorInfoService")
	private VisitorInfoService visitorInfoService;
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 查询部门和被访人树
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/bevisited/getEmployeees")
	@ResponseBody		
	public Object getEmployeees(HttpServletRequest request, HttpServletResponse response){
		String name=request.getParameter("name");
     	String telphone=request.getParameter("telphone");
		List<Map<String, Object>> list=hrmService.getEmployeees(name,telphone);
		return list;
	}
	
	/**
	 * 更新访问记录状态，保存记录被访人名单，发送审核短信
	 * @param mv
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/bevisited/sendSingleMessage")
	@ResponseBody
	public Object sendSingleMessage(HttpServletRequest request, HttpServletResponse response,String recordVisitors,String id){
		//保存记录
		VisitorRecord visitorRecord=new VisitorRecord();
		String recordid =visitorRecordService.saveOrUpdate(visitorRecord);
		//保存记录访客
		List<Map> rvs=JsonUtils.parse(recordVisitors.replaceAll("\'", "\""), List.class);
		String visitorNames="";//访客名
		for (Map rv : rvs) {
			if(rv.get("cardID")!=null){
				RecordVisitors recordVisitor= new RecordVisitors();
				VisitorInfo visitorInfo=visitorInfoService.selectOneBycardID(rv.get("cardID").toString());
				visitorNames=visitorNames+","+visitorInfo.getCardName();
				BeanUtils.copyProperties(visitorInfo, recordVisitor);
				recordVisitor.setRecordID(recordid);
				recordVisitor.setVisitStatus(1);
				recordVisitor.setVisitReason(rv.get("visitReason")!=null?rv.get("visitReason").toString():"");
				recordVisitorsService.save(recordVisitor);
			}
		}
		
		if(visitorNames.contains(",")) {
			visitorNames.substring(1);
		}
		//保存记录被访人
		Employee employee= hrmService.findEmployeeById(Integer.parseInt(id));
		RecordBevisiteds recordBevisiteds=emp2Bevisited(recordid, employee);
		recordBevisitedsService.save(recordBevisiteds);
		//调用短信接口
		sendAudiSMS(visitorNames, employee.getName(), DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD), recordid, employee.getPhone());
		
		return getAudiUrl(request)+recordid;
	}
	
	/**
	 * 更新访问记录状态，保存记录被访人名单，发送审核短信
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/bevisited/sendMoreMessage")
	@ResponseBody
	public Object sendMoreMessage(HttpServletRequest request, HttpServletResponse response,String recordVisitors,String ids){
		RecordVisitors recordVisitor=JsonUtils.parse(recordVisitors.replaceAll("\'", "\""), RecordVisitors.class);
		String[] idss=ids.split(",");
		String returnString="";
		for (String id : idss) {
			//保存记录
			VisitorRecord visitorRecord=new VisitorRecord();
			String recordid =visitorRecordService.saveOrUpdate(visitorRecord);
			String visitorNames="";//访客名
			//保存记录访客
			if(recordVisitor.getCardID()!=null){
				VisitorInfo visitorInfo=visitorInfoService.selectOneBycardID(recordVisitor.getCardID());
				visitorNames=visitorInfo.getCardName();
				recordVisitor.setRecordID(recordid);
				recordVisitor.setVisitStatus(1);
				recordVisitor.setVisitorID(visitorInfo.getVisitorID());
				recordVisitorsService.save(recordVisitor);
			}
			//根据电话号码保存记录被访人
			Employee employee= hrmService.findEmployeeById(Integer.parseInt(id));
			RecordBevisiteds recordBevisiteds=emp2Bevisited(recordid, employee);
			recordBevisitedsService.save(recordBevisiteds);
			
			//发送验证短信
			sendAudiSMS(visitorNames, employee.getName(), DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD), recordid, employee.getPhone());
			
			returnString=returnString+getAudiUrl(request)+recordid+"<br/>";
		}
		//调用短信接口
		return returnString;
		
	}
	
	/**
	 * 员工和被访人转化
	 * @param recordid
	 * @param id
	 * @return
	 */
	private  RecordBevisiteds emp2Bevisited(String recordid,Employee employee){
		RecordBevisiteds recordBevisiteds=new RecordBevisiteds();
		recordBevisiteds.setRecordID(recordid);   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '访问记录ID' ,
		recordBevisiteds.setBevisitedID(employee.getId()+"");   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人ID' ,
		recordBevisiteds.setBevisitedCardNo(employee.getCardno());	//varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人卡号' ,
		recordBevisiteds.setBevisitedName(employee.getName());   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人姓名' ,
		if(employee.getDept()!=null){
			recordBevisiteds.setDeptID(employee.getDept().getId()+"");   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人部门ID' ,
			recordBevisiteds.setDeptName(employee.getDept().getName());   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人姓名' ,
		}
		if(employee.getJob()!=null){
			recordBevisiteds.setBevisitedPosition(employee.getJob().getName());   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人职位' ,
		}
		recordBevisiteds.setBevisitedStatus("0");   // tinyint(4) NOT NULL DEFAULT 0 COMMENT '被访人状态（0=正常，1=离职.......）' ,
		recordBevisiteds.setBevisitedTel(employee.getPhone());   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人手机号码' ,
		recordBevisiteds.setBevisitedDoor("");   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人门禁' ,
		recordBevisiteds.setBevisitedChannel("");   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人通道' ,
		recordBevisiteds.setBevisitedFloor("");   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人楼层' ,
		recordBevisiteds.setBevisitedRoom("");   // varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人房间号' ,
		recordBevisiteds.setBevisitedAddress(employee.getAddress());   // varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访问详细地址' ,
		return recordBevisiteds;
	}
	
	
	/**
	 * 更新访问记录状态，保存记录被访人名单，发送审核短信（废弃）
	 * @param mv
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/bevisited/sendSingleMessage_old")
	@ResponseBody
	public Object sendSingleMessage_old(HttpServletRequest request, HttpServletResponse response,String recordVisitors,String tel){
		//保存记录
		VisitorRecord visitorRecord=new VisitorRecord();
		String recordid =visitorRecordService.saveOrUpdate(visitorRecord);
				
		//保存记录访客
		List<Map> rvs=JsonUtils.parse(recordVisitors.replaceAll("\'", "\""), List.class);
		for (Map rv : rvs) {
			if(rv.get("cardID")!=null){
				RecordVisitors recordVisitor= new RecordVisitors();
				VisitorInfo visitorInfo=visitorInfoService.selectOneBycardID(rv.get("cardID").toString());
				BeanUtils.copyProperties(visitorInfo, recordVisitor);
				recordVisitor.setRecordID(recordid);
				recordVisitorsService.save(recordVisitor);
			}
		}
		//保存记录被访人
		BevisitedInfo bevisitedInfo=bevisitedInfoService.selectOneByTel(tel);
		RecordBevisiteds recordBevisiteds=new RecordBevisiteds();
		BeanUtils.copyProperties(bevisitedInfo, recordBevisiteds);
		recordBevisiteds.setRecordID(recordid);
		recordBevisitedsService.save(recordBevisiteds);
		//调用短信接口
		return getAudiUrl(request)+recordid;
	}
	
	/**
	 * 更新访问记录状态，保存记录被访人名单，发送审核短信（废弃）
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/bevisited/sendMoreMessage_old")
	@ResponseBody
	public Object sendMoreMessage_old(HttpServletRequest request, HttpServletResponse response,String recordVisitors,String tels){
		RecordVisitors recordVisitor=JsonUtils.parse(recordVisitors.replaceAll("\'", "\""), RecordVisitors.class);
		String[] telphones=tels.split(",");
		String returnString="";
		for (String tel : telphones) {
			//保存记录
			VisitorRecord visitorRecord=new VisitorRecord();
			String recordid =visitorRecordService.saveOrUpdate(visitorRecord);
			//保存记录访客
			recordVisitor.setRecordID(recordid);
			recordVisitor.setVisitStatus(1);
			recordVisitorsService.save(recordVisitor);
			//根据电话号码保存记录被访人
			BevisitedInfo bevisitedInfo=bevisitedInfoService.selectOneByTel(tel);
			RecordBevisiteds recordBevisited=new RecordBevisiteds();
			BeanUtils.copyProperties(bevisitedInfo, recordBevisited);
			recordBevisited.setRecordID(recordid);
			recordBevisitedsService.save(recordBevisited);
			returnString=returnString+getAudiUrl(request)+recordid+"<br/>";
		}
		//调用短信接口
		return returnString;
		
	}
	
	private String getAudiUrl(HttpServletRequest request){
		return PropUtil.getSysValue("serverPath")+request.getContextPath()+"/visitor/forwardVisitorAck?recordid=";
	}
	
	/**
	 * 发送授权短信
	 * @param visitedName
	 * @param visitorName
	 * @param visitorDate
	 * @param recordid
	 * @param phoneNumbers
	 */
	private void sendAudiSMS(String visitedName,String visitorName,String visitorDate,String recordid,String phoneNumbers){
		//发送外网程序错误，需要把访问人和被访问人对调
		try {
			HttpKit.post(PropUtil.getSysValue("smsPath"), 
					"visitedName="+visitorName+
					"&visitorName="+visitedName+
					"&visitorDate="+visitorDate+
					"&recordid="+recordid+
					"&phoneNumbers="+phoneNumbers,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
