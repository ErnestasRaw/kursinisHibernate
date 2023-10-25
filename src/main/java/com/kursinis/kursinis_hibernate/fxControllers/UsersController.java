package com.kursinis.kursinis_hibernate.fxControllers;

import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Address;
import com.kursinis.kursinis_hibernate.model.Cart;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.model.Warehouse;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	@FXML
	public ListView<User> usersList;
	@FXML
	public TextField nameField;
	@FXML
	public TextField loginField;
	@FXML
	public TextField surnameField;
	@FXML
	public TextField cityField;
	@FXML
	public DatePicker birthDateField;
	@FXML
	public TextField addressField;
	@FXML
	public TextField countryField;
	@FXML
	public TextField cardNoField;
	@FXML
	public TextField employeeIdField;
	@FXML
	public TextField medCertificateField;
	@FXML
	public CheckBox isAdminCheckbox;
	@FXML
	public TextField passwordField;
	public ToggleGroup userTypeRadio;
	public RadioButton employeeRadio;
	public RadioButton clientRadio;
	@FXML
	GenericHib genericHib;

	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		usersList.getItems().clear();
		usersList.getItems().addAll( genericHib.getAllRecords( User.class ) );
	}

	public void onUserSelect(MouseEvent mouseEvent) {
		User selectedUser = (User) usersList.getSelectionModel().getSelectedItem();
		nameField.setText( selectedUser.getName() );
		surnameField.setText( selectedUser.getSurname() );
		loginField.setText( selectedUser.getLogin() );
		if ( selectedUser instanceof Client client ) {
			cityField.setText( ( (Client) selectedUser ).getAddress().getCity() );
			birthDateField.setValue( client.getBirthDate() );
			addressField.setText( ( (Client) selectedUser ).getAddress().getAddress() );
			countryField.setText( ( (Client) selectedUser ).getAddress().getCountry() );
			cardNoField.setText( ( (Client) selectedUser ).getCardNo() );
		}
		if ( selectedUser instanceof Employee employee ) {
			employeeIdField.setText( employee.getEmployeeId() );
			medCertificateField.setText( employee.getMedCertificate() );
			isAdminCheckbox.setSelected( employee.isAdmin() );
		}
	}

	public void addNewUser(ActionEvent actionEvent) {
		Cart cart = new Cart();
		genericHib.create( cart );
		User user = new Client(
				loginField.getText(),
				PasswordHashingUtil.hashPassword( passwordField.getText() ),
				birthDateField.getValue(),
				nameField.getText(),
				surnameField.getText(),
				new Address(
						addressField.getText(),
						cityField.getText(),
						countryField.getText()
				),
				cardNoField.getText(),
				cart
		);
	}

	public void updateUser(ActionEvent actionEvent) {
		User user = new Client(
				loginField.getText(),
				PasswordHashingUtil.hashPassword( passwordField.getText() ),
				birthDateField.getValue(),
				nameField.getText(),
				surnameField.getText(),
				new Address(
						addressField.getText(),
						cityField.getText(),
						countryField.getText()
				),
				cardNoField.getText()
		);
	}

	public void deleteUser(ActionEvent actionEvent) {
	}

	public void disableClientFields(ActionEvent actionEvent) {
	}

	public void disableEmployeeFields(ActionEvent actionEvent) {

	}
}
