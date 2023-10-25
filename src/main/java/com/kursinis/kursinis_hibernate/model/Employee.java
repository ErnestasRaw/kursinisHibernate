package com.kursinis.kursinis_hibernate.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("E")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {

	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
	@Column(unique = true)
	private String employeeId;
	@Column(unique = true)
	private String medCertificate;
	private LocalDate employmentDate;
	private boolean isAdmin;
	@ManyToMany
	private List<Warehouse> worksAtWarehouse;

	public Employee(
			String login,
			String password,
			LocalDate birthDate,
			String name,
			String surname,
			String employeeId,
			String medCertificate,
			LocalDate employmentDate,
			boolean isAdmin) {
		super( login, password, birthDate, name, surname );
		this.medCertificate = medCertificate;
		this.employmentDate = employmentDate;
		this.employeeId = employeeId;
		this.isAdmin = isAdmin;
	}

	public Employee(
			String login,
			String password,
			LocalDate birthDate,
			String name,
			String surname,
			String employeeId,
			LocalDate employmentDate,
			String medCertificate,
			boolean isAdmin) {
		super( login, password, birthDate, name, surname );
		this.employmentDate = employmentDate;
		this.medCertificate = medCertificate;
		this.employeeId = employeeId;
		this.isAdmin = isAdmin;
	}

	public Employee(String login, String password, LocalDate birthDate) {

	}

}
