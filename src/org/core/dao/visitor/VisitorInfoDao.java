package org.core.dao.visitor;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.VisitorInfoProvider;
import org.core.domain.visitor.VisitorInfo;

/**   
 * @Description: Mapper接口
 */
public interface VisitorInfoDao {
	@SelectProvider(type=VisitorInfoProvider.class,method="save")
	void save(VisitorInfo entity);
	
	@Delete(" delete from "+VisitorInfo.tableName+" where visitorID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=VisitorInfoProvider.class,method="update")
	void update(VisitorInfo entity);
	
	@Select("select * from "+VisitorInfo.tableName+" where visitorID = #{id}")
	VisitorInfo selectById(String id);
	
	@SelectProvider(type=VisitorInfoProvider.class,method="selectByPage")
	List<VisitorInfo> selectByPage(VisitorInfo entity);
	
	@Select("select * from "+VisitorInfo.tableName+" where cardID = #{cardID}")
	VisitorInfo selectOneBycardID(String cardID);

}
