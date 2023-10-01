package kursinis_hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.Getter;


public class HibernateUtil {

	@Getter
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			System.err.println( "Initial SessionFactory creation failed." + ex );
			throw new ExceptionInInitializerError( ex );
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

}