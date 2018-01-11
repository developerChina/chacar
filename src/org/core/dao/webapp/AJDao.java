package org.core.dao.webapp;

import static org.core.util.GlobleConstants.ACCESSGROUPTABLE;
import static org.core.util.GlobleConstants.ACCESSJTABLE;
import static org.core.util.GlobleConstants.ACCESSTABLE;
import static org.core.util.GlobleConstants.MiddletoAGTABLE;
import static org.core.util.GlobleConstants.TotleAuthTABLE;
import static org.core.util.GlobleConstants.EMPLOYEETABLE;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.webapp.provider.AJSqlProvider;
import org.core.dao.webapp.provider.TotleAuthorizationProvider;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.AccessGroup;
import org.core.domain.webapp.Accessj;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.TotleAuthorization;

//查询所有权限
public interface AJDao {
	// 查询授权表中是否已经存在EMP对象
	@Select(" select count(*) from " + ACCESSJTABLE + " where ajemp = #{id}")
	int selectAJG(String id);

	// 查询所有门禁分组
	@Select(" select * from " + ACCESSGROUPTABLE)
	List<AccessGroup> findAGAll();

	@SelectProvider(method = "countgy", type = AJSqlProvider.class)
	int count(Map<String, Object> gy);

	@SelectProvider(method = "selectWhitGy", type = AJSqlProvider.class)
	List<Accessj> selectByPagegy(Map<String, Object> gy);

	// 删除
	@Delete(" delete from " + ACCESSJTABLE + " where ajempid = #{arg0} and ajaccessid = #{arg1} ")
	void removeAccessjByID(String myempid, String accessid);

	// 添加
	@SelectProvider(method = "saveAJ", type = AJSqlProvider.class)
	void saveAJ(Accessj accessj);

	// 查询自己
	@Select(" select * from " + ACCESSJTABLE + " where ajid = #{id}")
	Accessj selectAjByid(String id);

	// 修改
	@SelectProvider(method = "updateAj", type = AJSqlProvider.class)
	void updateAj(Accessj accessj);

	@Select(" select * from " + ACCESSJTABLE + " where ajempid = #{id}")
	List<Accessj> selectAjByEmpid(String id);

	@Select("select * from " + ACCESSTABLE + " as ai," + MiddletoAGTABLE + " as gm"
			+ " where ai.accessid=gm.accessid and agroupid=#{groupid} ORDER BY ai.acno")
	List<Access> getAccess(String groupid);

	@Select("select * from  accessj_info aj , access_info ac where aj.ajempid=#{empid} and  ac.accessid=aj.ajaccessid")
	List<Access> getGrantAuthorization(int empid);
	// 添加总权限
	@SelectProvider(method = "saveTotleAuthorization", type = TotleAuthorizationProvider.class)
	void saveTotleAuthorization(TotleAuthorization totleAuthorization);
	@SelectProvider(method = "updateTotleAuthorization", type = TotleAuthorizationProvider.class)
	void updateTotleAuthorization(TotleAuthorization totleAuthorization);
	@Select(" select * from " + TotleAuthTABLE + " where userids = #{userids}")
	TotleAuthorization selectTotleAuthorizationByid(String userids);
	
	//门禁名称的模糊查询
		@Select(" select * from "+ACCESSTABLE+" where accessname LIKE CONCAT('%',#{vague},'%')")
		List<Access> getPlist(String vague);
		@Select(" select * from "+EMPLOYEETABLE+" where name LIKE CONCAT('%',#{empName},'%')")
		List<Employee> getEmp(String empName);
		@Select(" select * from "+EMPLOYEETABLE+" where phone LIKE CONCAT('%',#{aph},'%')")
		List<Employee> getEmpByPh(String aph);
}
