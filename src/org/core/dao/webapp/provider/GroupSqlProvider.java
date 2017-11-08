package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ELEVATORGROUPTABLE;
import static org.core.util.GlobleConstants.ELEVATORTABLE;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.ElevatorGroup;

public class GroupSqlProvider {
	//查电梯组的总条数
		public String count(Map<String, Object> params){
			return new SQL(){
				{	
					SELECT("count(*)");
					FROM(ELEVATORGROUPTABLE);
					if(params.get("elevatorGroup")!=null){
						ElevatorGroup elevatorGroup = (ElevatorGroup) params.get("elevatorGroup");
						
						if(elevatorGroup.getEgname() != null && !elevatorGroup.getEgname().equals("")){
							WHERE(" egname LIKE CONCAT('%',#{elevatorGroup.egname},'%') ");
						}
						if(elevatorGroup.getEgssxj() != null && !elevatorGroup.getEgssxj().equals("")){
							WHERE(" egssxj LIKE CONCAT('%',#{elevatorGroup.egssxj},'%') ");
						}
					}
				}
			}.toString();
		}
		//查电梯组的集合
				public String selectWhitParam(Map<String, Object> params){
					String sql =  new SQL(){
						{	
							SELECT("*");
							FROM(ELEVATORGROUPTABLE);
							if(params.get("elevatorGroup")!=null){
								ElevatorGroup elevatorGroup = (ElevatorGroup) params.get("elevatorGroup");
								
								if(elevatorGroup.getEgname() != null && !elevatorGroup.getEgname().equals("")){
									WHERE(" egname LIKE CONCAT('%',#{elevatorGroup.egname},'%') ");
								}
								if(elevatorGroup.getEgssxj() != null && !elevatorGroup.getEgssxj().equals("")){
									WHERE(" egssxj LIKE CONCAT('%',#{elevatorGroup.egssxj},'%') ");
								}
							}
						}
					}.toString();
					
					if(params.get("pageModel") != null){
						sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
					}
					return sql;
				}
		
				
		
				public String selectAll(){
					String sql =  new SQL(){
						{	
							SELECT("*");
							FROM(ELEVATORTABLE);
						}
					}.toString();
					return sql;
				}
		
				// 动态插入
				public String addStringtoEG(String ids, String egname,String uuid){
					String sql =  new SQL(){
						{	
							INSERT_INTO(ELEVATORGROUPTABLE);
							if(uuid!= null && !uuid.equals("")){
								VALUES("egid", "#{arg2}");
							}
							if(ids!=null){
								VALUES("egssxj", "#{arg0}");
							}
							if(egname!= null && !egname.equals("")){
								VALUES("egname", "#{arg1}");
							}
						}
					}.toString();
					return sql;
				}
		
			//修改前查询
				public String selectElevatorByEGid(Integer id){
					String sql =  new SQL(){
						{	
							SELECT("*");
							FROM(ELEVATORTABLE);
							WHERE(" elevatorID = #{id} ");
						}
					}.toString();
					return sql;
				}
		//动态修改
				public String updateEG(ElevatorGroup elevatorGroup){
					String sql =  new SQL(){
						{	
							UPDATE(ELEVATORGROUPTABLE);
							if(elevatorGroup.getEgname() != null){
								SET(" egname = #{egname} ");
							}
							if(elevatorGroup.getEgssxj() != null){
								SET(" egssxj = #{egssxj} ");
							}
							WHERE(" egid = #{egid}");
						}
					}.toString();
					return sql;
				}
			
				
				
				
	
}
