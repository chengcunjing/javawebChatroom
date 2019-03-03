package com.orlando.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

 /** 
 * @ClassName: TestServletRequestAttributeListener 
 * @Description: 监听请求作用域的属性变化
 * @author: 章征武【orlando】
 * @date: 2018年9月28日 下午3:46:14 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
@WebListener
public class TestServletRequestAttributeListener implements ServletRequestAttributeListener {

	/**
	 * request作用域 第一次set属性值时
	 */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttributeListener attributeAdded() ... request作用域 第一次set属性值时... ");
		this.attributeAddedHandler(event);  // 监听/事件/事件源/处理/（委托） Listener/Event/EventSource/Handler/（Delegate）
	}

	private void attributeAddedHandler(ServletRequestAttributeEvent event) { //事件对象
		
	}

	/**
	 * request作用域删除属性值时
	 */
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttributeListener attributeRemoved() ... request作用域删除属性值时... ");

	}

	/**
	 * request作用域 非第一次set属性值时
	 */
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent event) {
		System.out.println("ServletRequestAttributeListener attributeReplaced() ... request作用域 非第一次set属性值时... ");

	}

}
