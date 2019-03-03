package com.orlando.test;

import com.orlando.dao.PersonListener;
import com.orlando.entity.Event;
import com.orlando.entity.Person;

public class ListenerPersonTest {

	public static void main(String[] args) {
		Person p = new Person();
		p.registerListener(new PersonListener() {
			
			@Override
			public void dorun(Event e) {
				Person p = e.getSource();
				System.out.println(p+"在吃东西");
			}
			
			@Override
			public void doeat(Event e) {
				Person p = e.getSource();
				System.out.println(p+"在跑步");
				
			}
		});
		
		p.run();
		p.eat();
	}

}
