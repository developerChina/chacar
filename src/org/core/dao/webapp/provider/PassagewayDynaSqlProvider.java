package org.core.dao.webapp.provider;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Passageway;
import org.core.util.StringUtils;

import static org.core.util.GlobleConstants.PASSAGEWAYTABLE;
import java.util.Map;
public class PassagewayDynaSqlProvider {
	
	
		//分页动态查询
		public String selectWhitGy(Map<String, Object> gy){
			String sql=new SQL(){
				{
					SELECT("*");
					FROM(PASSAGEWAYTABLE);
					if(gy.get("passageway")!=null){
						Passageway passageway=(Passageway) gy.get("passageway");
						if(passageway.getPassagewayName()!=null && !passageway.getPassagewayName().equals("")){
							WHERE("passagewayName LIKE CONCAT('%',#{passageway.passagewayName},'%')");
						}
						if(passageway.getControllerIP()!=null && !passageway.getControllerIP().equals("")){
							WHERE("ControllerIP LIKE CONCAT('%',#{passageway.ControllerIP},'%')");
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
					FROM(PASSAGEWAYTABLE);
					if(gy.get("passageway") != null){
						Passageway passageway = (Passageway)gy.get("passageway");
						if(passageway.getPassagewayName() != null && !passageway.getPassagewayName().equals("")){
							WHERE(" passagewayName LIKE CONCAT('%',#{passageway.passagewayName},'%') ");
						}
						if(passageway.getControllerIP()!=null && !passageway.getControllerIP().equals("")){
							WHERE("ControllerIP LIKE CONCAT('%',#{passageway.ControllerIP},'%')");
						}
					} 
				}
			}.toString();
		}
		//动态更新
			public String updatePassageway(Passageway passageway){
			
			return new SQL(){
				{
					UPDATE(PASSAGEWAYTABLE);
					if(passageway.getPassagewayName() != null){
						SET(" passagewayName = #{passagewayName} ");
					}
					if(passageway.getControllerSN() != null){
						SET(" ControllerSN = #{ControllerSN} ");
					}
					if(passageway.getControllerIP()!= null){
						SET(" ControllerIP = #{ControllerIP} ");
					}
					if(passageway.getPtype() !=null){
						SET("ptype = #{ptype}");
					}
					if(passageway.getPno() !=null){
						SET("pno = #{pno}");
					}
					WHERE(" passagewayID = #{passagewayID} ");
				}
			}.toString();
		}
			//插入。。。
			public String insertPassageway(Passageway passageway){
				
				return new SQL(){
					{
						INSERT_INTO(PASSAGEWAYTABLE);
						if(passageway.getPassagewayName() !=null && !passageway.getPassagewayName().equals(""));{
							VALUES("passagewayName","#{passagewayName}");
						}
						if(passageway.getControllerIP() != null && !passageway.getControllerIP().equals("")){
							VALUES("ControllerIP", "#{ControllerIP}");
						}
						if(passageway.getControllerSN()!=null && !passageway.getControllerSN().equals("")){
							VALUES("ControllerSN","#{ControllerSN}");
						}
						if(passageway.getPtype() !=null && !passageway.getPtype().equals("")){
							VALUES("ptype","#{ptype}");
						}
						if(passageway.getPno() !=null && !passageway.getPno().equals("")){
							VALUES("pno","#{pno}");
						}
						
					}
				}.toString();
			}
			
			
			public String selectByIds(String ids) {
				String where="";
				if(StringUtils.isNotBlank(ids)){
					where=" passagewayID in ( "+ ids +" )";
				}else{
					where=" 1=0 ";
				}
				String sql ="select * from " +PASSAGEWAYTABLE +" where "+ where;
				return sql;
			}
			
}