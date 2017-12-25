package org.core.controller.queuing;

import java.util.List;

import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.QueuingVip;
import org.core.service.queuing.QueuingService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理排队叫号控制器
 * */
@Controller
public class QueuingConteoller {
		@Autowired
		@Qualifier("queuingService")
		private QueuingService queuingService;
		
		
		//排队叫号操作 控制器
				//1-->卸货岛查询带分页
			/**
			 * 卸货岛管理 跳向首页
			 * 分页查询
			 */
			@RequestMapping(value="/queuingI/IslandAck")
			 public ModelAndView IslandAck(Integer pageIndex,
					 @ModelAttribute Island island,
					 ModelAndView mv){
				PageModel pageModel = new PageModel();
				if(pageIndex != null){
					pageModel.setPageIndex(pageIndex);
				}
				List<Island> pageListI = queuingService.selectIByPage(island, pageModel);
				mv.addObject("pageListI", pageListI);
				mv.addObject("pageModel", pageModel);
				// 设置客户端跳转到查询请求
				mv.setViewName("queuing/showI");
				// 返回ModelAndView
				return mv;
			}
		
		
			/**
			 * 卸货岛添加 <br>
			 * flag=1跳向添加页面
			 * <br>
			 * flag=2执行添加
			 */
			@RequestMapping(value="/queuingI/IslandAdd")
			 public ModelAndView IslandAdd(String flag,
					 @ModelAttribute Island island,
					 ModelAndView mv){
				if(flag.equals("1")){
					// 设置跳转到添加页面
					mv.setViewName("/queuing/showAddI");
				}else{
					//执行添加
					queuingService.addI(island);
					mv.setViewName("redirect:/queuingI/IslandAck");
				}
				return mv;
			}
		
			/**
			 * 卸货岛删除 <br>
			 * 
			 * @param no 卸货岛的主键编号值
			 * 
			 */
		@RequestMapping(value="/queuingI/delIslandAck")
		 public ModelAndView delRoomAck(Integer id,
				 ModelAndView mv){
			queuingService.delIsland(id);
			mv.setViewName("redirect:/queuingI/IslandAck");
			return mv;
		}
		
		/**
		 * 卸货岛修改 <br>
		 * flag=1跳向修改页面
		 * <br>
		 * flag=2执行修改
		 */
		@RequestMapping(value="/queuingI/updateIslandAck")
		 public ModelAndView IslandUpd(String flag,Integer id,
				 @ModelAttribute Island island,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到修改页面
				Island updateI = queuingService.updateISel(id);
				
				mv.addObject("updateI", updateI);
				mv.setViewName("/queuing/showUpdI");
			}else{
				//执行修改
				queuingService.UpdI(island);
				mv.setViewName("redirect:/queuingI/IslandAck");
			}
		// 返回ModelAndView
			return mv;
		}

		
		
		
		
		
		
		
		//2-->VIP队列查询带分页
		/**
		 * VIP队列管理 跳向首页
		 * 分页查询
		 */
		@RequestMapping(value="/queuingV/VipAck")
		 public ModelAndView VipAck(Integer pageIndex,
				 @ModelAttribute QueuingVip queuingVip,
				 ModelAndView mv){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<QueuingVip> pageListV = queuingService.selectVByPage(queuingVip, pageModel);
			mv.addObject("pageListV", pageListV);
			mv.addObject("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			mv.setViewName("queuing/showV");
			// 返回ModelAndView
			return mv;
		}
		
		
		/**
		 * VIP队列添加 <br>
		 * flag=1跳向添加页面
		 * <br>
		 * flag=2执行添加
		 */
		@RequestMapping(value="/queuingV/VipAdd")
		 public ModelAndView VipAdd(String flag,
				 @ModelAttribute QueuingVip queuingVip,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
						//卸货岛组件
				List<Island> AddVgetI = queuingService.AddVgetI();
				mv.addObject("AddVgetI", AddVgetI);
				
				mv.setViewName("/queuing/showAddV");
			}else{
				//执行添加
				queuingService.addV(queuingVip);
				mv.setViewName("redirect:/queuingV/VipAck");
			}
			return mv;
		}
		
		/**
		 * VIP队列删除 <br>
		 * 
		 * @param vid VIP队列的主键编号值
		 * 
		 */
	@RequestMapping(value="/queuingV/delVipAck")
	 public ModelAndView delVipAck(Integer vid,
			 ModelAndView mv){
		
		queuingService.delVip(vid);
		mv.setViewName("redirect:/queuingV/VipAck");
		return mv;
	}
		
	/**
	 * VIP队列修改 <br>
	 * flag=1跳向修改页面
	 * <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value="/queuingV/updateVipAck")
	 public ModelAndView VipUpd(String flag,Integer vid,
			 @ModelAttribute QueuingVip queuingVip,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到修改页面
			QueuingVip updateV = queuingService.updateVSel(vid);
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			mv.addObject("updateV", updateV);
			mv.setViewName("/queuing/showUpdV");
		}else{
			//执行修改
			queuingService.UpdV(queuingVip);
			mv.setViewName("redirect:/queuingV/VipAck");
		}
	// 返回ModelAndView
		return mv;
	}
		
		
		
		
		
		
	//3-->历史队列查询带分页
		/**
		 * 历史队列的管理 跳向首页
		 * 分页查询
		 */
		@RequestMapping(value="/queuingH/HistoryAck")
		 public ModelAndView HistoryAck(Integer pageIndex,
				 @ModelAttribute History history,
				 ModelAndView mv){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<History> pageListH = queuingService.selectHByPage(history, pageModel);
			mv.addObject("pageListH", pageListH);
			mv.addObject("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			mv.setViewName("queuing/showH");
			// 返回ModelAndView
			return mv;
		}
		
		
		
		
		
		
}
