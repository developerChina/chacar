package org.core.controller.locationController;

import java.util.List;

import org.core.domain.location.LocationClient;
import org.core.service.location.ClientService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
		@Autowired
		@Qualifier("clientService")
		private ClientService clientService;
		
		
		@RequestMapping(value="/Client/LocationClientAck")
		 public String LocationClientAck(Integer pageIndex, @ModelAttribute LocationClient locationClient,Model model){
					PageModel pageModel = new PageModel();
					if(pageIndex != null){
						pageModel.setPageIndex(pageIndex);
			}
			/** 查询通道信息     */
			List<LocationClient> locationClients = clientService.findLocationClient(locationClient, pageModel);
			model.addAttribute("locationClients", locationClients);
			model.addAttribute("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			
			// 返回ModelAndView
			return "location/showLclient";

		}
				//删除
		@RequestMapping(value="/Client/removeLocationClient")
		 public ModelAndView removeLocationClient(String ids,ModelAndView mv){
			// 分解id字符串
			String[] idArray = ids.split(",");
			for(String id : idArray){
				 
					// 根据id删除通道
				clientService.removeLocationClientById(id);
				
			}
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/Client/LocationClientAck");
			// 返回ModelAndView
			return mv;
		}
		
		/*修改*/
		@RequestMapping(value="/Client/updateLocationClient")
		 public ModelAndView updateLocationClient(String flag,@ModelAttribute LocationClient locationClient,ModelAndView mv){
			if(flag.equals("1")){
				// 根据id查询通道
				LocationClient ggy = clientService.findLocationClientById(locationClient.getId());
				// 设置Model数据
				mv.addObject("locationClient", ggy);
				// 返回修改通道页面
				mv.setViewName("location/showUpdateLocationClient");
				
			}else{
				// 执行修改操作
				clientService.modifyLocationClient(locationClient);
				// 设置客户端跳转到查询请求
				mv.setViewName("redirect:/Client/LocationClientAck");
			}
			// 返回
			return mv;
		}
		//添加
		@RequestMapping(value="/Client/addLocationClient")
		 public ModelAndView addLocationClient(
				 String flag,
				 @ModelAttribute LocationClient locationClient,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
				mv.setViewName("location/showAddLocationClient");
			}else{
				// 执行添加操作
				clientService.addLocationClient(locationClient);
				// 设置客户端跳转到查询请求
				mv.setViewName("redirect:/Client/LocationClientAck");
			}
			// 返回
			return mv;
		
	}
}
