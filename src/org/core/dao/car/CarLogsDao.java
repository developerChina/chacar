package org.core.dao.car;

import java.util.List;
import java.util.Map;

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

}
