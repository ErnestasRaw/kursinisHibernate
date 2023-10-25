package com.kursinis.kursinis_hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", nullable = false)
	private int id;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@Column(name = "country", length = 50, nullable = false)
	private String country;

	public Address(String address, String city, String country) {
		this.address = address;
		this.city = city;
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address{" +
				", address='" + address + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				'}';
	}

	public boolean checkIfFieldsAreEmpty() {
		return address.isEmpty() || city.isEmpty() || country.isEmpty();
	}
}