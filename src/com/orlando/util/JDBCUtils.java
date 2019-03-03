package com.orlando.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

 /** 
 * @ClassName: JDBCUtils 
 * @Description: JDBC数据库连接池
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:17:09 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class JDBCUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	static {
		Properties prop = new Properties();
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			prop.load(is);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void closeConnection(Connection conn,PreparedStatement ps,ResultSet rs){
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if(conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	
}
