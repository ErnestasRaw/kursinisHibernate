package kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
	private Label exit;
	@FXML
	private StackPane contentArea;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println( "LoginController initialized" );
		exit.setOnMouseClicked( e -> System.exit( 0 ) );

		try {
			Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Login.fxml" ) ) );
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll( fxml );
		}
		catch (IOException e) {
			Logger.getLogger( PanelController.class.getName() ).log( Level.SEVERE, null, e );
		}
	}

	public void onLoginButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Login.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}

	public void onRegisterButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Register.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}
}
