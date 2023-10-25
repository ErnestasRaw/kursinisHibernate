package com.kursinis.kursinis_hibernate.hibernateControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CartHib {
	private EntityManagerFactory entityManagerFactory;

	public CartHib(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public void removeFromCartByClientId(String userId, String productId) {


	}


	public void addToCartByClientId(int id, Long productId) {

	}
}
