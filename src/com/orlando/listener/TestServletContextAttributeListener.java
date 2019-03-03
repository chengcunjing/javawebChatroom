package com.orlando.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
/** 
 * @ClassName: TestServletContextAttributeListener 
 * @Description: 监听上下文作用域的属性变化
 * @author: 章征武【orlando】
 * @date: 2018年9月26日 下午11:50:44 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestServletContextAttributeListener implements ServletContextAttributeListener {
	/**
	 * application作用域 第一次 set属性 值时
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("ServletContextAttributeListener attributeAdded() ... application作用域 第一次 set属性值时... ");
	}

	/**
	 * application作用域 删除属性值时... 
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("ServletContextAttributeListener attributeRemoved() ... application作用域 删除属性值时... ");
	}
	/**
	 * application作用域 非第一次 set属性值时
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("ServletContextAttributeListener attributeReplaced() ... application作用域 非第一次 set属性值时... ");
	}

}
