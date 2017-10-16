package org.core.controller.example;

import java.util.List;

import org.core.domain.Example;
import org.core.service.example.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例子 -》 请求控制器
 * 
 * 浏览器访问： http://127.0.0.1:8080/chacar/example/forwardExample
 */
@Controller
public class ExampleController {
	/**
	 * 自动注入exampleService
	 */
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;

	@RequestMapping(value = "/example/forwardExample")
	public ModelAndView forwardExample(ModelAndView mv) {
		// 设置客户端跳转到查询请求
		mv.setViewName("example/example");
		// 返回ModelAndView
		return mv;
	}
	/**
	 * 处理查询请求
	 */
	@RequestMapping(value = "/example/selectExample")
	public String selectExample(Integer pageIndex, @ModelAttribute Example example, Model model) {
		/** 查询用户信息 */
		List<Example> examples = exampleService.findExample(example);
		model.addAttribute("examples", examples);
		return "user/user";
	}

	/**
	 * 处理添加请求
	 */
	@RequestMapping(value = "/example/addExample")
	public ModelAndView addUser(String flag, @ModelAttribute Example example, ModelAndView mv) {
		// 执行添加操作
		exampleService.addExample(example);
		// 设置客户端跳转到查询请求
		// mv.setViewName("redirect:/example/selectExample");
		// 返回
		return mv;
	}

	@RequestMapping(value = "/example/updateExample")
	public ModelAndView updateExample(String flag, @ModelAttribute Example example, ModelAndView mv) {
		// 执行修改操作
		exampleService.modifyExample(example);
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectUser");
		// 返回
		return mv;
	}
	/**
	 * 处理删除用户请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/example/removeExample")
	 public ModelAndView removeExample(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除员工
			exampleService.removeExampleById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/user/selectExample");
		// 返回ModelAndView
		return mv;
	}
}
