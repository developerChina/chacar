package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ACCESSJTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Accessj;

public class AJSqlProvider {
	public String selectWhitGy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(ACCESSJTABLE);
				if(gy.get("accessj")!=null){
					Accessj accessj=(Accessj) gy.get("accessj");
					if(accessj.getAjname()!=null && !accessj.getAjname().equals("")){
						WHERE(" ajname LIKE CONCAT('%',#{accessj.ajname},'%')");
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
	public String countgy(Map<String, Object> gy){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(ACCESSJTABLE);
				if(gy.get("accessj") != null){
					Accessj accessj = (Accessj)gy.get("accessj");
					if(accessj.getAjname()!= null && !accessj.getAjname().equals("")){
						WHERE(" ajname LIKE CONCAT('%',#{accessj.ajname},'%') ");
					}
					} 
			}
		}.toString();
	}
	//添加
	public String saveAJ(Accessj accessj){
		return new SQL(){
			{

				INSERT_INTO(ACCESSJTABLE);
				if(accessj.getAjid()!=null && !accessj.getAjid().equals("")){
					VALUES("ajid", "#{ajid}");
				}
				if(accessj.getAjname()!=null && !accessj.getAjname().equals("")){
					VALUES("ajname", "#{ajname}");
				}
				if(accessj.getAjemp()!= null && !accessj.getAjemp().equals("")){
					VALUES("ajemp", "#{ajemp}");
				}
				if(accessj.getAjcard()!= null && !accessj.getAjcard().equals("")){
					VALUES("ajcard", "#{ajcard}");
				}
				if(accessj.getAjgroup()!= null && !accessj.getAjgroup().equals("")){
					VALUES("ajgroup", "#{ajgroup}");
				}
				if(accessj.getAjaccess()!= null && !accessj.getAjaccess().equals("")){
					VALUES("ajaccess", "#{ajaccess}");
				}
			
			}
		}.toString();
	
	}
	//修改
	public String updateAj(Accessj accessj){
		return new SQL(){
			{

				{	
					UPDATE(ACCESSJTABLE);
					if(accessj.getAjname() != null){
						SET(" ajname = #{ajname} ");
					}
					if(accessj.getAjgroup() != null){
						SET(" ajgroup = #{ajgroup} ");
					}
					WHERE(" ajid = #{ajid}");
				}

			}
		}.toString();
	}
}
