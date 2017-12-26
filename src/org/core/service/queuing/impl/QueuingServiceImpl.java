package org.core.service.queuing.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.queuing.QueuingDao;
import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.service.queuing.QueuingService;
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
		//H-->添加时如果卸货岛编号已经存在 执行修改
		Island AddtoUpdSelect = queuingDao.AddtoUpdSelect(island.getNo());
		
		if(AddtoUpdSelect!=null){
			island.setId(AddtoUpdSelect.getId());
			queuingDao.UpdI(island);
		}else{
			queuingDao.addI(island);
		}
		
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
		//H-->修改时如果卸货岛编号不存在 执行添加
		Island AddtoUpdSelect = queuingDao.AddtoUpdSelect(island.getNo());
		
		if(AddtoUpdSelect!=null){
			queuingDao.UpdI(island);
		}else{
			queuingDao.addI(island);
		}
		
		
		
	}
	
	
	@Override
	public Island selectByNo(Integer No) {
		return queuingDao.getparts(No);
	}
	
//2、VIP队列的业务逻辑层接口的实现
	@Override
	public List<QueuingVip> selectVByPage(QueuingVip queuingVip, PageModel pageModel) {
		
		//VIP表中只存卸货岛编号  如何用卸货岛名称 模糊查VIP呢?
		
		String vague = queuingVip.getVagueiname();
		if(vague!=null && !"".equals(vague)){
			String nos = "";
			List<Island> vagueI = queuingDao.vagueI(vague);
			for (Island Single : vagueI) {
				nos+=Single.getNo()+",";
			}
			//System.out.println(nos); 1,2,3,
			nos = nos.substring(0,nos.length() - 1);
			//System.out.println(nos); 1,2,3
			queuingVip.setVagueiname(nos);
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
		}
		
		return pageListV;
	}
	@Override
	public List<Island> AddVgetI() {
		return queuingDao.AddVgetI();
	}
	
	
	
	
	
	@Override
	public void addV(QueuingVip queuingVip) {
		//1添加前判断 如果普通表里也有 说明自己拍过队将普通表里的删除
			//删除的条件是 卸货岛编号和车牌号 
			//一个车牌能同时排好几个卸货岛吗
		//2排序
		
		 int max =vipAddSort(queuingVip.getIsland_no());
		 queuingVip.setQueue_number(max);
		 
		//3执行添加
		queuingDao.addV(queuingVip);
	}

	@Override
	public void delVip(Integer id) {
		//1	删除之后将其重新添回之前队列
			//误操作添到vip 再删除 岂不白排了
		//2	之前队列的排序要不要重新弄回去？
		//3 重新排序
			vipAgain(id);
		//4	执行删除
		queuingDao.delVip(id);
		
		
	}
	
	@Override
	public QueuingVip updateVSel(Integer vid) {
		
		return queuingDao.updateVSel(vid);
	}
	
	@Override
	public void UpdV(QueuingVip queuingVip) {
		// 修改那排序怎么玩！
		
		//执行修改
		vipUpd(queuingVip);
	}
	
//3、历史记录的业务逻辑层接口的实现
	@Override
	public List<History> selectHByPage(History history, PageModel pageModel) {
		
		String vague = history.getVagueiname();
		if(vague!=null && !"".equals(vague)){
			String nos = "";
			List<Island> vagueI = queuingDao.vagueI(vague);
			for (Island Single : vagueI) {
				nos+=Single.getNo()+",";
			}
			//System.out.println(nos); 1,2,3,
			nos = nos.substring(0,nos.length() - 1);
			//System.out.println(nos); 1,2,3
			history.setVagueiname(nos);
		}
		
		
		Map<String,Object> params = new HashMap<>();
		params.put("history", history);
		int recordCount = queuingDao.countH(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<History> pageListH = queuingDao.pageSelectH(params);
		//将卸货岛封装到历史记录里
		for (History Hparts : pageListH) {
			Island myVpartsI = queuingDao.getparts(Hparts.getIsland_no());
			Hparts.setHpartsI(myVpartsI);
		}
		return pageListH;
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
		Ordinary exo=queuingDao.selectOBycarno(ordinary.getCar_code());
		QueuingVip exv=queuingDao.selectVBycarno(ordinary.getCar_code());
		if(exo!=null&&exv!=null){
			queuingDao.addO(ordinary);
		}
	}
	
	
//VIP 排序的方法
	
	//添加时排序
	public int vipAddSort(int no){
		String maxstring = queuingDao.getQueueMaxsByno(no);
		if(maxstring==null){
			return 1;
		}else{
			int maxint = queuingDao.getQueueMaxiByno(no);
			return maxint+1;
		}
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
	public Ordinary selectMaxOByLand(int landid) {
		return queuingDao.selectMaxOByLand(landid);
	}
	@Override
	public Ordinary selectOBycarno(String carno) {
		return queuingDao.selectOBycarno(carno);
	}
	@Override
	public List<QueuingVip> selectVAll(int landid) {
		return queuingDao.selectVAll(landid);
	}
	@Override
	public List<Ordinary> selectOAll(int landid) {
		return queuingDao.selectOAll(landid);
	}
//VIP修改时重新排序
	public void vipUpd(QueuingVip queuingVip) {

		QueuingVip updateVSel = queuingDao.updateVSel(queuingVip.getId());
		//QFront 修改以前的位置 IFront 修改以前的岛号
		int QFront=updateVSel.getQueue_number();
		int IFront=updateVSel.getIsland_no();
		//QAfter 修改以后的位置 IAfter 修改以后的岛号
		int	QAfter=queuingVip.getQueue_number();
		int IAfter=queuingVip.getIsland_no();
		
		//1--->岛没变
			if(IFront==IAfter){
				//判断位置改变了没有 1没变 2变了
				//之前表最大值
				int maxint = queuingDao.getQueueMaxiByno(IFront);
				if(QAfter==QFront){
						queuingDao.UpdV(queuingVip);
				}else{
					//1-2-1从小往大改  比它大的都-1
					if(QFront<QAfter){
						
						List<QueuingVip> Sortlist = queuingDao.selectVipByQI(QFront,IFront);
						for (QueuingVip small : Sortlist) {
							int man = small.getQueue_number()-1;
							queuingDao.updateQByid(man,small.getId());
						}
					}
					//1-2-2从大往小改  比它小的比修改以后大于等于的  都+1！  
					if(QFront>QAfter){
						List<QueuingVip> Sortlist = queuingDao.selListByetc(QFront,IFront,QAfter);
						for (QueuingVip big : Sortlist) {
							int man = big.getQueue_number()+1;
							queuingDao.updateQByid(man,big.getId());
						}
					}
					//执行修改自己  1改4那没问题 1改100 改到4
					if(QAfter>maxint){
						queuingVip.setQueue_number(maxint);
						queuingDao.UpdV(queuingVip);
					}else{
						queuingDao.UpdV(queuingVip);
					}
				}
				
			}
	//2--->岛变
	if(IFront!=IAfter){
		//当前岛有没有排队 
		String maxstring = queuingDao.getQueueMaxsByno(IAfter);
			//1没有排队  新添一个 删掉以前的 保证一条
		if(maxstring==null){
			addV(queuingVip);
			delVip(queuingVip.getId());
		}else{
			//2有排队 插队 条件：修改之后的岛号 位置
			int maxint = queuingDao.getQueueMaxiByno(IAfter);
			if(QAfter>maxint){
				queuingVip.setQueue_number(maxint+1);
				queuingDao.UpdV(queuingVip);
				//修改以前的排序
				List<QueuingVip> Sortlist = queuingDao.selectListBybig(QFront,IFront);
				for (QueuingVip small : Sortlist) {
					int man = small.getQueue_number()-1;
					queuingDao.updateQByid(man,small.getId());
				}
			}else{
				List<QueuingVip> Sortlist = queuingDao.selectVIByQI(QAfter,IAfter);
				for (QueuingVip insert : Sortlist) {
					int man = insert.getQueue_number()+1;
					queuingDao.updateQByid(man,insert.getId());
				}
				//修改以前的排序
				List<QueuingVip> SortList = queuingDao.selectListBybig(QFront,IFront);
				for (QueuingVip small : SortList) {
					int man = small.getQueue_number()-1;
					queuingDao.updateQByid(man,small.getId());
				}
				queuingDao.UpdV(queuingVip);
			}
			
		}
	}

	}
	
//4、普通表的业务逻辑层接口的实现
		@Override
		public List<Ordinary> selectOByPage(Ordinary ordinary, PageModel pageModel) {
			
			String vague = ordinary.getVagueiname();
			if(vague!=null && !"".equals(vague)){
				String nos = "";
				List<Island> vagueI = queuingDao.vagueI(vague);
				for (Island Single : vagueI) {
					nos+=Single.getNo()+",";
				}
				//System.out.println(nos); 1,2,3,
				nos = nos.substring(0,nos.length() - 1);
				//System.out.println(nos); 1,2,3
				ordinary.setVagueiname(nos);
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
			ordAgain(id);
			queuingDao.delOrdinary(id);
		}
		//修改
		@Override
		public void UpdO(Ordinary ordinary) {
			
			ordUpdSort(ordinary);
			
		}
		

		//添加时排序
			/*
			 * 传入岛号查询普通表
			 * 
			 * if 查出的为null 普通队列没有在此岛排车 返回1
			 * 	else 普通队列在此岛排车了 再查int 最大值+1
			 * */
		public int ordAddSort(int no){
			String maxstring = queuingDao.getQueueOMaxs(no);
			if(maxstring==null){
				return 1;
			}else{
				int maxint = queuingDao.getQueueOMaxi(no);
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
		
		//修改时重新 排序
		/*
		 * 传入你修改的对象
		 * id 修改后的位置 修改后的岛号
		 * 		根据id查出  之前的位置 之前的岛号
		 * 岛号是否变化   1没变 2变了
		 *  1--->岛没变
		 *  	判断位置改变了没有 1没变 2变了
		 *  	1-1
		 *  	执行修改 业务无关的修改 车牌号+原因 
		 * 		1-2 位置改变了 -->1从小往大改 2还是从大往小改
		 * 			1-2-1从小往大改  比它大的都-1  修改他人了
		 * 			1-2-2从大往小改  比它小的都+1 修改他人了
		 * 		执行修改  修改自己
		 * 	2--->岛变了
		 * 		当前岛有没有排队 1没有 2 有
		 * 		2-1没有排队 不用管它位置 新添加(添加那块做了判断 null 则位置重新赋值1)
		 * 		直接新添加一个  根据id删除之前的
		 * 		2-2有排队 插队
		 * 		比修改之后的值大的都加1 根据 修改之后的岛号 位置>=修改以后的值
		 * 		修改自己
		 * 
		 * */
		public void ordUpdSort(Ordinary ordinary) {
			Ordinary updateOSel = queuingDao.updateOSel(ordinary.getId());
			//QFront 修改以前的位置 IFront 修改以前的岛号
			int QFront=updateOSel.getQueue_number();
			int IFront=updateOSel.getIsland_no();
			//QAfter 修改以后的位置 IAfter 修改以后的岛号
			int	QAfter=ordinary.getQueue_number();
			int IAfter=ordinary.getIsland_no();
			
			//1--->岛没变
				if(IFront==IAfter){
					//判断位置改变了没有 1没变 2变了
					//之前表最大值
					int maxint = queuingDao.getQueueOMaxi(IFront);
					if(QAfter==QFront){
							queuingDao.UpdO(ordinary);
					}else{
						//1-2-1从小往大改  比它大的都-1
						if(QFront<QAfter){
							
							List<Ordinary> Sortlist = queuingDao.selectOrdByQI(QFront,IFront);
							for (Ordinary small : Sortlist) {
								int man = small.getQueue_number()-1;
								queuingDao.updOrdQByid(man,small.getId());
							}
						}
						//1-2-2从大往小改  比它小的比修改以后大于等于的  都+1  
						if(QFront>QAfter){
							List<Ordinary> Sortlist = queuingDao.selectOrdAByQI(QFront,IFront,QAfter);
							for (Ordinary big : Sortlist) {
								int man = big.getQueue_number()+1;
								queuingDao.updOrdQByid(man,big.getId());
							}
						}
						//执行修改自己  1改4那没问题 1改100 改到4
						if(QAfter>maxint){
							ordinary.setQueue_number(maxint);
							queuingDao.UpdO(ordinary);
						}else{
							queuingDao.UpdO(ordinary);
						}
					}
					
				}
		//2--->岛变
		if(IFront!=IAfter){
			//当前岛有没有排队 
			String maxstring = queuingDao.getQueueOMaxs(IAfter);
				//1没有排队  新添一个 删掉以前的 保证一条
			if(maxstring==null){
				addO(ordinary);
				delOrdinary(ordinary.getId());
			}else{
				//2有排队 插队 条件：修改之后的岛号 位置
				int maxint = queuingDao.getQueueOMaxi(IAfter);
				if(QAfter>maxint){
					ordinary.setQueue_number(maxint+1);
					queuingDao.UpdO(ordinary);
					//修改以前的排序
					List<Ordinary> Sortlist = queuingDao.selectOrdByQI(QFront,IFront);
					for (Ordinary small : Sortlist) {
						int man = small.getQueue_number()-1;
						queuingDao.updOrdQByid(man,small.getId());
					}
				}else{
					List<Ordinary> Sortlist = queuingDao.selectOrdIByQI(QAfter,IAfter);
					for (Ordinary insert : Sortlist) {
						int man = insert.getQueue_number()+1;
						queuingDao.updOrdQByid(man,insert.getId());
					}
					//修改以前的排序
					List<Ordinary> SortList = queuingDao.selectOrdByQI(QFront,IFront);
					for (Ordinary small : SortList) {
						int man = small.getQueue_number()-1;
						queuingDao.updOrdQByid(man,small.getId());
					}
					queuingDao.UpdO(ordinary);
				}
				
			}
		}
	}
}
