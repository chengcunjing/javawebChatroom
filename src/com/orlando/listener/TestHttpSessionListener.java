package com.orlando.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

 /** 
 * @ClassName: TestHttpSessionListener 
 * @Description: 监听会话作用域生命周期
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午4:11:46 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestHttpSessionListener implements HttpSessionListener {

	/**
	 * session会话创建时
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("HttpSessionListener sessionCreated() ... session会话创建时... ");
	}

	/**
	 * session会话结束时
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("HttpSessionListener sessionDestroyed() ... session会话结束时... ");
	}

}
