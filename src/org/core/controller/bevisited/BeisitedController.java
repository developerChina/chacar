package org.core.controller.bevisited;

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
import org.core.util.JsonUtils;
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
	
	//审核地址
	String  auditUrl="http://localhost:8080/chacar/visitor/forwardVisitorAck?recordid=";
	/**
	 * 查询部门和被访人树
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/bevisited/getBevisitedTree")
	@ResponseBody		
	public Object getBevisitedTree(HttpServletRequest request, HttpServletResponse response){
		List<Map<String, Object>> list=bevisitedInfoService.getBevisitedTree();
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
		Employee employee= hrmService.findEmployeeById(Integer.parseInt(id.split("_")[1]));
		RecordBevisiteds recordBevisiteds=emp2Bevisited(recordid, employee);
		recordBevisitedsService.save(recordBevisiteds);
		//调用短信接口
		return auditUrl+recordid;
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
			//保存记录访客
			recordVisitor.setRecordID(recordid);
			recordVisitor.setVisitStatus(1);
			recordVisitorsService.save(recordVisitor);
			//根据电话号码保存记录被访人
			Employee employee= hrmService.findEmployeeById(Integer.parseInt(id.split("_")[1]));
			RecordBevisiteds recordBevisiteds=emp2Bevisited(recordid, employee);
			recordBevisitedsService.save(recordBevisiteds);
			returnString=returnString+auditUrl+recordid+"<br/>";
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
		recordBevisiteds.setDeptID(employee.getDept().getId()+"");   // varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人部门ID' ,
		recordBevisiteds.setDeptName(employee.getDept().getName());   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '被访人姓名' ,
		recordBevisiteds.setBevisitedPosition(employee.getJob().getName());   // varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人职位' ,
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
		return auditUrl+recordid;
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
			returnString=returnString+auditUrl+recordid+"<br/>";
		}
		//调用短信接口
		return returnString;
		
	}
}
