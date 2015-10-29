package com.powerlogic.jcompany.core.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class PlcApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		Class clazz;
		try {
			clazz = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
			classes.add(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
}
