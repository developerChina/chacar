package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ELEVATORJTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Elevatorj;

public class EJSqlProvider {

	//保存授权表!
	public String saveEj(Elevatorj elevatorj){
		String sql =  new SQL(){
			{	
				INSERT_INTO(ELEVATORJTABLE);
				if(elevatorj.getEjid()!=null && !elevatorj.getEjid().equals("")){
					VALUES("ejid", "#{ejid}");
				}
				if(elevatorj.getEjemp()!=null && !elevatorj.getEjemp().equals("")){
					VALUES("ejname", "#{ejname}");
				}
				if(elevatorj.getEjemp()!= null && !elevatorj.getEjemp().equals("")){
					VALUES("ejemp", "#{ejemp}");
				}
				if(elevatorj.getEjcard()!= null && !elevatorj.getEjcard().equals("")){
					VALUES("ejcard", "#{ejcard}");
				}
				if(elevatorj.getEjgroup()!= null && !elevatorj.getEjgroup().equals("")){
					VALUES("ejgroup", "#{ejgroup}");
				}
				if(elevatorj.getEjelevator()!= null && !elevatorj.getEjelevator().equals("")){
					VALUES("ejelevator", "#{ejelevator}");
				}
			}
		}.toString();
		
		return sql;
	}

	
	
	//查电梯授权的总条数
			public String count(Map<String, Object> params){
				return new SQL(){
					{	
						SELECT("count(*)");
						FROM(ELEVATORJTABLE);
						if(params.get("elevatorj")!=null){
							Elevatorj elevatorj = (Elevatorj) params.get("elevatorj");
							
							if(elevatorj.getEjname() != null && !elevatorj.getEjname().equals("")){
								WHERE(" ejname LIKE CONCAT('%',#{elevatorj.ejname},'%') ");
							}
							
						}
					}
				}.toString();
			}
			//查电梯授权的集合
					public String selectWhitParam(Map<String, Object> params){
						String sql =  new SQL(){
							{	
								SELECT("*");
								FROM(ELEVATORJTABLE);
								if(params.get("elevatorj")!=null){
									Elevatorj elevatorj = (Elevatorj) params.get("elevatorj");
									
									if(elevatorj.getEjname() != null && !elevatorj.getEjname().equals("")){
										WHERE(" ejname LIKE CONCAT('%',#{elevatorj.ejname},'%') ");
									}
									
								}
							}
						}.toString();
						
						if(params.get("pageModel") != null){
							sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
						}
						return sql;
					}
	
	
				//修改电梯授权表	
					public String updateEj(Elevatorj elevatorj){
						String sql =  new SQL(){
							{	
								UPDATE(ELEVATORJTABLE);
								if(elevatorj.getEjname() != null){
									SET(" ejname = #{ejname} ");
								}
								if(elevatorj.getEjgroup() != null){
									SET(" ejgroup = #{ejgroup} ");
								}
								WHERE(" ejid = #{ejid}");
							}
						}.toString();
						return sql;
					}
								
					
					
}
