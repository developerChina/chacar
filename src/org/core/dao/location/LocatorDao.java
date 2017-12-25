package org.core.dao.location;
import static org.core.domain.location.LocationConstants.LOCATOR;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.LocatorSqlProvider;
import org.core.domain.location.LocationLocator;

public interface LocatorDao {
	//count
	@SelectProvider(type=LocatorSqlProvider.class,method="count")
	int count(Map<String, Object> gy);
	//动态查询
	@SelectProvider(type=LocatorSqlProvider.class,method="selectByPagegy")
	List<LocationLocator> selectByPagegy(Map<String, Object> gy);
	//删除
	@Delete(" delete from "+LOCATOR+" where id = #{id} ")
	void removeLocationLocatorById(String id);
	//根据id查询
	@Select(" select * from "+LOCATOR+" where id = #{id}")
	LocationLocator findLocationLocatorById(Integer id);
	//修改
	@SelectProvider(type=LocatorSqlProvider.class,method="modifyLocationLocator")
	void modifyLocationLocator(LocationLocator locationLocator);
	//添加
	@SelectProvider(method = "addLocationLocator", type = LocatorSqlProvider.class)
	void addLocationLocator(LocationLocator locationLocator);
}
