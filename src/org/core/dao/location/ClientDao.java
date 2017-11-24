package org.core.dao.location;

import static org.core.domain.location.LocationConstants.CLIENTTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.ClientSqlProvider;
import org.core.domain.location.LocationClient;

public interface ClientDao {
	//count
	@SelectProvider(type=ClientSqlProvider.class,method="count")
	Integer count(Map<String, Object> gy);
	//动态查询
	@SelectProvider(type=ClientSqlProvider.class,method="selectByPagegy")
	List<LocationClient> selectByPagegy(Map<String, Object> gy);
	//删除
	@Delete(" delete from "+CLIENTTABLE+" where id = #{id} ")
	void removeLocationClientById(String id);
	//根据id查询
	@Select(" select * from "+CLIENTTABLE+" where id = #{id}")
	LocationClient findLocationClientById(String id);
	//修改
	@SelectProvider(type=ClientSqlProvider.class,method="modifyLocationClient")
	void modifyLocationClient(LocationClient locationClient);
	//添加
	@SelectProvider(method = "addLocationClient", type = ClientSqlProvider.class)
	void addLocationClient(LocationClient locationClient);

}
