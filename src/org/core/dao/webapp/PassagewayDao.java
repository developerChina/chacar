package org.core.dao.webapp;

import static org.core.util.GlobleConstants.PASSAGEWAYTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.PassagewayDynaSqlProvider;
import org.core.domain.webapp.Passageway;

public interface PassagewayDao {
		// 动态查询
		@SelectProvider(type=PassagewayDynaSqlProvider.class,method="selectWhitGy")
		List<Passageway> selectByPagegy(Map<String, Object> gy);
		// 根据参数查询用户总数
		@SelectProvider(type=PassagewayDynaSqlProvider.class,method="countgy")
		Integer count(Map<String, Object> gy);
		//删除
		@Delete(" delete from "+PASSAGEWAYTABLE+" where passagewayID = #{id} ")
		void deleteBypassagewayID(Integer id);
		// 动态修改通道
		@SelectProvider(type=PassagewayDynaSqlProvider.class,method="updatePassageway")
		void update(Passageway passageway);
		//根据id查通道
		@Select("select * from "+PASSAGEWAYTABLE+" where passagewayID = #{passagewayID}")
		Passageway selectBypassagewayID(Integer passagewayID);
		//添加通道
		@SelectProvider(method = "insertPassageway", type = PassagewayDynaSqlProvider.class)
		void save(Passageway passageway);
		//根据id模糊查询
		@SelectProvider(method = "selectPassagewayGroupByid", type = PassagewayDynaSqlProvider.class)
		int selectPassagewayGroupByid(String id);
		
}
