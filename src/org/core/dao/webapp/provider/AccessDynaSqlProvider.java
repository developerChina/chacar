package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ACCESSTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Access;

public class AccessDynaSqlProvider {


	
	
	//分页动态查询
	public String selectWhitSgy(Map<String, Object> sgy){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(ACCESSTABLE);
				if(sgy.get("access")!=null){
					Access access=(Access) sgy.get("access");
					if(access.getAccessname()!=null && !access.getAccessname().equals("")){
						WHERE("accessname LIKE CONCAT('%',#{access.accessname},'%')");
					}
					if(access.getCip()!=null && !access.getCip().equals("")){
						WHERE("cip LIKE CONCAT('%',#{access.cip},'%')");
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
				FROM(ACCESSTABLE);
				if(sgyy.get("access") != null){
					Access access = (Access)sgyy.get("access");
					if(access.getAccessname()!= null && !access.getAccessname().equals("")){
						WHERE(" accessname LIKE CONCAT('%',#{access.accessname},'%') ");
					}
					if(access.getCip()!=null && !access.getCip().equals("")){
						WHERE("cip LIKE CONCAT('%',#{access.cip},'%')");
					}
				} 
			}
		}.toString();
	}
	//动态更新
		public String updateAccess(Access access){
		
		return new SQL(){
			{
				UPDATE(ACCESSTABLE);
				if(access.getAccessname() != null){
					SET(" accessname = #{accessname} ");
				}
				if(access.getCsn() != null){
					SET(" csn = #{csn} ");
				}
				if(access.getCip()!= null){
					SET(" cip = #{cip} ");
				}
				WHERE(" accessid = #{accessid} ");
			}
		}.toString();
	}
		//插入。。。
		public String insertAccess(Access access){
			
			return new SQL(){
				{
					INSERT_INTO(ACCESSTABLE);
					if(access.getAccessname()!=null && !access.getAccessname().equals(""));{
						VALUES("accessname","#{accessname}");
					}
					if(access.getCip()!= null && !access.getCip().equals("")){
						VALUES("cip", "#{cip}");
					}
					if(access.getCsn()!=null && !access.getCsn().equals("")){
						VALUES("csn","#{csn}");
					}
					
				}
			}.toString();
		}
		

}