package org.soni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {
	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure("user.cfg.xml");
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		Session session=sessionFactory.openSession();
		
		UserDetails user=new UserDetails();
		
		user.setUserName("Test user");
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();		// Now 'user' is in deteched state.
		
		
		session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);		// Now again 'user' goes to persistent state.
		session.getTransaction().commit();
		session.close();
	}
}
