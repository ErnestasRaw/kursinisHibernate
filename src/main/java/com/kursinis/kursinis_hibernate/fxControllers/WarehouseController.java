package com.kursinis.kursinis_hibernate.fxControllers;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Warehouse;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class WarehouseController {
	@FXML
	public ListView<Warehouse> warehouseList;
	@FXML
	public TextField addressWarehouseField;
	@FXML
	public TextField titleWarehouseField;

	GenericHib genericHib;

	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		loadSelectedWarehouseData();
	}


	public void onWarehouseSelect() {
		Warehouse selectedWarehouse = (Warehouse) warehouseList.getSelectionModel().getSelectedItem();
		titleWarehouseField.setText( selectedWarehouse.getTitle() );
		addressWarehouseField.setText( selectedWarehouse.getAddress() );
	}

	public void loadSelectedWarehouseData() {
		warehouseList.getItems().clear();
		warehouseList.getItems().addAll( genericHib.getAllRecords( Warehouse.class ) );
	}

	public void addNewWarehouse(ActionEvent actionEvent) {
		genericHib.create( new Warehouse( titleWarehouseField.getText(), addressWarehouseField.getText() ) );
		loadWarehouseList();
	}

	private void loadWarehouseList() {
		warehouseList.getItems().clear();
		warehouseList.getItems().addAll( genericHib.getAllRecords( Warehouse.class ) );
	}

	public void updateWarehouse(ActionEvent actionEvent) {
		Warehouse selectedWarehouse = (Warehouse) warehouseList.getSelectionModel().getSelectedItem();
		Warehouse warehouse = genericHib.getEntityById( Warehouse.class, selectedWarehouse.getId() );
		warehouse.setTitle( titleWarehouseField.getText() );
		warehouse.setAddress( addressWarehouseField.getText() );
		genericHib.update( warehouse );
		loadWarehouseList();
	}

	public void removeWarehouse(ActionEvent actionEvent) {
		Warehouse selectedWarehouse = (Warehouse) warehouseList.getSelectionModel().getSelectedItem();
		Warehouse warehouse = genericHib.getEntityById( Warehouse.class, selectedWarehouse.getId() );
		genericHib.delete( Warehouse.class, selectedWarehouse.getId() );
		loadWarehouseList();
	}


}
