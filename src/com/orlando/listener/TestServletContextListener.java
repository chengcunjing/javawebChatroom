package com.orlando.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/** 
 * @ClassName: TestServletContextListener 
 * @Description: 监听上下文作用域生命周期
 * @author: 章征武【orlando】
 * @date: 2018年9月26日 下午11:51:23 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestServletContextListener implements ServletContextListener {

	/**
	 * 服务启动时
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("ServletContextListener contextInitialized() ... 服务器启动时... ");
		
	}

	/**
	 * 服务停止时
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("ServletContextListener contextDestroyed() ... 服务器停止时... ");
	}

}
