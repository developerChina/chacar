package org.core.controller.bevisited;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.bevisited.BevisitedInfo;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.visitor.RecordVisitors;
import org.core.service.bevisited.BevisitedInfoService;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
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
	@RequestMapping(value="/bevisited/sendSingleMessage")
	@ResponseBody
	public Object sendSingleMessage(String recordid,String tel){
		
		//删除所有记录id的被访人
		recordBevisitedsService.deleteByRecordID(recordid);
		//存储记录被访人
		BevisitedInfo bevisitedInfo=bevisitedInfoService.selectOneByTel(tel);
		RecordBevisiteds recordBevisiteds=new RecordBevisiteds();
		BeanUtils.copyProperties(bevisitedInfo, recordBevisiteds);
		recordBevisiteds.setRecordID(recordid);
		recordBevisitedsService.save(recordBevisiteds);
		//修改记录状态为审核中...
		List<RecordVisitors> recordVisitors=recordVisitorsService.selectVisitorByRecordId(recordid);
		for (RecordVisitors recordVisitor : recordVisitors) {
			recordVisitor.setVisitStatus(1);
			recordVisitorsService.update(recordVisitor);
		}
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
	public Object sendMoreMessage(String recordid,String tels){
		//删除所有记录id的被访人
		recordBevisitedsService.deleteByRecordID(recordid);
		//存储记录被访人
		String[] telphones=tels.split(",");
		for (String tel : telphones) {
			BevisitedInfo bevisitedInfo=bevisitedInfoService.selectOneByTel(tel);
			RecordBevisiteds recordBevisiteds=new RecordBevisiteds();
			BeanUtils.copyProperties(bevisitedInfo, recordBevisiteds);
			recordBevisiteds.setRecordID(recordid);
			recordBevisitedsService.save(recordBevisiteds);
			//修改记录状态为审核中...
			List<RecordVisitors> recordVisitors=recordVisitorsService.selectVisitorByRecordId(recordid);
			for (RecordVisitors recordVisitor : recordVisitors) {
				recordVisitor.setVisitStatus(1);
				recordVisitorsService.update(recordVisitor);
			}
		}
		//调用短信接口
		
		return auditUrl+recordid;
	}
}
