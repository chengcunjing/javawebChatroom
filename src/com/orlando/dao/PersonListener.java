package com.orlando.dao;

import com.orlando.entity.Event;

/** 
 * @ClassName: PersonListener 
 * @Description: 设计Person类(事件源)的监听器接口
 * @author: 章征武【orlando】
 * @date: 2018年9月21日 下午4:48:27 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public interface PersonListener {
	
	/**
	 * @Title: doeat
	 * @Description: 这个方法是用来监听Person对象eat(吃)这个行为动作，
	 *               当实现类实现doeat方法时就可以监听到Person类对象eat(吃)这个动作
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月21日 下午4:50:45  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	void doeat(Event e);
	
	
	/**
	 * @Title: dorun
	 * @Description: 这个方法是用来监听Person对象run(跑)这个行为动作，
     *               当实现类实现dorun方法时就可以监听到Person类对象run(跑)这个动作
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月21日 下午4:51:12  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	void dorun(Event e);
	
}
