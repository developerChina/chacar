package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.DEPTTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Dept;;

/**   
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */
public class DeptDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(DEPTTABLE);
				if(params.get("dept") != null){
					Dept dept = (Dept) params.get("dept");
					if(dept.getName() != null && !dept.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
					}
					if(null!=dept.getPid()){
						WHERE(" pid = #{dept.pid} ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(DEPTTABLE);
				if(params.get("dept") != null){
					Dept dept = (Dept) params.get("dept");
					if(dept.getName() != null && !dept.getName().equals("")){
						WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
					}
					if(null!=dept.getPid()){
						WHERE(" pid = #{dept.pid} ");
					}
				}
			}
		}.toString();
	}	
	// 动态插入
	public String insertDept(Dept dept){
		
		return new SQL(){
			{
				INSERT_INTO(DEPTTABLE);
				if(dept.getName() != null && !dept.getName().equals("")){
					VALUES("name", "#{name}");
				}
				if(dept.getPid() != null && !dept.getPid().equals("")){
					VALUES("pid", "#{pid}");
				}
				if(dept.getRemark() != null && !dept.getRemark().equals("")){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateDept(Dept dept){
		return new SQL(){
			{
				UPDATE(DEPTTABLE);
				if(dept.getName() != null){
					SET(" name = #{name} ");
				}
				if(dept.getPid() != null ){
					SET(" pid = #{pid} ");
				}
				if(dept.getRemark() != null){
					SET(" remark = #{remark} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
