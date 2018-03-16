package org.core.dao.webapp;

import java.util.List;
import java.util.Map;

import static org.core.util.GlobleConstants.BLACKLISTTABLE;
import static org.core.util.GlobleConstants.EMPLOYEETABLE;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.webapp.provider.TrajectoryEmpDynaSqlProvider;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.TrajectoryEmp;


public interface TrajectoryEmpDao {
	//动态查询
	@SelectProvider(type=TrajectoryEmpDynaSqlProvider.class,method="selectWhitParam")
	List<TrajectoryEmp> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=TrajectoryEmpDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from "+EMPLOYEETABLE+" where DEPT_ID = #{vague}")
	List<Employee> vagueDept(String vague);
	
	@Delete(" delete from "+TrajectoryEmp.tableName+" where id = #{id} ")
	void deleteTrajectory(String id);
}
