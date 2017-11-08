package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.PASSAGEWAYGROUPTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.PassagewayGroup;

public class PassagewayGroupSqlProvider {
	/*
	 * 通道分组
	 * */
//分页动态查询
	public String selectWhitSgy(Map<String, Object> sgy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(PASSAGEWAYGROUPTABLE);
				if(sgy.get("passagewayGroup")!=null){
					
					PassagewayGroup passagewayGroup=(PassagewayGroup) sgy.get("passagewayGroup");
					if(passagewayGroup.getPgname()!=null && !passagewayGroup.getPgname().equals("")){
						WHERE("pgname LIKE CONCAT('%',#{passagewayGroup.pgname},'%')");
					}
					if(passagewayGroup.getPgssxj()!=null && !passagewayGroup.getPgssxj().equals("")){
						WHERE("pgssxj LIKE CONCAT('%',#{passagewayGroup.pgssxj},'%')");
					}
				}
			}
		}.toString();
		if(sgy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		return sql;
	}
	//动态查询总数量
	public String countsgy(Map<String, Object> sgyy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(PASSAGEWAYGROUPTABLE);
				if(sgyy.get("passagewayGroup") != null){
					
					PassagewayGroup passagewayGroup = (PassagewayGroup)sgyy.get("passagewayGroup");
					if(passagewayGroup.getPgname()!= null && !passagewayGroup.getPgname().equals("")){
						WHERE(" pgname LIKE CONCAT('%',#{passagewayGroup.pgname},'%') ");
					}
					if(passagewayGroup.getPgssxj()!=null && !passagewayGroup.getPgssxj().equals("")){
						WHERE("pgssxj LIKE CONCAT('%',#{passagewayGroup.pgssxj},'%')");
					}
				} 
			}
		}.toString();
	}
	//添加
	public String savePgroup(String pgname,String ids,String uuid){
		return new SQL(){
			{
				INSERT_INTO(PASSAGEWAYGROUPTABLE);
				if(ids!=null){
					VALUES("pgssxj","#{arg0}");
				}
				if(pgname!=null && !pgname.equals("")){
					VALUES("pgname","#{arg1}");
				}
				if(uuid!=null && !uuid.equals("")){
					VALUES("pgid","#{arg2}");
				}
			}
		}.toString();
	}
	public String updatePG(PassagewayGroup passagewayGroup){
		return new SQL(){
			{
				UPDATE(PASSAGEWAYGROUPTABLE);
				if(passagewayGroup.getPgname()!=null){
					SET(" pgname = #{pgname}");
				}
				if(passagewayGroup.getPgssxj()!=null){
					SET(" pgssxj = #{pgssxj}");
				}
				WHERE(" pgid=#{pgid}");
			}
		}.toString();
	}
}
