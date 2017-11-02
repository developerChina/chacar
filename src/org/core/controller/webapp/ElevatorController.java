package org.core.controller.webapp;

import java.util.List;

import org.core.domain.webapp.Elevator;
import org.core.service.webapp.ElevatorService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class ElevatorController {
	
	
	@Autowired
	@Qualifier("elevatorService")
	private ElevatorService elevatorService;
	
	
	@RequestMapping(value="/elevator/elevatorAck")
	 public ModelAndView elevatorAck(Integer pageIndex,
			 @ModelAttribute Elevator elevator,
			 Model model,ModelAndView mv){
		
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		//System.out.println(elevator.getControllerSN()+"6666"+elevator.getElevatorName());
		/** 查询电梯信息     */
		List<Elevator> elevators = elevatorService.findElevator(elevator, pageModel);
		model.addAttribute("elevators", elevators);
		model.addAttribute("pageModel", pageModel);
		
		// 设置客户端跳转到查询请求
		mv.setViewName("dept/elevator");
		// 返回ModelAndView
		return mv;
	}
	
	
	
	/**
	 * 处理删除电梯请求
	 * */
	@RequestMapping(value="/elevator/removeElevator")
	 public ModelAndView removeUser(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			elevatorService.removeElevatorByID(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/elevator/elevatorAck");
		// 返回ModelAndView
		return mv;
	}
	
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 */
	@RequestMapping(value="/elevator/addElevator")
	 public ModelAndView addElevator(
			 String flag,
			 @ModelAttribute Elevator elevator,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("dept/showAddElevator");
		}else{
			// 执行添加操作
			elevatorService.addElevator(elevator);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/elevator/elevatorAck");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理修改用户请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * */
	@RequestMapping(value="/elevator/updateElevator")
	 public ModelAndView updateUser(
			 String flag,
			 @ModelAttribute Elevator elevator,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询用户
			Elevator target = elevatorService.findElevatorById(elevator.getElevatorID());
			// 设置Model数据
			mv.addObject("myelevator", target);
			// 返回修改员工页面
			mv.setViewName("dept/showUpdateElevator");
		}else{
			// 执行修改操作
			elevatorService.modifyElevator(elevator);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/elevator/elevatorAck");
		}
		// 返回
		return mv;
	}
	
	
	
}