package org.core.controller.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.visitor.RecordVisitors;
import org.core.domain.webapp.Access;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.webapp.AccessGroupService;
import org.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 访问记录
 * */
@Controller
public class RecordController {
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
	@Qualifier("accessgroupService")
	private AccessGroupService accessgroupService;
	/**
	 * 根据记录ID查询记录访客
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/visitor/selectVisitorByRecordId")
	@ResponseBody		
	public Object selectVisitorByRecordId(HttpServletRequest request, HttpServletResponse response){
		String recordid=request.getParameter("recordid");
		List<RecordVisitors> list=recordVisitorsService.selectVisitorByRecordId(recordid);
		if(list.size()==0){		
			return null;
		}else if(list.size()==0){
			return list.get(0);
		}else {
			return list;
		}
	}
	
	/**
	 * 审核访问记录
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/visitor/auditRecord")
	@ResponseBody		
	public Object auditRecord(HttpServletRequest request, HttpServletResponse response){
		String recordid=request.getParameter("recordid");
		String isAudit_Str=request.getParameter("isAudit");
		int isAudit=1;
		try {
			isAudit=Integer.parseInt(isAudit_Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String auditContent=request.getParameter("auditContent");
		
		String [] pw=request.getParameterValues("pw");
		String [] elt=request.getParameterValues("elt");
		String acce=request.getParameter("acce");
		String group=request.getParameter("group");
		
		List<RecordVisitors> rvs=recordVisitorsService.selectVisitorByRecordId(recordid);
		for (RecordVisitors rv : rvs) {
			rv.setVisitStatus(2);   // tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已经访问完成(0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除)' ,
			rv.setIsAudit(isAudit);   // tinyint(4) NOT NULL COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
			rv.setAuditContent(auditContent);   // varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '被访人审核意见' ,
			recordVisitorsService.update(rv);
		}
		
		RecordBevisiteds rbv=recordBevisitedsService.selectBevisitedByRecordId(recordid);
		Map<String, Object> map=new HashMap<>();
		if(rbv!=null){
			rbv.setBevisitedChannel(StringUtils.join(pw, ","));   // '被访人通道' ,
			List<Access> saveaccesss = accessgroupService.getAccessById(group);
			if(saveaccesss!=null && saveaccesss.size()>0){
				rbv.setBevisitedFloor(StringUtils.join(elt, ","));   // '被访人楼层' ,
				String doors="";
				for (Access access : saveaccesss) {
					doors=doors+","+access.getAccessid();
				}
				rbv.setBevisitedDoor(doors.substring(1));   // '被访人门禁' ,
			}
			rbv.setBevisitedRoom("");   // '被访人房间号' ,
			recordBevisitedsService.update(rbv);
			map.put("status", true);
			map.put("message", "审核通过");
		}else{
			map.put("status", false);
			map.put("message", "审核记录不存在");
		}
		return map;
	} 

}
