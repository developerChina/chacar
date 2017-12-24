package org.core.service.quartz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.core.util.SqlUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class VehicleJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Connection sqlServerConn = SqlUtil.getSqlServerConnection("gserver_synth");
		Connection mySqlConn = SqlUtil.getMySqlConnection();
		try {
			String sql = "select *from Vehicle";// 查询test表
			PreparedStatement statement = sqlServerConn.prepareStatement(sql);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				String title = res.getString("车牌号码");
				System.out.println("车牌号码：" + title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 进行资源的释放
			if (sqlServerConn != null || mySqlConn != null) {
				try {
					sqlServerConn.close();
					mySqlConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}