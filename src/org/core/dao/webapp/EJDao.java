package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ELEVATORJTABLE;
import static org.core.util.GlobleConstants.ELEVATORGROUPTABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.EJSqlProvider;
import org.core.domain.webapp.ElevatorGroup;
import org.core.domain.webapp.Elevatorj;

/**   
 * @Description: 电梯授权的DAO接口
 */

public interface EJDao {
	
	
	@Select(" select count(*) from "+ELEVATORJTABLE+" where ejemp=#{id}")
	int selectEmp(String id);
	@Select(" select * from "+ELEVATORGROUPTABLE)
	List<ElevatorGroup> findEGALL();
	
	@SelectProvider(type=EJSqlProvider.class,method="saveEj")
	void saveEj(Elevatorj elevatorj);
	
	//分页查询 查
	@SelectProvider(type=EJSqlProvider.class,method="count")
	int count(Map<String, Object> params);
	
	@SelectProvider(type=EJSqlProvider.class,method="selectWhitParam")
	List<Elevatorj> selectByPage(Map<String, Object> params);
	
	//删除
	@Delete(" delete from "+ELEVATORJTABLE+" where ejid = #{id} ")
	void EjDelByEjid(String id);
	//修改前查
	@Select(" select * from "+ELEVATORJTABLE+" where ejid=#{id}")
	Elevatorj selectEjByid(String id);
	//修改
	@SelectProvider(type=EJSqlProvider.class,method="updateEj")
	void updateEj(Elevatorj elevatorj);
	
	
	
}
