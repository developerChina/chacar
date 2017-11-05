package org.core.controller.webapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.resource.ResourceAccess;
import org.core.domain.resource.ResourceInfo;
import org.core.domain.webapp.User;
import org.core.service.resource.ResourceAccessService;
import org.core.service.resource.ResourceInfoService;
import org.core.service.webapp.HrmService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 转向首页
 * */
@Controller
public class AuthorityController {
	/**
	 * 自动注入resourceAccessService
	 * */
	@Autowired
	@Qualifier("resourceAccessService")
	private ResourceAccessService resourceAccessService;
	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	@Autowired
	@Qualifier("resourceInfoService")
	private ResourceInfoService resourceInfoService;
	
	@RequestMapping(value="/authority/authorityAck")
	public ModelAndView authorityAck(
			Integer pageIndex,
			@ModelAttribute User user,
			ModelAndView mv,
			HttpServletRequest request,
			HttpServletResponse response){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		/** 查询用户信息     */
		List<User> users = hrmService.findUser(user, pageModel);
		mv.addObject("users", users);
		mv.addObject("pageModel", pageModel);
		// 设置客户端跳转到查询请求
		mv.setViewName("user/authority");
		// 返回ModelAndView
		return mv;
	}
	
	
	/**
	 * 修改授权
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/authority/addAuthority")
	@ResponseBody		
	public Object addAuthority(HttpServletRequest request, HttpServletResponse response){
		String userids=request.getParameter("userids");
		String resources=request.getParameter("resources");
		for (String userid : userids.split(",")) {
			int user_id=Integer.parseInt(userid);
			resourceAccessService.deleteByUserid(user_id);
			for (String resourceid : resources.split(",")) {
				int resource_id=Integer.parseInt(resourceid);
				ResourceAccess ra=new ResourceAccess();
				ra.setUser_id(user_id);
				ra.setResource_id(resource_id);
				resourceAccessService.save(ra);
			}
		}
		Map<String, Object> map=new HashMap<>();
		map.put("state", true);
		map.put("message", "授权成功");
		return map;
	}
	
	/**
	 * 显示授权
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/authority/showAuthority")
	@ResponseBody		
	public Object showAuthority(HttpServletRequest request, HttpServletResponse response){
		String userid=request.getParameter("userid");
		List<ResourceInfo> resources= resourceInfoService.selectAll();
		List<ResourceAccess> ras= resourceAccessService.selectByUserid(Integer.parseInt(userid));
		Map<Object, Object> map_ras=new HashMap<>();
		for (ResourceAccess resourceAccess : ras) {
			map_ras.put(resourceAccess.getResource_id(), true);
		}
		List<Map<String, Object>> list=new ArrayList<>();
		for (ResourceInfo resourceInfo : resources) {
			Map<String, Object> map=new HashMap<>();
			map.put("pid", resourceInfo.getPid());
			map.put("id",resourceInfo.getId());
			map.put("name",resourceInfo.getName());
			if(map_ras.get(resourceInfo.getId())!=null){
				map.put("checked", true);	
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 门禁绑定授权
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/authority/floorBindAck")
	 public ModelAndView floorBindAck(ModelAndView mv){
		// 设置客户端跳转到查询请求
		mv.setViewName("dept/floorBind");
		// 返回ModelAndView
		return mv;
	}
}