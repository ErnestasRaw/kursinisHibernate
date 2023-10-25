package com.kursinis.kursinis_hibernate.fxControllers;

import java.io.IOException;
import java.util.Objects;

import com.kursinis.kursinis_hibernate.utils.UserController;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.utils.ErrorUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class PanelController {
	public Button productsButton;
	public Button cartButton;
	public Button ordersButton;
	public Button profileButton;
	public Button userOrdersButton;
	public Button usersButton;
	public Button warehouseButton;

	@FXML
	private StackPane contentArea;

	private EntityManagerFactory entityManagerFactory;

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		toggleVisibilityByUserType();
		try {
			onProfileButtonClicked( null );
		}
		catch (IOException e) {
			ErrorUtil.showError( "Klaida", "Klaida", "Nepavyko užkrauti profilio lango!", Alert.AlertType.ERROR );
		}
	}
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------VISIBILITY--------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//

	private void toggleVisibilityByUserType() {
		if ( UserController.getInstance().getLoggedInUser() instanceof Client ) {
			warehouseButton.setVisible( false );
			usersButton.setVisible( false );
			ordersButton.setVisible( false );

		}
		else if ( UserController.getInstance().getLoggedInUser() instanceof Employee employee ) {
			cartButton.setVisible( false );
			warehouseButton.setVisible( true );
			usersButton.setVisible( employee.isAdmin() );
			ordersButton.setVisible( true );
		}
	}

	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------NAVIGATION--------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//

	public void onProfileButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader(
				Objects.requireNonNull( getClass().getResource(
						"/profile.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		ProfileController profileController = fxml.getController();
		profileController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onUsersButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/users.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		UsersController usersController = fxml.getController();
		usersController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onUserOrderButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/userOrders.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		UserOrdersController usersOrdersController = fxml.getController();
		usersOrdersController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onOrdersButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/allOrders.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		AllOrdersController usersOrdersController = fxml.getController();
		usersOrdersController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
	}

	public void onWarehouseButtonClicked(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxml = new FXMLLoader( Objects.requireNonNull( getClass().getResource(
				"/warehouse.fxml" ) ) );
		Parent fxmlRoot = fxml.load();
		WarehouseController warehouseController = fxml.getController();
		warehouseController.setData( entityManagerFactory );
		contentArea.getChildren().removeAll();
		contentArea.getChildren().setAll( fxmlRoot );
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

}
