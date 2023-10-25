package com.kursinis.kursinis_hibernate.fxControllers;

import com.kursinis.kursinis_hibernate.model.Other;
import com.kursinis.kursinis_hibernate.utils.UserController;
import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Bike;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.model.Product;
import com.kursinis.kursinis_hibernate.model.ProductType;
import com.kursinis.kursinis_hibernate.model.Scooter;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.model.Warehouse;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsController {
	//Table
	public TableView<Product> productsTable;
	public TableColumn<Product, String> titleColumn;
	public TableColumn<Product, String> descriptionColumn;
	public TableColumn<Product, Double> priceColumn;
	public TableColumn<Product, ProductType> typeColumn;
	public TableColumn<Product, Warehouse> warehouseColumn;
	public TableColumn<Product, Integer> heightColumn;
	public TableColumn<Product, String> materialColumn;
	public TableColumn<Product, Double> wheelSizeColumn;
	public TableColumn<Product, Double> frameSizeColumn;
	//Comments
	public TextArea commentTextArea;
	public Button commentButton;
	//Product CRUD
	public Pane employeePane;
	public TextField titleTextField;
	public TextField descriptionTextField;
	public TextField priceTextField;
	public ComboBox<ProductType> productTypeComboBox;
	public ComboBox<Warehouse> warehouseComboBox;
	public TextField wheelSizeTextField;
	public TextField frameSizeTextField;
	public TextField heightTextField;
	public TextField materialTextField;
	public Button insertButton;
	public Button updateButton;
	public Button deleteButton;

	ObservableList<Product> products = FXCollections.observableArrayList();
	private GenericHib genericHib;
	Product selectedProduct;
	ProductType selectedProductType;
	final User user = UserController.getInstance().getLoggedInUser();


	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		initializeTable();
		toggleEmployeePane();
		setComboBoxValues();
		setVisibility();
		readData();
		productsTable.getItems().addAll( products );
	}

	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//-----------------------------------CRUD-----------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//

	private void readData() {
		products.removeAll();
		products.addAll( genericHib.getAllRecords( Product.class ) );
	}

	public void insertProduct(ActionEvent actionEvent) {
		Warehouse selectedWarehouse = warehouseComboBox.getSelectionModel().getSelectedItem();
		Warehouse warehouse = genericHib.getEntityById( Warehouse.class, selectedWarehouse.getId() );

		if ( productTypeComboBox.getSelectionModel().getSelectedItem() == ProductType.SCOOTER ) {
			Scooter scooter = new Scooter(
					titleTextField.getText(),
					descriptionTextField.getText(),
					productTypeComboBox.getSelectionModel().getSelectedItem().toString(),
					Double.parseDouble( priceTextField.getText() ),
					Integer.parseInt( heightTextField.getText() ),
					materialTextField.getText(),
					warehouse
			);
			genericHib.create( scooter );
		}
		else if ( productTypeComboBox.getSelectionModel().getSelectedItem() == ProductType.BIKE ) {
			Bike bike = new Bike(
					titleTextField.getText(),
					descriptionTextField.getText(),
					productTypeComboBox.getSelectionModel().getSelectedItem().toString(),
					Double.parseDouble( priceTextField.getText() ),
					Integer.parseInt( wheelSizeTextField.getText() ),
					Integer.parseInt( frameSizeTextField.getText() ),
					warehouse
			);
			genericHib.create( bike );
		}
		else {
			Other other = new Other(
					titleTextField.getText(),
					descriptionTextField.getText(),
					productTypeComboBox.getSelectionModel().getSelectedItem().toString(),
					Double.parseDouble( priceTextField.getText() ),
					warehouse
			);
			readData();
			productsTable.getItems().clear();
			productsTable.getItems().addAll( products );
			productsTable.refresh();
			genericHib.create( other );
		}
	}

	public void deleteProduct() {
		genericHib.delete( Product.class, Math.toIntExact( selectedProduct.getId() ) );
		products.removeAll();
		productsTable.getItems().clear();
		productsTable.getItems().addAll( products );
		productsTable.refresh();
	}

	public void updateProduct(ActionEvent actionEvent) {
		genericHib.update( selectedProduct );
	}


	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------UTILITIES---------------------------------//
	//--------------------------------------------------------------------------//
	//--------------------------------------------------------------------------//

	private void setComboBoxValues() {
		productTypeComboBox.getItems().addAll( ProductType.values() );
		productTypeComboBox.getSelectionModel().select( 2 );
		warehouseComboBox.getItems().addAll( genericHib.getAllRecords( Warehouse.class ) );
		warehouseComboBox.getSelectionModel().select( 0 );
	}

	private void initializeTable() {
		titleColumn.setCellValueFactory( new PropertyValueFactory<>( "title" ) );
		descriptionColumn.setCellValueFactory( new PropertyValueFactory<>( "description" ) );
		priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ) );
		typeColumn.setCellValueFactory( new PropertyValueFactory<>( "productType" ) );
		warehouseColumn.setCellValueFactory( new PropertyValueFactory<>( "warehouse" ) );
		heightColumn.setCellValueFactory( new PropertyValueFactory<>( "height" ) );
		materialColumn.setCellValueFactory( new PropertyValueFactory<>( "material" ) );
		wheelSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "wheelSize" ) );
		frameSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "frameSize" ) );
		productsTable.setOnMouseClicked( event -> {
			if ( event.getClickCount() == 1 ) {
				selectedProduct = productsTable.getSelectionModel().getSelectedItem();
				setVisibility();
			}
		} );
	}

	private void setVisibility() {
		if ( selectedProduct instanceof Scooter ) {
			productTypeComboBox.getSelectionModel().select( ProductType.SCOOTER );
			heightTextField.setText( String.valueOf( ( (Scooter) selectedProduct ).getHeight() ) );
			materialTextField.setText( ( (Scooter) selectedProduct ).getMaterial() );
			frameSizeTextField.setVisible( false );
			wheelSizeTextField.setVisible( false );
			materialTextField.setVisible( true );
			heightTextField.setVisible( true );
		}
		else if ( selectedProduct instanceof Bike ) {
			productTypeComboBox.getSelectionModel().select( ProductType.BIKE );
			wheelSizeTextField.setText( String.valueOf( ( (Bike) selectedProduct ).getWheelSize() ) );
			frameSizeTextField.setText( String.valueOf( ( (Bike) selectedProduct ).getFrameSize() ) );
			frameSizeTextField.setVisible( true );
			wheelSizeTextField.setVisible( true );
			materialTextField.setVisible( false );
			heightTextField.setVisible( false );
		}
		else {
			productTypeComboBox.getSelectionModel().select( ProductType.OTHER );
			frameSizeTextField.setVisible( false );
			wheelSizeTextField.setVisible( false );
			materialTextField.setVisible( false );
			heightTextField.setVisible( false );
		}
		if ( selectedProduct != null ) {
			titleTextField.setText( selectedProduct.getTitle() );
			descriptionTextField.setText( selectedProduct.getDescription() );
			priceTextField.setText( String.valueOf( selectedProduct.getPrice() ) );
			warehouseComboBox.getSelectionModel().select( selectedProduct.getWarehouse() );
			productTypeComboBox.getSelectionModel()
					.select( ProductType.valueOf( selectedProduct.getProductType() ) );
			warehouseComboBox.getSelectionModel().select( selectedProduct.getWarehouse() );
		}

	}

	private void toggleEmployeePane() {
		employeePane.setVisible( UserController.getInstance().getLoggedInUser() instanceof Employee );
	}

	public void addComment() {
	}


	public void typeSelectHandler(ActionEvent actionEvent) {
		if ( productTypeComboBox.getSelectionModel().getSelectedItem() == ProductType.SCOOTER ) {
			frameSizeTextField.setVisible( false );
			wheelSizeTextField.setVisible( false );
			materialTextField.setVisible( true );
			heightTextField.setVisible( true );
		}
		else if ( productTypeComboBox.getSelectionModel().getSelectedItem() == ProductType.BIKE ) {
			frameSizeTextField.setVisible( true );
			wheelSizeTextField.setVisible( true );
			materialTextField.setVisible( false );
			heightTextField.setVisible( false );
		}
		else {
			frameSizeTextField.setVisible( false );
			wheelSizeTextField.setVisible( false );
			materialTextField.setVisible( false );
			heightTextField.setVisible( false );
		}
	}
}
