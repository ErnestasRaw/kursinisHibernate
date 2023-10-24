module com.kursinis.prif4kursinis {
	requires javafx.controls;
	requires javafx.fxml;
	requires lombok;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires jakarta.persistence;


	opens com.kursinis.kursinis_hibernate to javafx.fxml;
	exports com.kursinis.kursinis_hibernate;
	opens com.kursinis.kursinis_hibernate.model to javafx.fxml, org.hibernate.orm.core;
	exports com.kursinis.kursinis_hibernate.model;
	opens com.kursinis.kursinis_hibernate.fxControllers to javafx.fxml;
	exports com.kursinis.kursinis_hibernate.fxControllers to javafx.fxml;
	exports com.kursinis.kursinis_hibernate.utils to javafx.fxml;
	opens com.kursinis.kursinis_hibernate.utils to javafx.fxml;
}