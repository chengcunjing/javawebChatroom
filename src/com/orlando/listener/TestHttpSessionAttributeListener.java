package com.orlando.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

 /** 
 * @ClassName: TestHttpSessionAttributeListener 
 * @Description: 监听会话作用域的属性变化
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午4:16:06 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestHttpSessionAttributeListener implements HttpSessionAttributeListener {

	/**
	 * session作用域第一次set属性值时
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttributeListener attributeAdded() ... session作用域第一次set属性值时... ");
	}

	/**
	 * session作用域删除属性值时
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttributeListener attributeRemoved() ... session作用域删除属性值时... ");
	}

	/**
	 * session作用域非第一次set属性值时
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttributeListener attributeReplaced() ... session作用域非第一次set属性值时... ");
	}

}
