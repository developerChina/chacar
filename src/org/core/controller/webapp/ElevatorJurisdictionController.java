package org.core.controller.webapp;


import java.util.List;

import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.ElevatorGroup;
import org.core.domain.webapp.Elevatorj;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.service.webapp.EJService;
import org.core.service.webapp.GroupService;
import org.core.service.webapp.HrmService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 电梯授权请求控制器
 * 
 */
@Controller
public class ElevatorJurisdictionController {
	//授权的自身Service接口
	@Autowired
	@Qualifier("eJService")
	private EJService eJService;
	//注入分组的Service接口
	@Autowired
	@Qualifier("groupService")
	private GroupService groupService;
	//员工的Service接口
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	/**
	 * 处理查询请求
	 * @param pageIndex 请求的是第几页
	 * @param String job_id 职位编号
	 * @param String dept_id 部门编号
	 * @param employee 模糊查询参数
	 * @param Model model
	 * */
	//点击授权 查询员工
	@RequestMapping(value = "Jurisdiction/elevatorJurisdiction")
	public String selectEmployee(Integer pageIndex,
			 Integer job_id,Integer dept_id,
			 @ModelAttribute Employee employee,
			 Model model){
		// 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
		this.genericAssociation(job_id, dept_id, employee);
		// 创建分页对象
		PageModel pageModel = new PageModel();
		// 如果参数pageIndex不为null，设置pageIndex，即显示第几页
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		// 查询职位信息，用于模糊查询
		List<Job> jobs = hrmService.findAllJob();
		// 查询部门信息 ，用于模糊查询
		List<Dept> depts = hrmService.findAllDept();
		// 查询员工信息    
		List<Employee> employees = hrmService.findEmployee(employee,pageModel);
		// 设置Model数据
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		// 跳转到电梯显示员工的界面
		return "group/EJshowEmp";
		
	}
	/**
	 * 由于部门和职位在Employee中是对象关联映射，
	 * 所以不能直接接收参数，需要创建Job对象和Dept对象
	 * */
	private void genericAssociation(Integer job_id,
			Integer dept_id,Employee employee){
		if(job_id != null){
			Job job = new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id != null){
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}
	
	
	//给单个员工授权 分配电梯组
	@RequestMapping(value = "Jurisdiction/getParts")
	public ModelAndView getParts(String id,String flag,ModelAndView mv,
			 @ModelAttribute Elevatorj elevatorj,
			Model model){
		//查询数据库的电梯授权表 根据传上来的 EMP 的id 查询表中所属员工是否存在
		int count = eJService.selectEmp(id);
		
		/*判断是否已经授权*/
		if(count>0){
			//存在 提示用户！
		}else{
			//不存在 
			if(flag.equals("1")){
				//执行flag=1 得到所有配件
				Employee findEmployeeById = hrmService.findEmployeeById(Integer.parseInt(id));
				List<ElevatorGroup> elevatorGroups = eJService.findEGALL();
				for (ElevatorGroup myE : elevatorGroups) {
					String selectids = myE.getEgssxj();
					List<Elevator> addElevators =groupService.getElevatorByEGid(selectids);
					for (Elevator myelevator : addElevators) {
						myE.getOrderItems().add(myelevator);
					}
				}
				List<Elevator> egElevators= groupService.selectEGSubordinate();
				model.addAttribute("egElevators",egElevators);
				model.addAttribute("elevatorGroups", elevatorGroups);
				model.addAttribute("findEmployeeById", findEmployeeById);
				mv.setViewName("group/showAddEmptoEJ");
			}else{
				//flag=其他
				eJService.saveEj(elevatorj);
				mv.setViewName("redirect:/Jurisdiction/getEJ");
			}
		}
		return mv;
		 
	}
	
	//查询电梯授权表
	@RequestMapping(value = "Jurisdiction/getEJ")
	public ModelAndView elevatorSplit(Integer epageIndex,
			 @ModelAttribute Elevatorj elevatorj,
			 Model model,ModelAndView mv) {
		
		PageModel pageModel = new PageModel();
		if(epageIndex != null){
			pageModel.setPageIndex(epageIndex);
		}
		List<Elevatorj> selectEJ = eJService.selectEJAll(pageModel,elevatorj);
		for (Elevatorj myelevatorj : selectEJ) {
			//先来 电梯组
			String selectEGs = myelevatorj.getEjgroup();
			List<ElevatorGroup> groupById = eJService.selectEGbyId(selectEGs);
			if(groupById.size()>0){
				for (ElevatorGroup myGroup : groupById) {
					if(myGroup!=null){
						String selectids =myGroup.getEgssxj();
						List<Elevator> addElevators =groupService.getElevatorByEGid(selectids);
						for (Elevator myelevator : addElevators) {
							
							myGroup.getOrderItems().add(myelevator);
						}
					}
					myelevatorj.getEgroups().add(myGroup);
				}
			}
			
			/*String selectEmps = myelevatorj.getEjemp();
			
			String selectEs = myelevatorj.getEjelevator();*/
		}
		
		model.addAttribute("selectEJ", selectEJ);
		model.addAttribute("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("group/showEJ");
		// 返回ModelAndView
		return mv;
	}
	
	//删除电梯授权
	@RequestMapping(value = "Jurisdiction/removeEj")
	public ModelAndView removeEgroup(String ids,ModelAndView mv) {
		
		String[] idArry = ids.split(",");
		for (String id : idArry) {
			eJService.EjDelByEjid(id);
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/Jurisdiction/getEJ");
		// 返回ModelAndView
		return mv;
	}
	
	
	
	//修改
	@RequestMapping(value = "Jurisdiction/updetaEj")
	public ModelAndView updetaEj(String id,String flag,Model model,
			@ModelAttribute Elevatorj elevatorj,
			ModelAndView mv) {
		if(flag.equals("1")){
			
			Elevatorj myUPelevatorj = eJService.selectEjByid(id);
			
			
			//所有的电梯组 及其下级单位
			List<ElevatorGroup> elevatorGroups = eJService.findEGALL();
			for (ElevatorGroup myE : elevatorGroups) {
				String selectids = myE.getEgssxj();
				List<Elevator> addElevators =groupService.getElevatorByEGid(selectids);
				for (Elevator myelevator : addElevators) {
					myE.getOrderItems().add(myelevator);
				}
			}
			
			//自身的东西
			model.addAttribute("myUPelevatorj", myUPelevatorj);
			
			//所有东西
			model.addAttribute("elevatorGroups", elevatorGroups);

			// 设置客户端跳转到查询请求
			mv.setViewName("group/showUpdateEJ");
		}else{
				
			 eJService.updateEj(elevatorj);
			 mv.setViewName("redirect:/Jurisdiction/getEJ");
		}
		
		return mv;
	}
	
	
	
	
	
}
