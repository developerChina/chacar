package org.core.dao.location;

import static org.core.domain.location.LocationConstants.GROUPTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.LgDynaSqlProvider;
import org.core.domain.location.LocationGroup;

public interface LGroupDao {
	//分页查询 条数
	@SelectProvider( type =LgDynaSqlProvider.class ,method ="count")
	int count(Map<String, Object> params);
	//分页查询 集合
	@SelectProvider( type =LgDynaSqlProvider.class ,method ="selectWhitParam")
	List<LocationGroup> selectByPage(Map<String, Object> params);
	//添加
	@SelectProvider( type =LgDynaSqlProvider.class ,method ="saveLgroup")
	void save(LocationGroup locationGroup);
	//删除
	@Delete(" delete from "+GROUPTABLE+" where id = #{lgroup} ")
	void delLocationGroup(int lgroup);
	//修改 查询
	@Select(" select * from "+GROUPTABLE+" where id = #{lgroup} ")
	LocationGroup getUpdate(int lgroup);
	//修改
	@SelectProvider( type =LgDynaSqlProvider.class ,method ="updateLgroup")
	void updateLgroup(LocationGroup locationGroup);

}
