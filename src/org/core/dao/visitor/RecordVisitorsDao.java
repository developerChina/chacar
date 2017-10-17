package org.core.dao.visitor;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.RecordVisitorsProvider;
import org.core.domain.visitor.RecordVisitors;

/**   
 * @Description: Mapper接口
 */
public interface RecordVisitorsDao {
	@SelectProvider(type=RecordVisitorsProvider.class,method="save")
	void save(RecordVisitors entity);
	
	@Delete(" delete from "+RecordVisitors.tableName+" where recordVID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=RecordVisitorsProvider.class,method="update")
	void update(RecordVisitors entity);
	
	@Select("select * from "+RecordVisitors.tableName+" where recordVID = #{id}")
	RecordVisitors selectById(String id);
	
	@SelectProvider(type=RecordVisitorsProvider.class,method="selectByPage")
	List<RecordVisitors> selectByPage(RecordVisitors entity);
	
}
