package com.kursinis.kursinis_hibernate.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("C")
public class Client extends User {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = true)
	private Address address;
	private String cardNo;
	@OneToOne
	private Cart cart;

	public Client(
			String login,
			String password,
			LocalDate birthDate,
			String name,
			String surname,
			Address address,
			String cardNo,
			Cart cart
	) {
		super( login, password, birthDate, name, surname );
		this.address = address;
		this.cardNo = cardNo;
		this.cart = cart;
	}

	public Client(
			String login,
			String password,
			LocalDate birthDate,
			String name,
			String surname,
			Address address,
			String cardNo
	) {
		super( login, password, birthDate, name, surname );
		this.address = address;
		this.cardNo = cardNo;
	}

}
