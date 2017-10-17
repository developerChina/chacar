package org.core.dao.example;

import java.util.List;
import java.util.Map;
import static org.core.util.GlobleConstants.EXAMPLETABLE;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.core.dao.example.provider.ExampleDynaSqlProvider;
import org.core.domain.Example;


/**   
 * @Description: Mapper接口
 */
public interface ExampleDao {
		
		// 根据登录名和密码查询员工
		@Select("select * from "+EXAMPLETABLE+" where loginname = #{loginname} and password = #{password}")
		Example selectByLoginnameAndPassword(
				@Param("loginname") String loginname,
				@Param("password") String password);
		
		// 根据id查询用户
		@Select("select * from "+EXAMPLETABLE+" where ID = #{id}")
		Example selectById(Integer id);
		
		// 根据id删除用户
		@Delete(" delete from "+EXAMPLETABLE+" where id = #{id} ")
		void deleteById(Integer id);
			
		// 动态修改用户
		@SelectProvider(type=ExampleDynaSqlProvider.class,method="updateExample")
		void update(Example example);
			
		// 动态查询
		@SelectProvider(type=ExampleDynaSqlProvider.class,method="selectWhitParam")
		List<Example> selectByPage(Map<String, Object> params);
		
		// 根据参数查询用户总数
		@SelectProvider(type=ExampleDynaSqlProvider.class,method="count")
		Integer count(Map<String, Object> params);
		
		// 动态插入用户
		@SelectProvider(type=ExampleDynaSqlProvider.class,method="insertExample")
		void save(Example example);
}
