package org.core.controller.queuing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.util.Region;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.core.domain.location.LocationInout;
import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.service.location.InoutService;
import org.core.service.queuing.QueuingService;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.PropUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 处理排队叫号控制器
 */
@Controller
public class QueuingConteoller {
	@Autowired
	@Qualifier("queuingService")
	private QueuingService queuingService;
	
	@Autowired
	@Qualifier("inoutService")
	private InoutService inoutService;
	
	 
	@RequestMapping(value = "/queuingI/islandIndex")
	public ModelAndView islandIndex(Integer no, ModelAndView mv) {
		if(no!=null){
			Island island=queuingService.selectIByNo(no);
			if(island!=null){
				mv.addObject("island", island);
			}else{
				island=new Island();
				island.setIname("卸货岛不存在");
				mv.addObject("island", island);
			}
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("island/island");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value = "/queuingI/addQueue")
	@ResponseBody		
	public synchronized Object addQueue(@ModelAttribute Ordinary ordinary,Integer isadd) {
		Map<String, Object> map=new HashMap<>();
		//判断卸货岛是否存在
		Island island=queuingService.selectIByNo(ordinary.getIsland_no());
		if(island!=null){
			//判断车辆是否入场
			LocationInout inout= inoutService.selectNewRecord(ordinary.getCar_code());
			if(inout!=null){
				String message="";
				if(isadd==0){
					QueuingVip exv=queuingService.selectVBycarno(ordinary.getIsland_no(),ordinary.getCar_code());
					if(exv!=null){
						message="急件正在排队";
					}else{
						ordinary.setRemarks("普通号");
						Ordinary exo=queuingService.selectOBycarno(ordinary.getIsland_no(),ordinary.getCar_code());
						Ordinary maxo=queuingService.selectMaxOByLand(ordinary.getIsland_no());
						if(exo!=null){
							if(!exo.getCar_code().equals(maxo.getCar_code())){
								ordinary.setQueue_number(maxo.getQueue_number()+1);
								ordinary.setId(exo.getId());
								queuingService.updateO(ordinary); 
							}
						}else{
							if(maxo!=null){
								ordinary.setQueue_number(maxo.getQueue_number()+1);
							}else{
								ordinary.setQueue_number(1);
							}
							queuingService.addO(ordinary);
						}
						message="排队成功";
					}
					
				}else{
					message="查询成功";
				}
				//查询车辆排队信息
				List<Object> returnList=new ArrayList<>();
				boolean isLoopOrdinary=true;//是否循环普通队列
				int waiting=0;
				List<QueuingVip> vips=queuingService.selectVAll(ordinary.getIsland_no());
				for (QueuingVip vip : vips) {
					if(!ordinary.getCar_code().equals(vip.getCar_code())){
						returnList.add(vip);
						waiting++;
					}else{
						returnList.add(vip);
						isLoopOrdinary=false;
						break;
					}
				}
				List<Ordinary> os=queuingService.selectOAll(ordinary.getIsland_no());
				if(isLoopOrdinary){
					for (Ordinary o : os) {
						if(!ordinary.getCar_code().equals(o.getCar_code())){
							returnList.add(o);
							waiting++;
						}else{
							returnList.add(o);
							break;
						}
					}
				}
				map.put("message", message);
				map.put("status", true);
				map.put("waiting", waiting);
				map.put("all", vips.size()+os.size());
				map.put("list", returnList);
			}else{
				map.put("status", false);
				map.put("message", "车辆没有入场不能查询或排队");
			}
		}else{
			map.put("status", false);
			map.put("message", "卸货岛不存在");
		}
		return map;
	}
	

	// 排队叫号操作 控制器
	// 1-->卸货岛查询带分页
	/**
	 * 卸货岛管理 跳向首页 分页查询
	 */
	@RequestMapping(value="/queuingI/IslandAck")
			 public ModelAndView IslandAck(Integer pageIndex,
					 @ModelAttribute Island island,
					 ModelAndView mv){		
	    PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Island> pageListI = queuingService.selectIByPage(island, pageModel);
		mv.addObject("pageListI", pageListI);
		mv.addObject("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("queuing/showI");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 卸货岛添加 <br>
	 * flag=1跳向添加页面 <br>
	 * flag=2执行添加
	 */
	@RequestMapping(value="/queuingI/IslandAdd")
			 public ModelAndView IslandAdd(String flag,
					 @ModelAttribute Island island,
					 ModelAndView mv){		
	    if (flag.equals("1")) {
			// 设置跳转到添加页面
	    	mv.addObject("qrcodePath", PropUtil.getSys().get("qrcodePath"));
			mv.setViewName("/queuing/showAddI");
		} else {
			// 执行添加
			queuingService.addI(island);
			mv.setViewName("redirect:/queuingI/IslandAck");
		}
		return mv;
	}

	/**
	 * 卸货岛删除 <br>
	 * 
	 * @param no
	 *            卸货岛的主键编号值
	 * 
	 */
	@RequestMapping(value = "/queuingI/delIslandAck")
	public ModelAndView delRoomAck(Integer id, ModelAndView mv) {
		queuingService.delIsland(id);
		mv.setViewName("redirect:/queuingI/IslandAck");
		return mv;
	}

	/**
	 * 卸货岛修改 <br>
	 * flag=1跳向修改页面 <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value = "/queuingI/updateIslandAck")
	public ModelAndView IslandUpd(String flag, Integer id, @ModelAttribute Island island, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到修改页面
			Island updateI = queuingService.updateISel(id);
			mv.addObject("qrcodePath", PropUtil.getSys().get("qrcodePath"));
			mv.addObject("updateI", updateI);
			mv.setViewName("/queuing/showUpdI");
		} else {
			// 执行修改
			queuingService.UpdI(island);
			mv.setViewName("redirect:/queuingI/IslandAck");
		}
		// 返回ModelAndView
		return mv;
	}

	// 2-->VIP队列查询带分页
	/**
	 * VIP队列管理 跳向首页 分页查询
	 */
	@RequestMapping(value = "/queuingV/VipAck")
	public ModelAndView VipAck(Integer pageIndex, 
			@ModelAttribute QueuingVip queuingVip,
			Integer island_no,ModelAndView mv) {
		
		this.islandV(island_no,queuingVip);
		
		String pageParam="";
		if(queuingVip.getVagueiname()!=null){
			pageParam+="&vagueiname="+queuingVip.getVagueiname();
		}
		if(island_no!=null&&island_no>0){
			pageParam+="&island_no="+island_no;
		}
		mv.addObject("pageParam", pageParam);
		mv.addObject("model", queuingVip.getVagueiname());
		mv.addObject("target", queuingVip.getCar_code());
		mv.addObject("island_no", island_no);
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<QueuingVip> pageListV = queuingService.selectVByPage(queuingVip, pageModel);
		mv.addObject("pageListV", pageListV);
		mv.addObject("pageModel", pageModel);
		
		List<Island> AddVgetI = queuingService.AddVgetI();
		mv.addObject("AddVgetI", AddVgetI);
		// 设置客户端跳转到查询请求
		mv.setViewName("queuing/showV");
		// 返回ModelAndView
		
		
		return mv;
	}

	
	/**
	 * 由于卸货岛在VIP表是对象关联映射， 所以不能直接接收参数，需要创建卸货岛对象
	 */
	private void islandV(Integer island_no,QueuingVip queuingVip) {
		if (island_no != null) {
			Island island = new Island();
			island.setNo(island_no);
			queuingVip.setVpartsI(island);
		}
	}
	
	
	/**
	 * VIP队列添加 <br>
	 * flag=1跳向添加页面 <br>
	 * flag=2执行添加
	 */
	@RequestMapping(value = "/queuingV/VipAdd")
	public ModelAndView VipAdd(String flag, @ModelAttribute QueuingVip queuingVip, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			// 卸货岛组件
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);

			mv.setViewName("/queuing/showAddV");
		} else {
			// 执行添加
			queuingService.addV(queuingVip);
			mv.setViewName("redirect:/queuingV/VipAck");
		}
		return mv;
	}

	/**
	 * VIP队列删除 <br>
	 * 
	 * @param vid
	 *            VIP队列的主键编号值
	 * 
	 */
	@RequestMapping(value = "/queuingV/delVipAck")
	public ModelAndView delVipAck(Integer id,
	  ModelAndView mv) {

		queuingService.delVip(id);
		mv.setViewName("redirect:/queuingV/VipAck");
		return mv;
	}

	/**
	 * VIP队列修改 <br>
	 * flag=1跳向修改页面 <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value = "/queuingV/updateVipAck")
	public ModelAndView VipUpd(String flag, Integer id, 
		@ModelAttribute QueuingVip queuingVip, 
		ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到修改页面
			QueuingVip updateV = queuingService.updateVSel(id);
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			mv.addObject("updateV", updateV);
			mv.setViewName("/queuing/showUpdV");
		} else {
			// 执行修改
			queuingService.UpdV(queuingVip);
			mv.setViewName("redirect:/queuingV/VipAck");
		}
		// 返回ModelAndView
		return mv;
	}

	// 3-->历史队列查询带分页
	/**
	 * 历史队列的管理 跳向首页 分页查询
	 */
	@RequestMapping(value = "/queuingH/HistoryAck")
	public ModelAndView HistoryAck(Integer pageIndex,
			@ModelAttribute History history,
			Integer island_no, ModelAndView mv,
			HttpServletRequest request,HttpServletResponse response) {
		
		this.islandH(island_no,history);
		
		String pageParam="";
		if(history.getVagueiname()!=null && !history.getVagueiname().equals("")){
			pageParam+="&vagueiname="+history.getVagueiname();
		}
		if(history.getCar_code()!=null && !history.getCar_code().equals("")){
			pageParam+="&car_code="+history.getCar_code();
		}
		if(island_no!=null&&island_no>0){
			pageParam+="&island_no="+island_no;
		}
		if(history.getSupplier()!=null && !history.getSupplier().equals("")){
			pageParam+="&supplier="+history.getSupplier();
		}
		

		String sDate=request.getParameter("sDate");
		//System.out.println("1:"+sDate);
		if(sDate!=null && !"".equals(sDate)){
			pageParam+="&sDate="+sDate;
		}
		Date startDate=null;
		try {
			startDate=DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate=request.getParameter("eDate");
		//System.out.println("2:"+eDate);
		if(eDate!=null && !"".equals(eDate)){
			pageParam+="&eDate="+eDate;
		}
		Date endDate=null;
		try {
			endDate=DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("pageParam", pageParam);
		mv.addObject("model", history.getVagueiname());
		mv.addObject("target", history.getCar_code());
		mv.addObject("island_no", island_no);
		mv.addObject("targetSupplier", history.getSupplier());
		mv.addObject("sDate", sDate);
		mv.addObject("eDate", eDate);
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<History> pageListH = queuingService.selectHByPage(history, pageModel,startDate,endDate);
		mv.addObject("pageListH", pageListH);
		mv.addObject("pageModel", pageModel);
		
		List<Island> AddVgetI = queuingService.AddVgetI();
		mv.addObject("AddVgetI", AddVgetI);
		// 设置客户端跳转到查询请求
		mv.setViewName("queuing/showH");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 由于卸货岛在历史表是对象关联映射， 所以不能直接接收参数，需要创建卸货岛对象
	 */
	private void islandH(Integer island_no,History history) {
		if (island_no != null) {
			Island island = new Island();
			island.setNo(island_no);
			history.setHpartsI(island);
		}
	}
	
	@RequestMapping(value = "/queuingH/TodayAck")
	public ModelAndView TodayAck(HttpServletRequest request,ModelAndView mv) {
		// 设置客户端跳转到查询请求
		List<Island> lands=queuingService.selectIAll();
		mv.addObject("lands", lands);
		mv.setViewName("queuing/today");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value = "/queuingH/toPie")
	@ResponseBody
	public Object toPie(HttpServletRequest request) {
		// 设置客户端跳转到查询请求
		List<Island> lands=queuingService.selectIAll();
		List<Map<String, Object>> list=new ArrayList<>();
		int index=0;
		for (Island land : lands) {
			Map<String, Object> map= new HashMap<>();
			List<QueuingVip> listV=queuingService.selectVAll(land.getNo());
			List<Ordinary> listO=queuingService.selectOAll(land.getNo());
			History ing=queuingService.selectIng(land.getNo());
			map.put("all", listV.size()+listO.size());
			map.put("vip", listV.size());
			map.put("o", listO.size());
			map.put("ing", ing); 
			map.put("land", land);
			map.put("index", index++);
			list.add(map);
		}
		return list;
	}
	
	
	
	
//4、普通队列		
		/**
		 * 普队列的管理 跳向首页
		 * 分页查询
		 */
		@RequestMapping(value="/queuingO/OrdinaryAck")
		 public ModelAndView OrdinaryAck(Integer pageIndex,
				 @ModelAttribute Ordinary ordinary,
				 Integer island_no,ModelAndView mv){
			this.islandO(island_no,ordinary);
			String pageParam="";
			if(ordinary.getVagueiname()!=null){
				pageParam+="&vagueiname="+ordinary.getVagueiname();
			}
			if(island_no!=null&&island_no>0){
				pageParam+="&island_no="+island_no;
			}
			mv.addObject("pageParam", pageParam);
			mv.addObject("model", ordinary.getVagueiname());
			mv.addObject("target", ordinary.getCar_code());
			mv.addObject("island_no", island_no);
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Ordinary> pageListO = queuingService.selectOByPage(ordinary, pageModel);
			mv.addObject("pageListO", pageListO);
			mv.addObject("pageModel", pageModel);
			
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			
			// 设置客户端跳转到查询请求
			mv.setViewName("queuing/showO");
			// 返回ModelAndView
			return mv;
		}
		
		/**
		 * 由于卸货岛在普通表是对象关联映射， 所以不能直接接收参数，需要创建卸货岛对象
		 */
		private void islandO(Integer island_no,Ordinary ordinary) {
			if (island_no != null) {
				Island island = new Island();
				island.setNo(island_no);
				ordinary.setOpartsI(island);
			}
		}
		
		/**
		 * 普通队列添加 <br>
		 * flag=1跳向添加页面
		 * <br>
		 * flag=2执行添加
		 */
		@RequestMapping(value="/queuingO/OrdinaryAdd")
		 public ModelAndView OrdinaryAdd(String flag,
				 @ModelAttribute Ordinary ordinary,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
						//卸货岛组件
				List<Island> AddVgetI = queuingService.AddVgetI();
				mv.addObject("AddVgetI", AddVgetI);
				
				mv.setViewName("/queuing/showAddO");
			}else{
				//执行添加
				queuingService.addConteollerO(ordinary);
				mv.setViewName("redirect:/queuingO/OrdinaryAck");
			}
			return mv;
		}
		
		
		
		/**
		 * 普通队列删除 <br>
		 * 
		 * @param id 普通队列的主键编号值
		 * 
		 */
	@RequestMapping(value="/queuingO/delOAck")
	 public ModelAndView delOAck(Integer id,
			 ModelAndView mv){
		
		queuingService.delOrdinary(id);
		mv.setViewName("redirect:/queuingO/OrdinaryAck");
		return mv;
	}
		
		
		
		
	/**
	 * 普通队列修改 <br>
	 * flag=1跳向修改页面
	 * <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value="/queuingO/updOrdinaryAck")
	 public ModelAndView OrdinaryUpd(String flag,Integer id,
			 @ModelAttribute Ordinary ordinary,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到修改页面
			Ordinary updateO = queuingService.updateOSel(id);
			
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			mv.addObject("updateO", updateO);
			mv.setViewName("/queuing/showUpdO");
		}else{
			//执行修改
			queuingService.UpdO(ordinary);
			mv.setViewName("redirect:/queuingO/OrdinaryAck");
		}
	// 返回ModelAndView
		return mv;
	}
//友好提示
		@ResponseBody
		@RequestMapping(value="/queuingAdd/addValidate")
		public Object addValidate(HttpServletRequest request,
				 HttpServletResponse response){
			String car_code = request.getParameter("car_code");
			String judge = request.getParameter("flag");
			int queue_number = new Integer(request.getParameter("queue_number"));
			int island_no = new Integer(request.getParameter("island_no"));
			Map<String,Object> map = new HashMap<>();
			String test = queuingService.addValidate(island_no,car_code,judge);
				//judge=1-->VIP judge=2-->普通 验证车牌号是否已经存在
				if(!"".equals(test)){
					map.put("status", false);
					map.put("message", test);
				}else{
					map.put("status", true);
					map.put("message", "验证通过");
				}
				
				//验证排序位置是否合理 给出的提示
				
				if(judge.equals("1")){
					String position =queuingService.position(island_no,queue_number);
					//VIP队列 验证排序位置是否合理 给出的提示
					if(!"".equals(position)){
						map.put("queue", true);
						map.put("queuemessage", position);
					}else{
						map.put("queue", false);
						map.put("queuemessage", "验证通过");
					}
				}
				
				if(judge.equals("2")){
					/*String plain =queuingService.plain(island_no,queue_number);
					//普通队列 验证排序位置是否合理 给出的提示
					if(!"".equals(plain)){
						map.put("queue", true);
						map.put("queuemessage", plain);
					}else{
						map.put("queue", false);
						map.put("queuemessage", "验证通过");
					}*/
				}
			return map;
		}

		
		@ResponseBody
		@RequestMapping(value="/queuingAdd/IaddValidate")
		public Object IaddValidate(HttpServletRequest request,
				 HttpServletResponse response){
			
			String no = request.getParameter("no");
			String term = request.getParameter("term");
			//System.out.println(no+"==="+term);
			Map<String,Object> map = new HashMap<>();
			String flag = queuingService.IaddValidate(no,term);
				System.out.println(flag);
				if(!"".equals(flag)){
					map.put("status", false);
					map.put("message", flag);
				}else{
					map.put("status", true);
					map.put("message", "验证通过");
				}
			return map;
		}
		
		//导出历史记录Excel git提交
		@RequestMapping(value="/queuingH/exportExcel")
		public void exportExcel(HttpServletRequest request,HttpServletResponse response,
		Integer island_no,@ModelAttribute History history){
			
			this.islandH(island_no,history);
			PageModel pageModel = new PageModel();
			pageModel.setPageSize(Integer.MAX_VALUE);
			
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
			
			
			List<History> pageListH = queuingService.selectHByPage(history, pageModel,startDate,endDate);
			
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			String sheetName = "历史记录";//sheet名称
			HSSFSheet sheet = workbook.createSheet(sheetName);
			sheet.setFitToPage(true);  
		    sheet.setHorizontallyCenter(true);
		    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		    CellRangeAddress c = CellRangeAddress.valueOf("A2:L2");  
			sheet.setAutoFilter(c);
		    //设置列宽
		    sheet.setColumnWidth(0, 4200);
	        sheet.setColumnWidth(1, 4200);		
	        sheet.setColumnWidth(2, 3200);
	        sheet.setColumnWidth(3, 5800);
	        sheet.setColumnWidth(4, 5800);
	        sheet.setColumnWidth(5, 5800);
	        sheet.setColumnWidth(6, 5800);
	        sheet.setColumnWidth(7, 5800);
	        sheet.setColumnWidth(8, 5800);
	        sheet.setColumnWidth(9, 5800);
	        sheet.setColumnWidth(10, 5800);
	        sheet.setColumnWidth(11, 3200);
			//定义表格行索引
	        int index=0;
	        
	      //添加标题
	        HSSFRow row_title = sheet.createRow(index++);
	        row_title.setHeight((short) 600);// 设置行高 
	        HSSFCell row_title0 = row_title.createCell(0);   
	        row_title0.setCellValue(new HSSFRichTextString("历史记录")); 
	        //合并表头单元格
	        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)11),ExcelUtil.createTitleStyle(workbook));
	        sheet.addMergedRegion(new Region(
	        0 //first row (0-based) from 行  
	        ,(short)0 //first column (0-based) from 列     
	        ,0//last row  (0-based)  to 行
	        ,(short)11//last column  (0-based)  to 列     
	        ));
	        
	        //添加头信息
	        String[] titles={"卸货岛名称","供应商","车牌号","车辆类型","进场时间","取号时间","卸货开始","卸货结束","出场时间","卸货时长","在场时长","描述"};
	        HSSFRow row_head = sheet.createRow(index++);
	        for (int i=0; i<titles.length;i++) {
	        	HSSFCell cell = row_head.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
			}
	        
	        for (History entity:pageListH) {
	        	HSSFRow row = sheet.createRow(index++);
	        	
				//卸货岛名称
				HSSFCell cell0 = row.createCell(0);
				if(!"".equals(entity.getHpartsI())&&entity.getHpartsI()!=null){
					cell0.setCellValue(entity.getHpartsI().getIname());
				}else{
					cell0.setCellValue("");
				}
				
				//供应商
				HSSFCell cell1 = row.createCell(1);
				if(entity.getSupplier()!=null){
					cell1.setCellValue(entity.getSupplier());
				}
				//车牌号
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(entity.getCar_code());
				
				//车辆类型
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(entity.getVehicleType());
				
				
				//进场时间
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(DateUtil.DateToString(entity.getCominDate(), "yyyy-MM-dd HH:mm:ss"));
				
				//取号时间
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(DateUtil.DateToString(entity.getTake_time(), "yyyy-MM-dd HH:mm:ss"));
				
				//驶入时间
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(DateUtil.DateToString(entity.getComein_time(), "yyyy-MM-dd HH:mm:ss"));
				//驶出时间
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(DateUtil.DateToString(entity.getGoout_time(), "yyyy-MM-dd HH:mm:ss"));
				
				//出场时间
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(DateUtil.DateToString(entity.getOutDate(), "yyyy-MM-dd HH:mm:ss"));
				
				
				//卸货时长
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(entity.getReduce());
				
				//在场时长
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(entity.getPlant());
				
				//备注
				HSSFCell cell11 = row.createCell(11);
			if(!"".equals(entity.getSource())){
				if(entity.getSource()==1){
					cell11.setCellValue("普通");
				}
				if(entity.getSource()==0){
					cell11.setCellValue("急件");
				}
			}	
		}
	        try {
				String fileName="历史记录";
				ExcelUtil.write(request, response, workbook, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/*
		 * 现场查询统计 以表格形式展示将饼图屏蔽 
		 * 饼图的链接  
		 * Conteoller:
		 * @RequestMapping(value="/queuingH/TodayAck") 
		 * @RequestMapping(value="/queuingH/toPie")
		 * jsp:
		 * today.jsp           
		 */
		@RequestMapping(value ="/queuingS/SceneStatistics")
		public ModelAndView scene(HttpServletRequest request,ModelAndView mv) {
			// 设置客户端跳转到查询请求
			List<Map<String, Object>> list=queuingService.selectScene();
			mv.addObject("list", list);
			//统计总数
			int Sum = queuingService.selectSum();
			int VipSum = queuingService.selectVipSum();
			int OSum = queuingService.selectOSum();
			mv.addObject("Sum", Sum);
			mv.addObject("VipSum", VipSum);
			mv.addObject("OSum", OSum);
			mv.setViewName("queuing/scene");
			// 返回ModelAndView
			return mv;
		}
		
		//进场未排队车辆查询  
		@RequestMapping(value = "/queuingT/TargetSelect")
		public String TargetSelect(
				Integer pageIndex,
				 @ModelAttribute LocationInout locationInout,
				 HttpServletRequest request,Model model) {
			
			String pageParam="";
			if(locationInout.getVehicleCode()!=null&&!"".equals(locationInout.getVehicleCode())){
				pageParam+="&vehicleCode="+locationInout.getVehicleCode();
			}
			if(locationInout.getVehicleType()!=null){
				pageParam+="&vehicleType="+locationInout.getVehicleType();
			}
			if(locationInout.getSupplier()!=null && !locationInout.getSupplier().equals("")){
				pageParam+="&supplier="+locationInout.getSupplier();
			}
			model.addAttribute("targetSupplier", locationInout.getSupplier());
			
			String sDate=request.getParameter("sDate");
			if(sDate!=null && !"".equals(sDate)){
				pageParam+="&sDate="+sDate;
			}
			Date startDate=null;
			try {
				startDate=DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
			} catch (Exception e) {
				e.printStackTrace();
			}
			String eDate=request.getParameter("eDate");
			if(eDate!=null && !"".equals(eDate)){
				pageParam+="&eDate="+eDate;
			}
			Date endDate=null;
			try {
				endDate=DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("cominDate", sDate);
			model.addAttribute("outDate", eDate);
			model.addAttribute("pageParam", pageParam);
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			
			List<LocationInout> locationInouts = queuingService.findInout(locationInout, pageModel,startDate,endDate);
			model.addAttribute("locationInouts", locationInouts);
			model.addAttribute("pageModel", pageModel);
			model.addAttribute("model", locationInout);
			
			return "queuing/queuingShowInout";
		}
		
		
		
		
		
}
