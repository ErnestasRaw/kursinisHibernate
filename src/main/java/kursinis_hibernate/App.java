package kursinis_hibernate;

import org.hibernate.Session;

import jakarta.persistence.Query;
import kursinis_hibernate.domain.User;
import kursinis_hibernate.util.HibernateUtil;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = new User();

		session.persist( user );
		session.getTransaction().commit();
		Query q = session.createQuery( "From User", User.class );
		var resultList = q.getResultList();
		System.out.println( "num of employees:" + resultList.size() );
		for ( Object next : resultList ) {
			System.out.println( "next employee: " + next );
		}
		HibernateUtil.shutdown();
	}
}
