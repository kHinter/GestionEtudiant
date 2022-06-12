package controllers;

import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class StudentHomeController {

    @FXML
    private Text studentNameText;

    @FXML
    private Text groupeNameText;

    @FXML
    private ChoiceBox groupChoiceBox;
    @FXML
    private TableView listEtuTableView;

    Student student = new Student();

    public void initialize(Stage stage) {

        System.out.println("essais du controlleur qui marche");
        stage.setTitle("test");

        studentNameText.setText(student.getName());

        groupChoiceBox.getItems().addAll("Promo","TD","TP");

    }
}
