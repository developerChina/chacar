package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.car.provider.CarAuthorityProvider;
import org.core.domain.car.CarAuthority;

/**   
 * @Description: Mapper接口
 */
public interface CarAuthorityDao {
	@SelectProvider(type=CarAuthorityProvider.class,method="save")
	void save(CarAuthority entity);
	
	@Delete(" delete from "+CarAuthority.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CarAuthorityProvider.class,method="update")
	void update(CarAuthority entity);
	
	@Select("select * from "+CarAuthority.tableName+" where id = #{id}")
	CarAuthority selectById(int id);
	
	@SelectProvider(type=CarAuthorityProvider.class,method="selectByPage")
	List<CarAuthority> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarAuthorityProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+CarAuthority.tableName )
	List<CarAuthority> selectAll();
	
	@Select("select * from "+CarAuthority.tableName+" where carno = #{carno} and passageway_id=#{passagewayid} ")
	CarAuthority selectByCarno_Passagewayid(@Param("carno")String carno, @Param("passagewayid")int passagewayid);

}
