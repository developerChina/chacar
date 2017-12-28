package org.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlUtil {

	private static Connection dbConn;
	private static Statement stmt ;
	private static ResultSet resultSet;
	
	public static Connection getSqlServerConnection(String dbname) {
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		// 连接服务器和数据库ServletUser
		String dbURL = "jdbc:sqlserver://" + getSys().getProperty("dbIp") + ":14533; DatabaseName=" + dbname;
		String userName = getSys().getProperty("userName"); // 默认用户名
		String userPwd = getSys().getProperty("userPwd"); // 密码
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}

	public static Connection getMySqlConnection() {
		try {
			Class.forName(getDb().getProperty("dataSource.driverClass"));
			dbConn = DriverManager.getConnection(getDb().getProperty("dataSource.jdbcUrl"),
					getDb().getProperty("dataSource.user"), getDb().getProperty("dataSource.password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
	}

	public static ResultSet executeQuery(String SQL, Connection dbConn) {
		try {
			stmt = dbConn.createStatement();
			resultSet = stmt.executeQuery(SQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public static boolean executeUpdate(String SQL, Connection dbConn) {
		try {
			stmt = dbConn.createStatement();
			int result = stmt.executeUpdate(SQL);
			if (result > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (dbConn != null) {
			try {
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 根据路劲获取配置文件
	 * 
	 * @param path
	 * @return
	 */
	public static Properties getPropertiesFile(String path) {
		Properties prop = new Properties();
		try {
			InputStream inStream = PropUtil.class.getClassLoader().getResourceAsStream(path);
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 获取sys.properties配置文件
	 * 
	 * @return
	 */
	public static Properties getSys() {
		Properties prop = getPropertiesFile("sqlserver.properties");
		return prop;
	}

	/**
	 * 获取sys.properties配置文件的值
	 * 
	 * @return
	 */
	public static String getDbValue(String key) {
		return getDb().getProperty(key);
	}

	/**
	 * 获取sys.properties配置文件
	 * 
	 * @return
	 */
	public static Properties getDb() {
		Properties prop = getPropertiesFile("db.properties");
		return prop;
	}

	/**
	 * 获取sys.properties配置文件的值
	 * 
	 * @return
	 */
	public static String getSysValue(String key) {
		return getSys().getProperty(key);
	}

	public static void main(String[] args) {
		Connection dbConn = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		// 连接服务器和数据库ServletUser
		String dbURL = "jdbc:sqlserver://120.79.82.175:14533; DatabaseName=gserver_synth";
		String userName = "sa"; // 默认用户名
		String userPwd = "QAZwsx@123"; // 密码
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("Connection Successful!"); // 如果连接成功

			String sql = "select *from Vehicle";// 查询test表
			PreparedStatement statement = dbConn.prepareStatement(sql);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				String title = res.getString("车牌号码");// 获取test_name列的元素 ;
				System.out.println("车牌号码：" + title);
			}

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