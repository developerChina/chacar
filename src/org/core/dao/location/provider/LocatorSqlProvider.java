package org.core.dao.location.provider;
import static org.core.domain.location.LocationConstants.LOCATOR;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.core.domain.location.LocationLocator;
public class LocatorSqlProvider {
	
	
	//分页动态查询
	public String selectByPagegy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(LOCATOR);
				if(gy.get("locationLocator")!=null){
					LocationLocator locationLocator=(LocationLocator) gy.get("locationLocator");
					if(locationLocator.getDeviceid()!=null && !"".equals(locationLocator.getDeviceid())){
						WHERE(" deviceid LIKE CONCAT('%',#{locationLocator.deviceid},'%')");
					}
					if(locationLocator.getName()!=null && !"".equals(locationLocator.getName())){
						WHERE(" name LIKE CONCAT('%',#{locationLocator.name},'%')");
					}
					if(locationLocator.getSim()!=null && !"".equals(locationLocator.getSim())){
						WHERE(" sim LIKE CONCAT('%',#{locationLocator.sim},'%')");
					}
					if(locationLocator.getType()!=null && !"".equals(locationLocator.getType())){
						WHERE(" type LIKE CONCAT('%',#{locationLocator.type},'%')");
					}
				}
			}
		}.toString();
		if(gy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	//动态查询总数量
	public String count(Map<String, Object> gy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(LOCATOR);
				if(gy.get("locationLocator")!=null){
					LocationLocator locationLocator=(LocationLocator) gy.get("locationLocator");
					if(locationLocator.getDeviceid()!=null && !"".equals(locationLocator.getDeviceid())){
						WHERE(" deviceid LIKE CONCAT('%',#{locationLocator.deviceid},'%')");
					}
					if(locationLocator.getName()!=null && !"".equals(locationLocator.getName())){
						WHERE(" name LIKE CONCAT('%',#{locationLocator.name},'%')");
					}
					if(locationLocator.getSim()!=null && !"".equals(locationLocator.getSim())){
						WHERE(" sim LIKE CONCAT('%',#{locationLocator.sim},'%')");
					}
					if(locationLocator.getType()!=null && !"".equals(locationLocator.getType())){
						WHERE(" type LIKE CONCAT('%',#{locationLocator.type},'%')");
					}
				}
			}
		}.toString();
	}
	//动态更新
		public String modifyLocationLocator(LocationLocator locationLocator){
		return new SQL(){
			{
				UPDATE(LOCATOR);
				if(locationLocator.getDeviceid()!= null){
					SET(" deviceid = #{deviceid} ");
				}
				if(locationLocator.getName() != null){
					SET(" name = #{name} ");
				}
				if(locationLocator.getSim()!= null){
					SET(" sim = #{sim} ");
				}
				if(locationLocator.getType() !=null){
					SET(" type = #{type}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
		//插入。。。
		public String addLocationLocator(LocationLocator locationLocator){
			
			return new SQL(){
				{
					INSERT_INTO(LOCATOR);
					if(locationLocator.getDeviceid() !=null && ! "".equals(locationLocator.getDeviceid()));{
						VALUES("deviceid","#{deviceid}");
					}
					if(locationLocator.getName() != null && !"".equals(locationLocator.getName())){
						VALUES("name", "#{name}");
					}
					if(locationLocator.getSim()!=null && !"".equals(locationLocator.getSim())){
						VALUES("sim","#{sim}");
					}
					if(locationLocator.getType() !=null && !"".equals(locationLocator.getType())){
						VALUES("type","#{type}");
					}
					if(locationLocator.getId() !=null && !"".equals(locationLocator.getId())){
						VALUES("id","#{id}");
					}
					
				}
			}.toString();
		}
}
