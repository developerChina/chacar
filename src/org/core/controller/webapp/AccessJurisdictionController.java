package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.Dept;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Job;
import org.core.service.webapp.AJService;
import org.core.service.webapp.AccessGroupService;
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
 * @Description:门禁绑定授权
 * */
@Controller
public class AccessJurisdictionController {
		/**
		 * 注入Service
		 * */
	@Autowired
	@Qualifier("aJService")
	private AJService aJService;
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	@Autowired
	@Qualifier("accessgroupService")
	private AccessGroupService accessgroupService;
	/**
	 * 查询一遍员工，显示id，姓名，卡号
	 * */
	@RequestMapping(value="/AccessJurisdiction/selectEmploee")
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
			return "group/AJshowEmp";
			
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
		@RequestMapping(value="/AccessJurisdiction/shouAJG")
		public ModelAndView shouAJG(String id,String flag,Model model,
				ModelAndView mv,
				@ModelAttribute Accessj accessj){
			int count=aJService.selectAJG(id);
			//判断是否已经授权
			if(count>0){
				//System.out.println("已经存在权限。。。");
			}else{
				if(flag.equals("1")){
					Employee findEmployeeById = hrmService.findEmployeeById(Integer.parseInt(id));
					List<AccessGroup> accessGroups = aJService.findAGAll();
					for (AccessGroup myA : accessGroups) {
						String selectids=myA.getAgssxj();
						List<Access> saveaccesss = accessgroupService.getAccessById(selectids);
						for (Access access : saveaccesss) {
							//把查到的门禁集合挨个添加到门禁组的一个集合中
							myA.getOrderItems().add(access);
						}
					}
					List<Access> agAccesss=accessgroupService.selectAGSubordinate();
					model.addAttribute("accessGroups", accessGroups);
					model.addAttribute("findEmployeeById", findEmployeeById);
					model.addAttribute("agAccesss", agAccesss);
					mv.setViewName("group/showaddEmptoAJ");
				}else{
					aJService.saveAJ(accessj);
					mv.setViewName("redirect:/AccessJurisdiction/selectAJ");
				}
			}
			return mv;
		}
		/**
		 * 查询授权表
		 * */
		@RequestMapping(value="/AccessJurisdiction/selectAJ")
		public String selectPJ(Integer pageIndex, @ModelAttribute Accessj accessj,Model model){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
					}
			List<Accessj> accessjs = aJService.selectAJ(accessj,pageModel);
			for (Accessj selectaccessj : accessjs) {
				//先来 电梯组
				String selectEGs = selectaccessj.getAjgroup();
				List<AccessGroup> groupById = aJService.selectPGbyId(selectEGs);
				if(groupById.size()>0){
					for (AccessGroup myGroup : groupById) {
						if(myGroup!=null){
							String selectids =myGroup.getAgssxj();
							List<Access> addElevators =accessgroupService.getAccessById(selectids);
							for (Access myelevator : addElevators) {
								
								myGroup.getOrderItems().add(myelevator);
							}
						}
						selectaccessj.getAgroups().add(myGroup);
					}
				}
				
				/*String selectEmps = myelevatorj.getEjemp();
				
				String selectEs = myelevatorj.getEjelevator();*/
			

			}
			model.addAttribute("accessjs", accessjs);
			model.addAttribute("pageModel", pageModel);
			return "group/showAJ";
		}
		/**
		 * 删除授权表
		 * */
		@RequestMapping(value="/AccessJurisdiction/removeAJ")
		public ModelAndView removeAccessj(String ids,ModelAndView mv){
			// 分解id字符串
			String[] idArray = ids.split(",");
			for(String id : idArray){
				// 根据id删除员工
				aJService.removeAccessjByID(id);
			}
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/AccessJurisdiction/selectAJ");
			// 返回ModelAndView
			return mv;
		}
		//修改
		@RequestMapping(value = "/AccessJurisdiction/updetaAj")
		public ModelAndView updetaAj(String id,String flag,Model model,
				@ModelAttribute Accessj accessj,
				ModelAndView mv) {
			if(flag.equals("1")){
				//根据id查自身
				Accessj myUPAccessj = aJService.selectAjByid(id);
				//所有的电梯组 及其下级单位
				List<AccessGroup> accessGroups = aJService.findAGAll();
				for (AccessGroup myA : accessGroups) {
					String selectids = myA.getAgssxj();
					List<Access> addAccesss =accessgroupService.getAccessById(selectids);
					for (Access myaccess : addAccesss) {
						myA.getOrderItems().add(myaccess);
					}
				}
				
				//自身的东西
				model.addAttribute("myUPAccessj", myUPAccessj);
				
				//所有东西
				model.addAttribute("accessGroups", accessGroups);

				// 设置客户端跳转到查询请求
				mv.setViewName("group/showUpdateAJ");
			}else{
					
				aJService.updateAj(accessj);
				 mv.setViewName("redirect:/AccessJurisdiction/selectAJ");
			}
			
			return mv;
		}

}
