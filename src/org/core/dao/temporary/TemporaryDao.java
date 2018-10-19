package org.core.dao.temporary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.temporary.provider.TemporaryProvider;
import org.core.domain.temporary.TemporaryInfo;

public interface TemporaryDao {
//查询中使用的方法
	@SelectProvider(type=TemporaryProvider.class,method="count")
	int count(Map<String, Object> params);
	@SelectProvider(type=TemporaryProvider.class,method="selectByPage")
	List<TemporaryInfo> selectByPage(Map<String, Object> params);
//办理中使用的方法
	@SelectProvider(type=TemporaryProvider.class,method="countHandle")
	int countHandle(Map<String, Object> params);
	@SelectProvider(type=TemporaryProvider.class,method="selectByPageHandle")
	List<TemporaryInfo> selectByPageHandle(Map<String, Object> params);
	
	@SelectProvider(type=TemporaryProvider.class,method="save")
	void save(TemporaryInfo entity);
	@SelectProvider(type=TemporaryProvider.class,method="update")
	void update(TemporaryInfo entity);
	
	@Select("select * from "+TemporaryInfo.tableName+" where  id=#{id}")
	TemporaryInfo getEntityById(int id);
	@Delete("delete from "+TemporaryInfo.tableName+" where  id=#{id}")
	void deleteById(int id);
	
	
}
