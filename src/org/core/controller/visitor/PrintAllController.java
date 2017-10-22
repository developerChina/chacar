package org.core.controller.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.visitor.RecordVisitors;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
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
			List<RecordBevisiteds> rbs=recordBevisitedsService.selectBevisitedByRecordId(recordid);
			for (RecordBevisiteds rb : rbs) {
				Map<String, Object> map=new HashMap<>();
				map.put("visitor", rv);
				map.put("bevisited", rb);
				list.add(map);
			}
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
			rv.setVisitStatus(3);//0=申请中，1=审核中，2=已审核，3=正在访问，4=访问结束,5=删除
			rv.setCardNo(cardno);//设置物理卡
			recordVisitorsService.update(rv);
		}
		//发送权限，发送打印数据给前端
		
		return true;
	}
	
}
