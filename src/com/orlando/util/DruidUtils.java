package com.orlando.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;


 /** 
 * @ClassName: DruidUtils 
 * @Description: DruidUtils 数据库连接池
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:16:55 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class DruidUtils {
	private static DataSource ds;
	private static ThreadLocal<Connection> t = new ThreadLocal<>();
	static {
		Properties prop = new Properties();
		try {
			prop.load(DruidUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			prop.setProperty("driverClassName", prop.getProperty("driver"));
			prop.setProperty("url", prop.getProperty("url"));
			prop.setProperty("username", prop.getProperty("user"));
			prop.setProperty("password", prop.getProperty("password"));
			ds = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection  conn = t.get();
		if(conn == null) {
			conn = ds.getConnection();
			t.set(conn);
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	//开启事务
	public static void openTranslation() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//提交事务
	public static void commitTranslation() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//回滚事务
	public static void rollbackTranslation() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
