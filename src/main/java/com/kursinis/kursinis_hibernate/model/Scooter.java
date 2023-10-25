package com.kursinis.kursinis_hibernate.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("SCOOTER")
public class Scooter extends Product {


	private String material;
	private int height;

	public Scooter(
			String title,
			String description,
			String productType,
			double price,
			int height,
			String material, Warehouse warehouse) {
		super( title, description, productType, price, warehouse );
		this.height = height;
		this.material = material;
	}

}
