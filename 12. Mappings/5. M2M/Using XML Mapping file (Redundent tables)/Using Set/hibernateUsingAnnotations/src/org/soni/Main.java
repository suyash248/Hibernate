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
		
		UserDetails userDetails=new UserDetails();
		
		Vehicle vehicle=new Vehicle();
		vehicle.setVehicleName("Aircraft");
		vehicle.setVehicleOwner("Sunny");
		vehicle.getUserDetailsCollection().add(userDetails);
		
		Vehicle vehicle2=new Vehicle();
		vehicle2.setVehicleName("Tank");
		vehicle2.setVehicleOwner("Abhishek");
		vehicle2.getUserDetailsCollection().add(userDetails);
		
		Vehicle vehicle3=new Vehicle();
		vehicle3.setVehicleName("Bus");
		vehicle3.setVehicleOwner("Golu");
		vehicle3.getUserDetailsCollection().add(userDetails);
		
		userDetails.setUserName("Pappu");
		userDetails.getVehicles().add(vehicle);
		userDetails.getVehicles().add(vehicle2);
		userDetails.getVehicles().add(vehicle3);
		
		session.beginTransaction();
		
		try{
			session.save(userDetails);
			session.save(vehicle);
			session.save(vehicle2);
			session.save(vehicle3);
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
