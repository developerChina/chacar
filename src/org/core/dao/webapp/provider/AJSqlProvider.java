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
				if(accessj.getAjempid()!= null && !accessj.getAjempid().equals("")){
					VALUES("ajempid", "#{ajempid}");
				}
				if(accessj.getAjgroupid()!= null && !accessj.getAjgroupid().equals("")){
					VALUES("ajgroupid", "#{ajgroupid}");
				}
				if(accessj.getAjaccessid()!= null && !accessj.getAjaccessid().equals("")){
					VALUES("ajaccessid", "#{ajaccessid}");
				}
				if(accessj.getAjempno()!= null && !accessj.getAjempno().equals("")){
					VALUES("ajempno", "#{ajempno}");
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
					if(accessj.getAjgroupid() != null){
						SET(" ajgroupid = #{ajgroupid} ");
					}
					WHERE(" ajid = #{ajid}");
				}

			}
		}.toString();
	}
}
