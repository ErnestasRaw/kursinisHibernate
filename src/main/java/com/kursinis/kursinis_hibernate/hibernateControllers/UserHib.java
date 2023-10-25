package com.kursinis.kursinis_hibernate.hibernateControllers;

import java.util.ArrayList;
import java.util.List;

import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Alert;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.fxControllers.ErrorUtil;

public class UserHib {

	private EntityManagerFactory entityManagerFactory;

	public UserHib(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}


	public void createUser(User user) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist( user );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
	}

	//Merge atitiks UPDATE
	public void updateUser(User user) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge( user );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
	}

	//
	public void deleteUser(int id) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			User user = null;
			try {
				user = em.getReference( User.class, id );
				user.getId();
			}
			catch (Exception e) {
				System.out.println( "No such user by given ID" );
			}

			//Biski i ateiti, bet cia reikes nulinkint nuo susijusiu objektu, kad man leistu istrinti

			em.remove( user );
			em.getTransaction().commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
	}

	public List<Client> getAllCustomers() {
		EntityManager em = null;
		try {
			em = getEntityManager();
			CriteriaQuery query = em.getCriteriaBuilder().createQuery();
			query.select( query.from( Client.class ) );
			Query q = em.createQuery( query );
			return q.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
		return new ArrayList<>();
	}

	//Gal iskelt i controlleri?
	public User getUserById(int id) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = cb.createQuery( User.class );
			Root<User> root = criteriaQuery.from( User.class );
			Predicate idPredicate = cb.equal( root.get( "id" ), id );
			criteriaQuery.where( idPredicate );
			TypedQuery<User> query = em.createQuery( criteriaQuery );
			User user = query.getSingleResult();
			em.close();
			if ( user != null ) {
				return user;
			}
		}
		catch (NoResultException e) {
			ErrorUtil.showError( "Klaida", "Klaida", "Nerastas vartotojas!",
								 Alert.AlertType.ERROR
			);
		}
		finally {
			if ( em != null && em.isOpen() ) {
				em.close();
			}
		}
		return null;
	}

	public List<Employee> getAllEmployees() {
		EntityManager em = null;
		try {
			em = getEntityManager();
			CriteriaQuery query = em.getCriteriaBuilder().createQuery();
			query.select( query.from( Employee.class ) );
			Query q = em.createQuery( query );
			return q.getResultList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
		return new ArrayList<>();
	}

	public User authenticateUserByCredentials(String login, String password) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = cb.createQuery( User.class );
			Root<User> root = criteriaQuery.from( User.class );

			Predicate usernamePredicate = cb.equal( root.get( "login" ), login );
			Predicate passwordPredicate = cb.equal(
					root.get( "password" ),
					PasswordHashingUtil.hashPassword( password )
			);

			criteriaQuery.where( usernamePredicate, passwordPredicate );

			TypedQuery<User> query = em.createQuery( criteriaQuery );
			User user = query.getSingleResult();

			em.close();

			if ( user != null ) {
				return user;
			}
		}
		catch (NoResultException e) {
			ErrorUtil.showError( "Klaida", "Klaida", "Neteisingas prisijungimo vardas arba slaptažodis!",
								 Alert.AlertType.ERROR
			);
		}
		finally {
			if ( em != null && em.isOpen() ) {
				em.close();
			}
		}

		return null;
	}
}
