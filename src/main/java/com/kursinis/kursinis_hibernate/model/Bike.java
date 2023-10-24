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
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("BIKE")
public class Bike extends Product {


	private int wheelSize;
	private int frameSize;

	public Bike(String title, String description, double price, int wheelSize, int frameSize) {
		super( title, description, price );
		this.wheelSize = wheelSize;
		this.frameSize = frameSize;
	}

}
