package org.core.dao.webapp;

import static org.core.util.GlobleConstants.PASSAGEWAYGROUPTABLE;
import static org.core.util.GlobleConstants.PASSAGEWAYTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.PassagewayGroupSqlProvider;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;

public interface PassagewayGroupDao {
	/*
	 * 通道分组
	 * */
	
	//动态查询
	@SelectProvider(type=PassagewayGroupSqlProvider.class,method="selectWhitSgy")
	List<PassagewayGroup> selectByPagesgy(Map<String, Object> sgy);
	
	//根据参数查询用户总数
	@SelectProvider(type=PassagewayGroupSqlProvider.class,method="countsgy")
	Integer count(Map<String, Object> sgyy);
	
	//删除
	@Delete(" delete from "+PASSAGEWAYGROUPTABLE+" where pgid=#{id}")
	void deletePassagewayGroupById(String id);
	
	//查询下级
	@Select(" select * from "+PASSAGEWAYTABLE+" where passagewayID=#{id}")
	Passageway getPassagewayByid(Integer id);
	
	//查询所有通道
	@Select(" select * from "+PASSAGEWAYTABLE)
	List<Passageway> selectPGSubordinate();
	
	//添加分组
	@SelectProvider(method = "savePgroup", type = PassagewayGroupSqlProvider.class)
	void addPGroup(String ids, String pgname, String uuid);
	//修改前先查询
	@Select(" select * from "+PASSAGEWAYGROUPTABLE+" where pgid=#{id}")
	PassagewayGroup selectPGbyId(String id);
	//修改
	@SelectProvider(method = "updatePG", type = PassagewayGroupSqlProvider.class)
	void updatePG(PassagewayGroup passagewayGroup);
}
