package com.orlando.entity;


 /** 
 * @ClassName: Event 
 * @Description: 设计事件类，用来封装事件源
 * @author: 章征武【orlando】
 * @date: 2018年9月21日 下午4:49:31 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class Event {
	
	/**
	 * 事件源 Person
	 */
	private Person source;
	
	public Event(){}
	public Event(Person source){
		this.source = source;
	}
	public Person getSource() {
		return source;
	}
	public void setSource(Person source) {
		this.source = source;
	}
	
}
