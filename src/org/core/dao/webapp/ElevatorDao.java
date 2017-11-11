package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ELEVATORTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.ElevatorDynaSqlProvider;
import org.core.domain.webapp.Elevator;

public interface ElevatorDao {

	//获得条数 有多少条 电梯的信息
	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	//分页查询 具体信息
	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="selectWhitParam")
	List<Elevator> selectByPage(Map<String, Object> params);

	// 根据id删除电梯
	@Delete(" delete from "+ELEVATORTABLE+" where elevatorID = #{id} ")
	void deleteById(int id);
	
	//动态插入电梯
	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="insertElevator")
	void save(Elevator elevator);
   
	//修改
	@Select("select * from "+ELEVATORTABLE+" where elevatorID = #{elevatorID}")
	Elevator selectById(Integer elevatorID);
	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="updateElevator")
	void update(Elevator elevator);
	
	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="selectEGisE")
	int selectEGisE(String id);

	@SelectProvider(type=ElevatorDynaSqlProvider.class,method="selectByIds")
	List<Elevator> selectByIds(String ids);

	
	
	
	
	
}
