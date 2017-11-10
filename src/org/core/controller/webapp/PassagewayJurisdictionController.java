package org.core.controller.webapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;
import org.core.service.webapp.HrmService;
import org.core.service.webapp.PJService;
import org.core.service.webapp.PassagewayGroupService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:通道绑定授权
 * */
@Controller
public class PassagewayJurisdictionController {
	/**
	 * 注入Service
	 * */
	@Autowired
	@Qualifier("pJService")
	private PJService pJService;
	@Autowired
	@Qualifier("passagewayGroupService")
	private PassagewayGroupService passagewayGroupService;
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 查询一遍员工，显示id，姓名，卡号
	 * */
	@RequestMapping(value="/PassagewayJurisdiction/selectEmploee")
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
			return "group/PJshowEmp";
			
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
		//通道绑定授权
		@RequestMapping(value="/PassagewayJurisdiction/shouPJG")
		public ModelAndView shouPJG(String ids,String flag,Model model,ModelAndView mv,
				String pjemps,String pjname,String pjgroup,
				HttpServletRequest request){
			/*int count = pJService.selectPJG(id);
			if(count>0){
				//System.out.println("已经存在权限。。。");
			}else{*/
			
				if(flag.equals("1")){
					//查询一下单个员工
					List<Employee> findEmployeeById = pJService.findEmployeeByIds(ids);
					//查询一下通道分组所有信息
					List<PassagewayGroup> passageGroups = pJService.selectAll();
					//循环遍历
					for (PassagewayGroup myP : passageGroups) {
						String selectids=myP.getPgid();
						List<Passageway> savepassageway = passagewayGroupService.getPassagewayById(selectids);
						for (Passageway passageway : savepassageway) {
							//把查到的通道集合挨个添加到通道的一个集合中
							myP.getOrderItems().add(passageway);
						}
					}
					List<Passageway> pgPassageways = passagewayGroupService.selectPGSubordinate();
					model.addAttribute("passageGroups", passageGroups);
					model.addAttribute("findEmployeeById", findEmployeeById);
					model.addAttribute("pgPassageways", pgPassageways);
					mv.setViewName("group/showaddEmptoPJ");
				}else{
					//执行保存
					String[] empids = request.getParameterValues("pjemps");
					
						pJService.savePJNew(empids,pjname,pjgroup);
						
				
					//pJService.savePJ(passagewayj);
					mv.setViewName("redirect:/PassagewayJurisdiction/selectPJ");
				}
			
			return mv;
		}
		/**
		 * 查询授权表
		 * */
		@RequestMapping(value="/PassagewayJurisdiction/selectPJ")
		public String selectPJ(Integer pageIndex, @ModelAttribute Passagewayj passagewayj,Model model){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
					}
			
			List<Passagewayj> passagewayjs = pJService.selectPJ(passagewayj,pageModel);
			for (Passagewayj ps: passagewayjs) {
				//得到单个通道分组id
				String selectEGs = ps.getPjgroupid();
				//得到通道分组
				PassagewayGroup groupById = pJService.selectPGbyId(selectEGs);
				//保存到授权表中
				ps.setPgroups(groupById);
				
				//得到单个通道id
				String Danpid = ps.getPassagewayjid();
				//得到通道
				Passageway passagewaybyid= pJService.selecPbypid(Danpid);
				//保存一个通道对象到授权实体中的Pass中
				ps.setPass(passagewaybyid);
				
				String selectEmps = ps.getPjempid();
				Employee EmpById = pJService.selectPjEmpbyId(selectEmps);
				ps.setPjEmployee(EmpById);

			}
			model.addAttribute("passagewayjs", passagewayjs);
			model.addAttribute("pageModel", pageModel);
			return "group/showPJ";
		}
		/**
		 * 删除授权表
		 * */
		@RequestMapping(value="/PassagewayJurisdiction/removePJ")
		public ModelAndView removePassagewayj(String ids,ModelAndView mv,
				HttpServletRequest request){
			pJService.removePassagewayjByID(ids);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/PassagewayJurisdiction/selectPJ");
			// 返回ModelAndView
			return mv;
		}
		/**
		 * 修改
		 * 
		 * */
				@RequestMapping(value = "/PassagewayJurisdiction/updetaPj")
				public ModelAndView updetaPj(String id,String flag,Model model,
						@ModelAttribute Passagewayj passagewayj,
						ModelAndView mv) {
					if(flag.equals("1")){
						//根据id查自身
						Passagewayj myUPPassagewayj = pJService.selectPjByid(id);
						//所有的电梯组 及其下级单位
						List<PassagewayGroup> passagewayGroups = pJService.selectAll();
						for (PassagewayGroup myP : passagewayGroups) {
							String selectids = myP.getPgid();
							List<Passageway> addPassageway =passagewayGroupService.getPassagewayById(selectids);
							for (Passageway mypassageway : addPassageway) {
								myP.getOrderItems().add(mypassageway);
							}
						}
						
						//自身的东西
						model.addAttribute("myUPPassagewayj", myUPPassagewayj);
						
						//所有东西
						model.addAttribute("passagewayGroups", passagewayGroups);

						// 设置客户端跳转到查询请求
						mv.setViewName("group/showUpdatePJ");
					}else{
							
						pJService.updatePj(passagewayj);
						 mv.setViewName("redirect:/PassagewayJurisdiction/selectPJ");
					}
					return mv;
				}
}
