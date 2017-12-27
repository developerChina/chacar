package org.core.controller.webapp;

import java.io.IOException;
import java.io.InputStream;
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
import org.core.domain.webapp.Dept;
import org.core.service.webapp.HrmService;
import org.core.util.ExcelUtil;
import org.core.util.StringUtils;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理部门请求控制器
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */

@SuppressWarnings("deprecation")
@Controller
public class DeptController {
		
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理部门列表展示
	 * */
	@RequestMapping(value="/dept/selectDept")
	 public String selectDept(Model model,Integer pageIndex,
			 @ModelAttribute Dept dept){
		System.out.println("selectDept -->>");
		System.out.println("pageIndex = " + pageIndex);
		System.out.println("dept = " + dept);
		PageModel pageModel = new PageModel();
		System.out.println("getPageIndex = " + pageModel.getPageIndex());
		System.out.println("getPageSize = " + pageModel.getPageSize());
		System.out.println("getRecordCount = " + pageModel.getRecordCount());
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Dept> depts = hrmService.findDept(dept, pageModel);
		model.addAttribute("depts", depts);
		model.addAttribute("model", dept);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
		
	}
	
	@RequestMapping(value="/dept/selectAllDept")
	@ResponseBody
	public Object selectAllDept(HttpServletRequest request, HttpServletResponse response){
		List<Dept> depts = hrmService.findAllDept();
		return depts; 
	}
	
	/**
	 * 处理删除部门请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/removeDept")
	 public ModelAndView removeDept(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除部门
			hrmService.removeDeptById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/dept/selectDept");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Dept  dept  要添加的部门对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/addDept")
	 public ModelAndView addDept(
			 String flag,
			 @ModelAttribute Dept dept,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddDept");
		}else{
			// 执行添加操作
			hrmService.addDept(dept);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/dept/selectDept");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理修改部门请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Dept dept 要修改部门的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/dept/updateDept")
	 public ModelAndView updateDpet(
			 String flag,
			 @ModelAttribute Dept dept,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询部门
			Dept target = hrmService.findDeptById(dept.getId());
			// 设置Model数据
			mv.addObject("dept", target);
			// 设置跳转到修改页面
			mv.setViewName("dept/showUpdateDept");
		}else{
			// 执行修改操作
			hrmService.modifyDept(dept);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/dept/selectDept");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 导出部门
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping(value="/dept/exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Dept dept){
		PageModel pageModel = new PageModel();
		pageModel.setPageSize(Integer.MAX_VALUE);
		List<Dept> depts = hrmService.findDept(dept, pageModel);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "部门";//sheet名称
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setFitToPage(true);  
	    sheet.setHorizontallyCenter(true);
	    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
	    CellRangeAddress c = CellRangeAddress.valueOf("A2:D2");  
		sheet.setAutoFilter(c);
	    //设置列宽
	    sheet.setColumnWidth(0, 7800);
        sheet.setColumnWidth(1, 9600);		
        sheet.setColumnWidth(2, 7800);
        sheet.setColumnWidth(3, 9600);
		//定义表格行索引
        int index=0;
        
        //添加标题
        HSSFRow row_title = sheet.createRow(index++);
        row_title.setHeight((short) 600);// 设置行高 
        HSSFCell row_title0 = row_title.createCell(0);   
        row_title0.setCellValue(new HSSFRichTextString("部门")); 
        //合并表头单元格
        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)3),ExcelUtil.createTitleStyle(workbook));
        sheet.addMergedRegion(new Region(
        0 //first row (0-based) from 行  
        ,(short)0 //first column (0-based) from 列     
        ,0//last row  (0-based)  to 行
        ,(short)3//last column  (0-based)  to 列     
        ));
        
        //添加头信息
        String[] titles={"编码","名称","上级部门","描述"};
        HSSFRow row_head = sheet.createRow(index++);
        for (int i=0; i<titles.length;i++) {
        	HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
        //添加内容
        for (Dept entity : depts) {
        	HSSFRow row = sheet.createRow(index++);
        	//编码
        	HSSFCell cell0 = row.createCell(0);
        	cell0.setCellValue(entity.getId());
        	cell0.setCellStyle(ExcelUtil.createTextStyle(workbook));
			//名称
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entity.getName());
			cell1.setCellStyle(ExcelUtil.createTextStyle(workbook));
			//上级部门
			HSSFCell cell2 = row.createCell(2);
			if(entity.getDept()!=null){
				cell2.setCellValue(entity.getDept().getName());
			}
			cell2.setCellStyle(ExcelUtil.createTextStyle(workbook));
			//描述
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(entity.getRemark());
			cell3.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
		try {
			String fileName="部门管理";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/dept/exportTemplate")
	public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		this.createDept(workbook);
		try {
			String fileName="部门导入模板";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void createDept(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("部门信息");
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A1:C1");
		sheet.setAutoFilter(c);
		// 设置列宽
		sheet.setColumnWidth(0, 3800);
		sheet.setColumnWidth(1, 4600);
		sheet.setColumnWidth(2, 6800);
		// 定义表格行索引
		int index = 0;
		// 添加头信息
		String[] titles = {"部门", "上级部门", "描述"};
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
	}

	/**
	 * 批量导入部门页面
	 */
	@RequestMapping(value = "/dept/importDeptPage")
	public ModelAndView importDeptPage(ModelAndView mv) {
		mv.setViewName("dept/deptImport");
		return mv;
	}
	
	/**
	 * 批量导入部门
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/dept/importDept")
	public ModelAndView importDept(ModelAndView mv,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<String, Object> map = new HashMap<>();
		List<Dept> depts=hrmService.findAllDept();
		Map<String, Integer> map_dept=new HashMap<>();
		for (Dept dept : depts) {
			map_dept.put(dept.getName(), dept.getId());
		}
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
				Dept dept=new Dept();
				for (Integer key : data.keySet()) {
					dept.setName(data.get(0));
					if(StringUtils.isNotBlank(data.get(1))){
						if(map_dept.get(data.get(1))!=null){
							dept.setPid(map_dept.get(data.get(1)));
						}else{
							//新建上级部门
							Dept deptNew=new Dept();
							deptNew.setName(data.get(1));
							hrmService.addDept(deptNew);
							//重新添加部门Map
							List<Dept> deptsNew=hrmService.findAllDept();
							map_dept.clear();
							for (Dept d : deptsNew) {
								map_dept.put(d.getName(), d.getId());
							}
							dept.setPid(map_dept.get(data.get(1)));
						}
					}
					dept.setRemark(data.get(2));
				}
				if(StringUtils.isNotBlank(dept.getName())){
					hrmService.saveOrUpdateDept(dept);
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
	 
}
