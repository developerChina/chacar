package org.core.controller.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.domain.webapp.Passageway;
import org.core.service.webapp.AccessGroupService;
import org.core.service.webapp.GroupService;
import org.core.service.webapp.HrmService;
import org.core.service.webapp.PassagewayGroupService;
import org.core.util.AControlUtil;
import org.core.util.DateStyle;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.LadderControlUtil;
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
 * @Description: 处理员工请求控制器 <br>
 * 				网站：<a href="http://www.fkit.org">疯狂Java</a>
 * @author 肖文吉 36750064@qq.com
 * @version V1.0
 */
@Controller
public class EmployeeController {
	/**
	 * 自动注入hrmService
	 */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;

	@Autowired 
	@Qualifier("accessgroupService")
	private AccessGroupService accessgroupService;
	
	@Autowired
	@Qualifier("passagewayGroupService")
	private PassagewayGroupService passagewayGroupService;
	
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	/**
	 * 处理查询请求
	 * 
	 * @param pageIndex
	 *            请求的是第几页
	 * @param String
	 *            job_id 职位编号
	 * @param String
	 *            dept_id 部门编号
	 * @param employee
	 *            模糊查询参数
	 * @param Model
	 *            model
	 */
	@RequestMapping(value = "/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
			Model model) {
		// 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(job_id, dept_id, employee);
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		// 查询职位信息，用于模糊查询
		List<Job> jobs = hrmService.findAllJob();
		// 查询部门信息 ，用于模糊查询
		List<Dept> depts = hrmService.findAllDept();
		// 查询员工信息
		List<Employee> employees = hrmService.findEmployee(employee, pageModel);
		// 设置Model数据
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		// 返回员工页面
		return "employee/employee";

	}

	@RequestMapping(value = "/employee/bingdEmployee")
	@ResponseBody
	public Object bingdEmployee(HttpServletRequest request,HttpServletResponse response) {
		String ids=request.getParameter("ids");
		String flag=request.getParameter("flag");
		//所有门禁
		List<Access> agAccesss=accessgroupService.selectAGSubordinate();
		//所有通道
		List<Passageway> passList=passagewayGroupService.selectPGSubordinate();
		//所有电梯
		List<Elevator> egElevators= groupService.selectEGSubordinate();

		boolean b=true;
		// 分解id字符串
		try {
			String[] idArray = ids.split(",");
			for(String id : idArray){
				// 根据id查询员工
				Employee employee = hrmService.findEmployeeById(Integer.parseInt(id));
				//授权
				GrantAuthorization(agAccesss,passList,egElevators,employee.getCardno(),Integer.parseInt(flag));
				//修改状态
				employee.setCarstatus(new Integer(flag));
				hrmService.modifyEmployee(employee);
			}
		}catch(Exception e) {
			b=false;
		}
		return b;
	}
    //门禁,通道  int authority[] = { 1, 1, 1, 1 };
	public static void GrantAuthorization(List<Access> agAccesss,List<Passageway> passList,List<Elevator> egElevators,String cardno,int flag) {
		int authority[] = { 0, 0, 0, 0 };
		int lay[] = { 0, 0, 0, 0, 0 };
		if(flag==1) {
			authority[0]=1;
			authority[1]=1;
			authority[2]=1;
			authority[3]=1;
			lay[0]=255;
			lay[1]=255;
			lay[2]=255;
			lay[3]=255;
			lay[4]=255;
		}
		for(Access ac:agAccesss) {
		   AControlUtil.AddUserCard(Long.valueOf(ac.getCsn()), ac.getCip(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29, (byte) 0x12,
					(byte) 0x31, authority);
		}
		for(Passageway pa:passList) {
			   AControlUtil.AddUserCard(Long.valueOf(pa.getControllerSN()), pa.getControllerIP(), Long.valueOf(cardno), (byte) 0x20, (byte) 0x29, (byte) 0x12,
						(byte) 0x31, authority);
		}
		for(Elevator ea:egElevators) {
			LadderControlUtil.LadderControlUserCard(Long.valueOf(ea.getControllerSN()), ea.getControllerIP(), Long.valueOf(cardno), 1, (byte) 0x20, (byte) 0x29,
					(byte) 0x12, (byte) 0x31, lay[0], lay[1], lay[2], lay[3], lay[4]);
		}
	}
	/**
	 * 处理添加员工请求
	 * 
	 * @param String
	 *            flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param String
	 *            job_id 职位编号
	 * @param String
	 *            dept_id 部门编号
	 * @param Employee
	 *            employee 接收添加参数
	 * @param ModelAndView
	 *            mv
	 */
	@RequestMapping(value = "/employee/addEmployee")
	public ModelAndView addEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
			ModelAndView mv) {
		if (flag.equals("1")) {
			// 查询职位信息
			List<Job> jobs = hrmService.findAllJob();
			// 查询部门信息
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			// 返回添加员工页面
			mv.setViewName("employee/showAddEmployee");
		} else {
			// 判断是否有关联对象传递，如果有，创建关联对象
			this.genericAssociation(job_id, dept_id, employee);
			// 添加操作
			hrmService.addEmployee(employee);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;

	}

	/**
	 * 处理删除员工请求
	 * 
	 * @param String
	 *            ids 需要删除的id字符串
	 * @param ModelAndView
	 *            mv
	 */
	@RequestMapping(value = "/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids, ModelAndView mv) {
		// 分解id字符串
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			// 根据id删除员工
			hrmService.removeEmployeeById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		// mv.setView(new RedirectView("/hrmapp/employee/selectEmployee"));
		// mv.setViewName("forward:/employee/selectEmployee");
		mv.setViewName("redirect:/employee/selectEmployee");
		// 返回ModelAndView
		return mv;
	}

	/**
	 * 处理修改员工请求
	 * 
	 * @param String
	 *            flag 标记，1表示跳转到修改页面，2表示执行修改操作
	 * @param String
	 *            job_id 职位编号
	 * @param String
	 *            dept_id 部门编号
	 * @param Employee
	 *            employee 要修改员工的对象
	 * @param ModelAndView
	 *            mv
	 */
	@RequestMapping(value = "/employee/updateEmployee")
	public ModelAndView updateEmployee(String flag, Integer job_id, Integer dept_id, @ModelAttribute Employee employee,
			ModelAndView mv) {
		if (flag.equals("1")) {
			// 根据id查询员工
			Employee target = hrmService.findEmployeeById(employee.getId());
			// 需要查询职位信息
			List<Job> jobs = hrmService.findAllJob();
			// 需要查询部门信息
			List<Dept> depts = hrmService.findAllDept();
			// 设置Model数据
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.addObject("employee", target);
			// 返回修改员工页面
			mv.setViewName("employee/showUpdateEmployee");
		} else {
			// 创建并封装关联对象
			this.genericAssociation(job_id, dept_id, employee);
			//System.out.println("updateEmployee -->> " + employee);
			// 执行修改操作
			hrmService.modifyEmployee(employee);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		// 返回
		return mv;
	}

	/**
	 * 由于部门和职位在Employee中是对象关联映射， 所以不能直接接收参数，需要创建Job对象和Dept对象
	 */
	private void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
		if (job_id != null) {
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if (dept_id != null) {
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}

	/**
	 * 批量导入员工页面
	 */
	@RequestMapping(value = "/employee/importEmployeePage")
	public ModelAndView importEmployeePage(ModelAndView mv) {
		mv.setViewName("employee/employeeImport");
		return mv;
	}

	@RequestMapping(value = "/employee/exportTemplate")
	public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		this.ceateEmoloyee(workbook);
		this.createDept(workbook);
		this.createJob(workbook);
		try {
			String fileName="员工导入模板";
			ExcelUtil.write(request, response, workbook, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void ceateEmoloyee(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("员工信息");
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A1:V1");
		sheet.setAutoFilter(c);
		// 定义表格行索引
		int index = 0;
		// 添加头信息
		String[] titles = { "名称", "身份证号", "邮政编码", "电话", "手机", "qq号码", "邮箱", "性别", "政治面貌", "生日", "民族", "学历", "专业", " 爱好",
				"备注", "卡号", "车牌号", "地址", "部门", "职位", "部门ID", "职位ID" };
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
	}

	void createDept(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("部门信息");
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A1:D1");
		sheet.setAutoFilter(c);
		// 设置列宽
		sheet.setColumnWidth(0, 3800);
		sheet.setColumnWidth(1, 4600);
		sheet.setColumnWidth(2, 4800);
		sheet.setColumnWidth(3, 4600);
		// 定义表格行索引
		int index = 0;
		// 添加头信息
		String[] titles = { "编码", "名称", "上级部门", "描述" };
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
		List<Dept> depts = hrmService.findAllDept();
		// 添加内容
		for (Dept entity : depts) {
			HSSFRow row = sheet.createRow(index++);
			// 编码
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(entity.getId());
			cell0.setCellStyle(ExcelUtil.createTextStyle(workbook));
			// 名称
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entity.getName());
			cell1.setCellStyle(ExcelUtil.createTextStyle(workbook));
			// 上级部门
			HSSFCell cell2 = row.createCell(2);
			if (entity.getDept() != null) {
				cell2.setCellValue(entity.getDept().getName());
			}
			cell2.setCellStyle(ExcelUtil.createTextStyle(workbook));
			// 描述
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(entity.getRemark());
			cell3.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
	}

	void createJob(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet("职位信息");
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		// 里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
		CellRangeAddress c = CellRangeAddress.valueOf("A1:C1");
		sheet.setAutoFilter(c);
		// 设置列宽
		sheet.setColumnWidth(0, 3800);
		sheet.setColumnWidth(1, 4600);
		sheet.setColumnWidth(2, 4800);
		// 定义表格行索引
		int index = 0;

		// 添加头信息
		String[] titles = { "编码", "名称", "描述" };
		HSSFRow row_head = sheet.createRow(index++);
		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell = row_head.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
		List<Job> jobs = hrmService.findAllJob();
		// 添加内容
		for (Job entity : jobs) {
			HSSFRow row = sheet.createRow(index++);
			// 编码
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(entity.getId());
			cell0.setCellStyle(ExcelUtil.createTextStyle(workbook));
			// 名称
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(entity.getName());
			cell1.setCellStyle(ExcelUtil.createTextStyle(workbook));
			// 描述
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(entity.getRemark());
			cell2.setCellStyle(ExcelUtil.createTextStyle(workbook));
		}
	}

	/**
	 * 批量导入员工
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/employee/importEmployee")
	public ModelAndView importEmployee(ModelAndView mv,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Map<String, Object> map = new HashMap<>();
		try {
			InputStream is = file.getInputStream();
			Workbook workbook = new HSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			List<Map<Integer, String>> list = ExcelUtil.readSheet(sheet, colNum);
			// 名称,身份证号,邮政编码,电话,手机,qq号码,邮箱,性别,政治面貌,生日,民族,学历,专业,爱好,备注,卡号,车牌号,部门,职位,部门ID,职位ID
			for (Map<Integer, String> data : list) {
				Employee employee = new Employee();
				for (Integer key : data.keySet()) {
					employee.setName(data.get(0));
					employee.setCardId(data.get(1));
					employee.setPostCode(data.get(2));
					employee.setTel(data.get(3));
					employee.setPhone(data.get(4));
					employee.setQqNum(data.get(5));
					employee.setEmail(data.get(6));
					if ("女".equals(data.get(7))) {
						employee.setSex(0);
					} else {
						employee.setSex(1);
					}
					employee.setParty(data.get(8));
					employee.setBirthday(DateUtil.StringToDate(data.get(9), DateStyle.YYYY_MM_DD));
					employee.setRace(data.get(10));
					employee.setEducation(data.get(11));
					employee.setSpeciality(data.get(12));
					employee.setHobby(data.get(13));
					employee.setRemark(data.get(14));
					employee.setCardno(data.get(15));
					employee.setCarno(data.get(16));
					employee.setAddress(data.get(17));
					Dept dept = new Dept();
					dept.setId(Integer.parseInt(data.get(20)));
					employee.setDept(dept);
					Job job = new Job();
					job.setId(Integer.parseInt(data.get(21)));
					employee.setJob(job);

				}

				hrmService.addEmployee(employee);

			}
		} catch (IOException e1) {
			e1.printStackTrace();
			map.put("status", false);
			map.put("message", "成功导入0行数据");
			map.put("exception", e1.getMessage());
		}
		mv.addObject("map", map);
		mv.setViewName("upload/resultImport");
		return mv;

	}

}
