package org.core.service.quartz;

import java.sql.Connection;
import java.sql.ResultSet;

import org.core.util.SqlUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class VehicleJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			Connection sqlServerConn = SqlUtil.getSqlServerConnection("gserver_synth");
			Connection mySqlConn = SqlUtil.getMySqlConnection();
			/**
			 * 远程  阿里 定位  车辆 表信息
			 * 0:正常，1：删除

			 * 一： 先查del=0  添加修改原有数据    
			 * 二： 查 del=1 删除临时定位仪的数据
			 * 
			 */
			StringBuffer sb=new StringBuffer();
			sb.append("select ve.车牌号码 as VehicleCode,ve.车机号码 as DeviceId,ve.GPRS号码 as SIM,mt.name as MobileID,dr.姓名 as DriverID,vt.用途 as VehicleTypeID,cor.颜色 as ColorID,cl.Compyname as ClientId ");
			sb.append(" from Vehicle ve ");
			sb.append(" left join MobileType mt on ve.MobileID=mt.ID ");
			sb.append(" left join driver dr on ve.DriverID=dr.ID ");
			sb.append(" left join vehicletype vt on ve.VehicleTypeID=vt.ID ");
			sb.append(" left join color cor on ve.ColorID=cor.ID ");
			sb.append(" left join client cl on ve.ClientId=cl.ID ");
			sb.append(" where ve.del=1 ");

			//1: 先删除 
			ResultSet rs_delete_vehicle = SqlUtil.executeQuery(sb.toString(), sqlServerConn);
			while (rs_delete_vehicle.next()) {
				String DeviceId = rs_delete_vehicle.getString("DeviceId");
				String delete="delete  from logis_vehicle where  DeviceId='"+DeviceId+"'";;
				SqlUtil.executeUpdate(delete, mySqlConn);
			}
			//2: 添加  修改
			sb.delete(413, 428);
			sb.append(" where ve.del=0 ");
			ResultSet rs_remote_vehicle = SqlUtil.executeQuery(sb.toString(), sqlServerConn);
			while (rs_remote_vehicle.next()) {
				String MobileID = rs_remote_vehicle.getString("MobileID");
				String DriverID = rs_remote_vehicle.getString("DriverID");
				String VehicleTypeID = rs_remote_vehicle.getString("VehicleTypeID");
				String ColorID = rs_remote_vehicle.getString("ColorID");
				String VehicleCode = rs_remote_vehicle.getString("VehicleCode");
				String SIM = rs_remote_vehicle.getString("SIM");
				String DeviceId = rs_remote_vehicle.getString("DeviceId");
				String ClientId = rs_remote_vehicle.getString("ClientId");//176080固定车,176164临时车,178710非物流进厂车
				/**
				 * 1：固定车和非物流进厂车  进口不做设定，也不喊，都可以正常进出
				 * 2：临时车如何做删除
				 */
				Integer DeviceType=1;
				if("NULL".equals(ClientId) || "null".equals(ClientId) || ClientId==null || 
				   "临时".equals(ClientId) || "临时车".equals(ClientId) || "临时车辆".equals(ClientId))	
				{
					ClientId = "临时车辆";
					DeviceType=0;
				}
				/**
				 * 本地车辆表  如果没有就添加 有就修改
				 */
				String vehicle="select * from logis_vehicle lv where lv.DeviceId='"+DeviceId+"'"; //定位仪的编号 查
				ResultSet rs_local_vehicle = SqlUtil.executeQuery(vehicle, mySqlConn);
				if(rs_local_vehicle.next()==false) {
						String insert=getOptSql("insert",MobileID,DriverID,VehicleTypeID,ColorID,VehicleCode,SIM,DeviceId,ClientId,DeviceType,"");
						SqlUtil.executeUpdate(insert, mySqlConn);
				}else {
					String Id=rs_local_vehicle.getString("id");
					String update=getOptSql("update",MobileID,DriverID,VehicleTypeID,ColorID,VehicleCode,SIM,DeviceId,ClientId,DeviceType,Id);
					SqlUtil.executeUpdate(update, mySqlConn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 进行资源的释放
			SqlUtil.releaseConn();
		}
	}

	public static String getOptSql(String insertOrUpdate,String MobileID,String DriverID,String VehicleTypeID,
			String ColorID,String VehicleCode,String SIM,String DeviceId,String ClientId,Integer DeviceType,String Id) {
		String rsstr="";
		if("insert".equals(insertOrUpdate))
		{
			rsstr="insert into logis_vehicle(MobileID,DriverID,VehicleTypeID,ColorID,VehicleCode,SIM,DeviceId,ClientId,DeviceType) "
					+ "values('"+MobileID+"','"+DriverID+"','"+VehicleTypeID+"','"+ColorID
					+"','"+VehicleCode+"','"+SIM+"','"+DeviceId+"','"+ClientId+"',"+DeviceType+") ";
		}
		if("update".equals(insertOrUpdate))
		{
			rsstr="update  logis_vehicle set MobileID='"+MobileID+"',DriverID='"+DriverID+"',VehicleTypeID='"+VehicleTypeID+"',ColorID='"+ColorID+"'"
					+ ",VehicleCode='"+VehicleCode+"',SIM='"+SIM+"',DeviceId='"+DeviceId+"',ClientId='"+ClientId+"',DeviceType="+DeviceType+"  where id='"+Id+"'";
		}
		return rsstr;
	}
}