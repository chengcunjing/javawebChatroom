package com.orlando.entity;

import com.orlando.dao.PersonListener;

 /** 
 * @ClassName: Person 
 * @Description: 设计一个Person类作为事件源，这个类的对象的行为(比如吃饭、跑步)可以被其他的对象监听
 * @author: 章征武【orlando】
 * @date: 2018年9月21日 下午4:54:09 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class Person {
	
	
	/**
	 * 在Person类中定义一个PersonListener变量来记住传递进来的监听器
	 */
	private PersonListener listener;
	
	/**
	 * @Title: eat
	 * @Description: 调用监听器的doeat方法监听Person类对象eat(吃)这个动作，将事件对象Event传递给doeat方法，
     * 				  事件对象封装了事件源，new Event(this)中的this代表的就是事件源
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月21日 下午4:59:16  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public void eat(){
		if(listener != null){
			listener.doeat(new Event(this));
		}
	}
	
	/**
	 * @Title: run
	 * @Description: 调用监听器的dorun方法监听Person类对象run(跑)这个动作，将事件对象Event传递给doeat方法，
     * 				  事件对象封装了事件源，new Event(this)中的this代表的就是事件源
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月21日 下午4:59:19  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public void run(){
		if(listener != null){
			listener.dorun(new Event(this));
		}
	}
	
	
	/**
	 * @Title: registerListener
	 * @Description: 这个方法是用来注册对Person类对象的行为进行监听的监听器
	 * @param @param listener    参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月21日 下午5:12:21  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public void registerListener(PersonListener listener){
		this.listener = listener;
	}
	
}
