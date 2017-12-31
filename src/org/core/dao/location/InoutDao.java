package org.core.dao.location;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.InoutSqlProvider;
import org.core.domain.location.LocationInout;

public interface InoutDao {
	@SelectProvider(type=InoutSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	@SelectProvider(type=InoutSqlProvider.class,method="selectByPagegy")
	List<LocationInout> selectByPagegy(Map<String, Object> gy);

}
