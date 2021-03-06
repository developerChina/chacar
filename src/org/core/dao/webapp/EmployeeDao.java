package org.core.dao.webapp;

import static org.core.util.GlobleConstants.EMPLOYEETABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.core.dao.webapp.provider.EmployeeDynaSqlProvider;
import org.core.domain.webapp.Employee;

/**   
 * @Description: EmployeeMapper接口
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public interface EmployeeDao {

	// 根据参数查询员工总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 根据参数动态查询员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",
			one=@One(select="org.core.dao.webapp.DeptDao.selectById",
		fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",
			one=@One(select="org.core.dao.webapp.JobDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Employee> selectByPage(Map<String, Object> params);
	
	// 动态插入员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);

	// 根据id删除员工
	@Delete(" delete from "+EMPLOYEETABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 根据id查询员工
	@Select("select * from "+EMPLOYEETABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	Employee selectById(Integer id);
	
	// 动态修改员工
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void update(Employee employee);

	
	@Select("select * from "+EMPLOYEETABLE+" where name = #{name} or phone = #{phone}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> getEmployeeesBy_name_phone(@Param("name")String name, @Param("phone")String phone);
	
	
	@Select("select * from "+EMPLOYEETABLE+" where name = #{name} ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> getEmployeeesBy_name(String name);
	
	@Select("select * from "+EMPLOYEETABLE+" where phone = #{phone}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> getEmployeeesBy_phone(String phone);

	@Select("select * from "+EMPLOYEETABLE+" where cardno = #{cardNo} and carstatus= #{carstatus}")
	List<Employee> findEmployeeByCardNo_carstatus(@Param("cardNo")String cardNo, @Param("carstatus")int carstatus);

	@Select("select * from "+EMPLOYEETABLE+" where cardno = #{cardno}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> getEmployeeesBy_cardno(String cardno);
	
	
	@Select("select * from "+EMPLOYEETABLE+" "
			+ "where "
			+ "name like CONCAT('%',#{name},'%') "
			+ "and cardno like CONCAT('%',#{cardno},'%') "
			+ "and phone like CONCAT('%',#{phone},'%')")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="CARD_ID",property="cardId"),
		@Result(column="POST_CODE",property="postCode"),
		@Result(column="QQ_NUM",property="qqNum"),
		@Result(column="BIRTHDAY",property="birthday",javaType=java.util.Date.class),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="DEPT_ID",property="dept",one=@One(select="org.core.dao.webapp.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="JOB_ID",property="job",one=@One(select="org.core.dao.webapp.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> getEmployeees(@Param("name")String name,@Param("cardno")String cardno,@Param("phone")String phone);

	@Select("select * from "+EMPLOYEETABLE+" where cardno = #{cardno}")
	List<Employee> addValidate(String cardno);

	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="getEmployeeByIds")
	List<Employee> getEmployeeByIds(String ids);

	
}
