package org.core.controller.temporary;

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
import org.core.domain.temporary.TemporaryInfo;
import org.core.service.temporary.HandleService;
import org.core.service.temporary.QueryService;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemporaryController {
	@Autowired
	@Qualifier("queryService")
	private QueryService queryService;

	@Autowired
	@Qualifier("handleService")
	private HandleService handleService;

	/**
	 * 临时定位仪查询 状态为1（一进一出完整数据）
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/temporary/queryAck")
	public ModelAndView query(Integer pageIndex, ModelAndView mv, @ModelAttribute TemporaryInfo entity,
			HttpServletRequest request) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		String pageParam = "";
		if (entity.getCarno() != null && !"".equals(entity.getCarno())) {
			pageParam += "&carno=" + entity.getCarno();
		}
		if (entity.getName() != null && !"".equals(entity.getName())) {
			pageParam += "&name=" + entity.getName();
		}
		if (entity.getGps() != null && !"".equals(entity.getGps())) {
			pageParam += "&gps=" + entity.getGps();
		}
		if (entity.getContacts() != null && !"".equals(entity.getContacts())) {
			pageParam += "&contacts=" + entity.getContacts();
		}

		String sDate = request.getParameter("sDate");
		if (sDate != null && !"".equals(sDate)) {
			pageParam += "&sDate=" + sDate;
		}
		Date startDate = null;
		try {
			startDate = DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate = request.getParameter("eDate");
		if (eDate != null && !"".equals(eDate)) {
			pageParam += "&eDate=" + eDate;
		}
		Date endDate = null;
		try {
			endDate = DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("pageParam", pageParam);
		mv.addObject("sDate", sDate);
		mv.addObject("eDate", eDate);

		List<TemporaryInfo> entityList = queryService.selectByPage(entity, pageModel, startDate, endDate);

		mv.addObject("pageModel", pageModel);
		mv.addObject("entityList", entityList);
		mv.addObject("temporaryInfo", entity);

		mv.setViewName("temporary/query");
		return mv;
	}

	/**
	 * 导出 状态为1的数据
	 * 
	 * @param mv
	 * @return Excel
	 */
	// 导出历史记录Excel git提交
	@RequestMapping(value = "/temporary/induce")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Integer island_no,
			@ModelAttribute TemporaryInfo entity) {
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(Integer.MAX_VALUE);

		String sDate = request.getParameter("sDate");
		Date startDate = null;
		try {
			startDate = DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate = request.getParameter("eDate");
		Date endDate = null;
		try {
			endDate = DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TemporaryInfo> entityList = queryService.selectByPage(entity, pageModel, startDate, endDate);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "临时定位仪历史记录";// sheet名称
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A2:H2");
		sheet.setAutoFilter(c);
		// 设置列宽
		sheet.setColumnWidth(0, 7000);
		sheet.setColumnWidth(1, 3200);
		sheet.setColumnWidth(2, 3200);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 5800);
		sheet.setColumnWidth(6, 5800);
		sheet.setColumnWidth(7, 3200);
		// 定义表格行索引
		int index = 0;

		// 添加标题
		HSSFRow row_title = sheet.createRow(index++);
		row_title.setHeight((short) 600);// 设置行高
		HSSFCell row_title0 = row_title.createCell(0);
		row_title0.setCellValue(new HSSFRichTextString("临时定位仪历史记录"));
		// 合并表头单元格
		ExcelUtil.setRegionStyle(sheet, new Region(0, (short) 0, 0, (short) 7), ExcelUtil.createTitleStyle(workbook));
		sheet.addMergedRegion(new Region(0 // first row (0-based) from 行
				, (short) 0 // first column (0-based) from 列
				, 0// last row (0-based) to 行
				, (short) 7// last column (0-based) to 列
		));

		// 添加头信息
		String[] titles = { "车主名称", "车牌号", "联系人", "联系方式", "GPS号码", "进场时间", "回收时间", "备注" };
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
		for (TemporaryInfo entityExcel : entityList) {
			HSSFRow row = sheet.createRow(index++);

			// "车主名称"
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(entityExcel.getName());
			// "车牌号"
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entityExcel.getCarno());
			// "联系人"
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(entityExcel.getContacts());
			// "联系方式"
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(entityExcel.getTel());
			// GPS号码
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(entityExcel.getGps());
			// "进场时间"
			HSSFCell cell5 = row.createCell(5);
			cell5.setCellValue(DateUtil.DateToString(entityExcel.getCominDate(), "yyyy-MM-dd HH:mm:ss"));
			// "回收时间"
			HSSFCell cell6 = row.createCell(6);
			cell6.setCellValue(DateUtil.DateToString(entityExcel.getOutDate(), "yyyy-MM-dd HH:mm:ss"));
			// "备注"
			HSSFCell cell7 = row.createCell(7);
			cell7.setCellValue(entityExcel.getRemarks());
		}
		try {
			String fileName = "临时定位仪历史记录";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 临时定位仪查询 状态为2（正在办理）
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/temporary/handleAck")
	public ModelAndView handle(Integer pageIndex, ModelAndView mv, @ModelAttribute TemporaryInfo entity,
			HttpServletRequest request) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		String pageParam = "";
		if (entity.getCarno() != null && !"".equals(entity.getCarno())) {
			pageParam += "&carno=" + entity.getCarno();
		}
		if (entity.getName() != null && !"".equals(entity.getName())) {
			pageParam += "&name=" + entity.getName();
		}
		if (entity.getGps() != null && !"".equals(entity.getGps())) {
			pageParam += "&gps=" + entity.getGps();
		}
		if (entity.getContacts() != null && !"".equals(entity.getContacts())) {
			pageParam += "&contacts=" + entity.getContacts();
		}

		String sDate = request.getParameter("sDate");
		if (sDate != null && !"".equals(sDate)) {
			pageParam += "&sDate=" + sDate;
		}
		Date startDate = null;
		try {
			startDate = DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate = request.getParameter("eDate");
		if (eDate != null && !"".equals(eDate)) {
			pageParam += "&eDate=" + eDate;
		}
		Date endDate = null;
		try {
			endDate = DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("pageParam", pageParam);
		mv.addObject("sDate", sDate);
		mv.addObject("eDate", eDate);

		List<TemporaryInfo> entityList = handleService.selectByPage(entity, pageModel, startDate, endDate);

		mv.addObject("pageModel", pageModel);
		mv.addObject("entityList", entityList);
		mv.addObject("temporaryInfo", entity);

		mv.setViewName("temporary/handle");
		return mv;
	}

	/**
	 * 导出 状态为2的数据
	 * 
	 * @param mv
	 * @return Excel
	 */
	// 导出历史记录Excel git提交
	@RequestMapping(value = "/temporary/handleAckInduce")
	public void exportExcelHandleAck(HttpServletRequest request, HttpServletResponse response, Integer island_no,
			@ModelAttribute TemporaryInfo entity) {
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(Integer.MAX_VALUE);

		String sDate = request.getParameter("sDate");
		Date startDate = null;
		try {
			startDate = DateUtil.StringToDate(sDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String eDate = request.getParameter("eDate");
		Date endDate = null;
		try {
			endDate = DateUtil.StringToDate(eDate, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TemporaryInfo> entityList = handleService.selectByPage(entity, pageModel, startDate, endDate);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "临时定位仪处理中记录";// sheet名称
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A2:H2");
		sheet.setAutoFilter(c);
		// 设置列宽
		sheet.setColumnWidth(0, 7000);
		sheet.setColumnWidth(1, 3200);
		sheet.setColumnWidth(2, 3200);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 5800);
		sheet.setColumnWidth(6, 5800);
		sheet.setColumnWidth(7, 3200);
		// 定义表格行索引
		int index = 0;

		// 添加标题
		HSSFRow row_title = sheet.createRow(index++);
		row_title.setHeight((short) 600);// 设置行高
		HSSFCell row_title0 = row_title.createCell(0);
		row_title0.setCellValue(new HSSFRichTextString("临时定位仪处理中记录"));
		// 合并表头单元格
		ExcelUtil.setRegionStyle(sheet, new Region(0, (short) 0, 0, (short) 7), ExcelUtil.createTitleStyle(workbook));
		sheet.addMergedRegion(new Region(0 // first row (0-based) from 行
				, (short) 0 // first column (0-based) from 列
				, 0// last row (0-based) to 行
				, (short) 7// last column (0-based) to 列
		));

		// 添加头信息
		String[] titles = { "车主名称", "车牌号", "联系人", "联系方式", "GPS号码", "进场时间", "回收时间", "备注" };
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
		for (TemporaryInfo entityExcel : entityList) {
			HSSFRow row = sheet.createRow(index++);

			// "车主名称"
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(entityExcel.getName());
			// "车牌号"
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entityExcel.getCarno());
			// "联系人"
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(entityExcel.getContacts());
			// "联系方式"
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(entityExcel.getTel());
			// GPS号码
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(entityExcel.getGps());
			// "进场时间"
			HSSFCell cell5 = row.createCell(5);
			cell5.setCellValue(DateUtil.DateToString(entityExcel.getCominDate(), "yyyy-MM-dd HH:mm:ss"));
			// "回收时间"
			HSSFCell cell6 = row.createCell(6);
			cell6.setCellValue("暂无回收信息");
			// "备注"
			HSSFCell cell7 = row.createCell(7);
			cell7.setCellValue(entityExcel.getRemarks());
		}
		try {
			String fileName = "临时定位仪办理中记录";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 临时定位仪发放
	 */
	@RequestMapping(value = "/temporary/addTemporary")
	public ModelAndView addTemporary(String flag, @ModelAttribute TemporaryInfo entity, ModelAndView mv) {
		if (flag.equals("1")) {
			mv.setViewName("temporary/temporaryAdd");
		} else {
			handleService.save(entity);
			mv.setViewName("redirect:/temporary/handleAck");
		}
		return mv;
	}

	/**
	 * 临时定位仪修改
	 */
	@RequestMapping(value = "/temporary/updateTemporary")
	public ModelAndView updateTemporary(String flag, @ModelAttribute TemporaryInfo entity, ModelAndView mv) {
		if (flag.equals("1")) {
			TemporaryInfo entityAgain = handleService.getEntityById(entity.getId());
			mv.addObject("entity", entityAgain);
			mv.setViewName("temporary/temporaryUpdate");
		} else {
			handleService.update(entity);
			mv.setViewName("redirect:/temporary/handleAck");
		}
		return mv;
	}
	
	
	/**
	 * 临时定位仪删除
	 */
	@RequestMapping(value="/temporary/delete")
	 public ModelAndView deleteTemporary(String ids,ModelAndView mv){
		handleService.deleteById(ids);
		mv.setViewName("redirect:/temporary/handleAck");
		return mv;
	}
	
	/**
	 * 临时定位仪确认回收
	 */
	@RequestMapping(value="/temporary/confirmTemporary")
	 public ModelAndView confirmTemporary(Integer id,ModelAndView mv){
		handleService.confirmById(id);
		mv.setViewName("redirect:/temporary/handleAck");
		return mv;
	}
	
//将状态1的重新发放定位仪 方便人员操作  重复发放。
	/**
	 * 临时定位仪确认回收
	 */
	@RequestMapping(value="/temporary/repeat")
	 public ModelAndView repeatTemporary(String flag, 
			 @ModelAttribute TemporaryInfo entity, ModelAndView mv){
		if (flag.equals("1")) {
			TemporaryInfo entityAgain = handleService.getEntityById(entity.getId());
			mv.addObject("entity", entityAgain);
			mv.setViewName("temporary/repeatAdd");
		}else {
			//重新添加 状态为2的一条新数据
			queryService.save(entity);
			mv.setViewName("redirect:/temporary/handleAck");
		}
		return mv;
	}
	
	
	
	
	
}
