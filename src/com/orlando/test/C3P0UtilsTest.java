package com.orlando.test;

import java.sql.SQLException;

import org.junit.Test;

import com.orlando.util.C3P0Utils;


 /** 
 * @ClassName: C3P0UtilsTest 
 * @Description: C3P0数据库连接池测试类
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:14:47 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class C3P0UtilsTest {

	@Test
	public void testGetConnection() {
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(C3P0Utils.getConnection());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
