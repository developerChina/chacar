package org.core.dao.bevisited;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.bevisited.provider.DepartInfoProvider;
import org.core.domain.bevisited.DepartInfo;

/**   
 * @Description: Mapper接口
 */
public interface DepartInfoDao {
	@SelectProvider(type=DepartInfoProvider.class,method="save")
	void save(DepartInfo entity);
	
	@Delete(" delete from "+DepartInfo.tableName+" where bevisitedID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=DepartInfoProvider.class,method="update")
	void update(DepartInfo entity);
	
	@Select("select * from "+DepartInfo.tableName+" where bevisitedID = #{id}")
	DepartInfo selectById(String id);
	
	@SelectProvider(type=DepartInfoProvider.class,method="selectByPage")
	List<DepartInfo> selectByPage(DepartInfo entity);
	
	@Select("select * from "+DepartInfo.tableName)
	List<DepartInfo> selectAll();
	
}
