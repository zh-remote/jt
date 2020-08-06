package com.jt.test;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {

	@Test
	public void test01() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-factory.xml");
		Calendar c = (Calendar) ac.getBean("calendar1");
		System.out.println(c.getTimeInMillis());
	}
}
