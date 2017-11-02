package org.core.dao.webapp.provider;

import static org.core.util.GlobleConstants.ELEVATORTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.core.domain.webapp.Elevator;

public class ElevatorDynaSqlProvider {
	
	//查电梯总条数
	public String count(Map<String, Object> params){
		
		return new SQL(){
			{	
				SELECT("count(*)");
				FROM(ELEVATORTABLE);
				if(params.get("elevator")!=null){
					Elevator elevator = (Elevator) params.get("elevator");
					
					if(elevator.getElevatorName() != null && !elevator.getElevatorName().equals("")){
						WHERE(" ElevatorName LIKE CONCAT('%',#{elevator.elevatorName},'%') ");
					}
					if(elevator.getControllerSN() != null && !elevator.getControllerSN().equals("")){
						WHERE(" ControllerSN LIKE CONCAT('%',#{elevator.controllerSN},'%') ");
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
					FROM(ELEVATORTABLE);
					if(params.get("elevator")!=null){
						Elevator elevator = (Elevator) params.get("elevator");
						if(elevator.getElevatorName() != null && !elevator.getElevatorName().equals("")){
							WHERE(" ElevatorName LIKE CONCAT('%',#{elevator.elevatorName},'%') ");
						}
						if(elevator.getControllerSN() != null && !elevator.getControllerSN().equals("")){
							WHERE(" ControllerSN LIKE CONCAT('%',#{elevator.controllerSN},'%') ");
						}
					}
				}
			}.toString();
			
			if(params.get("pageModel") != null){
				sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
			}
			
			return sql;
		}
		
		// 动态插入
		public String insertElevator(Elevator elevator){
			
			return new SQL(){
				{
					INSERT_INTO(ELEVATORTABLE);
					if(elevator.getElevatorName() != null && !elevator.getElevatorName().equals("")){
						VALUES("elevatorName", "#{elevatorName}");
					}
					if(elevator.getFloorNumber() != null && !elevator.getFloorNumber().equals("")){
						VALUES("floorNumber", "#{floorNumber}");
					}
					if(elevator.getControllerSN() != null && !elevator.getControllerSN().equals("")){
						VALUES("controllerSN", "#{controllerSN}");
					}
					if(elevator.getControllerIP() != null && !elevator.getControllerIP().equals("")){
						VALUES("controllerIP", "#{controllerIP}");
					}
					
					
				}
			}.toString();
		}	
		
		
		
		// 动态更新
				public String updateElevator(Elevator elevator){
					
					return new SQL(){
						{
							UPDATE(ELEVATORTABLE);
							if(elevator.getElevatorName() != null){
								SET(" elevatorname = #{elevatorName} ");
							}
							if(elevator.getFloorNumber() != null){
								SET(" floornumber = #{floorNumber} ");
							}
							if(elevator.getControllerSN() != null){
								SET(" controllersn = #{controllerSN} ");
							}
							if(elevator.getControllerIP() != null){
								SET(" controllerip = #{controllerIP} ");
							}
							WHERE(" elevatorid = #{elevatorID} ");
						}
					}.toString();
				}		
		
		
		
		
		
		
	
	
	
}
