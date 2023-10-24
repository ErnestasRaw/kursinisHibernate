package com.kursinis.kursinis_hibernate.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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


	private String employeeId;
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
		this.employeeId = employeeId;
		this.medCertificate = medCertificate;
		this.employmentDate = employmentDate;
		this.isAdmin = isAdmin;
	}

	public Employee(String login, String password, LocalDate birthDate) {

	}

}
