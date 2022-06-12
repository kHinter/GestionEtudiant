package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Student;

public class StudentHomeController {

    @FXML
    private Text studentNameText;

    @FXML
    private Text groupeNameText;

    @FXML
    private ChoiceBox groupChoiceBox = new ChoiceBox(FXCollections.observableArrayList("Promo","TD","TP"));

    Student student = new Student();

    String selectStmt = "SELECT * FROM etudiant";



}
