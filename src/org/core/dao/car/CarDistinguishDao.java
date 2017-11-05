package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.car.provider.CarDistinguishProvider;
import org.core.domain.car.CarDistinguish;

/**   
 * @Description: Mapper接口
 */
public interface CarDistinguishDao {
	@SelectProvider(type=CarDistinguishProvider.class,method="save")
	void save(CarDistinguish entity);
	
	@Delete(" delete from "+CarDistinguish.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CarDistinguishProvider.class,method="update")
	void update(CarDistinguish entity);
	
	@Select("select * from "+CarDistinguish.tableName+" where id = #{id}")
	CarDistinguish selectById(int id);
	
	@SelectProvider(type=CarDistinguishProvider.class,method="selectByIds")
	List<CarDistinguish> selectByIds(String ids);
	
	@SelectProvider(type=CarDistinguishProvider.class,method="selectFillterIds")
	List<CarDistinguish> selectFillterIds(String ids);
	
	@SelectProvider(type=CarDistinguishProvider.class,method="selectByPage")
	List<CarDistinguish> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarDistinguishProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+CarDistinguish.tableName )
	List<CarDistinguish> selectAll();

}
