package com.kursinis.kursinis_hibernate.model;

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
@DiscriminatorValue("OTHER")
public class Other extends Product {


	public Other(String title, String description, double price) {
		super( title, description, price );
	}
}
