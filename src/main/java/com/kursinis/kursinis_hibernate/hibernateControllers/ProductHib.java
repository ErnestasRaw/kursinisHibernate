package com.kursinis.kursinis_hibernate.hibernateControllers;

import java.util.List;

import com.kursinis.kursinis_hibernate.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ProductHib {
	private EntityManagerFactory entityManagerFactory;

	public ProductHib(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public List<Product> getAllProducts() {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Product> products = em.createQuery( "from Product", Product.class ).getResultList();
			em.getTransaction().commit();
			return products;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
		return null;
	}

	public Product insertProduct(Product product) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist( product );
			em.getTransaction().commit();
			return product;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
		return null;
	}

	public void deleteProduct(int id) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Product product = null;
			if ( product == null ) {
				throw new Exception( "Product with id " + id + " does not exist" );
			}
			em.remove( product );
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

	public void updateProduct(Product product) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge( product );
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

	public Product getProductById(int id) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Product product = em.find( Product.class, id );
			em.getTransaction().commit();
			return product;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( em != null ) {
				em.close();
			}
		}
		return null;
	}

}
