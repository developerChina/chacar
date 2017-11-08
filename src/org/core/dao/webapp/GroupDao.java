package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ELEVATORGROUPTABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.GroupSqlProvider;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.ElevatorGroup;

public interface GroupDao {
  //电梯分组的分页查询
	//数目
	@SelectProvider(type=GroupSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	//集合
	@SelectProvider(type=GroupSqlProvider.class,method="selectWhitParam")
	List<ElevatorGroup> selectByPage(Map<String, Object> params);

	//移除电梯分组
	@Delete(" delete from "+ELEVATORGROUPTABLE+" where egid = #{id} ")
	void EgroupDelByEGid(String id);
	
	//添加电梯分组
		//查询所属下级
	@SelectProvider(type=GroupSqlProvider.class,method="selectAll")
	List<Elevator> selectEGSubordinate();
	
	
	@SelectProvider(type=GroupSqlProvider.class,method="addStringtoEG")
	void addEtoEG( String ids, String egname, String uuid);
	
	@SelectProvider(type=GroupSqlProvider.class,method="selectElevatorByEGid")
	Elevator getElevatorByEGid(Integer id);
	
	@Select(" select * from "+ELEVATORGROUPTABLE+" where egid=#{id}")
	ElevatorGroup selectbyId(String id);
	
	@SelectProvider(type=GroupSqlProvider.class,method="updateEG")
	void updateEG(ElevatorGroup elevatorGroup);
	

}
