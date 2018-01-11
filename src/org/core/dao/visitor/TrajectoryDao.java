package org.core.dao.visitor;

import static org.core.util.GlobleConstants.BLACKLISTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.visitor.provider.TrajectoryProvider;
import org.core.domain.visitor.Trajectory;

public interface TrajectoryDao {
	
	@SelectProvider(type=TrajectoryProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	
	@SelectProvider(type=TrajectoryProvider.class,method="selectByPage")
	@Results({
		@Result(column="cardNo",property="recordVisitors",one=@One(select="org.core.dao.visitor.RecordVisitorsDao.selectVisitorByCardNo",fetchType=FetchType.EAGER))
	})
	List<Trajectory> selectByPage(Map<String, Object> params);

	@Delete(" delete from "+Trajectory.tableName+" where id = #{id} ")
	void deleteTrajectoryById(String id);

}
