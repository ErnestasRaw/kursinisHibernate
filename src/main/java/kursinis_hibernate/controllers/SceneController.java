package kursinis_hibernate.controllers;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SceneController {
	public static SceneController instance;
	private Stage stage;
	private Scene scene;
	private Parent root;

	public SceneController(Stage stage) {
		this.stage = stage;
	}


}
