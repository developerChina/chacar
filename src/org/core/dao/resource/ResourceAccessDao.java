package org.core.dao.resource;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.resource.provider.ResourceAccessProvider;
import org.core.domain.resource.ResourceAccess;

/**   
 * @Description: Mapper接口
 */
public interface ResourceAccessDao {
	@SelectProvider(type=ResourceAccessProvider.class,method="save")
	void save(ResourceAccess entity);
	
	@Delete(" delete from "+ResourceAccess.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=ResourceAccessProvider.class,method="update")
	void update(ResourceAccess entity);
	
	@Select("select * from "+ResourceAccess.tableName+" where id = #{id}")
	ResourceAccess selectById(int id);
	
	@SelectProvider(type=ResourceAccessProvider.class,method="selectByPage")
	@Results({
		@Result(column="resource_id",property="resource",one=@One(select="org.core.dao.resource.ResourceInfoDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="user_id",property="user",one=@One(select="org.core.dao.webapp.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<ResourceAccess> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=ResourceAccessProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+ResourceAccess.tableName+" where user_id = #{userid}")
	List<ResourceAccess> selectByUserid(int userid);
	
	@Delete(" delete from "+ResourceAccess.tableName+" where user_id = #{userid} ")
	void deleteByUserid(int userid);

	@Select("select * from "+ResourceAccess.tableName+" where resource_id = #{resourceid}")
	List<ResourceAccess> selectByResourceid(int resourceid);
	
	@Select("select * from "+ResourceAccess.tableName+" where user_id = #{userid} and resource_id = #{resourceid}")
	ResourceAccess selectByResource_User(@Param("userid")int userid, @Param("resourceid")int resourceid);

}
