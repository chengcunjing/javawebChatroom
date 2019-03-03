package com.orlando.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



 /** 
 * @ClassName: DBUtils 
 * @Description: 执行数据库更新和查询的公共类
 * @author: 章征武【orlando】
 * @date: 2018年9月10日 下午2:05:27 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class DBUtils {

	private String driver = "com.mysql.jdbc.Driver";   //driver 默认值
	private String url = "jdbc:mysql://127.0.0.1:3306/chatroom?characterEncoding=utf-8";
	private String user = "root";
	private String password = "root";
	private String ipaddress = "127.0.0.1";
	private int port = 3306;
	private String dbname = "test";
	private String charset = "utf-8";
	private Connection conn;
	
	/**
	 * 适用于任何数据库，需要提供数据库的driver，url，user，password
	 * @param driver
	 * @param url
	 * @param user
	 * @param password
	 */
	public DBUtils(String driver,String url,String user,String password){
		if(driver != null){
			this.driver = driver;
		}
		if(url != null){
			this.url = url;
		}
		if(user != null){
			this.user = user;
		}
		if(password != null){
			this.password = password;
		}
		loadDriverClass();
	}
	
	
	/**
	 * 适合mysql数据库的所有实例，可以设置url，但是user 和 password 不可变
	 */
	public DBUtils(){
		this(null, null, null, null);
	}
	
	/**
	 * 使用与mysql数据库的所有 database，需要提供user,password
	 * @param user
	 * @param password
	 */
	public DBUtils(String user,String password){
		this(null, null, user, password);
	}
	
	/**
	 * 适合所有的数据库，url可以改变，但是账号和密码必须一致
	 * @param driver
	 */
	public DBUtils(String driver){
		this(driver, null, null, null);
	}
	
	/**
	 * 适用于mysql数据库的所有 database，url 默认 test数据库表，需要提供 url, user ,password
	 * @param url
	 * @param user
	 * @param password
	 */
	public DBUtils(String url,String user,String password){
		this(null, url, user, password);
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
		this.url = "jdbc:mysql://"+this.ipaddress+":"+this.port+"/"+this.dbname+"?characterEncoding="+this.charset;
	}
	public void setPort(int port) {
		this.port = port;
		this.url = "jdbc:mysql://"+this.ipaddress+":"+this.port+"/"+this.dbname+"?characterEncoding="+this.charset;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
		this.url = "jdbc:mysql://"+this.ipaddress+":"+this.port+"/"+this.dbname+"?characterEncoding="+this.charset;
	}
	/**
	 * 加载驱动
	 */
	public void loadDriverClass(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	
	/**
	 * 获取PreparedStatement对象
	 * @param sql
	 * @param params 
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String sql, Object[] params) throws SQLException{
		PreparedStatement ps = getConnection().prepareStatement(sql);
		if(params != null && params.length > 0){
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
		}
		return ps;
	}
	
	/**
	 * 执行更新（增加，修改，删除）
	 * @param sql
	 * @return 返回影响的更新条数
	 * @throws SQLException
	 */
	public int doUpdate(String sql,Object...params) throws SQLException{
		int num = getPreparedStatement(sql,params).executeUpdate();
		return num;
	}
	
	
	/**
	 * 执行查询
	 * @param sql
	 * @return 返回ResultSet
	 * @throws SQLException
	 */
	public ResultSet doQuery(String sql, Object...params) throws SQLException{
		ResultSet rs = getPreparedStatement(sql,params).executeQuery();
		return rs;
	}
	
	
	/**
	 * 查询结果
	 * @param rs
	 * @throws SQLException
	 */
	public void showResults(ResultSet rs) throws SQLException{
		if(rs != null){
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				System.out.print(rs.getMetaData().getColumnName(i));
				if(i == rs.getMetaData().getColumnCount()){
					System.out.println();
				}else{
					System.out.print(",\t");
				}
			}	
		}
		rs.beforeFirst();  //先回到第一行
		while(rs.next()){
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				System.out.print(rs.getObject(i));
				if(i == rs.getMetaData().getColumnCount()){
					System.out.println();
				}else{
					System.out.print(",\t");
				}
			}	
		}
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void setDriver(String driver) {
		this.driver = driver;
		this.url = "jdbc:mysql://"+this.ipaddress+":"+this.port+"/"+this.dbname+"?characterEncoding="+this.charset;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setCharset(String charset) {
		this.charset = charset;
		this.url = "jdbc:mysql://"+this.ipaddress+":"+this.port+"/"+this.dbname+"?characterEncoding="+this.charset;
	}
	
	
	
	

}
