package com.kursinis.kursinis_hibernate.fxControllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.kursinis.kursinis_hibernate.hibernateControllers.ProductHib;
import com.kursinis.kursinis_hibernate.model.Bike;
import com.kursinis.kursinis_hibernate.model.Other;
import com.kursinis.kursinis_hibernate.model.Product;
import com.kursinis.kursinis_hibernate.model.ProductType;
import com.kursinis.kursinis_hibernate.model.Scooter;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class ProductsController implements Initializable {
	@FXML
	public TextField titleField;
	@FXML
	public TextField descriptionField;
	@FXML
	public TextField priceField;
	@FXML
	public TextField wheelSizeField;
	@FXML
	public TextField frameSizeField;
	@FXML
	public TextField materialField;
	@FXML
	public TextField heightField;
	@FXML
	public TableView<Product> productsTable;
	@FXML
	public Pane employeePane;

	@FXML
	public ComboBox<ProductType> typeField = new ComboBox<>();
	@FXML
	public Button insertButton;

	@FXML
	public TableColumn<Product, String> titleColumn;
	@FXML
	public TableColumn<Product, String> descriptionColumn;
	@FXML
	public TableColumn<Product, Double> priceColumn;
	@FXML
	public TableColumn<Product, String> typeColumn;
	@FXML
	public TableColumn<Product, Integer> heightColumn;
	@FXML
	public TableColumn<Product, Integer> wheelSizeColumn;
	@FXML
	public TableColumn<Product, Integer> frameSizeColumn;
	@FXML
	public TableColumn<Product, String> materialColumn;
	private EntityManagerFactory entityManagerFactory;


	ObservableList<Product> products = FXCollections.observableArrayList();

	private ProductHib productHib;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		titleColumn.setCellValueFactory( new PropertyValueFactory<>( "title" ) );
		descriptionColumn.setCellValueFactory( new PropertyValueFactory<>( "description" ) );
		priceColumn.setCellValueFactory( new PropertyValueFactory<>( "price" ) );
		typeColumn.setCellValueFactory( new PropertyValueFactory<>( "type" ) );
		heightColumn.setCellValueFactory( new PropertyValueFactory<>( "height" ) );
		wheelSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "wheelSize" ) );
		frameSizeColumn.setCellValueFactory( new PropertyValueFactory<>( "frameSize" ) );
		materialColumn.setCellValueFactory( new PropertyValueFactory<>( "material" ) );

		productsTable.setItems( products );

		if ( typeField.getValue() == null ) {
			wheelSizeField.setVisible( false );
			frameSizeField.setVisible( false );
			heightField.setVisible( false );
			materialField.setVisible( false );
		}
	}

	private void loadData() {
		products.removeAll();
		productHib = new ProductHib( entityManagerFactory );
		products.addAll( productHib.getAllProducts() );
	}

	public void setData(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
		typeField.getItems().addAll( ProductType.values() );
		loadData();
	}


	public void onInsertButtonClicked(ActionEvent actionEvent) {
		productHib = new ProductHib( entityManagerFactory );
		if ( typeField.getValue() == ProductType.SCOOTER ) {
			Product product = new Scooter(
					titleField.getText(),
					descriptionField.getText(),
					Double.parseDouble( priceField.getText() ),
					Integer.parseInt( heightField.getText() ),
					materialField.getText()
			);
			productHib.insertProduct( product );
		}
		else if ( typeField.getValue() == ProductType.BIKE ) {
			Product product = new Bike(
					titleField.getText(),
					descriptionField.getText(),
					Double.parseDouble( priceField.getText() ),
					Integer.parseInt( wheelSizeField.getText() ),
					Integer.parseInt( frameSizeField.getText() )
			);
			productHib.insertProduct( product );
		}
		else {
			Product product = new Other(
					titleField.getText(),
					descriptionField.getText(),
					Double.parseDouble( priceField.getText() )
			);
			productHib.insertProduct( product );
		}
		loadData();
	}


	public void onTypeChanged(ActionEvent actionEvent) {
		if ( typeField.getValue() == ProductType.SCOOTER ) {
			wheelSizeField.setVisible( false );
			frameSizeField.setVisible( false );
			heightField.setVisible( true );
			materialField.setVisible( true );
		}
		else if ( typeField.getValue() == ProductType.BIKE ) {
			wheelSizeField.setVisible( true );
			frameSizeField.setVisible( true );
			heightField.setVisible( false );
			materialField.setVisible( false );
		}
		else {
			wheelSizeField.setVisible( false );
			frameSizeField.setVisible( false );
			heightField.setVisible( false );
			materialField.setVisible( false );
		}
	}


}
