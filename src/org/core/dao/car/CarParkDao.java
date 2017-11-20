package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.car.provider.CarParkProvider;
import org.core.domain.car.CarPark;

/**   
 * @Description: Mapper接口
 */
public interface CarParkDao {
	@SelectProvider(type=CarParkProvider.class,method="save")
	void save(CarPark entity);
	
	@Delete(" delete from "+CarPark.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CarParkProvider.class,method="update")
	void update(CarPark entity);

	@Select("select * from "+CarPark.tableName+" where id = #{id}")
	CarPark selectById(int id);
	
	@SelectProvider(type=CarParkProvider.class,method="selectByPage")
	List<CarPark> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarParkProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+CarPark.tableName )
	List<CarPark> selectAll();
	
}
