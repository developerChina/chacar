package org.core.controller.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.core.domain.webapp.Job;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理职位请求控制器
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */

@Controller
public class JobController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理/login请求
	 * */
	@RequestMapping(value="/job/selectJob")
	 public String selectJob(HttpServletRequest request,Model model,Integer pageIndex,
			 @ModelAttribute Job job){
		System.out.println("selectJob -->> " + job);
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<Job> jobs = hrmService.findJob(job, pageModel);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		//分页参数
		model.addAttribute("model", job);
		String pageParam="";
		if(job.getName()!=null){
			pageParam+="&name="+job.getName();
		}
		if(StringUtils.isNotBlank(request.getParameter("message"))){
			model.addAttribute("message", request.getParameter("message"));
		}
		model.addAttribute("pageParam", pageParam);
		return "job/job";
		
	}
	
	/**
	 * 处理删除职位请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/removeJob")
	 public ModelAndView removeJob(String ids,ModelAndView mv){
		
		try {
			// 分解id字符串
			String[] idArray = ids.split(",");
			for(String id : idArray){
				// 根据id删除职位
				hrmService.removeJobById(Integer.parseInt(id));
			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message", "删除失败,选中职位有员工");
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/job/selectJob");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Job  job  要添加的职位对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/addJob")
	 public ModelAndView addJob(
			 String flag,
			 @ModelAttribute Job job,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("job/showAddJob");
		}else{
			// 执行添加操作
			hrmService.addJob(job);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理修改职位请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Job job 要修改部门的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/job/updateJob")
	 public ModelAndView updateDpet(
			 String flag,
			 @ModelAttribute Job job,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询部门
			Job target = hrmService.findJobById(job.getId());
			// 设置Model数据
			mv.addObject("job", target);
			// 设置跳转到修改页面
			mv.setViewName("job/showUpdateJob");
		}else{
			// 执行修改操作
			hrmService.modifyJob(job);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/job/selectJob");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 批量导入职位页面
	 */
	@RequestMapping(value = "/job/importJobPage")
	public ModelAndView importJobPage(ModelAndView mv) {
		mv.setViewName("job/jobImport");
		return mv;
	}
	
	/**
	 * 批量导入职位
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/job/importJob")
	public ModelAndView importJob(ModelAndView mv,
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
				Job job=new Job();
				for (Integer key : data.keySet()) {
					job.setName(data.get(0));
					job.setRemark(data.get(1));
				}
				if(StringUtils.isNotBlank(job.getName())){
					hrmService.saveOrUpdateJob(job);
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
