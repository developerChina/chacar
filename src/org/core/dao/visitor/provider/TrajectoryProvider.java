package org.core.dao.visitor.provider;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.visitor.Trajectory;;

/**
 * @Description: 动态SQL语句提供类
 */
public class TrajectoryProvider {		

	 
	
	public String selectByPage(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(Trajectory.tableName);
				if(params.get("entity") != null){
					
					Trajectory entity = (Trajectory) params.get("entity");
					if(entity.getCardNo()!= null && !"".equals(entity.getCardNo())){
						if(entity.getCardNo().contains(",")){
							WHERE(" cardNo in ( "+entity.getCardNo()+" )");
						}else{
							WHERE(" cardNo = #{entity.cardNo} ");
						}
					}
					
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" optDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" optDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" optDate <= #{endDate}  "); }
					}
				}
				ORDER_BY("optDate desc");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}
	public String count(Map<String, Object> params) {
		String sql =  new SQL(){
			{
				SELECT("count(*)");
				FROM(Trajectory.tableName);
				if(params.get("entity") != null){
					
					Trajectory entity = (Trajectory) params.get("entity");
					if(entity.getCardNo()!= null && "".equals(entity.getCardNo())){
						if(entity.getCardNo().contains(",")){
							WHERE(" cardNo in ( "+entity.getCardNo()+" )");
						}else{
							WHERE(" cardNo = #{entity.cardNo} ");
						}
					}
					
					Date startDate = (Date) params.get("startDate");
					Date endDate = (Date) params.get("endDate");
					if(startDate!=null&&endDate!=null){
						WHERE(" optDate  BETWEEN #{startDate} AND #{endDate} ");
					}else{
						if(startDate!=null){ WHERE(" optDate >= #{startDate}  "); }
						if(endDate!=null){ WHERE(" optDate <= #{endDate}  "); }
					}
				}
			}
		}.toString();
		return sql;
	}

}
