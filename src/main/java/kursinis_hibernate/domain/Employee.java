package kursinis_hibernate.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Employee extends User {
	@Column(name = "employeeId", nullable = true, length = 6)
	private String employeeId;

	@Column(name = "medCertificate", nullable = true, length = 12)
	private String medCertificate;

	@Column(name = "employmentDate", nullable = true)
	private LocalDate employmentDate;

	@Column(name = "isAdmin", nullable = true)
	private boolean isAdmin;
}
