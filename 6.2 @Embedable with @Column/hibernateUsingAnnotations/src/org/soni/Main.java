package org.soni;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration().configure("user.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		
		UserDetails userDetails=new UserDetails();
		
		Address address=new Address();
		address.setCityName("Bangalore");
		address.setPinCode(36920);
		address.setStateName("Karnataka");
		
		userDetails.setUserName("Suyash");
		userDetails.setAddress(address);
		session.beginTransaction();
		
		try{
			session.save(userDetails);
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
	}
}
