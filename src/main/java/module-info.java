module com.example.sae_gestion_etudiants {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sae_gestion_etudiants to javafx.fxml;
    exports com.example.sae_gestion_etudiants;
    exports controllers;
    opens controllers to javafx.fxml;
    exports controllers.secretary;
    opens controllers.secretary to javafx.fxml;
    exports controllers.student;
    opens controllers.student to javafx.fxml;
}