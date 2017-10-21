package org.core.dao.visitor;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.VisitorRecordProvider;
import org.core.domain.visitor.VisitorRecord;

/**   
 * @Description: Mapper接口
 */
public interface VisitorRecordDao {
	@SelectProvider(type=VisitorRecordProvider.class,method="save")
	void save(VisitorRecord entity);
	
	@Delete(" delete from "+VisitorRecord.tableName+" where recordID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=VisitorRecordProvider.class,method="update")
	void update(VisitorRecord entity);
	
	@Select("select * from "+VisitorRecord.tableName+" where recordID = #{id}")
	VisitorRecord selectById(String id);
	
	@SelectProvider(type=VisitorRecordProvider.class,method="selectByPage")
	List<VisitorRecord> selectByPage(VisitorRecord entity);

}
