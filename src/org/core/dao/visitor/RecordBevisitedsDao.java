package org.core.dao.visitor;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.RecordBevisitedsProvider;
import org.core.domain.visitor.RecordBevisiteds;

/**   
 * @Description: Mapper接口
 */
public interface RecordBevisitedsDao {
	@SelectProvider(type=RecordBevisitedsProvider.class,method="save")
	void save(RecordBevisiteds entity);
	
	@Delete(" delete from "+RecordBevisiteds.tableName+" where recordBVID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=RecordBevisitedsProvider.class,method="update")
	void update(RecordBevisiteds entity);
	
	@Select("select * from "+RecordBevisiteds.tableName+" where recordBVID = #{id}")
	RecordBevisiteds selectById(String id);
	
	@SelectProvider(type=RecordBevisitedsProvider.class,method="selectByPage")
	List<RecordBevisiteds> selectByPage(RecordBevisiteds entity);
	
}
