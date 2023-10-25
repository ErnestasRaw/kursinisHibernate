package com.kursinis.kursinis_hibernate.fxControllers;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Address;
import com.kursinis.kursinis_hibernate.model.Cart;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.model.User;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersController {
	public ListView<User> usersList;
	public TextField nameField;
	public TextField loginField;
	public TextField surnameField;
	public TextField cityField;
	public TextField countryField;
	public TextField addressField;
	public TextField cardNoField;
	public TextField medCertificateField;
	public TextField employeeIdField;
	public CheckBox isAdminCheckbox;
	public DatePicker birthDateField;
	public TextField passwordField;
	public RadioButton employeeRadio;
	public ToggleGroup userTypeRadio;
	public RadioButton clientRadio;
	public DatePicker employmentDatePicker;
	GenericHib genericHib;

	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		loadUsersList();
	}

	private void loadUsersList() {
		usersList.getItems().clear();
		usersList.getItems().addAll( genericHib.getAllRecords( User.class ) );
	}

	public void disableEmployeeFields(ActionEvent actionEvent) {
		employeeIdField.setVisible( false );
		medCertificateField.setVisible( false );
		employmentDatePicker.setVisible( false );
		isAdminCheckbox.setVisible( false );

		cityField.setVisible( true );
		countryField.setVisible( true );
		addressField.setVisible( true );
		cardNoField.setVisible( true );


	}

	public void disableClientFields(ActionEvent actionEvent) {
		cityField.setVisible( false );
		countryField.setVisible( false );
		addressField.setVisible( false );
		cardNoField.setVisible( false );

		employeeIdField.setVisible( true );
		medCertificateField.setVisible( true );
		employmentDatePicker.setVisible( true );
		isAdminCheckbox.setVisible( true );
	}

	public void onUserSelect(MouseEvent mouseEvent) {

		User selectedUser = usersList.getSelectionModel().getSelectedItem();
		if ( selectedUser instanceof Employee ) {
			disableClientFields( null );
			Employee employee = (Employee) usersList.getSelectionModel().getSelectedItem();
			nameField.setText( employee.getName() );
			surnameField.setText( employee.getSurname() );
			medCertificateField.setText( employee.getMedCertificate() );
			employeeIdField.setText( employee.getEmployeeId() );
			isAdminCheckbox.setSelected( employee.isAdmin() );
			birthDateField.setValue( employee.getBirthDate() );
			passwordField.setText( employee.getPassword() );
			loginField.setText( employee.getLogin() );
			employeeRadio.setSelected( true );
			clientRadio.setSelected( false );
			userTypeRadio.selectToggle( employeeRadio );
			isAdminCheckbox.setSelected( employee.isAdmin() );
		}
		else if ( selectedUser instanceof Client ) {
			disableEmployeeFields( null );
			Client client = (Client) usersList.getSelectionModel().getSelectedItem();
			nameField.setText( client.getName() );
			surnameField.setText( client.getSurname() );
			cityField.setText( client.getAddress().getCity() );
			countryField.setText( client.getAddress().getCountry() );
			addressField.setText( client.getAddress().getAddress() );
			cardNoField.setText( client.getCardNo() );
			birthDateField.setValue( client.getBirthDate() );
			passwordField.setText( client.getPassword() );
			loginField.setText( client.getLogin() );
			employeeRadio.setSelected( false );
			clientRadio.setSelected( true );
			isAdminCheckbox.setSelected( false );
		}
	}

	public void addNewUser(ActionEvent actionEvent) {
		if ( employeeRadio.isSelected() ) {
			User employee = new Employee(
					loginField.getText(),
					passwordField.getText(),
					birthDateField.getValue(),
					nameField.getText(),
					surnameField.getText(),
					employeeIdField.getText(),
					employmentDatePicker.getValue(),
					medCertificateField.getText(),
					isAdminCheckbox.isSelected()
			);
			genericHib.create( employee );

		}
		else {
			Address address = new Address(
					addressField.getText(),
					cityField.getText(),
					countryField.getText()
			);
			Cart cart = new Cart();
			genericHib.create( cart );
			User client = new Client(
					loginField.getText(),
					passwordField.getText(),
					birthDateField.getValue(),
					nameField.getText(),
					surnameField.getText(),
					address,
					cardNoField.getText(),
					cart
			);
			genericHib.create( client );
		}
		loadUsersList();
	}

	public void updateUser(ActionEvent actionEvent) {
		User selectedUser = usersList.getSelectionModel().getSelectedItem();
		User user = genericHib.getEntityById( User.class, selectedUser.getId() );
		if ( user instanceof Employee employee ) {
			employee.setEmployeeId( employeeIdField.getText() );
			employee.setMedCertificate( medCertificateField.getText() );
			employee.setEmploymentDate( employmentDatePicker.getValue() );
			employee.setAdmin( isAdminCheckbox.isSelected() );
		}
		else if ( user instanceof Client client ) {
			client.setCardNo( cardNoField.getText() );
			client.setAddress( new Address( addressField.getText(), cityField.getText(), countryField.getText() ) );
		}
		genericHib.update( user );
		loadUsersList();
	}

	public void deleteUser(ActionEvent actionEvent) {
		User selectedUser = usersList.getSelectionModel().getSelectedItem();
		genericHib.delete( User.class, selectedUser.getId() );
		loadUsersList();
	}
}
