package com.kursinis.kursinis_hibernate.fxControllers;

import java.util.List;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.Product;
import com.kursinis.kursinis_hibernate.utils.UserController;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {
	public TableView<Product> cartTable;
	public TableView<Product> productsTable;
	public TableColumn<Product, String> descriptionColumn;
	public TableColumn<Product, String> titleColumn;
	public TableColumn<Product, Integer> heightColumn;
	public TableColumn<Product, String> materialColumn;
	public TableColumn<Product, Integer> wheelSizeColumn;
	public TableColumn<Product, Integer> frameSizeColumn;
	public TableColumn<Product, String> cartTitleColumn;
	public TableColumn<Product, String> cartDescriptionColumn;
	public TableColumn<Product, Integer> cartHeightColumn;
	public TableColumn<Product, String> cartMaterialColumn;
	public TableColumn<Product, Integer> cartWheelSizeColumn;
	public TableColumn<Product, Integer> cartFrameSizeColumn;
	GenericHib genericHib;
	Product selectedProduct;

	ObservableList<Product> productObservableList = FXCollections.observableArrayList();
	ObservableList<Product> cartObservableList = FXCollections.observableArrayList();


	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		initializeTable();
		loadData();
		productsTable.setItems( productObservableList );
		cartTable.setItems( cartObservableList );
	}

	private void refreshList() {
		productObservableList.removeAll();
		productObservableList.addAll( genericHib.getAllRecords( Product.class ) );
		cartObservableList.removeAll();
		if ( UserController.getInstance().getLoggedInUser() instanceof Client client ) {
			cartObservableList.addAll( client.getCart().getProductsInCart() );
		}
	}

	private void loadData() {
		productObservableList.removeAll();
		productObservableList.addAll( genericHib.getAllRecords( Product.class ) );
		cartObservableList.removeAll();
		if ( UserController.getInstance().getLoggedInUser() instanceof Client client ) {
			cartObservableList.addAll( client.getCart().getProductsInCart() );
		}
		refreshList();
	}

	private void initializeTable() {
		initTables( titleColumn, descriptionColumn, heightColumn, materialColumn, wheelSizeColumn, frameSizeColumn );

		initTables(
				cartTitleColumn,
				cartDescriptionColumn,
				cartHeightColumn,
				cartMaterialColumn,
				cartWheelSizeColumn,
				cartFrameSizeColumn
		);
		productsTable.setOnMouseClicked( event -> {
			if ( event.getClickCount() == 1 ) {
				selectedProduct = productsTable.getSelectionModel().getSelectedItem();
				if ( UserController.getInstance().getLoggedInUser() instanceof Client client ) {
					List<Product> products = client.getCart().getProductsInCart();
					products.add( selectedProduct );
					client.getCart().setProductsInCart( products );

					refreshList();
				}
			}
		} );
	}

	private void initTables(
			TableColumn<Product, String> cartTitleColumn,
			TableColumn<Product, String> cartDescriptionColumn,
			TableColumn<Product, Integer> cartHeightColumn,
			TableColumn<Product, String> cartMaterialColumn,
			TableColumn<Product, Integer> cartWheelSizeColumn,
			TableColumn<Product, Integer> cartFrameSizeColumn) {
		cartTitleColumn.setCellValueFactory( new PropertyValueFactory<>( "title" ) );
		cartDescriptionColumn.setCellValueFactory( new PropertyValueFactory<>( "description" ) );
		cartHeightColumn.setCellValueFactory( new PropertyValueFactory<>( "height" ) );
		cartMaterialColumn.setCellValueFactory( new PropertyValueFactory<>( "material" ) );
		cartWheelSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "wheelSize" ) );
		cartFrameSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "frameSize" ) );
	}

}
