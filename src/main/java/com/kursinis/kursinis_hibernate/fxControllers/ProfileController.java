package com.kursinis.kursinis_hibernate.fxControllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.kursinis.kursinis_hibernate.Controllers.UserController;
import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.hibernateControllers.UserHib;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileController implements Initializable {
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public TextField loginField;
	@FXML
	public TextField nameField;
	@FXML
	public TextField surnameField;
	@FXML
	public DatePicker birthDateField;
	@FXML
	public TextField cardNoField;
	@FXML
	public TextField countryField;
	@FXML
	public TextField cityField;
	@FXML
	public TextField addressField;
	@FXML
	public TextField passwordField;
	@FXML
	public TextField repeatPasswordField;
	@FXML
	public Button updateButton;

	private EntityManagerFactory entityManagerFactory;
	private GenericHib genericHib;


	private void setDefaultValues() {
		User user = genericHib.getEntityById( User.class, UserController.getInstance().getLoggedInUserId() );
		if ( user instanceof Client client ) {
			cardNoField.setText( client.getCardNo() );
			countryField.setText( client.getAddress().getCountry() );
			cityField.setText( client.getAddress().getCity() );
			addressField.setText( client.getAddress().getAddress() );
		}
		loginField.setText( user.getLogin() );
		nameField.setText( user.getName() );
		surnameField.setText( user.getSurname() );
		birthDateField.setValue( user.getBirthDate() );
	}

	private void toggleVisibilityByUserType() {
		genericHib = new GenericHib( entityManagerFactory );
		User user = genericHib.getEntityById( User.class, UserController.getInstance().getLoggedInUserId() );

		if ( user instanceof Client ) {
			cardNoField.setVisible( true );
			countryField.setVisible( true );
			cityField.setVisible( true );
			addressField.setVisible( true );
		}
		else {

			cardNoField.setVisible( false );
			countryField.setVisible( false );
			cityField.setVisible( false );
			addressField.setVisible( false );
		}

	}

	public void onUpdateProfileButtonClicked(ActionEvent actionEvent) {
		User user = genericHib.getEntityById( User.class, UserController.getInstance().getLoggedInUserId() );
		user.setLogin( loginField.getText() );
		user.setName( nameField.getText() );
		user.setSurname( surnameField.getText() );
		user.setBirthDate( birthDateField.getValue() );
		if ( user instanceof Client client ) {
			client.setCardNo( cardNoField.getText() );
			client.getAddress().setCountry( countryField.getText() );
			client.getAddress().setCity( cityField.getText() );
			client.getAddress().setAddress( addressField.getText() );
		}
		if ( !passwordField.getText().equals( repeatPasswordField.getText() ) || passwordField.getText().isEmpty() ) {
			ErrorUtil.showError( "Klaida", "Klaida", "Patikrinkite slaptažodį", Alert.AlertType.ERROR );
			return;
		}
		else {
			user.setPassword( PasswordHashingUtil.hashPassword( passwordField.getText() ) );
		}
		genericHib.update( user );
	}

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		toggleVisibilityByUserType();
		setDefaultValues();
		loadData();
	}

	private void loadData() {
		genericHib = new GenericHib( entityManagerFactory );

	}


}
