package com.kursinis.kursinis_hibernate.fxControllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Cart;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.hibernateControllers.UserHib;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.model.Address;

public class RegistrationController implements Initializable {
	@FXML
	public TextField loginField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public Button registerButton;
	@FXML
	public PasswordField repeatPasswordField;
	@FXML
	public TextField nameField;
	@FXML
	public TextField surnameField;
	@FXML
	public TextField addressField;
	@FXML
	public DatePicker birthDateField;
	@FXML
	public TextField cardNoField;
	@FXML
	public TextField countryField;
	@FXML
	public TextField cityField;
	private EntityManagerFactory entityManagerFactory;
	private UserHib userHib;
	private GenericHib genericHib;

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		genericHib = new GenericHib( entityManagerFactory );
	}


	public void registerUser(ActionEvent actionEvent) {


		String repeatPassword = repeatPasswordField.getText();

		if ( Objects.equals( repeatPassword, passwordField.getText() ) ) {
			Cart cart = new Cart();
			genericHib.create( cart );
			User client = new Client(
					loginField.getText(),
					PasswordHashingUtil.hashPassword( passwordField.getText() ),
					birthDateField.getValue(),
					nameField.getText(),
					surnameField.getText(),
					new Address(
							addressField.getText(),
							cityField.getText(),
							countryField.getText()
					),
					cardNoField.getText(),
					cart
			);
			userHib = new UserHib( entityManagerFactory );
			userHib.createUser( client );
		}
		else {
			ErrorUtil.showError( "Klaida", "Klaida", "Slaptažodžiai nesutampa", Alert.AlertType.ERROR );
		}


	}

	private boolean doPasswordsMatch() {
		return passwordField.getText().equals( repeatPasswordField.getText() );
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Arba cia kazka su laukais darau
	}

}
