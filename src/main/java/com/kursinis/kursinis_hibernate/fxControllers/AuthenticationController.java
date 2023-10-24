package com.kursinis.kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationController implements Initializable {
	public ToggleButton loginButton;
	public ToggleButton registerButton;
	public ToggleGroup loginRegisterGroup;

	@FXML
	private StackPane stackPane;

	private jakarta.persistence.EntityManagerFactory entityManagerFactory;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		entityManagerFactory = Persistence.createEntityManagerFactory( "coursework-shop" );

		try {
			loadLogin();
		}
		catch (IOException e) {
			Logger.getLogger( PanelController.class.getName() ).log( Level.SEVERE, null, e );
		}
		System.out.println( "Authentication initialized" );
	}

	public void onLoginButtonClicked(ActionEvent actionEvent) throws IOException {
		loadLogin();

	}

	private void loadLogin() throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/login.fxml" ) ) );
		Parent scene = fxml.load();
		LoginController loginController = fxml.getController();
		loginController.setData( entityManagerFactory );
		stackPane.getChildren().removeAll();
		stackPane.getChildren().setAll( scene );
	}

	public void onRegisterButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/registration.fxml" ) ) );
		Parent scene = fxml.load();
		RegistrationController registrationController = fxml.getController();
		registrationController.setData( entityManagerFactory );
		stackPane.getChildren().removeAll();
		stackPane.getChildren().setAll( scene );
	}
}
