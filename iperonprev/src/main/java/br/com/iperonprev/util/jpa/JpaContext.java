package br.com.iperonprev.util.jpa;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class JpaContext implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
//		new EntityManagerProducer().getEntityManager().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		new EntityManagerProducer().getEntityManager();
	}

}
