package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.PASSAGEWAYJTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Passagewayj;

public class PJSqlProvider {
	public String selectWhitGy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(PASSAGEWAYJTABLE);
				if(gy.get("passagewayj")!=null){
					Passagewayj passagewayj=(Passagewayj) gy.get("passagewayj");
					if(passagewayj.getPjname()!=null && !passagewayj.getPjname().equals("")){
						WHERE(" pjname LIKE CONCAT('%',#{passagewayj.pjname},'%')");
					}
					if(passagewayj.getPganame()!=null && !passagewayj.getPganame().equals("")){
						WHERE(" passagewayjid in ( "+ passagewayj.getPganame() +"  ) ");
					}
					if(passagewayj.getpEmpName()!=null && !passagewayj.getpEmpName().equals("")){
						WHERE(" pjempid in ( "+ passagewayj.getpEmpName() +"  ) ");
					}
					}
			}
		}.toString();
		if(gy.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		System.out.println(sql);
		return sql;
	}
	//动态查询总数量  +','+PASSAGEWAYTABLE+','+MIDDLETOPGTABLE
	public String countgy(Map<String, Object> gy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(PASSAGEWAYJTABLE);
				if(gy.get("passagewayj") != null){
					Passagewayj passagewayj = (Passagewayj)gy.get("passagewayj");
					if(passagewayj.getPjname()!=null && !passagewayj.getPjname().equals("")){
						WHERE(" pjname LIKE CONCAT('%',#{passagewayj.pjname},'%')");
					}
					if(passagewayj.getPganame()!=null && !passagewayj.getPganame().equals("")){
						WHERE(" passagewayjid in ( "+ passagewayj.getPganame() +"  ) ");
					}
					if(passagewayj.getpEmpName()!=null && !passagewayj.getpEmpName().equals("")){
						WHERE(" pjempid in ( "+ passagewayj.getpEmpName() +"  ) ");
					}
					} 
			}
		}.toString();
	}
	public String updatePj(Passagewayj passagewayj){
		return new SQL(){
			{	
				UPDATE(PASSAGEWAYJTABLE);
				if(passagewayj.getPjname() != null){
					SET(" pjname = #{pjname} ");
				}
				if(passagewayj.getPjgroupid() != null){
					SET(" pjgroupid = #{pjgroupid} ");
				}
				WHERE(" pjid = #{pjid}");
			}
		}.toString();
	}
	
	
	public String savePJ(Passagewayj passagewayj){
		return new SQL(){
			{

				INSERT_INTO(PASSAGEWAYJTABLE);
				if(passagewayj.getPjid()!=null && !passagewayj.getPjid().equals("")){
					VALUES("pjid", "#{pjid}");
				}
				if(passagewayj.getPjname()!=null && !passagewayj.getPjname().equals("")){
					VALUES("pjname", "#{pjname}");
				}
				if(passagewayj.getPjempid()!= null && !passagewayj.getPjempid().equals("")){
					VALUES("pjempid", "#{pjempid}");
				}
				if(passagewayj.getPjgroupid()!= null && !passagewayj.getPjgroupid().equals("")){
					VALUES("pjgroupid", "#{pjgroupid}");
				}
				if(passagewayj.getPassagewayjid()!= null && !passagewayj.getPassagewayjid().equals("")){
					VALUES("passagewayjid", "#{passagewayjid}");
				}
				if(passagewayj.getPjempno()!= null && !passagewayj.getPjempno().equals("")){
					VALUES("pjempno", "#{pjempno}");
				}
				
			}
		}.toString();
	}
		


}
