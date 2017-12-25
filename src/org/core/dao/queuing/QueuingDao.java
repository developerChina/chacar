package org.core.dao.queuing;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.queuing.provider.QueuingAuthorityProvider;
import org.core.domain.queuing.History;
import org.core.domain.queuing.Island;
import org.core.domain.queuing.Ordinary;
import org.core.domain.queuing.QueuingVip;

public interface QueuingDao {
//卸货岛
	@SelectProvider(type=QueuingAuthorityProvider.class,method="countI")
	int countI(Map<String, Object> params);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="pageSelectI")
	List<Island> pageSelectI(Map<String, Object> params);
	
	@Select("select * from "+Island.tableName+" where no = #{no}")
	Island AddtoUpdSelect(Integer no);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="addI")
	void addI(Island island);
	
	@Delete(" delete from "+Island.tableName+" where id = #{id} ")
	void delIsland(Integer id);
	
	@Select("select * from "+Island.tableName+" where id = #{id}")
	Island updateISel(Integer id);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="UpdI")
	void UpdI(Island island);
	
//VIP队列	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="countV")
	int countV(Map<String, Object> params);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="pageSelectV")
	List<QueuingVip> pageSelectV(Map<String, Object> params);
	
	@Select("select * from "+Island.tableName)
	List<Island> AddVgetI();
	
	@Select("select max(queue_number) from "+QueuingVip.tableName)
	String getQueueMaxs();
	
	@Select("select max(queue_number) from "+QueuingVip.tableName)
	int getQueueMaxi();
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="addV")
	void addV(QueuingVip queuingVip);
	
	@Delete(" delete from "+QueuingVip.tableName+" where id = #{id} ")
	void delVip(Integer id);
	
	@Select("select * from "+QueuingVip.tableName+" where queue_number > #{queue_number}")
	List<QueuingVip> selectListBybig(int queue_number);
	
	@Select("select * from "+QueuingVip.tableName+" where id = #{id}")
	QueuingVip updateVSel(Integer id);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="UpdV")
	void UpdV(QueuingVip queuingVip);
	
//历史记录表	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="countH")
	int countH(Map<String, Object> params);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="pageSelectH")
	List<History> pageSelectH(Map<String, Object> params);
//配件区	
	@Select("select * from "+Island.tableName+" where no = #{island_no}")
	Island getparts(int island_no);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="addH")
	void addH(History history);
	//模糊查根据卸货岛名称
	@Select(" select * from "+Island.tableName+" where iname LIKE CONCAT('%',#{vague},'%')")
	List<Island> vagueI(String vague);
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="addO")
	void addO(Ordinary ordinary);
	
	@Select(" select * from "+Ordinary.tableName+" where island_no= #{landid} order by queue_number desc limit 1")
	Ordinary selectMaxOByLand(int landid);

	@Select(" select * from "+Ordinary.tableName+" where car_code= #{carno} ")
	Ordinary selectOBycarno(String carno);
	
	@Select("select * from "+QueuingVip.tableName+" where island_no= #{landid} order by queue_number")
	List<QueuingVip> selectVAll(int landid);

	@Select(" select * from "+QueuingVip.tableName+" where car_code= #{carno} ")
	QueuingVip selectVBycarno(String car_code);

	@Select("select * from "+Ordinary.tableName+" where island_no= #{landid} order by queue_number")
	List<Ordinary> selectOAll(int landid);
	

	


	

	

	

	
	
	

}
