package kursinis_hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kursinis_hibernate.domain.User;

public class UserRepository {
	private final SessionFactory sessionFactory;

	public UserRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public User authenticate(String login, String password) {
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery( User.class );
			Root<User> root = criteriaQuery.from( User.class );

			Predicate usernamePredicate = builder.equal( root.get( "login" ), login );
			Predicate passwordPredicate = builder.equal( root.get( "password" ), password );

			criteriaQuery.where( usernamePredicate, passwordPredicate );

			User user = session.createQuery( criteriaQuery ).uniqueResult();

			if ( user != null ) {
				return user;
			}
		}
		System.out.println( "User not found" );
		return null;
	}
}
