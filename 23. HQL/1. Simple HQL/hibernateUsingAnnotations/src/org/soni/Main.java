package org.soni;

import java.util.List;

import org.hibernate.Query;
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
		
		session.beginTransaction();
		
		Query query=session.createQuery("from UserDetails");
		List<UserDetails> users = (List<UserDetails>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		for(UserDetails u : users)
			System.out.println(u.getUserName());
	}
}
