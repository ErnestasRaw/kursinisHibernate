package com.kursinis.kursinis_hibernate.fxControllers;

import java.util.Objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;

public class ErrorUtil {

	public static void showError(String title, String header, String content, AlertType alertType) {

		Alert alert = new Alert( alertType );
		alert.initModality( Modality.APPLICATION_MODAL );
		alert.setTitle( title );
		alert.setHeaderText( header );
		alert.setContentText( content );

		// You can further customize the appearance of the dialog here

		alert.showAndWait();
	}
}