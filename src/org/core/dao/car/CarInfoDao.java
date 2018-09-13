package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.car.provider.CarInfoProvider;
import org.core.domain.car.CarInfo;

/**   
 * @Description: Mapper接口
 */
public interface CarInfoDao {
	@SelectProvider(type=CarInfoProvider.class,method="save")
	void save(CarInfo entity);
	
	@Delete(" delete from "+CarInfo.tableName+" where id = #{id} ")
	void deleteById(int id);
		
	@SelectProvider(type=CarInfoProvider.class,method="update")
	void update(CarInfo entity);

	@Select("select * from "+CarInfo.tableName+" where id = #{id}")
	CarInfo selectById(int id);
	
	@SelectProvider(type=CarInfoProvider.class,method="selectByPage")
	List<CarInfo> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=CarInfoProvider.class,method="count")
	int count(Map<String, Object> params);

	@Select("select * from "+CarInfo.tableName)
	List<CarInfo> selectAll();

	@Select("select * from "+CarInfo.tableName+" where carno = #{carno}")
	CarInfo selectByCarno(String carno);

	@Select("select * from "+CarInfo.tableName+" where  name LIKE CONCAT('%',#{vague},'%')")
	List<CarInfo> vagueI(String vague);
	
}
