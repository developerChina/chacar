package org.core.dao.webapp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.webapp.provider.TrajectoryEmpDynaSqlProvider;
import org.core.domain.webapp.TrajectoryEmp;


public interface TrajectoryEmpDao {
	//动态查询
	@SelectProvider(type=TrajectoryEmpDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(column="cardno",property="employees",one=@One(select="org.core.dao.webapp.EmployeeDao.getEmployeeesBy_cardno",fetchType=FetchType.EAGER))
	})
	List<TrajectoryEmp> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=TrajectoryEmpDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
}
