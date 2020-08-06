package com.jt.manager.factory;

import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;

public class SpringPFactory implements FactoryBean<Calendar> {

	@Override
	public Calendar getObject() throws Exception {
		return Calendar.getInstance();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Calendar.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
