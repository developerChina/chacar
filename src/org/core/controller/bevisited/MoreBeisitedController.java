package org.core.controller.bevisited;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.service.bevisited.BevisitedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
		
/**
 * 多被访人
 * */
@Controller
public class MoreBeisitedController {
	@Autowired
	@Qualifier("bevisitedInfoService")
	private BevisitedInfoService bevisitedInfoService;
	
	/**
	 * 查询部门和被访人树
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/bevisited/getBevisitedTree")
	@ResponseBody		
	public Object getBevisitedTree(HttpServletRequest request, HttpServletResponse response){
		List<Map<String, Object>> list=bevisitedInfoService.getBevisitedTree();
		return list;
	}
	 
}
