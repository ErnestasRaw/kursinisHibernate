package com.kursinis.kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import com.kursinis.kursinis_hibernate.utils.UserController;
import com.kursinis.kursinis_hibernate.hibernateControllers.UserHib;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.kursinis.kursinis_hibernate.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginController {

	@FXML
	public TextField loginField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public Button loginButton;

	private EntityManagerFactory entityManagerFactory;
	private UserHib userHib;

	public void onLoginClicked() throws IOException {
		User user = userHib.authenticateUserByCredentials( loginField.getText(), passwordField.getText() );
		if ( user != null ) {
			UserController.getInstance().setLoggedInUser( user );
			changeToPanelStage();
		}

	}

	private void changeToPanelStage() throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getClassLoader().getResource(
				"panel.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		PanelController panelController = fxml.getController();
		panelController.setData( entityManagerFactory );
		Stage stage = (Stage) loginButton.getScene().getWindow();
		double desiredWidth = 1116;
		double desiredHeight = 738;
		stage.setWidth( desiredWidth );
		stage.setHeight( desiredHeight );
		stage.centerOnScreen();
		stage.getScene().setRoot( fxmlRoot );
	}

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		userHib = new UserHib( entityManagerFactory );
		//TEMPORARY
		User employee = new Employee(
				"a",
				PasswordHashingUtil.hashPassword( "a" ),
				LocalDate.now(),
				"adminu",
				"adminas",
				"adm001",
				LocalDate.now(),
				"auydaosd",
				true
		);
		userHib.createUser( employee );
	}

}
