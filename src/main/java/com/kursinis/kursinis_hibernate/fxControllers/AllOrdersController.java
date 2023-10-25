package com.kursinis.kursinis_hibernate.fxControllers;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllOrdersController {
	private GenericHib genericHib;

	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
	}
}
