package com.orlando.test;

import java.sql.SQLException;

import org.junit.Test;

import com.orlando.util.JDBCUtils;

 /** 
 * @ClassName: JDBCUtilsTest 
 * @Description: JDBC数据库连接测试类
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:15:50 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class JDBCUtilsTest {

	@Test
	public void testGetConnection() throws SQLException {
		System.out.println(JDBCUtils.getConnection());
	}

}
