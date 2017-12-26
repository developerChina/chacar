package org.core.controller.queuing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.service.queuing.QueuingService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理排队叫号控制器
 */
@Controller
public class QueuingConteoller {
	@Autowired
	@Qualifier("queuingService")
	private QueuingService queuingService;
	 
	@RequestMapping(value = "/queuingI/islandIndex")
	public ModelAndView islandIndex(Integer no, ModelAndView mv) {
		if(no!=null){
			Island island=queuingService.selectByNo(no);
			if(island!=null){
				mv.addObject("island", island);
			}else{
				island=new Island();
				island.setIname("卸货岛不存在");
				mv.addObject("island", island);
			}
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("island/island");
		// 返回ModelAndView
		return mv;
	}
	@RequestMapping(value = "/queuingI/addQueue")
	@ResponseBody		
	public Object addQueue(@ModelAttribute Ordinary ordinary,Integer isadd) {
		Map<String, Object> map=new HashMap<>();
		//判断卸货岛是否存在
		Island island=queuingService.selectByNo(ordinary.getIsland_no());
		if(island!=null){
			//判断车辆是否入场
			if(1==1){
				if(isadd==0){
					//判断是否添加
					Ordinary maxo=queuingService.selectMaxOByLand(ordinary.getIsland_no());
					if(maxo!=null){
						ordinary.setQueue_number(maxo.getQueue_number()+1);
					}else{
						ordinary.setQueue_number(1);
					}
					ordinary.setRemarks("普通号");
					queuingService.addO(ordinary);
				}
				//查询车辆排队信息
				List<Object> returnList=new ArrayList<>();
				boolean isLoopOrdinary=true;//是否循环普通队列
				int waiting=0;
				List<QueuingVip> vips=queuingService.selectVAll(ordinary.getIsland_no());
				for (QueuingVip vip : vips) {
					if(!ordinary.getCar_code().equals(vip.getCar_code())){
						returnList.add(vip);
						waiting++;
					}else{
						returnList.add(vip);
						isLoopOrdinary=false;
						break;
					}
				}
				List<Ordinary> os=queuingService.selectOAll(ordinary.getIsland_no());
				if(isLoopOrdinary){
					for (Ordinary o : os) {
						if(!ordinary.getCar_code().equals(o.getCar_code())){
							returnList.add(o);
							waiting++;
						}else{
							returnList.add(o);
							break;
						}
					}
				}
				
				map.put("status", true);
				map.put("waiting", waiting);
				map.put("all", vips.size()+os.size());
				map.put("list", returnList);
			}else{
				map.put("status", false);
				map.put("message", "车辆没有入场不能查询或排队");
			}
		}else{
			map.put("status", false);
			map.put("message", "卸货岛不存在");
		}
		return map;
	}
	

	// 排队叫号操作 控制器
	// 1-->卸货岛查询带分页
	/**
	 * 卸货岛管理 跳向首页 分页查询
	 */
	@RequestMapping(value="/queuingI/IslandAck")
			 public ModelAndView IslandAck(Integer pageIndex,
					 @ModelAttribute Island island,
					 ModelAndView mv){		
	    PageModel pageModel = new PageModel();
		if (pageIndex != null) {
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
	 * flag=1跳向添加页面 <br>
	 * flag=2执行添加
	 */
	@RequestMapping(value="/queuingI/IslandAdd")
			 public ModelAndView IslandAdd(String flag,
					 @ModelAttribute Island island,
					 ModelAndView mv){		
	    if (flag.equals("1")) {
			// 设置跳转到添加页面
			mv.setViewName("/queuing/showAddI");
		} else {
			// 执行添加
			queuingService.addI(island);
			mv.setViewName("redirect:/queuingI/IslandAck");
		}
		return mv;
	}

	/**
	 * 卸货岛删除 <br>
	 * 
	 * @param no
	 *            卸货岛的主键编号值
	 * 
	 */
	@RequestMapping(value = "/queuingI/delIslandAck")
	public ModelAndView delRoomAck(Integer id, ModelAndView mv) {
		queuingService.delIsland(id);
		mv.setViewName("redirect:/queuingI/IslandAck");
		return mv;
	}

	/**
	 * 卸货岛修改 <br>
	 * flag=1跳向修改页面 <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value = "/queuingI/updateIslandAck")
	public ModelAndView IslandUpd(String flag, Integer id, @ModelAttribute Island island, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到修改页面
			Island updateI = queuingService.updateISel(id);

			mv.addObject("updateI", updateI);
			mv.setViewName("/queuing/showUpdI");
		} else {
			// 执行修改
			queuingService.UpdI(island);
			mv.setViewName("redirect:/queuingI/IslandAck");
		}
		// 返回ModelAndView
		return mv;
	}

	// 2-->VIP队列查询带分页
	/**
	 * VIP队列管理 跳向首页 分页查询
	 */
	@RequestMapping(value = "/queuingV/VipAck")
	public ModelAndView VipAck(Integer pageIndex, @ModelAttribute QueuingVip queuingVip, ModelAndView mv) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
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
	 * flag=1跳向添加页面 <br>
	 * flag=2执行添加
	 */
	@RequestMapping(value = "/queuingV/VipAdd")
	public ModelAndView VipAdd(String flag, @ModelAttribute QueuingVip queuingVip, ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到添加页面
			// 卸货岛组件
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);

			mv.setViewName("/queuing/showAddV");
		} else {
			// 执行添加
			queuingService.addV(queuingVip);
			mv.setViewName("redirect:/queuingV/VipAck");
		}
		return mv;
	}

	/**
	 * VIP队列删除 <br>
	 * 
	 * @param vid
	 *            VIP队列的主键编号值
	 * 
	 */
	@RequestMapping(value = "/queuingV/delVipAck")
	public ModelAndView delVipAck(Integer id,
	  ModelAndView mv) {

		queuingService.delVip(id);
		mv.setViewName("redirect:/queuingV/VipAck");
		return mv;
	}

	/**
	 * VIP队列修改 <br>
	 * flag=1跳向修改页面 <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value = "/queuingV/updateVipAck")
	public ModelAndView VipUpd(String flag, Integer id, 
		@ModelAttribute QueuingVip queuingVip, 
		ModelAndView mv) {
		if (flag.equals("1")) {
			// 设置跳转到修改页面
			QueuingVip updateV = queuingService.updateVSel(id);
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			mv.addObject("updateV", updateV);
			mv.setViewName("/queuing/showUpdV");
		} else {
			// 执行修改
			queuingService.UpdV(queuingVip);
			mv.setViewName("redirect:/queuingV/VipAck");
		}
		// 返回ModelAndView
		return mv;
	}

	// 3-->历史队列查询带分页
	/**
	 * 历史队列的管理 跳向首页 分页查询
	 */
	@RequestMapping(value = "/queuingH/HistoryAck")
	public ModelAndView HistoryAck(Integer pageIndex, @ModelAttribute History history, ModelAndView mv) {
		PageModel pageModel = new PageModel();
		if (pageIndex != null) {
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

//4、普通队列		
		/**
		 * 历史队列的管理 跳向首页
		 * 分页查询
		 */
		@RequestMapping(value="/queuingO/OrdinaryAck")
		 public ModelAndView OrdinaryAck(Integer pageIndex,
				 @ModelAttribute Ordinary ordinary,
				 ModelAndView mv){
			PageModel pageModel = new PageModel();
			if(pageIndex != null){
				pageModel.setPageIndex(pageIndex);
			}
			List<Ordinary> pageListO = queuingService.selectOByPage(ordinary, pageModel);
			mv.addObject("pageListO", pageListO);
			mv.addObject("pageModel", pageModel);
			// 设置客户端跳转到查询请求
			mv.setViewName("queuing/showO");
			// 返回ModelAndView
			return mv;
		}
		
		/**
		 * 普通队列添加 <br>
		 * flag=1跳向添加页面
		 * <br>
		 * flag=2执行添加
		 */
		@RequestMapping(value="/queuingO/OrdinaryAdd")
		 public ModelAndView OrdinaryAdd(String flag,
				 @ModelAttribute Ordinary ordinary,
				 ModelAndView mv){
			if(flag.equals("1")){
				// 设置跳转到添加页面
						//卸货岛组件
				List<Island> AddVgetI = queuingService.AddVgetI();
				mv.addObject("AddVgetI", AddVgetI);
				
				mv.setViewName("/queuing/showAddO");
			}else{
				//执行添加
				queuingService.addConteollerO(ordinary);
				mv.setViewName("redirect:/queuingO/OrdinaryAck");
			}
			return mv;
		}
		
		
		
		/**
		 * 普通队列删除 <br>
		 * 
		 * @param id 普通队列的主键编号值
		 * 
		 */
	@RequestMapping(value="/queuingO/delOAck")
	 public ModelAndView delOAck(Integer id,
			 ModelAndView mv){
		
		queuingService.delOrdinary(id);
		mv.setViewName("redirect:/queuingO/OrdinaryAck");
		return mv;
	}
		
		
		
		
	/**
	 * 普通队列修改 <br>
	 * flag=1跳向修改页面
	 * <br>
	 * flag=2执行修改
	 */
	@RequestMapping(value="/queuingO/updOrdinaryAck")
	 public ModelAndView OrdinaryUpd(String flag,Integer id,
			 @ModelAttribute Ordinary ordinary,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到修改页面
			Ordinary updateO = queuingService.updateOSel(id);
			
			List<Island> AddVgetI = queuingService.AddVgetI();
			mv.addObject("AddVgetI", AddVgetI);
			mv.addObject("updateO", updateO);
			mv.setViewName("/queuing/showUpdO");
		}else{
			//执行修改
			queuingService.UpdO(ordinary);
			mv.setViewName("redirect:/queuingO/OrdinaryAck");
		}
	// 返回ModelAndView
		return mv;
	}
	//友好提示
		@ResponseBody
		@RequestMapping(value="/queuingAdd/addValidate")
		public Object addValidate(HttpServletRequest request,
				 HttpServletResponse response){
			
			String car_code = request.getParameter("car_code");
			
			String judge = request.getParameter("flag");
			System.out.println(judge+car_code);
			Map<String,Object> map = new HashMap<>();
			
			String flag = queuingService.addValidate(car_code,judge);
				
				System.out.println(flag);
				if(!"".equals(flag)){
					map.put("status", false);
					map.put("message", flag);
				}else{
					map.put("status", true);
					map.put("message", "验证通过");
				}
				
			return map;
		}
		
		
}
