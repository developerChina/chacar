package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.car.provider.CarPassagewayProvider;
import org.core.domain.car.CarPassageway;

/**   
 * @Description: Mapper接口
 */
public interface CarPassagewayDao {
	@SelectProvider(type=CarPassagewayProvider.class,method="save")
	void save(CarPassageway entity);
	
	@Delete(" delete from "+CarPassageway.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CarPassagewayProvider.class,method="update")
	void update(CarPassageway entity);
	
	@Select("select * from "+CarPassageway.tableName+" where id = #{id}")
	CarPassageway selectById(int id);
	
	@SelectProvider(type=CarPassagewayProvider.class,method="selectByPage")
	@Results({
		@Result(column="park_id",property="carPark",one=@One(select="org.core.dao.car.CarParkDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="distinguish_ids",property="carDistinguishs",javaType=List.class,many=@Many(select="org.core.dao.car.CarDistinguishDao.selectByIds")) 
	})
	List<CarPassageway> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarPassagewayProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+CarPassageway.tableName )
	List<CarPassageway> selectAll();

	@Select("select * from "+CarPassageway.tableName+" where park_id = #{parkid}")
	List<CarPassageway> selectByParkid(int parkid);

}
