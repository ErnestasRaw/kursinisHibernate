package com.kursinis.kursinis_hibernate.fxControllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.kursinis.kursinis_hibernate.model.Employee;
import com.kursinis.kursinis_hibernate.utils.UserController;
import com.kursinis.kursinis_hibernate.hibernateControllers.GenericHib;
import com.kursinis.kursinis_hibernate.model.Client;
import com.kursinis.kursinis_hibernate.model.User;
import com.kursinis.kursinis_hibernate.utils.ErrorUtil;
import com.kursinis.kursinis_hibernate.utils.PasswordHashingUtil;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileController {
	//TextFields
	public TextField loginTextField;
	public TextField nameTextField;
	public TextField surnameTextField;
	public DatePicker birthDateDatePicker;
	public TextField cardNoTextField;
	public TextField countryTextField;
	public TextField cityTextField;
	public TextField addressTextField;
	public TextField passwordTextField;
	public TextField repeatPasswordTextField;
	public TextField employeeCodeTextField;
	public TextField medCertificateIdTextField;
	public DatePicker employmentDateDatePicker;
	public CheckBox isAdminCheckBox;
	//Texts
	public Text employeeCodeText;
	public Text employmentDateText;
	public Text isAdminText;
	public Text medCertificateText;
	public Text addressText;
	public Text cityText;
	public Text countryText;
	//Buttons
	public Button updateButton;
	GenericHib genericHib;
	User user = UserController.getInstance().getLoggedInUser();

	public void setData(EntityManagerFactory entityManagerFactory) {
		genericHib = new GenericHib( entityManagerFactory );
		initializeDefaultValues();
		initializeVisibility();
	}


	public void onUpdateProfileButtonClicked(ActionEvent actionEvent) {
		user.setLogin( loginTextField.getText() );
		user.setName( nameTextField.getText() );
		user.setSurname( surnameTextField.getText() );
		user.setBirthDate( birthDateDatePicker.getValue() );
		user.setPassword( PasswordHashingUtil.hashPassword( passwordTextField.getText() ) );
		if ( user instanceof Client client ) {
			client.setCardNo( cardNoTextField.getText() );
			client.getAddress().setCountry( countryTextField.getText() );
			client.getAddress().setCity( cityTextField.getText() );
			client.getAddress().setAddress( addressTextField.getText() );
		}
		else if ( user instanceof Employee employee ) {
			employee.setEmployeeId( employeeCodeTextField.getText() );
			employee.setMedCertificate( medCertificateIdTextField.getText() );
			employee.setEmploymentDate( employmentDateDatePicker.getValue() );
			employee.setAdmin( isAdminCheckBox.isSelected() );
		}
		genericHib.update( user );
		ErrorUtil.showError(
				"Profile INFO",
				"Profile updated",
				"Profile updated successfully",
				Alert.AlertType.INFORMATION
		);

	}

	private void initializeVisibility() {
		if ( user instanceof Client ) {
			employeeCodeText.setVisible( false );
			employmentDateText.setVisible( false );
			isAdminText.setVisible( false );
			medCertificateText.setVisible( false );
			employeeCodeTextField.setVisible( false );
			medCertificateIdTextField.setVisible( false );
			employmentDateDatePicker.setVisible( false );
			isAdminCheckBox.setVisible( false );
		}
		else if ( user instanceof Employee ) {
			addressText.setVisible( false );
			cityText.setVisible( false );
			countryText.setVisible( false );
			addressTextField.setVisible( false );
			cityTextField.setVisible( false );
			countryTextField.setVisible( false );
			isAdminCheckBox.setDisable( !( (Employee) user ).isAdmin() );
		}
	}

	private void initializeDefaultValues() {
		loginTextField.setText( user.getLogin() );
		nameTextField.setText( user.getName() );
		surnameTextField.setText( user.getSurname() );
		birthDateDatePicker.setValue( user.getBirthDate() );
		passwordTextField.setText( user.getPassword() );
		repeatPasswordTextField.setText( user.getPassword() );
		if ( user instanceof Client client ) {
			cardNoTextField.setText( client.getCardNo() );
			countryTextField.setText( client.getAddress().getCountry() );
			cityTextField.setText( client.getAddress().getCity() );
			addressTextField.setText( client.getAddress().getAddress() );
		}
		else if ( user instanceof Employee employee ) {
			employeeCodeTextField.setText( employee.getEmployeeId() );
			medCertificateIdTextField.setText( employee.getMedCertificate() );
			employmentDateDatePicker.setValue( employee.getEmploymentDate() );
			isAdminCheckBox.setSelected( employee.isAdmin() );

		}
	}
}
