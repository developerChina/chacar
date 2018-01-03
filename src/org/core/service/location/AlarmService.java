package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationAlarm;
import org.core.util.tag.PageModel;

public interface AlarmService {
		//查询
	List<LocationAlarm> findLocationAlarm(LocationAlarm locationAlarm, PageModel pageModel);

	void modifyLocationLocationAlarm(String ids);

}
