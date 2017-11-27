package org.core.dao.webapp;

import static org.core.util.GlobleConstants.RESONTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.ResonDynaSqlProvid;
import org.core.domain.webapp.Reson;

public interface ResonDao {
	// 动态查询
	@SelectProvider(type=ResonDynaSqlProvid.class,method="selectWhitGy")
	List<Reson> selectByPagegy(Map<String, Object> gy);
	// 根据参数查询用户总数
	@SelectProvider(type=ResonDynaSqlProvid.class,method="countgy")
	Integer count(Map<String, Object> gy);
	//删除
	@Delete(" delete from "+RESONTABLE+" where rid = #{id} ")
	void deleteByResonID(Integer id);
	// 动态修改通道
	@SelectProvider(type=ResonDynaSqlProvid.class,method="updateReson")
	void update(Reson reson);
	//根据id查通道
	@Select("select * from "+RESONTABLE+" where rid = #{rid}")
	Reson selectByResonID(Integer rid);
	//添加通道
	@SelectProvider(method = "insertReson", type = ResonDynaSqlProvid.class)
	void save(Reson reson);
	
	@Select("select * from "+RESONTABLE)
	List<Reson> findAll();
	
}
