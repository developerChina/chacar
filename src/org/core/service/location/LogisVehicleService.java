package org.core.service.location;

import java.util.List;

import org.core.domain.location.LocationLogisVehicle;
import org.core.util.tag.PageModel;

public interface LogisVehicleService {


	List<LocationLogisVehicle> findLocationLogisVehicle(LocationLogisVehicle locationLogisVehicle, PageModel pageModel);


}
