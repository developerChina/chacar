package org.core.dao.bevisited;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.bevisited.provider.BevisitedInfoProvider;
import org.core.domain.bevisited.BevisitedInfo;

/**   
 * @Description: Mapper接口
 */
public interface BevisitedInfoDao {
	@SelectProvider(type=BevisitedInfoProvider.class,method="save")
	void save(BevisitedInfo entity);
	
	@Delete(" delete from "+BevisitedInfo.tableName+" where bevisitedID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=BevisitedInfoProvider.class,method="update")
	void update(BevisitedInfo entity);
	
	@Select("select * from "+BevisitedInfo.tableName+" where bevisitedID = #{id}")
	BevisitedInfo selectById(String id);
	
	@SelectProvider(type=BevisitedInfoProvider.class,method="selectByPage")
	List<BevisitedInfo> selectByPage(BevisitedInfo entity);
	
	@Select("select * from "+BevisitedInfo.tableName)		
	List<BevisitedInfo> selectAll();

	@Select("select * from "+BevisitedInfo.tableName+" where bevisitedTel = #{tel}")
	BevisitedInfo selectOneByTel(String tel);
}
