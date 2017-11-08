package org.core.dao.webapp;
import static org.core.util.GlobleConstants.ACCESSJTABLE;
import static org.core.util.GlobleConstants.ACCESSGROUPTABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.AJSqlProvider;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;

//查询所有权限
public interface AJDao {
	//查询授权表中是否已经存在EMP对象
	@Select(" select count(*) from "+ACCESSJTABLE+" where ajemp = #{id}")
	int selectAJG(String id);
	//查询所有门禁分组
	@Select(" select * from "+ACCESSGROUPTABLE)
	List<AccessGroup> findAGAll();
	@SelectProvider(method = "countgy", type = AJSqlProvider.class)
	int count(Map<String, Object> gy);
	@SelectProvider(method = "selectWhitGy", type = AJSqlProvider.class)
	List<Accessj> selectByPagegy(Map<String, Object> gy);
	//删除
	@Delete(" delete from "+ACCESSJTABLE+" where ajid = #{id} ")
	void removeAccessjByID(String id);
	//添加
	@SelectProvider(method = "saveAJ", type = AJSqlProvider.class)
	void saveAJ(Accessj accessj);
	//查询自己
	@Select(" select * from "+ACCESSJTABLE+" where ajid = #{id}")
	Accessj selectAjByid(String id);
	//修改
	@SelectProvider(method = "updateAj", type = AJSqlProvider.class)
	void updateAj(Accessj accessj);
	
	@Select(" select * from "+ACCESSJTABLE+" where ajempid = #{id}")
	List<Accessj> selectAjByEmpid(String id);
}
