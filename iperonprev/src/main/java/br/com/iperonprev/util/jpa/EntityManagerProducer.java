package br.com.iperonprev.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer {

	private EntityManagerFactory em;
	private static EntityManagerProducer instance;

	private EntityManagerProducer() {
		this.em = Persistence.createEntityManagerFactory("iperon");
	}

	public static synchronized EntityManagerProducer getInstance() {
		if (instance == null) {
			instance = new EntityManagerProducer();
		}
		return instance;
	}
	
	public EntityManager getEntityManager(){
		return em.createEntityManager();
	}
	
	

	public void close(EntityManager em) {
		em.close();
	}
}
