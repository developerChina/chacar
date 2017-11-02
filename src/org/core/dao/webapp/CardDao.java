package org.core.dao.webapp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.CardAccessProvider;
import org.core.domain.webapp.CardAccess;		

/**   
 * @Description: Mapper接口
 */
public interface CardDao {
	@SelectProvider(type=CardAccessProvider.class,method="save")
	void save(CardAccess entity);
	
	@Delete(" delete from "+CardAccess.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CardAccessProvider.class,method="update")
	void update(CardAccess entity);
	
	@Select("select * from "+CardAccess.tableName+" where id = #{id}")
	CardAccess selectById(int id);
	
	@SelectProvider(type=CardAccessProvider.class,method="selectByPage")
	List<CardAccess> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CardAccessProvider.class,method="count")
	int count(Map<String, Object> params);

}
