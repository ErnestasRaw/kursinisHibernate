package com.kursinis.kursinis_hibernate.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "user_type",
		discriminatorType = DiscriminatorType.STRING
)
public abstract class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private int id;

	@Column(unique = true)
	private String login;

	private String password;

	private String name;

	private String surname;

	private LocalDate birthDate;

	public User(String login, String password, LocalDate birthDate, String name, String surname) {
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
		this.name = name;
		this.surname = surname;
	}

	public User(int id, String login, String password, LocalDate birthDate) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", birthDate=" + birthDate +
				'}';
	}
}
