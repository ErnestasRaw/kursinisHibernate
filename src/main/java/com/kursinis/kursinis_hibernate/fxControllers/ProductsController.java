package com.kursinis.kursinis_hibernate.fxControllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.kursinis.kursinis_hibernate.Controllers.UserController;
import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Bike;
import com.kursinis.kursinis_hibernate.model.Cart;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.model.Other;
import com.kursinis.kursinis_hibernate.model.Product;
import com.kursinis.kursinis_hibernate.model.ProductType;
import com.kursinis.kursinis_hibernate.model.Scooter;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.model.Warehouse;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsController implements Initializable {
	//Insert laukai
	@FXML
	public TextField titleInsertField;
	@FXML
	public TextField descriptionInsertField;
	@FXML
	public TextField priceInsertField;
	@FXML
	public TextField wheelSizeInsertField;
	@FXML
	public TextField frameSizeInsertField;
	@FXML
	public TextField materialInsertField;
	@FXML
	public TextField heightInsertField;
	@FXML
	public Pane employeePane;
	@FXML
	public ComboBox<ProductType> typeField = new ComboBox<>();
	@FXML
	public Button insertButton;
	@FXML
	public ComboBox<Warehouse> warehouseInsertField;
	@FXML
	public ComboBox<Warehouse> warehouseComboBox;
	//Table laukai
	@FXML
	public TableView<Product> productsTable;
	@FXML
	public TableColumn<Product, String> titleColumn;
	@FXML
	public TableColumn<Product, String> descriptionColumn;
	@FXML
	public TableColumn<Product, Double> priceColumn;
	@FXML
	public TableColumn<Product, Void> addToCartColumn;

	//Update/delete laukai
	public Label materialLabel;
	@FXML
	public Label warehouseLabel;
	@FXML
	public Label wheelSizeLabel;
	@FXML
	public Label frameSizeLabel;
	@FXML
	public Text heightTextField;
	@FXML
	public Text frameSizeTextField;
	@FXML
	public Label heightLabel;

	@FXML
	public Text materialTextField;
	@FXML
	public Text wheelSizeTextField;
	@FXML
	public TextField materialField;
	@FXML
	public TextField heightField;
	@FXML
	public TextField frameSizeField;
	@FXML
	public TextField wheelSizeField;
	@FXML
	public Button updateButton;
	@FXML
	public TextField priceField;
	@FXML
	public ComboBox<Warehouse> warehouseField = new ComboBox<>();
	public Button deleteButton;


	private EntityManagerFactory entityManagerFactory;


	ObservableList<Product> products = FXCollections.observableArrayList();

	private GenericHib genericHib;
	Product selectedProduct;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		toggleEmployeePane();
		titleColumn.setCellValueFactory( new PropertyValueFactory<>( "title" ) );
		descriptionColumn.setCellValueFactory( new PropertyValueFactory<>( "description" ) );
		priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ) );
		final User user = UserController.getInstance().getLoggedInUser();
		if ( user instanceof Client client ) {
			addToCartColumn.setCellFactory( param -> new TableCell<>() {
				private final Button addToCartButton = new Button( "Į krepšelį" );

				{
					addToCartButton.setOnAction( event -> {
						Product product = getTableView().getItems().get( getIndex() );
						Client client = (Client) user;
						Cart cart = client.getCart();
						cart.getProductsInCart().add( product );
					} );
				}

				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem( item, empty );
					if ( empty ) {
						setGraphic( null );
					}
					else {
						setGraphic( addToCartButton );
					}
				}
			} );
		}
		productsTable.setOnMouseClicked( event -> {
			if ( event.getClickCount() == 1 ) {
				selectedProduct = productsTable.getSelectionModel().getSelectedItem();
				if ( selectedProduct != null ) {
					showDetailedDescription( selectedProduct );
				}
			}
		} );
		if ( typeField.getValue() == null ) {
			wheelSizeInsertField.setVisible( false );
			frameSizeInsertField.setVisible( false );
			heightInsertField.setVisible( false );
			materialInsertField.setVisible( false );
		}
	}

	private void toggleEmployeePane() {
		employeePane.setVisible( UserController.getInstance().getLoggedInUser() instanceof Employee );
	}

	private void showDetailedDescription(Product selectedProduct) {
		if ( selectedProduct instanceof Scooter scooter ) {
			materialField.setText( scooter.getMaterial() );
			materialField.setVisible( true );
			heightField.setText( String.valueOf( scooter.getHeight() ) );
			heightInsertField.setVisible( true );

			frameSizeField.setVisible( false );
			wheelSizeField.setVisible( false );
		}
		else if ( selectedProduct instanceof Bike bike ) {
			wheelSizeField.setText( String.valueOf( bike.getWheelSize() ) );
			frameSizeField.setText( String.valueOf( bike.getFrameSize() ) );
			materialField.setVisible( false );
			heightField.setVisible( false );
			wheelSizeField.setVisible( true );
		}
		else {
			Other other = (Other) selectedProduct;
			materialField.setVisible( false );
			heightField.setVisible( false );
			frameSizeField.setVisible( false );
			wheelSizeField.setVisible( false );
		}

	}

	private void loadData() {
		products.removeAll();
		productsTable.refresh();
		products.addAll( genericHib.getAllRecords( Product.class ) );
		productsTable.setItems( products );
	}


	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		genericHib = new GenericHib( entityManagerFactory );
		typeField.getItems().addAll( ProductType.values() );
		typeField.getSelectionModel().select( 2 );

		List<Warehouse> record = genericHib.getAllRecords( Warehouse.class );
		warehouseComboBox.getItems().addAll( genericHib.getAllRecords( Warehouse.class ) );
		warehouseInsertField.getItems().addAll( genericHib.getAllRecords( Warehouse.class ) );
		List<ProductType> productTypes = genericHib.getAllRecords( ProductType.class );
		typeField.getItems().addAll( genericHib.getAllRecords( ProductType.class ) );
		loadData();
	}


	public void onInsertButtonClicked(ActionEvent actionEvent) {
		Warehouse selectedWarehouse = warehouseInsertField.getSelectionModel().getSelectedItem();
		Warehouse warehouse = genericHib.getEntityById( Warehouse.class, selectedWarehouse.getId() );
		if ( typeField.getValue() == ProductType.SCOOTER ) {
			Product product = new Scooter(
					titleInsertField.getText(),
					typeField.getValue().toString(),
					descriptionInsertField.getText(),
					Double.parseDouble( priceInsertField.getText() ),
					Integer.parseInt( heightInsertField.getText() ),
					materialInsertField.getText(),
					warehouse

			);
			genericHib.create( product );
		}
		else if ( typeField.getValue() == ProductType.BIKE ) {
			Product product = new Bike(
					titleInsertField.getText(),
					descriptionInsertField.getText(),
					typeField.getValue().toString(),
					Double.parseDouble( priceInsertField.getText() ),
					Integer.parseInt( wheelSizeInsertField.getText() ),
					Integer.parseInt( frameSizeInsertField.getText() ),
					warehouse
			);
			genericHib.create( product );
		}
		else {
			Product product = new Other(
					titleInsertField.getText(),
					descriptionInsertField.getText(),
					"OTHER",
					Double.parseDouble( priceInsertField.getText() ),
					warehouse
			);
			genericHib.create( product );
		}
		loadData();
	}


	public void onTypeChanged(ActionEvent actionEvent) {
		if ( typeField.getValue() == ProductType.SCOOTER ) {
			wheelSizeInsertField.setVisible( false );
			frameSizeInsertField.setVisible( false );
			heightInsertField.setVisible( true );
			materialInsertField.setVisible( true );
		}
		else if ( typeField.getValue() == ProductType.BIKE ) {
			wheelSizeInsertField.setVisible( true );
			frameSizeInsertField.setVisible( true );
			heightInsertField.setVisible( false );
			materialInsertField.setVisible( false );
		}
		else {
			wheelSizeInsertField.setVisible( false );
			frameSizeInsertField.setVisible( false );
			heightInsertField.setVisible( false );
			materialInsertField.setVisible( false );
		}
	}


	public void deleteProduct(ActionEvent actionEvent) {
		genericHib.delete( Product.class, selectedProduct.getId().intValue() );
		products.removeAll();
		productsTable.refresh();
	}

	public void updateProduct(ActionEvent actionEvent) {
		genericHib.update( selectedProduct );
	}
}
