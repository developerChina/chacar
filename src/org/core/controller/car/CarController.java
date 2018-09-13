package org.core.controller.car;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.core.domain.car.CarAuthority;
import org.core.domain.car.CarDistinguish;
import org.core.domain.car.CarInfo;
import org.core.domain.car.CarLogs;
import org.core.domain.car.CarPark;
import org.core.domain.car.CarPassageway;
import org.core.domain.location.LocationInout;
import org.core.service.car.CarAuthorityService;
import org.core.service.car.CarDistinguishService;
import org.core.service.car.CarInfoService;
import org.core.service.car.CarLogsService;
import org.core.service.car.CarParkService;
import org.core.service.car.CarPassagewayService;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.StringUtils;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class CarController {
	
	@Autowired
	@Qualifier("carParkService")
	private CarParkService carParkService;
	@Autowired
	@Qualifier("carDistinguishService")
	private CarDistinguishService carDistinguishService;
	@Autowired
	@Qualifier("carPassagewayService")
	private CarPassagewayService carPassagewayService;
	@Autowired
	@Qualifier("carAuthorityService")
	private CarAuthorityService carAuthorityService;
	
	@Autowired
	@Qualifier("carInfoService")
	private CarInfoService carInfoService;
	
	@Autowired
	@Qualifier("carLogsService")
	private CarLogsService carLogsService;
	
	/**
	 * 车场管理
	 */
	@RequestMapping(value="/car/carPark")
	 public ModelAndView carPark(Integer pageIndex,ModelAndView mv,@ModelAttribute CarPark carPark){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarPark> parks = carParkService.selectByPage(carPark, pageModel);
		mv.addObject("parks", parks);
		mv.addObject("pageModel", pageModel);
		mv.addObject("carPark", carPark);
		mv.setViewName("car/carPark");
		String pageParam="";
		if(carPark.getName()!=null){
			pageParam+="&name="+carPark.getName();
		}
		mv.addObject("pageParam", pageParam);
		return mv;
	}
	/**
	 * 车场添加
	 */
	@RequestMapping(value="/car/addcarPark")
	public ModelAndView addcarPark(
			 String flag,
			 @ModelAttribute CarPark carPark,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			mv.setViewName("car/carParkAdd");
		}else{
			carParkService.save(carPark); 
			mv.setViewName("redirect:/car/carPark");
		}
		return mv;
	}
	/**
	 * 车场修改
	 */
	@RequestMapping(value="/car/updatecarPark")
	public ModelAndView updatecarPark(
			 String flag,
			 @ModelAttribute CarPark carPark,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarPark target = carParkService.selectById(carPark.getId());
			mv.addObject("park", target);
			mv.setViewName("car/carParkUpdate");
		}else{
			carParkService.update(carPark);
			mv.setViewName("redirect:/car/carPark");
		}
		return mv;
	}
	/**
	 * 车场删除
	 */
	@RequestMapping(value="/car/deletecarPark")
	 public ModelAndView deletecarPark(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carParkService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carPark");
		return mv;
	}
	
	
	/**
	 * 车识别仪管理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carDistinguish")
	 public ModelAndView carDistinguish(Integer pageIndex,ModelAndView mv,@ModelAttribute CarDistinguish carDistinguish){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarDistinguish> distiguishes = carDistinguishService.selectByPage(carDistinguish, pageModel);
		mv.addObject("distiguishes", distiguishes);
		mv.addObject("pageModel", pageModel);
		mv.addObject("carDistinguish", carDistinguish);
		mv.setViewName("car/carDistinguish");
		String pageParam="";
		if(carDistinguish.getName()!=null){
			pageParam+="&name="+carDistinguish.getName();
		}
		if(carDistinguish.getIp()!=null){
			pageParam+="&ip="+carDistinguish.getIp();
		}
		mv.addObject("pageParam", pageParam);
		return mv;
	}
	
	/**
	 * 车识别仪添加
	 */
	@RequestMapping(value="/car/addcarDistinguish")
	public ModelAndView addcarDistinguish(
			 String flag,
			 @ModelAttribute CarDistinguish carDistinguish,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			mv.setViewName("car/carDistinguishAdd");
		}else{
			carDistinguishService.save(carDistinguish); 
			mv.setViewName("redirect:/car/carDistinguish");
		}
		return mv;
	}
	/**
	 * 车识别仪修改
	 */
	@RequestMapping(value="/car/updatecarDistinguish")
	public ModelAndView updatecarDistinguish(
			 String flag,
			 @ModelAttribute CarDistinguish carDistinguish,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarDistinguish target = carDistinguishService.selectById(carDistinguish.getId());
			mv.addObject("distinguish", target);
			mv.setViewName("car/carDistinguishUpdate");
		}else{
			carDistinguishService.update(carDistinguish);
			mv.setViewName("redirect:/car/carDistinguish");
		}
		return mv;
	}
	/**
	 * 车识别仪删除
	 */
	@RequestMapping(value="/car/deletecarDistinguish")
	 public ModelAndView deletecarDistinguish(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carDistinguishService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carDistinguish");
		return mv;
	}
	
	/**
	 * 出入口管理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carPassageway")
	 public ModelAndView carPassageway(Integer pageIndex,ModelAndView mv,@ModelAttribute CarPassageway carPassageway){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarPassageway> passageways = carPassagewayService.selectByPage(carPassageway, pageModel);
		List<CarPark> carParks=carParkService.selectAll();
		mv.addObject("carParks", carParks);
		mv.addObject("passageways", passageways);
		mv.addObject("pageModel", pageModel);
		mv.addObject("carPassageway", carPassageway);
		mv.setViewName("car/carPassageway");
		String pageParam="";
		if(carPassageway.getName()!=null){
			pageParam+="&name="+carPassageway.getName();
		}
		if(carPassageway.getPark_id()>0){
			pageParam+="&park_id="+carPassageway.getPark_id();
		}
		mv.addObject("pageParam", pageParam);
		
		return mv;
	}
	
	/**
	 * 出入口添加
	 */
	@RequestMapping(value="/car/addcarPassageway")
	public ModelAndView addcarPassageway(
			 String flag,
			 @ModelAttribute CarPassageway carPassageway,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			
			//添加未被使用识别仪
			List<CarPassageway> carPassageways=carPassagewayService.selectAll();
			String dids="";
			for (CarPassageway passageway : carPassageways) {
				dids=dids+passageway.getDistinguish_ids()+",";
			}
			List<CarDistinguish> distinguishs=carDistinguishService.selectFillterIds(dids);
			mv.addObject("distinguishs", distinguishs);
			mv.setViewName("car/carPassagewayAdd");
		}else{
			carPassagewayService.save(carPassageway); 
			mv.setViewName("redirect:/car/carPassageway");
		}
		return mv;
	}
	/**
	 * 出入口修改
	 */
	@RequestMapping(value="/car/updatecarPassageway")
	public ModelAndView updatecarPassageway(
			 String flag,
			 @ModelAttribute CarPassageway carPassageway,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarPassageway target = carPassagewayService.selectById(carPassageway.getId());
			mv.addObject("carPassageway", target);
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			
			//添加本出入口的识别器
			List<CarDistinguish> exitsdistinguishs=carDistinguishService.selectByIds(target.getDistinguish_ids());
			mv.addObject("exitsdistinguishs", exitsdistinguishs);
			
			//添加未被使用识别仪
			List<CarPassageway> carPassageways=carPassagewayService.selectAll();
			String dids="";
			for (CarPassageway passageway : carPassageways) {
				dids=dids+passageway.getDistinguish_ids()+",";
			}
			List<CarDistinguish> distinguishs=carDistinguishService.selectFillterIds(dids);
			mv.addObject("distinguishs", distinguishs);
			
			mv.setViewName("car/carPassagewayUpdate");
		}else{
			carPassagewayService.update(carPassageway);
			mv.setViewName("redirect:/car/carPassageway");
		}
		return mv;
	}
	/**
	 * 出入口删除
	 */
	@RequestMapping(value="/car/deletecarPassageway")
	 public ModelAndView deletecarPassageway(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carPassagewayService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carPassageway");
		return mv;
	}
	
	/**
	 * 审核访问记录
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/car/selectByParkid")
	@ResponseBody		
	public Object selectByParkid(HttpServletRequest request, HttpServletResponse response){
		String packid=request.getParameter("packid");
		List<CarPassageway> ways=new ArrayList<>();
		if(StringUtils.isNotBlank(packid)){
			ways=carPassagewayService.selectByParkid(Integer.parseInt(packid));
		}
		return ways;
	}
	
	
	/**
	 * 车辆绑定授权
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carAuthority")
	 public ModelAndView carAuthority(Integer pageIndex,ModelAndView mv,@ModelAttribute CarAuthority carAuthority){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		String pageParam="";
		if(carAuthority.getCarno()!=null&&!"".equals(carAuthority.getCarno())){
			pageParam+="&carno="+carAuthority.getCarno();
		}
		if(carAuthority.getName()!=null&&!"".equals(carAuthority.getName())){
			pageParam+="&name="+carAuthority.getName();
		}
		mv.addObject("name", carAuthority.getName());
		mv.addObject("pageParam", pageParam);
		
		
		List<CarAuthority> authoritys = carAuthorityService.selectByPage(carAuthority, pageModel);
		mv.addObject("authoritys", authoritys);
		mv.addObject("pageModel", pageModel);
		mv.addObject("carAuthority", carAuthority);
		mv.setViewName("car/carAuthority");
		
		return mv;
	}
	
	/**
	 * 车辆授权添加
	 */
	@RequestMapping(value="/car/addcarAuthority")
	public ModelAndView addcarAuthority(
			Integer pageIndex,
			 String flag,
			 @ModelAttribute CarAuthority carAuthority,
			 @ModelAttribute CarInfo carInfo,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		
		if(flag.equals("1")){
			//添加车辆列表
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			String pageParam="";
			if(carInfo.getName()!=null&&!"".equals(carInfo.getName())){
				pageParam+="&name="+carInfo.getName();
			}
			if(carInfo.getCarno()!=null&&!"".equals(carInfo.getCarno())){
				pageParam+="&carno="+carInfo.getCarno();
			}
			if(carInfo.getCompany()!=null&&!"".equals(carInfo.getCompany())){
				pageParam+="&company="+carInfo.getCompany();
			}
			if(carInfo.getWorkNumber()!=null&&!"".equals(carInfo.getWorkNumber())){
				pageParam+="&workNumber="+carInfo.getWorkNumber();
			}
			mv.addObject("pageParam", pageParam);
			List<CarInfo> cars = carInfoService.selectByPage(carInfo, pageModel);
			mv.addObject("cars", cars);
			mv.addObject("carInfo", carInfo);
			mv.addObject("pageModel", pageModel);
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			mv.setViewName("car/carAuthorityAdd");
		}else{
			String carnos=request.getParameter("carnos");
			String[] passageway_ids=request.getParameterValues("passageway_ids");
			for (String  passageway_id : passageway_ids) {
				for (String carno : carnos.split(",")) {
					carAuthority.setCarno(carno);
					carAuthority.setPassageway_id(Integer.parseInt(passageway_id));
					carAuthorityService.saveOrUpdate(carAuthority); 
				}
			}
			mv.setViewName("redirect:/car/carAuthority");
		}
		return mv;
	}
	/**
	 * 车辆授权修改( 直接授权，不需要指定修改 )
	 */
	@RequestMapping(value="/car/updatecarAuthority")
	public ModelAndView updatecarAuthority(
			 String flag,
			 @ModelAttribute CarAuthority carAuthority,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarAuthority target = carAuthorityService.selectById(carAuthority.getId());
			mv.addObject("authority", target);
			mv.setViewName("car/carAuthorityUpdate");
		}else{
			String[] passageway_ids=request.getParameterValues("passageway_ids");
			for (String  passageway_id : passageway_ids) {
				carAuthority.setPassageway_id(Integer.parseInt(passageway_id));
				carAuthorityService.saveOrUpdate(carAuthority); 
			}
			mv.setViewName("redirect:/car/carAuthority");
		}
		return mv;
	}
	/**
	 * 车辆授权删除
	 */
	@RequestMapping(value="/car/deletecarAuthority")
	 public ModelAndView deletecarAuthority(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carAuthorityService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carAuthority");
		return mv;
	}
	
	
	
	
	/**
	 * 车辆管理
	 */
	@RequestMapping(value="/car/carInfo")
	 public ModelAndView carInfo(Integer pageIndex,ModelAndView mv,@ModelAttribute CarInfo carInfo){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		String pageParam="";
		if(carInfo.getName()!=null&&!"".equals(carInfo.getName())){
			pageParam+="&name="+carInfo.getName();
		}
		if(carInfo.getCarno()!=null&&!"".equals(carInfo.getCarno())){
			pageParam+="&carno="+carInfo.getCarno();
		}
		if(carInfo.getCompany()!=null&&!"".equals(carInfo.getCompany())){
			pageParam+="&company="+carInfo.getCompany();
		}
		if(carInfo.getWorkNumber()!=null&&!"".equals(carInfo.getWorkNumber())){
			pageParam+="&workNumber="+carInfo.getWorkNumber();
		}
		mv.addObject("pageParam", pageParam);
		
		List<CarInfo> cars = carInfoService.selectByPage(carInfo, pageModel);
		mv.addObject("cars", cars);
		mv.addObject("pageModel", pageModel);
		mv.addObject("carInfo", carInfo);
		mv.setViewName("car/carInfo");
		
		return mv;
	}
	/**
	 * 车辆添加
	 */
	@RequestMapping(value="/car/addcarInfo")
	public ModelAndView addcarInfo(
			 String flag,
			 @ModelAttribute CarInfo carInfo,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			mv.setViewName("car/carInfoAdd");
		}else{
			carInfoService.saveCar(carInfo); 
			mv.setViewName("redirect:/car/carInfo");
		}
		return mv;
	}
	/**
	 * 车辆修改
	 */
	@RequestMapping(value="/car/updatecarInfo")
	public ModelAndView updatecarInfo(
			 String flag,
			 @ModelAttribute CarInfo carInfo,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarInfo target = carInfoService.selectById(carInfo.getId());
			mv.addObject("car", target);
			mv.setViewName("car/carInfoUpdate");
		}else{
			carInfoService.updateCar(carInfo);
			mv.setViewName("redirect:/car/carInfo");
		}
		return mv;
	}
	/**
	 * 车辆删除
	 */
	@RequestMapping(value="/car/deletecarInfo")
	 public ModelAndView deletecarInfo(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carInfoService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carInfo");
		return mv;
	}
	
	/**
	 * 批量导入车辆页面
	 */
	@RequestMapping(value = "/car/importcarPage")
	public ModelAndView importcarPage(ModelAndView mv) {
		mv.setViewName("car/carImport");
		return mv;
	}
	
	/**
	 * 批量导入车辆
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/car/importcar")
	public ModelAndView importcar(ModelAndView mv,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<String, Object> map = new HashMap<>();
		//执行excel的行索引
		int excelRowIndex=0;
		try {
			InputStream is = file.getInputStream();
			Workbook workbook = new HSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			List<Map<Integer, String>> list = ExcelUtil.readSheet(sheet, colNum);
			//名称
			for (Map<Integer, String> data : list) {
				CarInfo car=new CarInfo();
				for (Integer key : data.keySet()) {
					car.setName(data.get(1));
					car.setCarno(data.get(2));
				}
				if(StringUtils.isNotBlank(car.getCarno())){
					carInfoService.saveOrUpdateDept(car);
				}
				excelRowIndex++;
			}
			map.put("status", true);
			map.put("message", "成功导入"+list.size()+"行数据");
		} catch (IOException e1) {
			e1.printStackTrace();
			map.put("status", false);
			map.put("message", "成功导入"+excelRowIndex+"行数据");
			map.put("exception", "导入第"+(excelRowIndex+1)+"行数据出错："+e1.getMessage());
		}
		mv.addObject("map", map);
		mv.setViewName("upload/resultImport");
		return mv;
	}
	
	//停车场进出记录
	@RequestMapping(value="/car/carRecord")
	 public ModelAndView carRecord(Integer pageIndex,ModelAndView mv,
			 @ModelAttribute CarLogs carLogs,
			 HttpServletRequest request,HttpServletResponse response){
			
			String pageParam="";
			if(carLogs.getCacrno()!=null && !carLogs.getCacrno().equals("")){
				pageParam+="&cacrno="+carLogs.getCacrno();
			}
			if(carLogs.getCarMaster()!=null && !carLogs.getCarMaster().equals("")){
				pageParam+="&carMaster="+carLogs.getCarMaster();
			}
			
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
			mv.addObject("pageParam", pageParam);
			mv.addObject("targetCacrno",carLogs.getCacrno());
			mv.addObject("targetCarMaster",carLogs.getCarMaster());
			mv.addObject("sDate", sDate);
			mv.addObject("eDate", eDate);
			PageModel pageModel = new PageModel();
			if (pageIndex != null) {
				pageModel.setPageIndex(pageIndex);
			}
			List<CarLogs> carLogsList = carLogsService.selectCarLogs(carLogs,pageModel,startDate,endDate);
			
			mv.addObject("carLogsList", carLogsList);
			mv.addObject("pageModel", pageModel);
			mv.setViewName("car/showCarRecord");
			return mv;
	}
	//停车场进出记录导出
	@RequestMapping(value="/car/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,	
		@ModelAttribute CarLogs carLogs){
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
		List<CarLogs> carLogsList = carLogsService.selectCarLogs(carLogs,pageModel,startDate,endDate);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "停车场进出记录";//sheet名称
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setFitToPage(true);  
	    sheet.setHorizontallyCenter(true);
	    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
	    CellRangeAddress c = CellRangeAddress.valueOf("A2:G2");
	    sheet.setAutoFilter(c);
		sheet.setColumnWidth(0, 5800);
        sheet.setColumnWidth(1, 5800);
        sheet.setColumnWidth(2, 5800);
        sheet.setColumnWidth(3, 5800);
        sheet.setColumnWidth(4, 5800);
        sheet.setColumnWidth(5, 5800);
        sheet.setColumnWidth(6, 5800);
        int index=0;
        HSSFRow row_title = sheet.createRow(index++);
        row_title.setHeight((short) 600);// 设置行高 
        HSSFCell row_title0 = row_title.createCell(0);   
        row_title0.setCellValue(new HSSFRichTextString("停车场进出记录")); 
        //合并表头单元格
        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)6),ExcelUtil.createTitleStyle(workbook));
        sheet.addMergedRegion(new Region(
        0 //first row (0-based) from 行  
        ,(short)0 //first column (0-based) from 列     
        ,0//last row  (0-based)  to 行
        ,(short)6//last column  (0-based)  to 列     
        ));
		
        String[] titles={"车主","车牌号","进口","驶入时间","出口","驶出时间","停车时长"};
        HSSFRow row_head = sheet.createRow(index++);
        for (int i=0; i<titles.length;i++) {
        	HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
        
        for (CarLogs entity : carLogsList) {
        	HSSFRow row = sheet.createRow(index++);
        	//"车主",
			HSSFCell cell0 = row.createCell(0);
			if(entity.getCarMaster()!=null){
				cell0.setCellValue(entity.getCarMaster());
			}
			//"车牌号",
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entity.getCacrno());
			
			//"进口",
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(entity.getInIpName());
			
			//"驶入时间",
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(DateUtil.DateToString(entity.getShootTime(), "yyyy-MM-dd HH:mm:ss"));
			
			//"出口",
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(entity.getOutIpName());
			
			//"驶出时间",
			HSSFCell cell5 = row.createCell(5);
			if(entity.getOutTime()!=null){
				cell5.setCellValue(DateUtil.DateToString(entity.getOutTime(), "yyyy-MM-dd HH:mm:ss"));
			}
			
			//"停车时长",
			HSSFCell cell6 = row.createCell(6);
			if(entity.getPlant()!=null){
				cell6.setCellValue(entity.getPlant());
			}
        }
        
        try {
			String fileName="车辆进出厂记录";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	
	//停车场车辆信息导出
		@RequestMapping(value="/car/induce")
		public void exportExcelCar(HttpServletRequest request,
				HttpServletResponse response,
				@ModelAttribute CarInfo carInfo){
			PageModel pageModel = new PageModel();
			pageModel.setPageSize(Integer.MAX_VALUE);
			
			List<CarInfo> cars = carInfoService.selectByPage(carInfo, pageModel);

			HSSFWorkbook workbook = new HSSFWorkbook();
			String sheetName = "停车场车辆信息";//sheet名称
			HSSFSheet sheet = workbook.createSheet(sheetName);
			sheet.setFitToPage(true);  
		    sheet.setHorizontallyCenter(true);
		    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		    CellRangeAddress c = CellRangeAddress.valueOf("A2:K2");
		    sheet.setAutoFilter(c);
			sheet.setColumnWidth(0, 3800);
	        sheet.setColumnWidth(1, 3800);
	        sheet.setColumnWidth(2, 3800);
	        sheet.setColumnWidth(3, 5800);
	        sheet.setColumnWidth(4, 2800);
	        sheet.setColumnWidth(5, 5800);
	        sheet.setColumnWidth(6, 2800);
	        sheet.setColumnWidth(7, 2800);
	        sheet.setColumnWidth(8, 3800);
	        sheet.setColumnWidth(9, 3800);
	        sheet.setColumnWidth(10, 3800);
	        int index=0;
	        HSSFRow row_title = sheet.createRow(index++);
	        row_title.setHeight((short) 600);// 设置行高 
	        HSSFCell row_title0 = row_title.createCell(0);   
	        row_title0.setCellValue(new HSSFRichTextString("停车场车辆信息")); 
	        //合并表头单元格
	        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)10),ExcelUtil.createTitleStyle(workbook));
	        sheet.addMergedRegion(new Region(
	        0 //first row (0-based) from 行  
	        ,(short)0 //first column (0-based) from 列     
	        ,0//last row  (0-based)  to 行
	        ,(short)10//last column  (0-based)  to 列     
	        ));
			
	        String[] titles={"车主姓名","车牌号","联系电话","身份证号","工号","所在单位","班组","岗位职务","车辆品牌型号","车辆属性","颜色"};
	        HSSFRow row_head = sheet.createRow(index++);
	        for (int i=0; i<titles.length;i++) {
	        	HSSFCell cell = row_head.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
			}
			
	        for (CarInfo entity : cars) {
	        	HSSFRow row = sheet.createRow(index++);
	        	//"车主",
				HSSFCell cell0 = row.createCell(0);
				if(entity.getName()!=null){
					cell0.setCellValue(entity.getName());
				}
				//"车牌号",
				HSSFCell cell1 = row.createCell(1);
				if(entity.getCarno()!=null){
					cell1.setCellValue(entity.getCarno());
				}
				//"tel",
				HSSFCell cell2 = row.createCell(2);
				if(entity.getTel()!=null){
					cell2.setCellValue(entity.getTel());
				}
				//"idNumber",
				HSSFCell cell3 = row.createCell(3);
				if(entity.getIdNumber()!=null){
					cell3.setCellValue(entity.getIdNumber());
				}
				//"workNumber",
				HSSFCell cell4 = row.createCell(4);
				if(entity.getWorkNumber()!=null){
					cell4.setCellValue(entity.getWorkNumber());
				}
				//"company",
				HSSFCell cell5 = row.createCell(5);
				if(entity.getCompany()!=null){
					cell5.setCellValue(entity.getCompany());
				}
				//"team",
				HSSFCell cell6 = row.createCell(6);
				if(entity.getTeam()!=null){
					cell6.setCellValue(entity.getTeam());
				}
				//"job",
				HSSFCell cell7 = row.createCell(7);
				if(entity.getJob()!=null){
					cell7.setCellValue(entity.getJob());
				}
				//"model",
				HSSFCell cell8 = row.createCell(8);
				if(entity.getModel()!=null){
					cell8.setCellValue(entity.getModel());
				}
				//"attribute",
				HSSFCell cell9 = row.createCell(9);
				if(entity.getAttribute()!=null){
					cell9.setCellValue(entity.getAttribute());
				}
				//"colour",
				HSSFCell cell10 = row.createCell(10);
				if(entity.getColour()!=null){
					cell10.setCellValue(entity.getColour());
				}
	        }
	        try {
				String fileName="停车场车辆信息";
				ExcelUtil.write(request, response, workbook, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	
		//避免冲突 车辆导入模板			
		@RequestMapping(value = "/car/exportTemplate")
		public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
			// 声明一个工作薄
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("车辆信息");
			sheet.setFitToPage(true);
			sheet.setHorizontallyCenter(true);
			// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
			CellRangeAddress c = CellRangeAddress.valueOf("A1:L1");
			sheet.setAutoFilter(c);
			sheet.setColumnWidth(0, 2800);
			sheet.setColumnWidth(1, 3800);
	        sheet.setColumnWidth(2, 3800);
	        sheet.setColumnWidth(3, 3800);
	        sheet.setColumnWidth(4, 5800);
	        sheet.setColumnWidth(5, 2800);
	        sheet.setColumnWidth(6, 5800);
	        sheet.setColumnWidth(7, 2800);
	        sheet.setColumnWidth(8, 2800);
	        sheet.setColumnWidth(9, 3800);
	        sheet.setColumnWidth(10, 3800);
	        sheet.setColumnWidth(11, 3800);
			// 定义表格行索引
			int index = 0;
			// 添加头信息
			String[] titles = { "编号","车主姓名","车牌号","联系电话","身份证号","工号","所在单位","班组","岗位职务","车辆品牌型号","车辆属性","颜色"};
			HSSFRow row_head = sheet.createRow(index++);
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell = row_head.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
			}
			try {
				String fileName="车辆导入模板";
				ExcelUtil.write(request, response, workbook, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//友好提示 此车辆已经被添加	
		@ResponseBody
		@RequestMapping(value="/car/addValidate")
		public Object addValidate(HttpServletRequest request,
				 HttpServletResponse response){
			
			String carno = request.getParameter("carno");
			Map<String,Object> map = new HashMap<>();
			String test = carInfoService.addValidate(carno);
			if(!"".equals(test)){
				map.put("status", false);
				map.put("message", test);
			}else{
				map.put("status", true);
				map.put("message", "验证通过");
			}
			return map;
		}
	
		/**
		 * 批量导入车辆  改进
		 */
		@SuppressWarnings("unused")
		@RequestMapping(value = "/car/importCar")
		public ModelAndView importCar(ModelAndView mv,
				@RequestParam(value = "file", required = false) MultipartFile file) {
			Map<String, Object> map = new HashMap<>();
			//执行excel的行索引
			int excelRowIndex=0;
			int success=0;
			String failure="";
			try {
				InputStream is = file.getInputStream();
				Workbook workbook = new HSSFWorkbook(is);
				Sheet sheet = workbook.getSheetAt(0);
				Row row = sheet.getRow(0);
				int colNum = row.getPhysicalNumberOfCells();
				List<Map<Integer, String>> list = ExcelUtil.readSheet(sheet, colNum);
				//名称
				for (Map<Integer, String> data : list) {
					CarInfo car=new CarInfo();
					for (Integer key : data.keySet()) {
						car.setName(data.get(1));
						car.setCarno(data.get(2));
						car.setTel(data.get(3));
						car.setIdNumber(data.get(4));
						car.setWorkNumber(data.get(5));
						car.setCompany(data.get(6));
						car.setTeam(data.get(7));
						car.setJob(data.get(8));
						car.setModel(data.get(9));
						car.setAttribute(data.get(10));
						car.setColour(data.get(11));
					}
					if(StringUtils.isNotBlank(car.getCarno())){
						String test = carInfoService.addValidate(car.getCarno());
						if(!"".equals(test)){
							failure+="导入第"+(excelRowIndex+1)+"行数据出错:车牌号已经存在!<br/>";
						}else{
							carInfoService.saveCar(car);
							success++;
						}
					}else{
						failure+="导入第"+(excelRowIndex+1)+"行数据出错:车牌号为空!<br/>";
					}
					excelRowIndex++;
				}
				map.put("status", true);
				map.put("message", "成功导入"+success+"行数据");
				map.put("exception", failure);
			} catch (IOException e1) {
				e1.printStackTrace();
				map.put("status", false);
				map.put("message", "成功导入"+excelRowIndex+"行数据");
				map.put("exception", "导入第"+(excelRowIndex+1)+"行数据出错："+e1.getMessage());
			}
			mv.addObject("map", map);
			mv.setViewName("upload/resultImport");
			return mv;
		}
		
		
		
		
		
		
		
		
		
}