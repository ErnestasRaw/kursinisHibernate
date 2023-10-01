package kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.util.Objects;

import org.hibernate.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kursinis_hibernate.dao.UserRepository;
import kursinis_hibernate.domain.Client;
import kursinis_hibernate.domain.Employee;
import kursinis_hibernate.domain.User;
import kursinis_hibernate.util.HibernateUtil;

public class LoginController {
	public TextField loginField;
	public PasswordField passwordField;
	public Button loginButton;

	public void onLoginClicked(ActionEvent actionEvent) throws Exception {
		String login = loginField.getText();
		String password = passwordField.getText();
		System.out.println( "Login: " + login );
		System.out.println( "Password: " + password );

		UserRepository userRepository;
		userRepository = new UserRepository( HibernateUtil.getSessionFactory() );
		User user = userRepository.authenticate( login, password );
		if ( user != null ) {
			changeStage( "/views/Panel.fxml" );
			if ( user instanceof Client ) {
				// TODO: Handle Client login

			}
			else if ( user instanceof Employee ) {
				// TODO: Handle Employee login
				// Change the screen for Employee
			}
			else {
				// TODO: Handle other user types if necessary
			}
		}
		else {
			// TODO: Display an error message or handle the login failure
		}


	}

	private void changeStage(String location) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( location ) ) );
		Stage stage = (Stage) loginButton.getScene().getWindow();
		//TODO maybe there is a better way to set the size of the window?
		//Don't want to hardcode it, but it seems to be the only way to do it
		//<fx:root> does not work.
		double desiredWidth = 1100;
		double desiredHeight = 700;
		stage.setWidth( desiredWidth );
		stage.setHeight( desiredHeight );
		stage.centerOnScreen();
		stage.getScene().setRoot( fxml );
	}

	;
}
