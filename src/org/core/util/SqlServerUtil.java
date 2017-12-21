package org.core.util;

/**
 * 在Java中如何使用jdbc连接Sql2008数据库(转)
 * https://www.cnblogs.com/softidea/archive/2016/04/04/5351960.html
 * 
 * @author Administrator
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerUtil {

	public static void main(String[] args) {
		Connection dbConn = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		// 连接服务器和数据库ServletUser
		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ServletUser";
		String userName = "sa"; // 默认用户名
		String userPwd = "123456"; // 密码
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // 如果连接成功
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 进行资源的释放
			if (dbConn != null) {
				try {
					dbConn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}