package com.kursinis.kursinis_hibernate.Controllers;

import java.time.LocalDate;

import org.hibernate.usertype.UserType;

import com.kursinis.kursinis_hibernate.model.Address;
import com.kursinis.kursinis_hibernate.model.Client;
import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.Initializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
	private static UserController single_instance = null;

	private int id;

	public static UserController getInstance() {
		if ( single_instance == null ) {
			single_instance = new UserController();
		}

		return single_instance;
	}

}
