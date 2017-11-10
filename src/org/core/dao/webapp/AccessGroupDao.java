package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ACCESSGROUPTABLE;
import static org.core.util.GlobleConstants.MiddletoAGTABLE;
import static org.core.util.GlobleConstants.ACCESSTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.AccessGroupSqlProvider;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.MiddletoAG;
public interface AccessGroupDao {
	/*
	 * 门禁分组
	 * */
	//动态查询
	@SelectProvider(type=AccessGroupSqlProvider.class,method="selectWhitSgy")
	List<AccessGroup> selectByPagesgy(Map<String, Object> sgy);
	
	//根据参数查询用户总数
	@SelectProvider(type=AccessGroupSqlProvider.class,method="countsgy")
	Integer count(Map<String, Object> sgyy);
	
	//删除
	@Delete(" delete from "+ACCESSGROUPTABLE+" where agid = #{id} ")
	void deleteByagID(String id);
	@Delete(" delete from "+MiddletoAGTABLE+" where agroupid = #{id} ")
	void deleteAgMiddleByagID(String id);
	//查询所有门禁
	@Select(" select * from "+ACCESSTABLE)
	List<Access> selectAGSubordinate();
	
	//添加门禁分组
	@SelectProvider(method = "saveAgroup", type = AccessGroupSqlProvider.class)
	void addAGroup(String agname,String uuid);
	
	//根据id查询所属下级
	@Select(" select * from "+ACCESSTABLE+" where accessid=#{id}")
	Access getAccessByid(Integer id);
	//修改前查一遍
	@Select(" select * from "+ACCESSGROUPTABLE+" where agid=#{id}")
	AccessGroup selectAGbyId(String id);
	//修改
	@SelectProvider(method = "updateAG", type = AccessGroupSqlProvider.class)
	void updateAG(AccessGroup accessGroup);
	//向中间表中添加
	@SelectProvider(method = "addAGrouptoMiddle", type = AccessGroupSqlProvider.class)
	void addAGrouptoMiddle(String uuid, String id);
	
	
	
	
	@Select(" select * from "+MiddletoAGTABLE+" where agroupid=#{selectids}")
	List<MiddletoAG> getMiddle(String selectids);
	
	@Delete(" delete from "+MiddletoAGTABLE+" where agroupid = #{myagid} ")
	void upDelMiddletoAG(String myagid);
	
	
	
	

	
}
