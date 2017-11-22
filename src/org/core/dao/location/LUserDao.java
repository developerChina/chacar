package org.core.dao.location;

import static org.core.domain.location.LocationConstants.USERTABLE;
import static org.core.domain.location.LocationConstants.CLIENTTABLE;
import static org.core.domain.location.LocationConstants.GROUPTABLE;
import static org.core.domain.location.LocationConstants.USERGOUPTABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.location.provider.LuDynaSqlProvider;
import org.core.domain.location.LocationClient;
import org.core.domain.location.LocationGroup;
import org.core.domain.location.LocationUser;
import org.core.domain.location.LocationUserGroup;

public interface LUserDao {
	//分页查询 条数
	@SelectProvider( type =LuDynaSqlProvider.class ,method ="count")
	int count(Map<String, Object> params);
	//集合
	@SelectProvider( type =LuDynaSqlProvider.class ,method ="selectWhitParam")
	List<LocationUser> selectByPage(Map<String, Object> params);
	// 添加时得到的配件  1分组的集合 2客户的集合
	@Select(" select * from "+GROUPTABLE )
	List<LocationGroup> getGroupParts();
	@Select(" select * from "+CLIENTTABLE )
	List<LocationClient> getClientParts();
	@SelectProvider( type =LuDynaSqlProvider.class ,method ="addUserAndGroup")
	void addUserAndGroup(String myGroupid, String uuid);
	@SelectProvider( type =LuDynaSqlProvider.class ,method ="addLuser")
	void addLuser(LocationUser locationUser);
	//删除用户 并删除用户分组表里的相关数据
	@Delete(" delete from "+USERTABLE+" where id = #{luser} ")
	void removeUser(String luser);
	@Delete(" delete from "+USERGOUPTABLE+" where userid = #{luser} ")
	void removeUserGroup(String luser);
	//修改前查一遍
		//1 查自身表
	@Select(" select * from "+USERTABLE+" where id = #{luser} " )
	LocationUser getUpdateUser(String luser);
		//2查中间表
	@Select(" select * from "+USERGOUPTABLE+" where userid = #{luser} " )
	List<LocationUserGroup> getUpdateUserGroup(String luser);
		//3查分组表
	@Select(" select * from "+GROUPTABLE+" where id = #{groupid} " )
	LocationGroup grtGroup(int groupid);
		//4查客户表
	@Select(" select * from "+CLIENTTABLE+" where id = #{clientID} ")
	LocationClient getUpateUser(String clientID);
	//修改
		//1先删除中间表里的数据 执行了 removeUserGroup方法上面
		//2执行修改
	@SelectProvider( type =LuDynaSqlProvider.class ,method ="updateUser")
	void updateUser(LocationUser locationUser);
	
	
	
}
