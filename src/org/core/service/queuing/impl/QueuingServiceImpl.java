package org.core.service.queuing.impl;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.core.dao.location.InoutDao;
import org.core.dao.queuing.QueuingDao;
import org.core.domain.car.CarDistinguish;
import org.core.domain.location.LocationInout;
import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.service.queuing.QueuingService;
import org.core.util.DateUtil;
import org.core.util.GenId;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 排队叫号系统服务层/业务逻辑层接口的实现类<br>
 * @see (QueuingService)
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("queuingService")
public class QueuingServiceImpl implements QueuingService {
	@Autowired
	private QueuingDao queuingDao;
	@Autowired
	private InoutDao inoutDao;
//1、卸货岛的业务逻辑层接口的实现
	@Override
	public List<Island> selectIByPage(Island island, PageModel pageModel) {
		Map<String,Object> params = new HashMap<>();
		params.put("island", island);
		int recordCount = queuingDao.countI(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Island> pageListI = queuingDao.pageSelectI(params);
		return pageListI;
	}
	@Override
	public void addI(Island island) {
		/*//H-->添加时如果卸货岛编号已经存在 执行修改
		Island AddtoUpdSelect = queuingDao.AddtoUpdSelect(island.getNo());
		
		if(AddtoUpdSelect!=null){
			island.setId(AddtoUpdSelect.getId());
			queuingDao.UpdI(island);
		}else{
			queuingDao.addI(island);
		}*/
		queuingDao.addI(island);
	}
	@Override
	public void delIsland(Integer id) {
		//删除卸货岛预留限制位置
		queuingDao.delIsland(id);
	}
	
	@Override
	public Island updateISel(Integer id) {
		return queuingDao.updateISel(id);
	}
	@Override
	public void UpdI(Island island) {
		/*//H-->修改时如果卸货岛编号不存在 执行添加
		Island AddtoUpdSelect = queuingDao.AddtoUpdSelect(island.getNo());
		
		if(AddtoUpdSelect!=null){
			queuingDao.UpdI(island);
		}else{
			queuingDao.addI(island);
		}*/
		queuingDao.UpdI(island);
	}
	
	
	@Override
	public Island selectIByNo(Integer No) {
		return queuingDao.getparts(No);
	}
	@Override
	public List<Island> selectIAll() {
		return queuingDao.selectIAll();
	}
	
//2、VIP队列的业务逻辑层接口的实现
	@Override
	public List<QueuingVip> selectVByPage(QueuingVip queuingVip, PageModel pageModel) {
		
		//VIP表中只存卸货岛编号  如何用卸货岛名称 模糊查VIP呢?
		
		String vague = queuingVip.getVagueiname();
		if(vague!=null && !"".equals(vague)){
			String nos = "";
			List<Island> vagueI = queuingDao.vagueI(vague);
			if(vagueI!=null&&vagueI.size()>0){
				for (Island Single : vagueI) {
					nos+=Single.getNo()+",";
				}
				//System.out.println(nos); 1,2,3,
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println(nos); 1,2,3
				queuingVip.setVagueiname(nos);
			}else{
				queuingVip.setVagueiname("000000");
			}
		}
		Map<String,Object> params = new HashMap<>();
		params.put("queuingVip", queuingVip);
		int recordCount = queuingDao.countV(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<QueuingVip> pageListV = queuingDao.pageSelectV(params);
		//将卸货岛封装到Vip队列里
		for (QueuingVip Vparts : pageListV) {
			Island myVpartsI = queuingDao.getparts(Vparts.getIsland_no());
			Vparts.setVpartsI(myVpartsI);
			List<String> SupplierList = queuingDao.getSupplier(Vparts.getCar_code());
			if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
				Vparts.setSupplier(SupplierList.get(0));
			}else{
				Vparts.setSupplier("");
			}
		}
		
		return pageListV;
	}
	@Override
	public List<Island> AddVgetI() {
		return queuingDao.AddVgetI();
	}
	
	//vip添加
	@Override
	public void addV(QueuingVip queuingVip) {
		
		if(queuingVip.getRemarks()==null||queuingVip.getRemarks().equals("")){
			queuingVip.setRemarks("手动添加急件");
		}
		//判断 根据车牌号查vip表  
		QueuingVip exv=queuingDao.selectVBycarno(queuingVip.getIsland_no(),queuingVip.getCar_code());
		if(exv==null){
			//根据车牌号查普通表 有值就删除了它 条件车牌号
			Ordinary exo=queuingDao.selectOBycarno(queuingVip.getIsland_no(),queuingVip.getCar_code());
			  if(exo!=null){
				  //将普通表重新排序
				  int selectId = queuingDao.delSort(queuingVip.getIsland_no(),queuingVip.getCar_code());
				  ordAgain(selectId);
				  //删除它
				  queuingDao.delByCar_code(queuingVip.getIsland_no(),queuingVip.getCar_code());
			  }
			//2排序
			 int max =vipAddSort(queuingVip.getIsland_no(),queuingVip.getQueue_number());
			 queuingVip.setQueue_number(max);
			//取号时间
			 Date date = new Date();
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time = format.format(date);
			 queuingVip.setTake_time( DateUtil.StringToDate(time, "yyyy-MM-dd HH:mm:ss"));
			//3执行添加
			queuingDao.addV(queuingVip);
		}
	}

//VIP 排序的方法
		//添加时排序
		public int vipAddSort(int no,int qn){
			String maxstring = queuingDao.getQueueMaxsByno(no);
			if(maxstring==null){
				return 1;
			}else{
				int maxint = queuingDao.getQueueMaxiByno(no);
				if(qn>(maxint+1)){
					return maxint+1;
				}else{
					List<QueuingVip> Sortlist = queuingDao.selectVIByQI(qn,no);
					for (QueuingVip insert : Sortlist) {
						int man = insert.getQueue_number()+1;
						queuingDao.updateQByid(man,insert.getId());
					}
					return qn;
				}
			}
		}	
	
	@Override
	public void delVip(Integer id) {
			//vipAgain(id);
		//4	执行删除
		queuingDao.delVip(id);
	}
	
	@Override
	public QueuingVip updateVSel(Integer vid) {
		return queuingDao.updateVSel(vid);
	}
	@Override
	public void UpdV(QueuingVip queuingVip) {

	}
	
//3、历史记录的业务逻辑层接口的实现
	@Override
	public List<History> selectHByPage(History history, PageModel pageModel,Date startDate, Date endDate) {
		String vague = history.getVagueiname();
		if(vague!=null && !"".equals(vague)){
			String nos = "";
			List<Island> vagueI = queuingDao.vagueI(vague);
			if(vagueI!=null&&vagueI.size()>0){
				for (Island Single : vagueI) {
					nos+=Single.getNo()+",";
				}
				//System.out.println(nos); 1,2,3,
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println(nos); 1,2,3
				history.setVagueiname(nos);
			}else{
				history.setVagueiname("000000");
			}
		}
		
		String vSupplier = history.getSupplier();
		if(vSupplier!=null && !"".equals(vSupplier)){
			String nos = "";
			List<String> carList = queuingDao.vagueCar_code(vSupplier);
			if(carList!=null&&carList.size()>0){
				
				for (String code : carList) {
					nos+="'"+code+"'"+",";
				}
				//System.out.println(nos);
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println(nos); 
				history.setSupplier(nos);
			}else{
				history.setSupplier("000000");
			}
		}
		
		Map<String,Object> params = new HashMap<>();
		params.put("history", history);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int recordCount = queuingDao.countH(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<History> pageListH = queuingDao.pageSelectH(params);
		//1将卸货岛封装到历史记录里 2计算时间差 3查到车的供货商 
		for (History Hparts : pageListH) {
			Island myVpartsI = queuingDao.getparts(Hparts.getIsland_no());
			Hparts.setHpartsI(myVpartsI);
			//计算卸货时长
			if(Hparts.getGoout_time()!=null&&Hparts.getComein_time()!=null){
				/*long between = (Hparts.getGoout_time().getTime()-Hparts.getComein_time().getTime())/1000;
				long hour1=between%(24*3600)/3600;
				long minute1=between%3600/60;
				long second1=between%60;
				Hparts.setReduce(""+hour1+"小时"+minute1+"分"+second1+"秒");*/
				String a = formatMiliLongToString(Hparts.getGoout_time().getTime()-Hparts.getComein_time().getTime());
				Hparts.setReduce(a);
			}
			//计算在场时长
			if(Hparts.getCominDate()!=null&&Hparts.getOutDate()!=null){
				/*long between = (Hparts.getOutDate().getTime()-Hparts.getCominDate().getTime())/1000;
				long hour1=between%(24*3600)/3600;
				long minute1=between%3600/60;
				long second1=between%60;
				Hparts.setPlant(""+hour1+"小时"+minute1+"分"+second1+"秒");*/
				String a = formatMiliLongToString(Hparts.getOutDate().getTime()-Hparts.getCominDate().getTime());
				Hparts.setPlant(a);
			}
			//预防查出多条  供应商名称  
			List<String> SupplierList = queuingDao.getSupplier(Hparts.getCar_code());
			if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
				Hparts.setSupplier(SupplierList.get(0));
			}else{
				Hparts.setSupplier("");
			}
			//车辆类型
			List<String> vehicleTypeList = queuingDao.getVehicleTypeList(Hparts.getCar_code());
			if(vehicleTypeList!=null && vehicleTypeList.size()>0 && vehicleTypeList.get(0)!=null && !vehicleTypeList.get(0).equals("")){
				Hparts.setVehicleType(vehicleTypeList.get(0));
			}else{
				Hparts.setVehicleType("");
			}
		}
		return pageListH;
	}
	
	public static String formatMiliLongToString(Long mili) {
		if (0 == mili || null == mili) {
			return "00:00:00";
		}
		Date date = new Date(mili);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("UTC+8"));
		return format.format(date);
	}
	
	
	
//4、支持其他业务的实现
	//往历史记录表写数据
	@Override
	public void addH(History history) {
		//1、计算操作的时间 例如 驶入到驶出历经17分35秒
		history.setId(GenId.UUID());
		//2、执行添加
		queuingDao.addH(history);
	}
	//往普通队列表写数据
	@Override
	public void addO(Ordinary ordinary) {
		//取号时间
		 Date date = new Date();
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = format.format(date);
		 ordinary.setTake_time( DateUtil.StringToDate(time, "yyyy-MM-dd HH:mm:ss"));
		queuingDao.addO(ordinary);
	}

	//删除时重新排序
	public void vipAgain(Integer id) {
		QueuingVip updateVSel = queuingDao.updateVSel(id);
		
		List<QueuingVip> vipAgainList= queuingDao.selectListBybig(updateVSel.getQueue_number(),updateVSel.getIsland_no());
		if(vipAgainList.size()>0){
			for (QueuingVip AgainVip : vipAgainList) {
				int man = AgainVip.getQueue_number()-1;
				queuingDao.updateQByid(man,AgainVip.getId());
			}
		}
	}
	@Override
	public Ordinary selectMaxOByLand(int landno) {
		return queuingDao.selectMaxOByLand(landno);
	}
	@Override
	public Ordinary selectOBycarno(int landno,String carno) {
		return queuingDao.selectOBycarno(landno,carno);
	}
	@Override
	public List<QueuingVip> selectVAll(int landno) {
		return queuingDao.selectVAll(landno);
	}
	@Override
	public List<Ordinary> selectOAll(int landno) {
		return queuingDao.selectOAll(landno);
	}

	
//4、普通表的业务逻辑层接口的实现
		@Override
		public List<Ordinary> selectOByPage(Ordinary ordinary, PageModel pageModel) {
			String vague = ordinary.getVagueiname();
			if(vague!=null && !"".equals(vague)){
				String nos = "";
				List<Island> vagueI = queuingDao.vagueI(vague);
				if(vagueI!=null&&vagueI.size()>0){
					for (Island Single : vagueI) {
						nos+=Single.getNo()+",";
					}
					//System.out.println(nos); 1,2,3,
					nos = nos.substring(0,nos.length() - 1);
					//System.out.println(nos); 1,2,3
					ordinary.setVagueiname(nos);
				}else{
					ordinary.setVagueiname("000000");
				}
			}
			Map<String,Object> params = new HashMap<>();
			params.put("ordinary", ordinary);
			int recordCount = queuingDao.countO(params);
			pageModel.setRecordCount(recordCount);
			if(recordCount > 0){
			    params.put("pageModel", pageModel);
		    }
			List<Ordinary> pageListO = queuingDao.pageSelectO(params);
			//将卸货岛封装到历史记录里
			for (Ordinary Oparts : pageListO) {
				Island myVpartsI = queuingDao.getparts(Oparts.getIsland_no());
				Oparts.setOpartsI(myVpartsI);
				List<String> SupplierList = queuingDao.getSupplier(Oparts.getCar_code());
				if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
					Oparts.setSupplier(SupplierList.get(0));
				}else{
					Oparts.setSupplier("");
				}
			}
			return pageListO;
		}
		
		@Override
		public Ordinary updateOSel(Integer id) {
			
			return queuingDao.updateOSel(id);
		}
		
		 
		//删除
		@Override
		public void delOrdinary(Integer id) {
			//ordAgain(id);
			queuingDao.delOrdinary(id);
		}
		//修改
		@Override
		public void UpdO(Ordinary ordinary) {
				//ordUpdSort(ordinary);
		}

		//添加时排序
			/*
			 * 传入岛号查询普通表
			 * 
			 * if 查出的为null 普通队列没有在此岛排车 返回1
			 * 	else 普通队列在此岛排车了 再查int 最大值+1
			 * 
			 * */
		public int ordAddSort(int no,int qn){
			String maxstring = queuingDao.getQueueOMaxs(no);
			if(maxstring==null){
				return 1;
			}else{
				int maxint = queuingDao.getQueueOMaxi(no);
				/*if(qn>(maxint+1)){
					return maxint+1;
				}else{
					List<Ordinary> Sortlist = queuingDao.selectOrdIByQI(qn,no);
					for (Ordinary insert : Sortlist) {
						int man = insert.getQueue_number()+1;
						queuingDao.updOrdQByid(man,insert.getId());
					}
					return qn;
				}*/
				return maxint+1;
			}
		}
		//删除时排序
		/*
		 * 根据id查出这个要删除的普通对象 ----->传入id
		 * 	进而拿到它的岛号+当前位置
		 * 	根据这条件查出集合
		 * 	updateOSel.getQueue_number() 当前位置
		 * 	updateOSel.getIsland_no() 	岛编号
		 * 数据库中的 ：
		 * 			排序列>当前位置 and 岛列=岛编号
		 * 查询出的集合进行修改其当前位置排序  4-3 3-2 
		 * 		AgainOrd.getQueue_number()-1
		 * where id=(集合.对象.id)
		 * 
		 * 不返回值 之间修改之后 删除
		 * */
		public void ordAgain(Integer id) {
			Ordinary updateOSel = queuingDao.updateOSel(id);
			
			List<Ordinary> ordAgainList= queuingDao.OselectListBybig(updateOSel.getQueue_number(),updateOSel.getIsland_no());
			if(ordAgainList.size()>0){
				for (Ordinary AgainOrd : ordAgainList) {
					int man = AgainOrd.getQueue_number()-1;
					queuingDao.updateOQByid(man,AgainOrd.getId());
				}
			}
			
		}
		
		//总控平台的添加普通列表
			//判断 根据车牌号查俩张表  都为空才执行
		@Override
		public void addConteollerO(Ordinary ordinary) {
			Ordinary exo=queuingDao.selectOBycarno(ordinary.getIsland_no(),ordinary.getCar_code());
			QueuingVip exv=queuingDao.selectVBycarno(ordinary.getIsland_no(),ordinary.getCar_code());
			if(exo==null&&exv==null){
				int max =ordAddSort(ordinary.getIsland_no(),ordinary.getQueue_number());
			 	ordinary.setQueue_number(max);
			 	if(ordinary.getRemarks()==null||ordinary.getRemarks().equals("")){
					ordinary.setRemarks("手动添加普通号");
				}
			 	//取号时间
				 Date date = new Date();
				 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 String time = format.format(date);
				 ordinary.setTake_time( DateUtil.StringToDate(time, "yyyy-MM-dd HH:mm:ss"));
			 	 queuingDao.addO(ordinary);
			}
		}
//添加验证的友好提示
		@Override
		public String addValidate(int landno,String car_code,String judge) {
			String result="";
			//vip表
			if(judge.equals("1")){
				QueuingVip exv=queuingDao.selectVBycarno(landno,car_code);
				if(exv==null){
					//没有这个车牌
					result="";
				}else{
					//有这个车牌
					result="此车已经在当前队列里，请勿重复排队";
				}
			}
			//普通表
			if(judge.equals("2")){
				Ordinary exo=queuingDao.selectOBycarno(landno,car_code);
				if(exo==null){
					QueuingVip exv=queuingDao.selectVBycarno(landno,car_code);
					//没有这个车牌
					if(exv==null){
						result="";
					}else{
						result="此车已经在急件队列里，请勿重复排队";
					}
				}else{
					//有这个车牌
					result="此车已经在当前队列里，请勿重复排队";
				}
			}
			return result;
		}
		@Override
		public String IaddValidate(String no,String term) {
			String result="";
		if(term.equals("1")){
			//添加时的判断
			Island exi = queuingDao.selectOByNoToI(no);
			if(exi==null){
				result="";
			}else{
				result="编号重复 请勿重复添加";
			}
		}	
			return result;
		}
		
		@Override
		public History selectIng(int landno) {
			return queuingDao.selectIng(landno);
		}
		public String position(int island_no, int queue_number) {

			String resultQueue="";
			String maxstring = queuingDao.getQueueMaxsByno(island_no);
			if(maxstring==null){
				if(queue_number>1){
					resultQueue="此岛无车辆排序，系统默认排序1 请确认";
				}else{
					resultQueue="";
				}
			}else{
				int maxint = queuingDao.getQueueMaxiByno(island_no);
				if(queue_number>(maxint+1)){
					resultQueue="您选的位置超出最大值，系统默认排序当前最大 请确认";
				}else{
					resultQueue="";
				}
			}
			
			return resultQueue;
		}
		@Override
		public String plain(int island_no, int queue_number) {
			
			String resultQueue="";
			String maxstring = queuingDao.getQueueOMaxs(island_no);
			if(maxstring==null){
				if(queue_number>1){
					resultQueue="此岛无车辆排序，系统默认排序1 请确认";
				}else{
					resultQueue="";
				}
			}else{
				int maxint = queuingDao.getQueueOMaxi(island_no);
				if(queue_number>(maxint+1)){
					resultQueue="您选的位置超出最大值,系统默认排序为当前最大  请确认";
				}else{
					resultQueue="";
				}
			}
			return resultQueue;
		}
		@Override
		public QueuingVip selectVBycarno(int landno, String carno) {
			return queuingDao.selectVBycarno(landno,carno);
		}
		@Override
		public void updateO(Ordinary ordinary) {
			//取号时间
			 Date date = new Date();
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String time = format.format(date);
			 ordinary.setTake_time( DateUtil.StringToDate(time, "yyyy-MM-dd HH:mm:ss"));
			queuingDao.UpdO(ordinary);
		}
		
		//现场统计
		@Override
		public List<Map<String, Object>> selectScene() {
			List<Island> lands = queuingDao.selectIAll();
			List<Map<String, Object>> list=new ArrayList<>();
			for (Island land : lands) {
				Map<String, Object> map= new HashMap<>();
				map.put("iname",land.getIname());
				List<QueuingVip> listV = queuingDao.selectVAll(land.getNo());
				List<Ordinary> listO = queuingDao.selectOAll(land.getNo());
				//总数 急件数 普通数
				int Sum = listV.size()+listO.size();
				int VipSum = listV.size();
				int OSum =  listO.size();
				map.put("all", Sum);
				map.put("vip", VipSum);
				map.put("o", OSum);
				
				List<QueuingVip> limitV = queuingDao.selectVlimit(land.getNo());
				List<Ordinary> limitO = queuingDao.selectOlimit(land.getNo());
				
					//供应商 车牌号 标记  
					for (Ordinary Oparts : limitO) {
							List<String> SupplierList = queuingDao.getSupplier(Oparts.getCar_code());
							if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
								Oparts.setSupplier(SupplierList.get(0).replace(" ", ""));
							}else{
								Oparts.setSupplier("");
							}
						}
					map.put("Ordinarys", limitO);
					for (QueuingVip Vparts : limitV) {
							List<String> SupplierList = queuingDao.getSupplier(Vparts.getCar_code());
							if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
								Vparts.setSupplier(SupplierList.get(0).replace(" ", ""));
							}else{
								Vparts.setSupplier("");
							}
						}
					map.put("QueuingVips", limitV);
				
				list.add(map);
			}
			return list;
		}
		
		@Override
		public int selectSum() {
			int sum =queuingDao.selectVipSum()+queuingDao.selectOSum();
			return sum;
		}
		@Override
		public int selectVipSum() {
			return queuingDao.selectVipSum();
		}
		@Override
		public int selectOSum() {
			return queuingDao.selectOSum();
		}
		
		//进场未排队
		/*
		 * 查询所有进出厂记录  List<LocationInout> InoutAll = queuingDao.selectInoutAll();
		 * 根据 进出记录的车牌号和入厂时间 俩个条件 同历史记录进行判断
		 * 判断条件 车牌号相等  --->车牌对应的历史记录的进岛时间大于车牌对应的入厂时间 			
		 * 条件成立则：
		 * 将主键存在字符串中 提供查询
		 * 
		 */
		@Override
		public List<LocationInout> findInout(LocationInout locationInout, PageModel pageModel, Date startDate,
				Date endDate) {
			String vSupplier = locationInout.getSupplier();
			if(vSupplier!=null && !"".equals(vSupplier)){
				String nos = "";
				List<String> carList = queuingDao.vagueCar_code(vSupplier);
				if(carList!=null&&carList.size()>0){
					
					for (String code : carList) {
						nos+="'"+code+"'"+",";
					}
					//System.out.println(nos);
					nos = nos.substring(0,nos.length() - 1);
					//System.out.println(nos); 
					locationInout.setSupplier(nos);
				}else{
					locationInout.setSupplier("000000");
				}
			}
			//将主键当成一个条件
			List<LocationInout> InoutAll = queuingDao.selectInoutAll();
			for (LocationInout  Inout: InoutAll) {
				int Inoutcount = queuingDao.getCarByCondition(Inout.getVehicleCode(),Inout.getCominDate());
				if(Inoutcount==0){
					String nos = "";
					nos+=Inout.getId()+",";
					nos = nos.substring(0,nos.length() - 1);
					locationInout.setIds(nos);
				}
			}
			
			/** 当前需要分页的总数据条数  */
			Map<String,Object> params = new HashMap<>();
			params.put("locationInout", locationInout);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			int recordCount = queuingDao.countT(params);
			
			pageModel.setRecordCount(recordCount);
			if(recordCount > 0){
		        /** 开始分页查询数据：查询第几页的数据 */
				params.put("pageModel", pageModel);
		    }
			List<LocationInout> locationInouts = queuingDao.selectByTPagegy(params);
			
			for (LocationInout entity : locationInouts) {
				List<String> SupplierList = queuingDao.getSupplier(entity.getVehicleCode());
				if(SupplierList!=null && SupplierList.size()>0 && SupplierList.get(0)!=null && !SupplierList.get(0).equals("")){
					entity.setSupplier(SupplierList.get(0));
				}else{
					entity.setSupplier("");
				}
				
				if(entity.getServerInIp()!=null&&!"".equals(entity.getServerInIp())){
						CarDistinguish camera = inoutDao.getCamera(entity.getServerInIp());
						if(camera!=null){
						    entity.setServerInName(camera.getName());
						}
					}
				if(entity.getServerOutIp()!=null&&!"".equals(entity.getServerOutIp())){
					CarDistinguish camera = inoutDao.getCamera(entity.getServerOutIp());
					if(camera!=null){
						entity.setServerOutName(camera.getName());
					}
				}
				
			}
			return locationInouts;
		}
		
		
		
		
}
