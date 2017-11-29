package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.RESONTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Reson;

public class ResonDynaSqlProvid {
	
	
	//分页动态查询
	public String selectWhitGy(Map<String, Object> gy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(RESONTABLE);
				if(gy.get("reson")!=null){
					Reson reson=(Reson) gy.get("reson");
					if(reson.getContent()!=null && !reson.getContent().equals("")){
						WHERE("content LIKE CONCAT('%',#{reson.content},'%')");
					}
					/*if(reson.getRtime()!=null && !reson.getRtime().equals("")){
						WHERE("rtime LIKE CONCAT('%',#{reson.rtime},'%')");
					}*/
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
				FROM(RESONTABLE);
				if(gy.get("reson") != null){
					Reson reson = (Reson)gy.get("reson");
					if(reson.getContent()!= null && !reson.getContent().equals("")){
						WHERE(" content LIKE CONCAT('%',#{reson.content},'%') ");
					}
					/*if(reson.getRtime()!=null && !reson.getRtime().equals("")){
						WHERE(" rtime LIKE CONCAT('%',#{reson.rtime},'%')");
					}*/
				} 
			}
		}.toString();
	}
	//动态更新
		public String updateReson(Reson reson){
		
		return new SQL(){
			{
				UPDATE(RESONTABLE);
				if(reson.getContent()!= null){
					SET(" content = #{content} ");
				}
			/*	if(reson.getRtime()!=null){
					SET(" rtime = #{rtime}");
				}*/
				WHERE(" rid = #{rid} ");
			}
		}.toString();
	}
		//插入。。。
		public String insertReson(Reson reson){
			
			return new SQL(){
				{
					INSERT_INTO(RESONTABLE);
					if(reson.getContent() !=null && !reson.getContent().equals(""));{
						VALUES("content","#{content}");
					}
					/*if(reson.getRtime()!=null && !reson.getRtime().equals("")){
						VALUES("rtime","#{rtime}");
					}*/
				}
			}.toString();
		}
		
}
