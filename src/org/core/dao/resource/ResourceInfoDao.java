package org.core.dao.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.resource.provider.ResourceInfoProvider;
import org.core.domain.resource.ResourceInfo;

/**   
 * @Description: Mapper接口
 */
public interface ResourceInfoDao {
	@SelectProvider(type=ResourceInfoProvider.class,method="save")
	void save(ResourceInfo entity);
	
	@Delete(" delete from "+ResourceInfo.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=ResourceInfoProvider.class,method="update")
	void update(ResourceInfo entity);
	
	@Select("select * from "+ResourceInfo.tableName+" where id = #{id}")
	ResourceInfo selectById(int id);
	
	@SelectProvider(type=ResourceInfoProvider.class,method="selectByPage")
	@Results({
		@Result(column="pid",property="resource",one=@One(select="org.core.dao.resource.ResourceInfoDao.selectById",fetchType=FetchType.EAGER))
	})
	List<ResourceInfo> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=ResourceInfoProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+ResourceInfo.tableName )
	List<ResourceInfo> selectAll();

}
