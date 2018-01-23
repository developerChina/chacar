package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.TrajectoryEmp;
import org.core.util.tag.PageModel;

public interface TrajectoryEmpService {
	//查询员工轨迹表
	List<TrajectoryEmp> selectTrajectory(TrajectoryEmp trajectoryEmp, PageModel pageModel);
	
	void deleteTrajectory(String id);
}
