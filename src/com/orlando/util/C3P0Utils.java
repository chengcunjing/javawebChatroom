package com.orlando.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


 /** 
 * @ClassName: C3P0Utils 
 * @Description: C3P0Utils 数据库连接池
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:16:32 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class C3P0Utils {
    
//	static DataSource ds = new ComboPooledDataSource();
//	static DataSource ds = new ComboPooledDataSource("mysql");
	
	//线程局部变量，保存某个线程使用的连接
	private static ThreadLocal<Connection> t = new ThreadLocal<>();
	static DataSource ds = new ComboPooledDataSource("mysql");
	
	/**
	 * 
	 * @Title: getConnection
	 * @Description: 获得连接
	 * @param @return
	 * @param @throws SQLException    参数
	 * @return Connection    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月26日 上午9:47:31  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = t.get();
		if(conn == null) {
			conn = ds.getConnection();
			//将新的连接设置到ThreadLocal中便于管理
			t.set(conn);
		}
		return conn;
	}
	
	/**
	 * @Title: closeConnection
	 * @Description: 关闭连接
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月26日 上午9:47:45  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 *@throws SQLException 
	 */
	public static void closeConnection(){
		try {
			getConnection().close();
			t.remove();
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
