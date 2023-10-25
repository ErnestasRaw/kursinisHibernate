package com.kursinis.kursinis_hibernate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor

@Getter
@Setter
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dateCreated;
	private String status;

	@OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Product> productsInCart;
	@OneToOne
	private Client client;

	public Cart() {
		this.dateCreated = LocalDate.now();
		this.status = "not paid";
	}
}
