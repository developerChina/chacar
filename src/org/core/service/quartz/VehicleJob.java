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
			 */
			StringBuffer sb=new StringBuffer();
			sb.append("select ve.车牌号码 as VehicleCode,ve.车机号码 as DeviceId,ve.GPRS号码 as SIM,mt.name as MobileID,dr.姓名 as DriverID,vt.用途 as VehicleTypeID,cor.颜色 as ColorID,cl.Compyname as ClientId ");
			sb.append(" from Vehicle ve ");
			sb.append(" left join MobileType mt on ve.MobileID=mt.ID ");
			sb.append(" left join driver dr on ve.DriverID=dr.ID ");
			sb.append(" left join vehicletype vt on ve.VehicleTypeID=vt.ID ");
			sb.append(" left join color cor on ve.ColorID=cor.ID ");
			sb.append(" left join client cl on ve.ClientId=cl.ID ");

			ResultSet rs_remote_vehicle = SqlUtil.executeQuery(sb.toString(), sqlServerConn);
			while (rs_remote_vehicle.next()) {
				String MobileID = rs_remote_vehicle.getString("MobileID");
				String DriverID = rs_remote_vehicle.getString("DriverID");
				String VehicleTypeID = rs_remote_vehicle.getString("VehicleTypeID");
				String ColorID = rs_remote_vehicle.getString("ColorID");
				String VehicleCode = rs_remote_vehicle.getString("VehicleCode");
				String SIM = rs_remote_vehicle.getString("SIM");
				String DeviceId = rs_remote_vehicle.getString("DeviceId");
				String ClientId = rs_remote_vehicle.getString("ClientId");
				/**
				 * 本地车辆表  如果没有就添加 有就修改
				 */
				String vehicle="select * from logis_vehicle lv where lv.DeviceId='"+DeviceId+"'";
				ResultSet rs_local_vehicle = SqlUtil.executeQuery(vehicle, mySqlConn);
				
				//查 定位仪表里的临时  定位仪
				String locator="select count(*) as cnt from logis_locator t where  t.type=0 and t.deviceid='"+DeviceId+"'";
				ResultSet rs_local_locator = SqlUtil.executeQuery(locator, mySqlConn);
				Integer DeviceType=null;
				while(rs_local_locator.next()) {
					DeviceType= rs_local_locator.getInt("cnt")==0?new Integer(0):new Integer(1);
				}
				
				if(rs_local_vehicle.next()==false) {
					String insert=getOptSql("insert",MobileID,DriverID,VehicleTypeID,ColorID,VehicleCode,SIM,DeviceId,ClientId,DeviceType,"");
					//System.out.println("==insert = "+insert);
					SqlUtil.executeUpdate(insert, mySqlConn);
				}else {
					String Id=rs_local_vehicle.getString("id");
					String update=getOptSql("update",MobileID,DriverID,VehicleTypeID,ColorID,VehicleCode,SIM,DeviceId,ClientId,DeviceType,Id);
					//System.out.println("==update = "+update);
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