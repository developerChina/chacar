package org.core.service.record;

import java.util.Date;
import java.util.List;

import org.core.domain.visitor.Trajectory;
import org.core.util.tag.PageModel;


/**
 * Service-》 访问轨迹控制接口
 */
public interface TrajectoryService {
	
	/**
	 * 查询访问轨迹
	 */
	List<Trajectory> selectByPage(Trajectory entity,PageModel pageModel,Date startDate,Date endDate);

	void deleteTrajectoryById(String id);

}
