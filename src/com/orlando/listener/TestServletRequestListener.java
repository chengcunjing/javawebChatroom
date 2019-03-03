package com.orlando.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

 /** 
 * @ClassName: TestServletRequestListener 
 * @Description: 监听请求作用域生命周期
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午3:39:13 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestServletRequestListener implements ServletRequestListener {

	private Long time;
	/**
	 * request 请求结束时
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println("ServletRequestListener requestDestroyed() ... request 请求结束时... ");
		System.out.println("request请求耗时： "+ (System.currentTimeMillis() - this.time));
	}

	/**
	 * request 请求开始时
	 */
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		System.out.println("ServletRequestListener requestInitialized() ... request 请求开始时... ");
		time = System.currentTimeMillis();
	}

}
