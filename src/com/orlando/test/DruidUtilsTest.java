package com.orlando.test;

import java.sql.SQLException;

import org.junit.Test;

import com.orlando.util.DruidUtils;

 /** 
 * @ClassName: DruidUtilsTest 
 * @Description: Druid 数据库连接池测试类
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:15:30 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class DruidUtilsTest {

	@Test
	public void testGetConnection() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(DruidUtils.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
