package org.core.dao.visitor;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.visitor.provider.RecordBevisitedsProvider;
import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.webapp.Employee;
/**   
 * @Description: Mapper接口
 */
public interface RecordBevisitedsDao {
	@SelectProvider(type=RecordBevisitedsProvider.class,method="save")
	void save(RecordBevisiteds entity);
	
	@Delete(" delete from "+RecordBevisiteds.tableName+" where recordBVID = #{id} ")
	void deleteById(String id);
		
	@SelectProvider(type=RecordBevisitedsProvider.class,method="update")
	void update(RecordBevisiteds entity);
	
	@Select("select * from "+RecordBevisiteds.tableName+" where recordBVID = #{id}")
	RecordBevisiteds selectById(String id);
	
	@SelectProvider(type=RecordBevisitedsProvider.class,method="selectByPage")
	List<RecordBevisiteds> selectByPage(RecordBevisiteds entity);
	
	@Select("select * from "+RecordBevisiteds.tableName+" where recordID = #{recordid}")
	RecordBevisiteds selectBevisitedByRecordId(String recordid);

	@Delete(" delete from "+RecordBevisiteds.tableName+" where recordID = #{recordid} ")
	void deleteByRecordID(String recordid);

	@Select("SELECT * from employee_inf where id in "
			+ "("
				+ "SELECT DISTINCT(bevisitedID) from record_bevisiteds "
				+ "where recordID in "
				+ "("
					+ "SELECT recordID  from record_visitors where cardNo=#{cardNo} and  DATE_FORMAT(inDate,'%Y-%m-%d') =DATE_FORMAT(#{optDate},'%Y-%m-%d') and visitStatus>2 "
				+ ")"
			+ ")" )
	List<Employee> selectBycardNo(@Param("cardNo")String cardNo,@Param("optDate")Date optDate);
}
