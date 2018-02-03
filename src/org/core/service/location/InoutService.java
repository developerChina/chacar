package org.core.service.location;

import java.util.Date;
import java.util.List;

import org.core.domain.location.LocationInout;
import org.core.util.tag.PageModel;

public interface InoutService {

	List<LocationInout> findLocationAlarm(LocationInout locationInout, PageModel pageModel, Date startDate, Date endDate);

	LocationInout selectNewRecord(String car_code);

}
