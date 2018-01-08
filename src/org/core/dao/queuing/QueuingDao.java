package org.core.dao.queuing;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
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
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="addV")
	void addV(QueuingVip queuingVip);
	
	@Delete(" delete from "+QueuingVip.tableName+" where id = #{id} ")
	void delVip(Integer id);
	
	@Select("select * from "+QueuingVip.tableName+" where queue_number > #{arg0} and island_no = #{arg1}")
	List<QueuingVip> selectListBybig(int queue_number, int island_no);
	
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
	
	@Select(" select * from "+Ordinary.tableName+" where island_no= #{landno} order by queue_number desc limit 1")
	Ordinary selectMaxOByLand(@Param("landno")int landno);

	@Select(" select * from "+Ordinary.tableName+" where island_no= #{landno} and car_code= #{carno} ")
	Ordinary selectOBycarno(@Param("landno")int landno,@Param("carno")String carno);
	
	@Select("select * from "+QueuingVip.tableName+" where island_no= #{landno} order by queue_number")
	List<QueuingVip> selectVAll(int landno);

	@Select(" select * from "+QueuingVip.tableName+" where island_no= #{landno} and car_code= #{carno} ")
	QueuingVip selectVBycarno(@Param("landno")int landno,@Param("carno")String carno);

	@Select("select * from "+Ordinary.tableName+" where island_no= #{landno} order by queue_number")
	List<Ordinary> selectOAll(int landno);
	
//普通队列表
	
	@SelectProvider(type=QueuingAuthorityProvider.class,method="countO")
	int countO(Map<String, Object> params);

	@SelectProvider(type=QueuingAuthorityProvider.class,method="pageSelectO")
	List<Ordinary> pageSelectO(Map<String, Object> params);
	    
	@Delete(" delete from "+Ordinary.tableName+" where id = #{id} ")
	void delOrdinary(Integer id);

	@Select("select * from "+Ordinary.tableName+" where id = #{id}")
	Ordinary updateOSel(Integer id);

	@SelectProvider(type=QueuingAuthorityProvider.class,method="UpdO")
	void UpdO(Ordinary ordinary);
//根据岛号查的 先String 后int
	@Select("select max(queue_number) from "+Ordinary.tableName+" where island_no = #{no} ")
	String getQueueOMaxs(int no);
	
	@Select("select max(queue_number) from "+Ordinary.tableName+" where island_no = #{no} ")
	int getQueueOMaxi(int no);
	
	
	@Select("select * from "+Ordinary.tableName+" where queue_number > #{arg0} and island_no = #{arg1}")
	List<Ordinary> OselectListBybig(int queue_number, int island_no);

	@Update("update "+Ordinary.tableName+" set queue_number = #{arg0} where id=#{arg1}")
	void updateOQByid(int man, int id);

	@Select("select * from "+Ordinary.tableName+" where queue_number > #{arg0} and island_no = #{arg1}")
	List<Ordinary> selectOrdByQI(int qFront, int iFront);

	@Update("update "+Ordinary.tableName+" set queue_number = #{arg0} where id=#{arg1}")
	void updOrdQByid(int man, int id);

	@Select("select * from "+Ordinary.tableName+" where queue_number < #{arg0} and queue_number >= #{arg2} and island_no = #{arg1}")
	List<Ordinary> selectOrdAByQI(int qFront, int iFront, int qAfter);

	@Select("select * from "+Ordinary.tableName+" where queue_number >= #{arg0} and island_no = #{arg1}")
	List<Ordinary> selectOrdIByQI(int qAfter, int iAfter);

//vip重新排序的之前的不要了
	
	@Select("select * from "+QueuingVip.tableName+" where queue_number > #{arg0} and island_no = #{arg1}")
	List<QueuingVip> selectVipByQI(int qFront, int iFront);

	@Update("update "+QueuingVip.tableName+" set queue_number = #{arg0} where id=#{arg1}")
	void updateQByid(int man, int id);

	@Select("select * from "+QueuingVip.tableName+" where queue_number < #{arg0} and queue_number >= #{arg2} and island_no = #{arg1}")
	List<QueuingVip> selListByetc(int qFront, int iFront, int qAfter);
	

	
	@Select("select * from "+QueuingVip.tableName+" where queue_number > #{arg0} and queue_number <= #{arg2} and island_no = #{arg1}")
	List<QueuingVip> selListBymax(int queue_number, int island_no, int number);
	
	

	@Select("select max(queue_number) from "+QueuingVip.tableName+" where island_no = #{no} ")
	String getQueueMaxsByno(Integer no);
	
	@Select("select max(queue_number) from "+QueuingVip.tableName+" where island_no = #{no} ")
	int getQueueMaxiByno(Integer no);

	
	@Select("select * from "+QueuingVip.tableName+" where queue_number >= #{arg0} and island_no = #{arg1}")
	List<QueuingVip> selectVIByQI(int qAfter, int iAfter);
//根据车牌号查普通表 有值就删除了它
	@Delete(" delete from "+Ordinary.tableName+" where car_code = #{car_code} ")
	void delByCar_code(String car_code);
//根据岛编号查卸货岛表有值就友好提示
	@Select("select * from "+Island.tableName+" where no = #{no} ")
	Island selectOByNoToI(String no);
	
	@Select("select id from "+Ordinary.tableName+" where car_code = #{car_code} ")
	int delSort(String car_code);

	@Select("select * from "+Island.tableName)
	List<Island> selectIAll();

	@Select("select * from "+History.tableName+" where island_no = #{landno} and goout_time is null order by comein_time desc limit 1")
	History selectIng(int landno);
}
