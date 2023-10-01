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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PanelController implements Initializable {
	public Button productsButton;
	public Button orderButton;
	public Button ordersButton;
	public Button profileButton;

	@FXML
	private Label exit;

	@FXML
	private StackPane contentArea;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println( "PanelController initialized" );
		exit.setOnMouseClicked( e -> System.exit( 0 ) );

		try {
			Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Profile.fxml" ) ) );
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll( fxml );
		}
		catch (IOException e) {
			Logger.getLogger( PanelController.class.getName() ).log( Level.SEVERE, null, e );
		}
	}

	public void onProfileButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Profile.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}

	public void onOrderButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Cart.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}

	public void onProductsButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Products.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}


	public void onOrdersButtonClicked(ActionEvent actionEvent) throws IOException {
		Parent fxml = FXMLLoader.load( Objects.requireNonNull( getClass().getResource( "/views/Orders.fxml" ) ) );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxml );
	}

}
