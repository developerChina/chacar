package org.core.controller.record;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
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
	/**
	 * 根据记录ID查询记录访客
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/visitor/selectVisitorByRecordId")
	@ResponseBody		
	public Object selectVisitorByRecordId(HttpServletRequest request, HttpServletResponse response,String rr){
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
	
	 

}
