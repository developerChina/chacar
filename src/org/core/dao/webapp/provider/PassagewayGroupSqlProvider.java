package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.MIDDLETOPGTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.PassagewayGroup;
import static org.core.util.GlobleConstants.PASSAGEWAYGROUPTABLE;
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
					if(passagewayGroup.getPgid()!=null && !passagewayGroup.getPgid().equals("")){
						WHERE("pgid LIKE CONCAT('%',#{passagewayGroup.pgid},'%')");
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
					if(passagewayGroup.getPgid()!=null && !passagewayGroup.getPgid().equals("")){
						WHERE("pgid LIKE CONCAT('%',#{passagewayGroup.pgid},'%')");
					}
				} 
			}
		}.toString();
	}
	//添加
	public String savePgroup(String pgname,String uuid){
		return new SQL(){
			{
				INSERT_INTO(PASSAGEWAYGROUPTABLE);
				if(pgname!=null && !pgname.equals("")){
					VALUES("pgname","#{arg0}");
				}
				if(uuid!=null && !uuid.equals("")){
					VALUES("pgid","#{arg1}");
				}
			}
		}.toString();
	}
	//修改
	public String updatePG(PassagewayGroup passagewayGroup){
		return new SQL(){
			{
				UPDATE(PASSAGEWAYGROUPTABLE);
				if(passagewayGroup.getPgname()!=null){
					SET(" pgname = #{pgname}");
				}
				WHERE(" pgid=#{pgid} ");
			}
		}.toString();
	}
	//添加到中间表
	public String addaddPGrouptoMiddle(String uuid,String id){
		return new SQL(){
			{
				INSERT_INTO(MIDDLETOPGTABLE);
				if(id!=null && !id.equals("")){
					VALUES("passagewayid","#{arg1}");
				}
				if(uuid!=null && !uuid.equals("")){
					VALUES("pgroupid","#{arg0}");
				}
			}
		}.toString();
	}

}
