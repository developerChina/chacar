package org.core.dao.location;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.core.dao.location.provider.AlarmSqlProvider;
import org.core.domain.location.LocationAlarm;
import static org.core.domain.location.LocationConstants.ALARM;
public interface AlarmDao {
	
	@SelectProvider(type=AlarmSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	
	@SelectProvider(type=AlarmSqlProvider.class,method="selectByPagegy")
	List<LocationAlarm> selectByPagegy(Map<String, Object> gy);

	@Update(" update "+ALARM+" set handletype=1 where id=#{id}")
	void modifyLocationAlarm(Integer id);
}
