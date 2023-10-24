package com.kursinis.kursinis_hibernate;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user", schema = "kursinis", catalog = "")
public class UserEntity {
	@Basic
	@Column(name = "user_type")
	private String userType;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private int id;
	@Basic
	@Column(name = "birth_date")
	private Date birthDate;
	@Basic
	@Column(name = "login")
	private String login;
	@Basic
	@Column(name = "name")
	private String name;
	@Basic
	@Column(name = "password")
	private String password;
	@Basic
	@Column(name = "surname")
	private String surname;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		UserEntity that = (UserEntity) o;
		return id == that.id && Objects.equals( userType, that.userType ) && Objects.equals(
				birthDate,
				that.birthDate
		) && Objects.equals( login, that.login ) && Objects.equals(
				name,
				that.name
		) && Objects.equals( password, that.password ) && Objects.equals( surname, that.surname );
	}

	@Override
	public int hashCode() {
		return Objects.hash( userType, id, birthDate, login, name, password, surname );
	}
}
