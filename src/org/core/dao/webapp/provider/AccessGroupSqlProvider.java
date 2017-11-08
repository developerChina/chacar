package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ACCESSGROUPTABLE;
import static org.core.util.GlobleConstants.MiddletoAGTABLE;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.AccessGroup;

public class AccessGroupSqlProvider {
		/*
		 * 门禁分组
		 * */
	//分页动态查询
		public String selectWhitSgy(Map<String, Object> sgy){
			String sql=new SQL(){
				{
					SELECT("*");
					FROM(ACCESSGROUPTABLE);
					if(sgy.get("accessGroup")!=null){
						AccessGroup accessGroup=(AccessGroup) sgy.get("accessGroup");
						if(accessGroup.getAgname()!=null && !accessGroup.getAgname().equals("")){
							WHERE(" agname LIKE CONCAT('%',#{accessGroup.agname},'%') ");
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
					FROM(ACCESSGROUPTABLE);
					if(sgyy.get("accessGroup") != null){
						AccessGroup accessGroup = (AccessGroup)sgyy.get("accessGroup");
						if(accessGroup.getAgname()!= null && !accessGroup.getAgname().equals("")){
							WHERE(" agname LIKE CONCAT('%',#{accessGroup.agname},'%') ");
						}
					} 
				}
			}.toString();
		}
		//添加
		public String saveAgroup(String agname,String uuid){
			return new SQL(){
				{
					INSERT_INTO(ACCESSGROUPTABLE);
					if(agname!=null && !agname.equals("")){
						VALUES("agname","#{arg0}");
					}
					if(uuid!=null && !uuid.equals("")){
						VALUES("agid","#{arg1}");
					}
				}
			}.toString();
		}
		
		
		
		public String addaddAGrouptoMiddle(String id,String uuid){
			return new SQL(){
				{
					INSERT_INTO(MiddletoAGTABLE);
					if(id!=null && !id.equals("")){
						VALUES("agroupid","#{arg0}");
					}
					if(uuid!=null && !uuid.equals("")){
						VALUES("accessid","#{arg1}");
					}
				}
			}.toString();
		}
		
		
		
		
		
		
		
		
		
		public String updateAG(AccessGroup accessGroup){
			return new SQL(){
				{
					UPDATE(ACCESSGROUPTABLE);
					if(accessGroup.getAgname()!=null){
						SET(" agname = #{agname}");
					}
					if(accessGroup.getAgssxj()!= null){
						SET(" agssxj = #{agssxj} ");
					}
					WHERE(" agid = #{agid}");
				}
			}.toString();
		}
	}
