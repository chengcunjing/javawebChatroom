package com.orlando.listener;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

 /** 
 * @ClassName: TestHttpSessionActivationListener 
 * @Description: 会话激活相关的事件 监听session对象的序列化与反序列化 用于监控实现类本身
 * 				 在用户访问的时候，假如服务器突然关闭了，这个时候，用户的session就不存在了，
 * 				 假如是购物网站，也就相当于用户好不容易选好的物品，刚刚添加到购物车，结果，
 * 				 因为服务器的突然关闭一下，什么都没了，这样很不好，于是我们就需要实现会话的持久化。
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午5:48:02 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class TestHttpSessionActivationListener implements HttpSessionActivationListener,Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestHttpSessionActivationListener(){}
	
	public TestHttpSessionActivationListener(String name){
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "TestHttpSessionActivationListener [name=" + name + "]";
	}
	
	
	/**
	 * 对象实例化后保存到session中，当session失效要被序列化前执行，（只有保存到session中的对象才会被监听到）
	 */
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("HttpSessionActivationListener sessionWillPassivate() ... 当服务宕机时，保存到硬盘了 ... ");	
	}

	/**
	 * 当session被反序列化后执行
	 */
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("HttpSessionActivationListener sessionDidActivate() ... 当服务在次启动时，自动反序列化拿session中的指定对象，从硬盘读取并活化了 ... ");	
	}

}
