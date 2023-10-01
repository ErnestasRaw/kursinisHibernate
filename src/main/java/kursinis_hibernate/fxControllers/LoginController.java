package kursinis_hibernate.fxControllers;

import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	public TextField loginField;
	public PasswordField passwordField;
	public Button loginButton;

	public void onLoginClicked(ActionEvent actionEvent) throws Exception {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Panel.fxml" ) ) );
		Stage stage = (Stage) loginButton.getScene().getWindow();
		//TODO maybe there is a better way to set the size of the window?
		//Dont want to hardcode it, but it seems to be the only way to do it
		//<fx:root> does not work.
		double desiredWidth = 1100;
		double desiredHeight = 700;
		stage.setWidth( desiredWidth );
		stage.setHeight( desiredHeight );
		stage.centerOnScreen();
		stage.getScene().setRoot( fxml );
	}
}
