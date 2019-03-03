package com.orlando.mysql.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.orlando.entity.ChatUserInfo;
import com.orlando.mysql.dao.UserInfoDao;
import com.orlando.mysql.dao.impl.UserInfoDaoImpl;


public class UserInfoDaoImplTest {
	UserInfoDao userInfo = new UserInfoDaoImpl();
	
	@Test
	public void testAdd() {
		ChatUserInfo cui = new ChatUserInfo();
		cui.setUserName("牛九");
		cui.setUserPwd("999999");
		cui.setUserNick("老九");
		cui.setUserEmail("niujiu@qq.com");
		cui.setUserPhone("199-9999-9999");
		cui.setUserCardId("999999999999999999");
		cui.setUserRegisterTime(new Date(System.currentTimeMillis()));
		try {
			if(userInfo.add(cui)){
				System.out.println("add成功");
			}else{
				System.out.println("add失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			((UserInfoDaoImpl) userInfo).getDb().closeConnection();
		}
	}

	@Test
	public void testFindAll() {
		try {
			userInfo.showListResult(userInfo.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	@Test
	public void testFindById() {
		try {
			ChatUserInfo ui = userInfo.findById(1);
			System.out.println(ui);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
	@Test
	public void testRemoveChatUserInfo() {
		try {
			if(userInfo.remove(userInfo.findAll().get(0))){
				System.out.println("remove by obj成功");
			}else{
				System.out.println("remove by obj失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveInt() {
		try {
			if(userInfo.remove(5)){
				System.out.println("remove by id成功");
			}else{
				System.out.println("remove by id失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMerge() {
		try {
			ChatUserInfo user = userInfo.findAll().get(0);
			user.setUserPwd("111111");
			userInfo.merge(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	@Test
	public void testFindByExample() {
		ChatUserInfo cui = new ChatUserInfo();
		cui.setUserName("张");
		try {
			userInfo.showListResult(userInfo.findByExample(true,cui));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindBySQL() {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select * from tb_user_info");
		sql.append(" where u_register_time between ? and ?; ");
		try {
			List<ChatUserInfo> list = userInfo.findBySQL(sql.toString(),new Date(System.currentTimeMillis()),new Date(new java.util.Date().getTime()));
			userInfo.showListResult(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
