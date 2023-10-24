package com.kursinis.kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.util.Objects;

import com.kursinis.kursinis_hibernate.Controllers.UserController;
import com.kursinis.kursinis_hibernate.StartGUI;
import com.kursinis.kursinis_hibernate.hibernateControllers.UserHib;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

		userHib = new UserHib( entityManagerFactory );
		User user = userHib.authenticateUserByCredentials( loginField.getText(), passwordField.getText() );
		if ( user != null ) {
			UserController.getInstance().setId( user.getId() );
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
	}

}
