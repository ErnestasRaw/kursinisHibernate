package com.kursinis.kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
	public Button userOrdersButton;
	public Button usersButton;

	@FXML
	private StackPane contentArea;

	private EntityManagerFactory entityManagerFactory;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}


	private void loadProfileStackPane() throws IOException {
		FXMLLoader fxml = new FXMLLoader(
				Objects.requireNonNull( getClass().getResource(
						"/profile.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		ProfileController profileController = fxml.getController();
		profileController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onProfileButtonClicked(ActionEvent actionEvent) throws IOException {
		loadProfileStackPane();
	}

	public void onCartButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/cart.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		CartController cartController = fxml.getController();
		cartController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onProductsButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/products.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		ProductsController productsController = fxml.getController();
		productsController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}


	public void onUserOrdersButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/userOrders.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		UserOrdersController userOrdersController = fxml.getController();
		userOrdersController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onUserOrderButtonClicked(ActionEvent actionEvent) {

	}

	public void onUsersButtonClicked(ActionEvent actionEvent) {

	}

	public void onOrdersButtonClicked(ActionEvent actionEvent) {
	}

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		try {
			loadProfileStackPane();
		}
		catch (IOException e) {
			ErrorUtil.showError( "Klaida", "Klaida", "Nepavyko užkrauti profilio lango!", Alert.AlertType.ERROR );
			Logger.getLogger( PanelController.class.getName() ).log( Level.SEVERE, null, e );
		}
		System.out.println( "PanelController initialized" );
	}
}
