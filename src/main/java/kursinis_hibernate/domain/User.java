package kursinis_hibernate.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "login", unique = true, nullable = false, length = 50)
	private String login;

	@Column(name = "password", nullable = false, length = 200)
	private String password;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "surname", nullable = false, length = 100)
	private String surname;

	@Column(name = "userType", nullable = false, length = 1)
	private Character userType;

	@Column(name = "birthDate", nullable = false)
	private LocalDate birthDate;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", userType=" + userType +
				", birthDate=" + birthDate +
				'}';
	}
}
