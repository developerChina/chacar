package org.core.controller.car;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.core.domain.car.CarAuthority;
import org.core.domain.car.CarDistinguish;
import org.core.domain.car.CarPark;
import org.core.domain.car.CarPassageway;
import org.core.service.car.CarAuthorityService;
import org.core.service.car.CarDistinguishService;
import org.core.service.car.CarParkService;
import org.core.service.car.CarPassagewayService;
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
public class CarController {
	
	@Autowired
	@Qualifier("carParkService")
	private CarParkService carParkService;
	@Autowired
	@Qualifier("carDistinguishService")
	private CarDistinguishService carDistinguishService;
	@Autowired
	@Qualifier("carPassagewayService")
	private CarPassagewayService carPassagewayService;
	@Autowired
	@Qualifier("carAuthorityService")
	private CarAuthorityService carAuthorityService;
	
	/**
	 * 车场管理
	 */
	@RequestMapping(value="/car/carPark")
	 public ModelAndView carPark(Integer pageIndex,ModelAndView mv,@ModelAttribute CarPark carPark){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarPark> parks = carParkService.selectByPage(carPark, pageModel);
		mv.addObject("parks", parks);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("car/carPark");
		return mv;
	}
	/**
	 * 车场添加
	 */
	@RequestMapping(value="/car/addcarPark")
	public ModelAndView addcarPark(
			 String flag,
			 @ModelAttribute CarPark carPark,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			mv.setViewName("car/carParkAdd");
		}else{
			carParkService.save(carPark); 
			mv.setViewName("redirect:/car/carPark");
		}
		return mv;
	}
	/**
	 * 车场修改
	 */
	@RequestMapping(value="/car/updatecarPark")
	public ModelAndView updatecarPark(
			 String flag,
			 @ModelAttribute CarPark carPark,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarPark target = carParkService.selectById(carPark.getId());
			mv.addObject("park", target);
			mv.setViewName("car/carParkUpdate");
		}else{
			carParkService.update(carPark);
			mv.setViewName("redirect:/car/carPark");
		}
		return mv;
	}
	/**
	 * 车场删除
	 */
	@RequestMapping(value="/car/deletecarPark")
	 public ModelAndView deletecarPark(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carParkService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carPark");
		return mv;
	}
	
	
	/**
	 * 车识别仪管理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carDistinguish")
	 public ModelAndView carDistinguish(Integer pageIndex,ModelAndView mv,@ModelAttribute CarDistinguish carDistinguish){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarDistinguish> distiguishes = carDistinguishService.selectByPage(carDistinguish, pageModel);
		mv.addObject("distiguishes", distiguishes);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("car/carDistinguish");
		return mv;
	}
	
	/**
	 * 车识别仪添加
	 */
	@RequestMapping(value="/car/addcarDistinguish")
	public ModelAndView addcarDistinguish(
			 String flag,
			 @ModelAttribute CarDistinguish carDistinguish,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			mv.setViewName("car/carDistinguishAdd");
		}else{
			carDistinguishService.save(carDistinguish); 
			mv.setViewName("redirect:/car/carDistinguish");
		}
		return mv;
	}
	/**
	 * 车识别仪修改
	 */
	@RequestMapping(value="/car/updatecarDistinguish")
	public ModelAndView updatecarDistinguish(
			 String flag,
			 @ModelAttribute CarDistinguish carDistinguish,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarDistinguish target = carDistinguishService.selectById(carDistinguish.getId());
			mv.addObject("distinguish", target);
			mv.setViewName("car/carDistinguishUpdate");
		}else{
			carDistinguishService.update(carDistinguish);
			mv.setViewName("redirect:/car/carDistinguish");
		}
		return mv;
	}
	/**
	 * 车识别仪删除
	 */
	@RequestMapping(value="/car/deletecarDistinguish")
	 public ModelAndView deletecarDistinguish(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carDistinguishService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carDistinguish");
		return mv;
	}
	
	/**
	 * 出入口管理
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carPassageway")
	 public ModelAndView carPassageway(Integer pageIndex,ModelAndView mv,@ModelAttribute CarPassageway carPassageway){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarPassageway> passageways = carPassagewayService.selectByPage(carPassageway, pageModel);
		mv.addObject("passageways", passageways);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("car/carPassageway");
		return mv;
	}
	
	/**
	 * 出入口添加
	 */
	@RequestMapping(value="/car/addcarPassageway")
	public ModelAndView addcarPassageway(
			 String flag,
			 @ModelAttribute CarPassageway carPassageway,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			
			//添加未被使用识别仪
			List<CarPassageway> carPassageways=carPassagewayService.selectAll();
			String dids="";
			for (CarPassageway passageway : carPassageways) {
				dids=dids+passageway.getDistinguish_ids()+",";
			}
			List<CarDistinguish> distinguishs=carDistinguishService.selectFillterIds(dids);
			mv.addObject("distinguishs", distinguishs);
			mv.setViewName("car/carPassagewayAdd");
		}else{
			carPassagewayService.save(carPassageway); 
			mv.setViewName("redirect:/car/carPassageway");
		}
		return mv;
	}
	/**
	 * 出入口修改
	 */
	@RequestMapping(value="/car/updatecarPassageway")
	public ModelAndView updatecarPassageway(
			 String flag,
			 @ModelAttribute CarPassageway carPassageway,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarPassageway target = carPassagewayService.selectById(carPassageway.getId());
			mv.addObject("carPassageway", target);
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			
			//添加本出入口的识别器
			List<CarDistinguish> exitsdistinguishs=carDistinguishService.selectByIds(target.getDistinguish_ids());
			mv.addObject("exitsdistinguishs", exitsdistinguishs);
			
			//添加未被使用识别仪
			List<CarPassageway> carPassageways=carPassagewayService.selectAll();
			String dids="";
			for (CarPassageway passageway : carPassageways) {
				dids=dids+passageway.getDistinguish_ids()+",";
			}
			List<CarDistinguish> distinguishs=carDistinguishService.selectFillterIds(dids);
			mv.addObject("distinguishs", distinguishs);
			
			mv.setViewName("car/carPassagewayUpdate");
		}else{
			carPassagewayService.update(carPassageway);
			mv.setViewName("redirect:/car/carPassageway");
		}
		return mv;
	}
	/**
	 * 出入口删除
	 */
	@RequestMapping(value="/car/deletecarPassageway")
	 public ModelAndView deletecarPassageway(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carPassagewayService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carPassageway");
		return mv;
	}
	
	/**
	 * 审核访问记录
	 * @param mv
	 * @return		
	 */
	@RequestMapping(value="/car/selectByParkid")
	@ResponseBody		
	public Object selectByParkid(HttpServletRequest request, HttpServletResponse response){
		String packid=request.getParameter("packid");
		List<CarPassageway> ways=carPassagewayService.selectByParkid(Integer.parseInt(packid));
		return ways;
	}
	
	
	/**
	 * 车辆绑定授权
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/car/carAuthority")
	 public ModelAndView carAuthority(Integer pageIndex,ModelAndView mv,@ModelAttribute CarAuthority carAuthority){
		PageModel pageModel = new PageModel();
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		List<CarAuthority> authoritys = carAuthorityService.selectByPage(carAuthority, pageModel);
		mv.addObject("authoritys", authoritys);
		mv.addObject("pageModel", pageModel);
		mv.setViewName("car/carAuthority");
		return mv;
	}
	
	/**
	 * 车辆授权添加
	 */
	@RequestMapping(value="/car/addcarAuthority")
	public ModelAndView addcarAuthority(
			 String flag,
			 @ModelAttribute CarAuthority carAuthority,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			//添加停车场
			List<CarPark> parks=carParkService.selectAll();
			mv.addObject("parks", parks);
			mv.setViewName("car/carAuthorityAdd");
		}else{
			String[] passageway_ids=request.getParameterValues("passageway_ids");
			for (String  passageway_id : passageway_ids) {
				carAuthority.setPassageway_id(Integer.parseInt(passageway_id));
				carAuthorityService.saveOrUpdate(carAuthority); 
			}
			mv.setViewName("redirect:/car/carAuthority");
		}
		return mv;
	}
	/**
	 * 车辆授权修改( 直接授权，不需要指定修改 )
	 */
	@RequestMapping(value="/car/updatecarAuthority")
	public ModelAndView updatecarAuthority(
			 String flag,
			 @ModelAttribute CarAuthority carAuthority,
			 ModelAndView mv,
			 HttpServletRequest request,
			 HttpServletResponse response){
		if(flag.equals("1")){
			CarAuthority target = carAuthorityService.selectById(carAuthority.getId());
			mv.addObject("authority", target);
			mv.setViewName("car/carAuthorityUpdate");
		}else{
			String[] passageway_ids=request.getParameterValues("passageway_ids");
			for (String  passageway_id : passageway_ids) {
				carAuthority.setPassageway_id(Integer.parseInt(passageway_id));
				carAuthorityService.saveOrUpdate(carAuthority); 
			}
			mv.setViewName("redirect:/car/carAuthority");
		}
		return mv;
	}
	/**
	 * 车辆授权删除
	 */
	@RequestMapping(value="/car/deletecarAuthority")
	 public ModelAndView deletecarAuthority(String ids,ModelAndView mv){
		String[] idArray = ids.split(",");
		for(String id : idArray){
			carAuthorityService.deleteById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/car/carAuthority");
		return mv;
	}
}