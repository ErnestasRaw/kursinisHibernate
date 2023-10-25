package com.kursinis.kursinis_hibernate.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // or InheritanceType.JOINED, InheritanceType.TABLE_PER_CLASS
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	@Column(name = "price", length = 100)
	private Double price;
	@Column(name = "description", length = 2000, nullable = false)
	private String description;
	@Column(name = "product_type", insertable = false, updatable = false)
	private String productType;
	@OneToMany(mappedBy = "", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Comment> comments;

	@ManyToOne
	Warehouse warehouse;
	@ManyToOne
	Cart cart;

	public Product(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public Product(String title, String description, String productType, double price) {
		this.productType = productType;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public Product(String title, String description, String productType, double price, Warehouse warehouse) {
		this.title = title;
		this.description = description;
		this.productType = productType;
		this.price = price;
		this.warehouse = warehouse;
	}


	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				", warehouse=" + warehouse +
				", cart=" + cart +
				'}';
	}


}
