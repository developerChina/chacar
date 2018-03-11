package org.core.dao.car;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.car.provider.carLogsProvider;
import org.core.domain.car.CarDistinguish;
import org.core.domain.car.CarInfo;
import org.core.domain.car.CarLogs;

/**   
 * @Description: 停车场进出记录的 Mapper接口
 */
public interface CarLogsDao {

	@SelectProvider(type=carLogsProvider.class,method="countCarLogs")
	int countCarLogs(Map<String, Object> params);

	@SelectProvider(type=carLogsProvider.class,method="pageSelectCarLogs")
	List<CarLogs> pageSelectCarLogs(Map<String, Object> params);

	@Select("select * from "+CarDistinguish.tableName+" where action = 'plugin.InnerCarExec' and name LIKE CONCAT('%','进','%') ")
	List<CarDistinguish> selectIp();
	
	@Select("select * from "+CarInfo.tableName+" where carno = #{cacrno} ")
	CarInfo selectByCarno(String cacrno);

	@Select("select carno from "+CarInfo.tableName+" where name LIKE CONCAT('%',#{cacrno},'%')   ")
	List<String> vagueCar_code(String vSupplier);
	
	@Select("select * from "+CarDistinguish.tableName+" where ip = #{serverIp} ")
	CarDistinguish selectByIp(String serverIp);
	
	@Select(" select pas.distinguish_ids FROM car_passageway as pas where pas.distinguish_ids <> ( select pas.distinguish_ids FROM car_distinguish as dis,car_passageway as pas where dis.id=pas.distinguish_ids and dis.ip=#{serverIp} ) and pas.name = ( select pas.name FROM car_distinguish as dis,car_passageway as pas where dis.id=pas.distinguish_ids and dis.ip=#{serverIp} )")
	String getRelationId(String serverIp);

	@Select("select * from "+CarDistinguish.tableName+" where id = #{relationId} ")
	CarDistinguish getDisById(String relationId);

	@Select("select * from "+CarLogs.tableName+" where cacrno=#{cacrno} and serverIp=#{ip} and shootTime>#{shootTime} order by shootTime  limit 1 ")
	CarLogs getOut(@Param("ip")String ip, @Param("shootTime")String shootTime, @Param("cacrno")String cacrno);

}
