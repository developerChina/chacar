package org.core.service.car;

import java.util.List;

import org.core.domain.car.CarLogs;
import org.core.util.tag.PageModel;

/**   
 * @Description: 停车场进出记录服务层接口
 */
public interface CarLogsService {
	/**
	 * 停车场进出记录首页查询
	 */
	List<CarLogs> selectCarLogs(CarLogs carLogs, PageModel pageModel);

}
