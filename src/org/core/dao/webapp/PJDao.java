package org.core.dao.webapp;
import static org.core.util.GlobleConstants.PASSAGEWAYGROUPTABLE;
import static org.core.util.GlobleConstants.PASSAGEWAYJTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.PJSqlProvider;
import org.core.domain.webapp.Passageway;
import org.core.domain.webapp.PassagewayGroup;
import org.core.domain.webapp.Passagewayj;

public interface PJDao {
	@Select(" select count(*) from "+PASSAGEWAYJTABLE+" where pjemp = #{id}")
	int selectPJG(String id);
	@Select(" select * from "+PASSAGEWAYGROUPTABLE)
	List<PassagewayGroup> findPGAll();
	@SelectProvider(method = "countgy", type = PJSqlProvider.class)
	Integer count(Map<String, Object> gy);
	@SelectProvider(method = "selectWhitGy", type = PJSqlProvider.class)
	List<Passagewayj> selectByPagegy(Map<String, Object> gy);
	//删除通道授权
	@Delete(" delete from "+PASSAGEWAYJTABLE+" where pjempid = #{arg0} and passagewayjid = #{arg1} ")
	void removePassagewayjByID(String myempid,String mypid);
	//查自己
	@Select(" select * from "+PASSAGEWAYJTABLE+" where pjid = #{id} ")
	Passagewayj selectPjByid(String id);
	//修改
	@SelectProvider(method = "updatePj", type = PJSqlProvider.class)
	void updatePj(Passagewayj passagewayj);
	//添加
	@SelectProvider(method = "savePJ", type = PJSqlProvider.class)
	void savePJ(Passagewayj passagewayj);
	//根据pid查询通道
	@Select(" select * from "+PASSAGEWAYJTABLE+" where passagewayjid=#{danpid}")
	Passageway selecPbypid(String Danpid);
	@Select("select * from  passagewayj_info aj , passageway_info ac where aj.pjempid=#{empid} and  ac.passagewayID=aj.passagewayjid")
	List<Passageway> getGrantAuthorization(int empid);
}
