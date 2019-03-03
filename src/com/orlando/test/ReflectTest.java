package com.orlando.test;

import java.lang.reflect.Field;

/** 
 * @ClassName: MainTest1 
 * @Description: Main函数测试类，用于其他临时的测试
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:16:09 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class ReflectTest {

	public static void main(String[] args) {
		
		try {
			Class<?> clazz = Class.forName("com.orlando.entity.ChatUserInfo");
			System.out.println(clazz.getName());
			Object obj = clazz.newInstance();
			System.out.println(obj);
			Field field = clazz.getDeclaredField("userName");
			field.setAccessible(true);
			Object xxx = field.get(obj);
			System.out.println(xxx);
			field.set(obj, "张三");
			Object xx = field.get(obj);
			System.out.println(xx);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}

}
