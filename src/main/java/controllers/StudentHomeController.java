package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Student;

import java.net.URL;
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

    public void initialize(URL url, ResourceBundle resourceBundle) {

        groupChoiceBox.getItems().add("Promo");
        groupChoiceBox.getItems().add("TD");
        groupChoiceBox.getItems().add("(TP");
    }



}
