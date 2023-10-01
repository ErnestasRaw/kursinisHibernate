package kursinis_hibernate;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartGUI extends Application {

	double xOffset = 0;
	double yOffset = 0;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load( Objects.requireNonNull( getClass().getResource
				( "../views/Authentication.fxml" ) ) );
		Scene sc = new Scene( root );

		primaryStage.initStyle( StageStyle.UNDECORATED );
		//primaryStage.initStyle( StageStyle.DECORATED );

		primaryStage.setResizable( false );

		root.setOnMousePressed( out -> {
			xOffset = out.getSceneX();
			yOffset = out.getSceneY();
		} );
		root.setOnMouseDragged( out -> {
			primaryStage.setX( out.getScreenX() - xOffset );
			primaryStage.setY( out.getScreenY() - yOffset );
		} );

		primaryStage.setScene( sc );
		primaryStage.show();
	}
}