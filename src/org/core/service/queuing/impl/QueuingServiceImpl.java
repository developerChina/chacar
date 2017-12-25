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
		
		 int max =vipAddSort();
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
		queuingDao.UpdV(queuingVip);
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
		
		//2、执行添加
		queuingDao.addH(history);
	}
	//往普通队列表写数据
	@Override
	public void addO(Ordinary ordinary) {
		//执行添加
		queuingDao.addO(ordinary);
	}
	
	
//VIP 排序的方法
	
	//添加时排序
	public int vipAddSort(){
		String maxstring = queuingDao.getQueueMaxs();
		System.out.println(maxstring);
		if(maxstring==null){
			return 1;
		}else{
			int maxint = queuingDao.getQueueMaxi();
			return maxint+1;
		}
	}

	//删除时重新排序
	public void vipAgain(Integer id) {
		QueuingVip updateVSel = queuingDao.updateVSel(id);
		
		List<QueuingVip> vipAgainList= queuingDao.selectListBybig(updateVSel.getQueue_number()) ;
		if(vipAgainList.size()>0){
			for (QueuingVip AgainVip : vipAgainList) {
				int man = AgainVip.getQueue_number()-1;
				System.out.println(man);
				AgainVip.setQueue_number(man);
				System.out.println("看改变了没有"+AgainVip.getQueue_number());
			}
		}
		
		
	}
	
	
	
	
}
