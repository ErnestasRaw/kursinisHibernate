package com.kursinis.kursinis_hibernate.fxControllers;

import jakarta.persistence.EntityManagerFactory;

public class UserOrdersController {

	private EntityManagerFactory entityManagerFactory;

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

}
