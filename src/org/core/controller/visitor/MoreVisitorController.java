package org.core.controller.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.core.domain.visitor.RecordVisitors;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.visitor.VisitorRecord;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.VisitorRecordService;
import org.core.service.visitor.VisitorInfoService;
import org.core.util.JsonUtils;
import org.core.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 多访客
 * */
@Controller
public class MoreVisitorController {
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
	 * 多访客登记信息
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/visitor/forwardMoreVisitor")
	 public ModelAndView forwardMoreVisitor(ModelAndView mv,String recordid){
		if(StringUtils.isNotBlank(recordid)){
			List<RecordVisitors> list=recordVisitorsService.selectVisitorByRecordId(recordid);
			if(list.size()>0){
				mv.addObject("modles",list);
			}
		}
		mv.addObject("recordid",recordid);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/more-visitor");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 保存或者更新多访客，访问记录，记录访客名单，并且跳转到选择被访人选择页面
	 * @param mv
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/visitor/forwardMoreVisited")
	 public ModelAndView forwardMoreVisited(ModelAndView mv,String recordVisitors,String recordid){
		//格式化提交被访人数据
		List<Map> rvs=JsonUtils.parse(recordVisitors, List.class);
		List<RecordVisitors> list=new ArrayList<>();
		for (Map rv : rvs) {
			RecordVisitors r=new RecordVisitors();
			r.setRecordID(recordid);
			r.setCardName(rv.get("cardName")!=null?rv.get("cardName").toString():"");
			r.setCardSex(rv.get("cardSex")!=null?rv.get("cardSex").toString():"");
			r.setCardNation(rv.get("cardNation")!=null?rv.get("cardNation").toString():"");
			r.setCardID(rv.get("cardID")!=null?rv.get("cardID").toString():"");
			r.setCardAddress(rv.get("cardAddress")!=null?rv.get("cardAddress").toString():"");
			r.setCardPhoto(rv.get("cardPhoto")!=null?rv.get("cardPhoto").toString():"");
			r.setPhoto1(rv.get("photo1")!=null?rv.get("photo1").toString():"");
			r.setTelephone(rv.get("telephone")!=null?rv.get("telephone").toString():"");
			r.setCompany(rv.get("company")!=null?rv.get("company").toString():"");
			r.setVisitReason(rv.get("visitReason")!=null?rv.get("visitReason").toString():"");
			list.add(r);
		}
		
		//存储访问记录
		VisitorRecord visitorRecord=new VisitorRecord();
		visitorRecord.setRecordID(recordid);
		recordid=visitorRecordService.saveOrUpdate(visitorRecord);
		//删除访问记录人员
		recordVisitorsService.deleteByRecordID(recordid);
		
		for (RecordVisitors rv : list) {		
			VisitorInfo visitorInfo=new VisitorInfo();
			BeanUtils.copyProperties(rv, visitorInfo);
			//存储或修改访客信息
			String visitorID =visitorInfoService.saveOrUpdate(visitorInfo);
			//新增访问记录人员
			rv.setRecordID(recordid);
			rv.setVisitorID(visitorID);
			recordVisitorsService.save(rv);
		}		
		mv.addObject("recordid", recordid);
		// 设置客户端跳转到查询请求
		mv.setViewName("visitor/more-visited");
		// 返回ModelAndView
		return mv;
	}
	

}
