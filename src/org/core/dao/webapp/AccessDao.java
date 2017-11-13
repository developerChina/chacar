package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ACCESSTABLE;
import static org.core.util.GlobleConstants.MiddletoAGTABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.AccessDynaSqlProvider;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.MiddletoAG;

public interface AccessDao {
	// 动态查询
	@SelectProvider(type=AccessDynaSqlProvider.class,method="selectWhitSgy")
	List<Access> selectByPagesgy(Map<String, Object> sgy);
	// 根据参数查询用户总数
	@SelectProvider(type=AccessDynaSqlProvider.class,method="countsgy")
	Integer count(Map<String, Object> sgyy);
	//删除
	@Delete(" delete from "+ACCESSTABLE+" where accessid = #{id} ")
	void deleteBypassagewayID(Integer id);
	// 动态修改通道
	@SelectProvider(type=AccessDynaSqlProvider.class,method="updateAccess")
	void update(Access access);
	//根据id查通道
	@Select("select * from "+ACCESSTABLE+" where accessid= #{accessid}")
	Access selectByaccessid(Integer accessid);
	//添加通道
	@SelectProvider(method = "insertAccess", type = AccessDynaSqlProvider.class)
	void save(Access access);
	//根据id模糊查询
	@SelectProvider(method = "selectAccessGroupByid", type = AccessDynaSqlProvider.class)
	int selectAccessGroupByid(String id);
	//添加时候的验证
	@SelectProvider(method = "getList", type = AccessDynaSqlProvider.class)
	List<Access> getList(Access access);
	@Select("select * from "+MiddletoAGTABLE+" where accessid= #{id}")
	List<MiddletoAG> selectMiddletoAGTABLE(Integer id);
	
}
