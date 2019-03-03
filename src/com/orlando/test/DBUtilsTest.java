package com.orlando.test;

import java.sql.SQLException;

import com.orlando.utils.DBUtils;

 /** 
 * @ClassName: DBUtilsTest 
 * @Description: DBUtils 测试类
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:15:07 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class DBUtilsTest {

	public static void main(String[] args) {
		DBUtils db = new DBUtils();
		try {
			db.showResults(db.doQuery("select * from score where sc_id = ?", 10));
			System.out.println("-------");
			db.doUpdate("update score set result = ? where sc_id = ?", 100,10);
			db.showResults(db.doQuery("select * from score where sc_id = ?", 10));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.closeConnection();
		}
	}
}
