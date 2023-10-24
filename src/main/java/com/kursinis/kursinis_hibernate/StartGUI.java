package com.kursinis.kursinis_hibernate;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartGUI extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader( StartGUI.class.getResource( "/authentication.fxml" ) );
		Scene sc = new Scene( fxmlLoader.load() );
		primaryStage.setResizable( false );
		primaryStage.initStyle( StageStyle.DECORATED );
		primaryStage.setTitle( "Parduotuvė" );
		primaryStage.setScene( sc );
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}