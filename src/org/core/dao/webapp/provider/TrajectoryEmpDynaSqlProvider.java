package org.core.dao.webapp.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.TrajectoryEmp;
import org.core.util.DateStyle;
import org.core.util.DateUtil;
import org.core.util.StringUtils;

public class TrajectoryEmpDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(TrajectoryEmp.tableName);
				if(params.get("entity") != null){
					TrajectoryEmp entity = (TrajectoryEmp) params.get("entity");
					if(StringUtils.isNotBlank(entity.getCardno())){
						WHERE(" cardno in ("+entity.getCardno()+") ");
					}
					if(entity.getStartTime()!=null){
						WHERE(" optTime >= '"+DateUtil.DateToString(entity.getStartTime(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"'" );
					}
					if(entity.getEndTime()!=null){
						WHERE(" optTime <= '"+DateUtil.DateToString(entity.getEndTime(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"'" );
					}
					if(entity.getTrajectoryDept()!=null&&!"".equals(entity.getTrajectoryDept())){
						WHERE(" cardno in ("+ entity.getTrajectoryDept() +" ) ");
					}
				}
				ORDER_BY(" cardno, optTime DESC ");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	// 动态查询总数量
	public String count(Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(TrajectoryEmp.tableName);
				if(params.get("entity") != null){
					TrajectoryEmp entity = (TrajectoryEmp) params.get("entity");
					if(StringUtils.isNotBlank(entity.getCardno())){
						WHERE(" cardno in ("+entity.getCardno()+") ");
					}
					if(entity.getStartTime()!=null){
						WHERE(" optTime > '"+DateUtil.DateToString(entity.getStartTime(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"'" );
					}
					if(entity.getEndTime()!=null){
						WHERE(" optTime < '"+DateUtil.DateToString(entity.getEndTime(), DateStyle.YYYY_MM_DD_HH_MM_SS)+"'" );
					}
					if(entity.getTrajectoryDept()!=null&&!"".equals(entity.getTrajectoryDept())){
						WHERE(" cardno in ("+ entity.getTrajectoryDept() +" ) ");
					}
				}
			}
		}.toString();
	}	
}
