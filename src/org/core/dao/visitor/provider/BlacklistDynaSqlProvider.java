package org.core.dao.visitor.provider;

import static org.core.util.GlobleConstants.BLACKLISTTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Blacklist;

public class BlacklistDynaSqlProvider {

	public String count(Map<String, Object> params){
		
		return new SQL(){
			{	
				SELECT("count(*)");
				FROM(BLACKLISTTABLE);
				if(params.get("blacklist")!=null){
					Blacklist blacklist = (Blacklist) params.get("blacklist");
					
					if(blacklist.getBlacklistName() != null && !blacklist.getBlacklistName().equals("")){
						WHERE(" blacklistName LIKE CONCAT('%',#{blacklist.blacklistName},'%') ");
					}
					if(blacklist.getCompany() != null && !blacklist.getCompany().equals("")){
						WHERE(" company LIKE CONCAT('%',#{blacklist.company},'%') ");
					}
				}
			}
			
		}.toString();
	}
	
	// 分页动态查询
		public String selectWhitParam(Map<String, Object> params){
			String sql =  new SQL(){
				{
					SELECT("*");
					FROM(BLACKLISTTABLE);
					if(params.get("blacklist")!=null){
						Blacklist blacklist = (Blacklist) params.get("blacklist");
						
						if(blacklist.getBlacklistName() != null && !blacklist.getBlacklistName().equals("")){
							WHERE(" blacklistName LIKE CONCAT('%',#{blacklist.blacklistName},'%') ");
						}
						if(blacklist.getCompany() != null && !blacklist.getCompany().equals("")){
							WHERE(" company LIKE CONCAT('%',#{blacklist.company},'%') ");
						}
					}	
				}
			}.toString();
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}
		
		
		
	//动态插入
	public String insertBlack(Blacklist blacklist){
			
			return new SQL(){
				{
					INSERT_INTO(BLACKLISTTABLE);
					if(blacklist.getBlacklistName() != null && !blacklist.getBlacklistName().equals("")){
						VALUES("blacklistName", "#{blacklistName}");
					}
					if(blacklist.getCompany() != null && !blacklist.getCompany().equals("")){
						VALUES("company", "#{company}");
					}
					if(blacklist.getIdNumber() != null && !blacklist.getIdNumber().equals("")){
						VALUES("idNumber", "#{idNumber}");
					}
					if(blacklist.getReason() != null && !blacklist.getReason().equals("")){
						VALUES("reason", "#{reason}");
					}
					
					
				}
			}.toString();
		}	
		
		
		//查询访客信息
	public String myCount(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(VisitorInfo.tableName);
				if(params.get("entity")!=null){
					VisitorInfo entity = (VisitorInfo) params.get("entity");
					if(entity.getCardName() != null && !entity.getCardName().equals("")){
						WHERE(" cardName LIKE CONCAT('%',#{entity.cardName},'%') ");
					}
					if(entity.getCompany() != null && !entity.getCompany().equals("")){
						WHERE(" company LIKE CONCAT('%',#{entity.company},'%') ");
					}
					/*Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
					for (Map.Entry<String, Object> entry : map.entrySet()) { 
						WHERE(entry.getKey()+"="+"#{"+entry.getKey()+"}");
					}*/
				}
				
			}
		}.toString();

	}
		
public String myselectByPage(Map<String, Object> params) {
	String sql = new SQL() {
		{
			SELECT("*");
			FROM(VisitorInfo.tableName);
			if(params.get("entity")!=null){
				VisitorInfo entity = (VisitorInfo) params.get("entity");
				if(entity.getCardName() != null && !entity.getCardName().equals("")){
					WHERE(" cardName LIKE CONCAT('%',#{entity.cardName},'%') ");
				}
				if(entity.getCompany() != null && !entity.getCompany().equals("")){
					WHERE(" company LIKE CONCAT('%',#{entity.company},'%') ");
				}
				/*Map<String, Object> map=BeanUtil.getFiledsInfo(entity,"tableName,serialVersionUID");
				for (Map.Entry<String, Object> entry : map.entrySet()) { 
					WHERE(entry.getKey()+"="+"#{"+entry.getKey()+"}");
				}*/
			}
			
		}
	}.toString();

	if(params.get("pageModel") != null){
		sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize} ";
	}
	
	return sql;
}
		
		
		
		
		
		
		
		
		
}
