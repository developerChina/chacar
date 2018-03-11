package org.core.controller.locationController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;
import org.core.domain.location.LocationInout;
import org.core.service.location.InoutService;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class InoutController {
	@Autowired
	@Qualifier("inoutService")
	private InoutService inoutService;
	//查询
	@RequestMapping(value="/Inout/selectInout")
	public String selectInout(Integer pageIndex,
			 @ModelAttribute LocationInout locationInout,
			 HttpServletRequest request,Model model){
		
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
		/** 查询用户信息     */
		List<LocationInout> locationInouts = inoutService.findLocationAlarm(locationInout, pageModel,startDate,endDate);
		model.addAttribute("locationInouts", locationInouts);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("model", locationInout);
		
		return "location/showInout";
	}
	
	
	
	
	//车辆进出场记录导出
	@RequestMapping(value="/Inout/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,	
		@ModelAttribute LocationInout locationInout){
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
			List<LocationInout> locationInouts = inoutService.findLocationAlarm(locationInout, pageModel,startDate,endDate);

			HSSFWorkbook workbook = new HSSFWorkbook();
			String sheetName = "车辆进出厂记录";//sheet名称
			HSSFSheet sheet = workbook.createSheet(sheetName);
			sheet.setFitToPage(true);  
		    sheet.setHorizontallyCenter(true);
		    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		    CellRangeAddress c = CellRangeAddress.valueOf("A2:H2");  
			sheet.setAutoFilter(c);
			sheet.setColumnWidth(0, 5800);
	        sheet.setColumnWidth(1, 5800);
	        sheet.setColumnWidth(2, 5800);
	        sheet.setColumnWidth(3, 5800);
	        sheet.setColumnWidth(4, 5800);
	        sheet.setColumnWidth(5, 5800);
	        sheet.setColumnWidth(6, 5800);
	        sheet.setColumnWidth(7, 5800);
	        int index=0;
	        HSSFRow row_title = sheet.createRow(index++);
	        row_title.setHeight((short) 600);// 设置行高 
	        HSSFCell row_title0 = row_title.createCell(0);   
	        row_title0.setCellValue(new HSSFRichTextString("车辆进出厂记录")); 
	        //合并表头单元格
	        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)7),ExcelUtil.createTitleStyle(workbook));
	        sheet.addMergedRegion(new Region(
	        0 //first row (0-based) from 行  
	        ,(short)0 //first column (0-based) from 列     
	        ,0//last row  (0-based)  to 行
	        ,(short)7//last column  (0-based)  to 列     
	        ));
	        
	        String[] titles={"供应商","车牌号","车辆类型","进场门岗","进场时间","出场门岗","出场时间","在场时长"};
	        HSSFRow row_head = sheet.createRow(index++);
	        for (int i=0; i<titles.length;i++) {
	        	HSSFCell cell = row_head.createCell(i);
				cell.setCellValue(titles[i]);
				cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
			}
	        
	        for (LocationInout entity : locationInouts) {
	        	HSSFRow row = sheet.createRow(index++);
	        	//"供应商",
				HSSFCell cell0 = row.createCell(0);
				if(entity.getSupplier()!=null){
					cell0.setCellValue(entity.getSupplier());
				}
				//"车牌号",
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(entity.getVehicleCode());
				
				//"进场门岗",
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(entity.getType());
				
				//"进场门岗",
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(entity.getServerInName());
				//"进场时间",
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(DateUtil.DateToString(entity.getCominDate(), "yyyy-MM-dd HH:mm:ss"));
				//"出场门岗",
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(entity.getServerOutName());
				//"出场时间"
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(DateUtil.DateToString(entity.getOutDate(), "yyyy-MM-dd HH:mm:ss"));
	        
				//"出场时间"
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(entity.getPlant());
	        }
	        
	        try {
				String fileName="车辆进出厂记录";
				ExcelUtil.write(request, response, workbook, fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	}
	
	
	
	
	
	
	
	
	
}
