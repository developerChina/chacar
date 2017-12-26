package org.core.service.queuing;

import java.util.List;

import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;
import org.core.util.tag.PageModel;

public interface QueuingService {
//卸货岛
	List<Island> selectIByPage(Island island, PageModel pageModel);

	void addI(Island island);

	void delIsland(Integer id);

	Island updateISel(Integer id);

	void UpdI(Island island);
	
	Island selectByNo(Integer No);
	//VIP队列
	List<QueuingVip> selectVByPage(QueuingVip queuingVip, PageModel pageModel);
	
	List<Island> AddVgetI();

	
	
	void addV(QueuingVip queuingVip);
	
	void delVip(Integer id);
	
	QueuingVip updateVSel(Integer id);
	
	void UpdV(QueuingVip queuingVip);
	
	
	
	
	
	//历史记录
	List<History> selectHByPage(History history, PageModel pageModel);
	//其他业务所用
	//往数据库中	历史记录表里写数据
	void addH(History history);
	//往数据库中	普通队列表里写数据
	void addO(Ordinary ordinary);
	Ordinary selectMaxOByLand(int landid);
	Ordinary selectOBycarno(String carno);

	List<QueuingVip> selectVAll(int landid);

	List<Ordinary> selectOAll(int landid);

	
//普通队列首页
	List<Ordinary> selectOByPage(Ordinary ordinary, PageModel pageModel);

	void delOrdinary(Integer id);

	Ordinary updateOSel(Integer id);

	void UpdO(Ordinary ordinary);
	

	
	
	
	
	
	
	
	
	
	
}
