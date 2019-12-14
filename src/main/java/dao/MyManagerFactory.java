package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyManagerFactory {
	
	private static MyManagerFactory instance;
	
	private EntityManagerFactory factory;
	
	private MyManagerFactory(){
		this.factory = Persistence.createEntityManagerFactory("hibernate_jpa21");
	}
	
	public static MyManagerFactory getInstance(){
		if(instance == null) instance = new MyManagerFactory();
		return instance;
	}
	
	public EntityManager getEntityManager(){
		return this.factory.createEntityManager();
	}

	public void close() {
		this.factory.close();
	}

}
