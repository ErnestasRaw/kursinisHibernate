package kursinis_hibernate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client extends User {
	@Column(name = "address", nullable = true, length = 250)
	private String address;

	@Column(name = "cardNo", nullable = true, length = 12)
	private String cardNo;
}
