package com.br.rbs.sherlock.api.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
	
	static {
		Configuration config = new AnnotationConfiguration();
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	public static void close(){
		sessionFactory.close();
	}
	
}