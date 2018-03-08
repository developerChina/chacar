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
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.TrajectoryEmp;
import org.core.service.webapp.AccessGroupService;
import org.core.service.webapp.GroupService;
import org.core.service.webapp.HrmService;
import org.core.service.webapp.PassagewayGroupService;
import org.core.service.webapp.TrajectoryEmpService;
import org.core.util.AControlUtil;
import org.core.util.DateStyle;
import org.core.util.DateUtil;
import org.core.util.ExcelUtil;
import org.core.util.LadderControlUtil;
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
	
	@Autowired
	@Qualifier("trajectoryEmpService")
	private TrajectoryEmpService trajectoryEmpService;
	
	
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
	public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, 
			@ModelAttribute Employee employee,Model model) {
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
		//分页参数
		model.addAttribute("model", employee);
		model.addAttribute("job_id", job_id);
		model.addAttribute("dept_id", dept_id);
		String pageParam="";
		if(job_id!=null&&job_id>0){
			pageParam+="&job_id="+job_id;
		}
		if(StringUtils.isNotBlank(employee.getName())){
			pageParam+="&name="+employee.getName();
		}
		if(StringUtils.isNotBlank(employee.getCardId())){
			pageParam+="&cardId="+employee.getCardId();
		}
		if(employee.getCarstatus()!=null&&employee.getCarstatus()>0){
			pageParam+="&carstatus="+employee.getCarstatus();
		}
		if(StringUtils.isNotBlank(employee.getPhone())){
			pageParam+="&phone="+employee.getPhone();
		}
		if(dept_id!=null&&dept_id>0){
			pageParam+="&dept_id="+dept_id;
		}
		model.addAttribute("pageParam", pageParam);
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
		Map<String,Object> map = new HashMap<>();
		String b="授权操作成功";
		map.put("message", b);
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
			b="授权操作失败";
			map.put("message", b);
		}
		return map;
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
	 * 处理添加员工前验证物理卡号是否重复的Ajax请求
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/employee/AddEmpProving")
	public Object addValidate(HttpServletRequest request,
			 HttpServletResponse response){
		String cardno = request.getParameter("cardno");
		Map<String,Object> map = new HashMap<>();
		String test = hrmService.addValidate(cardno);
		if(!"".equals(test)){
			map.put("status", false);
			map.put("message", test);
		}else{
			map.put("status", true);
		}
		return map;
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
		String[] titles = { "姓名","身份证号","卡号","员工编号","性别","手机","部门","职位","生日","邮政编码","电话","qq号码","邮箱","政治面貌","民族","学历","专业","备注","车牌号","地址"};
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
		
		List<Dept> depts=hrmService.findAllDept();
		Map<String, Integer> map_dept=new HashMap<>();
		for (Dept dept : depts) {
			map_dept.put(dept.getName(), dept.getId());
		}
		List<Job> jobs=hrmService.findAllJob();
		Map<String, Integer> map_job=new HashMap<>();
		for (Job job : jobs) {
			map_job.put(job.getName(), job.getId());
		}
		
		//执行excel的行索引
		int excelRowIndex=0;
		
		Map<String, Object> map = new HashMap<>();
		try {
			InputStream is = file.getInputStream();
			Workbook workbook = new HSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();
			List<Map<Integer, String>> list = ExcelUtil.readSheet(sheet, colNum);
			// "姓名","身份证号","卡号"," 员工编号","性别","手机","部门","职位","生日","邮政编码","电话","qq号码","邮箱","政治面貌","民族","学历","专业","备注","车牌号","地址"
			for (Map<Integer, String> data : list) {
				Employee employee = new Employee();
				for (Integer key : data.keySet()) {
					employee.setName(data.get(0));
					employee.setCardId(data.get(1));
					employee.setCardno(data.get(2));
					employee.setHobby(data.get(3));
					if ("女".equals(data.get(4))) {
						employee.setSex(0);
					} else {
						employee.setSex(1);
					}
					employee.setPhone(data.get(5));
					if(StringUtils.isNotBlank(data.get(6))){
						Dept dept = new Dept();
						dept.setId(map_dept.get(data.get(6)));
						employee.setDept(dept);	
					}
					if(StringUtils.isNotBlank(data.get(7))){
						Job job = new Job();
						job.setId(map_job.get(data.get(7)));
						employee.setJob(job);
					}
					if(StringUtils.isNotBlank(data.get(8))){
						employee.setBirthday(DateUtil.StringToDate(data.get(8), DateStyle.YYYY_MM_DD_EN));
					}
					employee.setPostCode(data.get(9));
					employee.setTel(data.get(10));
					employee.setQqNum(data.get(11));
					employee.setEmail(data.get(12));
					employee.setParty(data.get(13));
					employee.setRace(data.get(14));
					employee.setEducation(data.get(15));
					employee.setSpeciality(data.get(16));
					employee.setRemark(data.get(17));
					employee.setCarno(data.get(18));
					employee.setAddress(data.get(19));
				}
				hrmService.addEmployee(employee);
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

	
	@RequestMapping(value = "/employee/selectTrajectoryEmp")
	public String selectTrajectoryEmp(HttpServletRequest request,Integer pageIndex,Model model) {
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if (pageIndex != null) {
			pageModel.setPageIndex(pageIndex);
		}
		String pageParam=""; 
		String name=request.getParameter("name");
		model.addAttribute("name", name);
		if(StringUtils.isNotBlank(name)){pageParam+="&name="+name;}
		String cardno=request.getParameter("cardno");
		model.addAttribute("cardno", cardno);
		if(StringUtils.isNotBlank(cardno)){pageParam+="&cardno="+cardno;}
		String phone=request.getParameter("phone");
		model.addAttribute("phone", phone);
		if(StringUtils.isNotBlank(phone)){pageParam+="&phone="+phone;}
		String sDate=request.getParameter("sDate");
		model.addAttribute("sDate", sDate);
		if(StringUtils.isNotBlank(sDate)){pageParam+="&sDate="+sDate;}
		String eDate=request.getParameter("eDate");
		model.addAttribute("eDate", eDate);
		if(StringUtils.isNotBlank(eDate)){pageParam+="&eDate="+eDate;}
		//Osnd 部门条件
		String dept_id=request.getParameter("dept_id");
		model.addAttribute("dept_id", dept_id);
		if(StringUtils.isNotBlank(dept_id)){
			pageParam+="&dept_id="+dept_id;
		}
		
		model.addAttribute("pageParam", pageParam);
		
		TrajectoryEmp trajectoryEmp=new TrajectoryEmp();
		if(StringUtils.isNotBlank(name)||StringUtils.isNotBlank(cardno)||StringUtils.isNotBlank(phone)){
			List<Employee> list=hrmService.getEmployeees(name, cardno, phone);
			String cardnos="";
			for (Employee employee : list) {
				cardnos+=",'"+employee.getCardno()+"' ";
			}
			if(cardnos.contains(",")){
				trajectoryEmp.setCardno(cardnos.substring(1));
			}else{
				trajectoryEmp.setCardno("null");
			}
		}
		
		if(StringUtils.isNotBlank(sDate)){
			trajectoryEmp.setStartTime(DateUtil.StringToDate(sDate));
		}
		if(StringUtils.isNotBlank(eDate)){
			trajectoryEmp.setEndTime(DateUtil.StringToDate(eDate));
		}
		//Osnd加的部门信息
		List<Dept> depts = hrmService.findAllDept();
		model.addAttribute("depts", depts);
		//按部门查
		if(StringUtils.isNotBlank(dept_id)){
			trajectoryEmp.setTrajectoryDept(dept_id);
		}
		
		List<TrajectoryEmp> traEmps=trajectoryEmpService.selectTrajectory(trajectoryEmp, pageModel);
		model.addAttribute("traEmps", traEmps);
		model.addAttribute("pageModel", pageModel);
		// 返回员工页面
		return "employee/trajectoryEmp";

	}
	//导出内部员工的出入记录
	//导出历史记录Excel 
			@RequestMapping(value="/employee/exportExcel")
			public void exportExcel(HttpServletRequest request,
					HttpServletResponse response){
				
			PageModel pageModel = new PageModel();
			pageModel.setPageSize(Integer.MAX_VALUE);
				
				//名字导出
				String name=request.getParameter("name");
				//卡号导出
				String cardno=request.getParameter("cardno");
				//电话号导出
				String phone=request.getParameter("phone");
				//时间导出
				String sDate=request.getParameter("sDate");
				String eDate=request.getParameter("eDate");
				//按部门
				String dept_id=request.getParameter("dept_id");
				
				TrajectoryEmp trajectoryEmp=new TrajectoryEmp();
				if(StringUtils.isNotBlank(name)||StringUtils.isNotBlank(cardno)||StringUtils.isNotBlank(phone)){
					List<Employee> list=hrmService.getEmployeees(name, cardno, phone);
					String cardnos="";
					for (Employee employee : list) {
						cardnos+=",'"+employee.getCardno()+"' ";
					}
					if(cardnos.contains(",")){
						trajectoryEmp.setCardno(cardnos.substring(1));
					}else{
						trajectoryEmp.setCardno("null");
					}
				}
				
				if(StringUtils.isNotBlank(sDate)){
					trajectoryEmp.setStartTime(DateUtil.StringToDate(sDate));
				}
				if(StringUtils.isNotBlank(eDate)){
					trajectoryEmp.setEndTime(DateUtil.StringToDate(eDate));
				}
				if(StringUtils.isNotBlank(dept_id)){
					trajectoryEmp.setTrajectoryDept(dept_id);
				}
				List<TrajectoryEmp> traEmps=trajectoryEmpService.selectTrajectory(trajectoryEmp, pageModel);
				
				// 声明一个工作薄
				HSSFWorkbook workbook = new HSSFWorkbook();
				String sheetName = "员工出入记录";//sheet名称
				HSSFSheet sheet = workbook.createSheet(sheetName);
				sheet.setFitToPage(true);  
			    sheet.setHorizontallyCenter(true);
			    //里的A1：R1，表示是从哪里开始，哪里结束这个筛选框
			    CellRangeAddress c = CellRangeAddress.valueOf("A2:D2");  
				sheet.setAutoFilter(c);
			    //设置列宽
			    sheet.setColumnWidth(0, 3200);
		        sheet.setColumnWidth(1, 4800);		
		        sheet.setColumnWidth(2, 6800);
		        sheet.setColumnWidth(3, 3200);
		        sheet.setColumnWidth(4, 5000);
		        sheet.setColumnWidth(5, 6000);
		      //定义表格行索引
		        int index=0;
		      //添加标题
		        HSSFRow row_title = sheet.createRow(index++);
		        row_title.setHeight((short) 600);// 设置行高 
		        HSSFCell row_title0 = row_title.createCell(0);   
		        row_title0.setCellValue(new HSSFRichTextString("员工出入记录")); 
		        //合并表头单元格
		        ExcelUtil.setRegionStyle(sheet, new Region(0,(short)0,0,(short)5),ExcelUtil.createTitleStyle(workbook));
		        sheet.addMergedRegion(new Region(
		        0 //first row (0-based) from 行  
		        ,(short)0 //first column (0-based) from 列     
		        ,0//last row  (0-based)  to 行
		        ,(short)5//last column  (0-based)  to 列     
		        ));
		        
		        //添加头信息
		        String[] titles={"姓名","手机号码","员工卡号","部门","进出","时间"};
		        HSSFRow row_head = sheet.createRow(index++);
		        for (int i=0; i<titles.length;i++) {
		        	HSSFCell cell = row_head.createCell(i);
					cell.setCellValue(titles[i]);
					cell.setCellStyle(ExcelUtil.createTextStyle(workbook));
				}
		        
		        for (TrajectoryEmp entity : traEmps) {
		        	HSSFRow row = sheet.createRow(index++);
		        	//姓名
		        	HSSFCell cell0 = row.createCell(0);
		        	cell0.setCellValue(entity.getEmployees().getName());
		        	//手机号码
		        	HSSFCell cell1 = row.createCell(1);
					cell1.setCellValue(entity.getEmployees().getPhone());
		        	//员工卡号
					HSSFCell cell2 = row.createCell(2);
					cell2.setCellValue(entity.getEmployees().getCardno());
		        	//部门
					HSSFCell cell3 = row.createCell(3);
					cell3.setCellValue(entity.getEmployees().getDept().getName());
		        	//进出
					HSSFCell cell4 = row.createCell(4);
					cell4.setCellValue(entity.getOptAction());
		        	//时间
					HSSFCell cell5 = row.createCell(5);
					cell5.setCellValue(DateUtil.DateToString(entity.getOptTime(), "yyyy-MM-dd HH:mm:ss"));
				}
		        try {
					String fileName="员工出入记录";
					ExcelUtil.write(request, response, workbook, fileName);
					
					for (TrajectoryEmp entity : traEmps) {
						trajectoryEmpService.deleteTrajectory(entity.getId());
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
	
}
