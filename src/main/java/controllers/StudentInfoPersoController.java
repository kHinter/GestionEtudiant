package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Student;

public class StudentInfoPersoController extends StudentController{

    @FXML
    private Text studentNameText,groupeNameText;

    @FXML
    private Label nameLabel, nicknameLabel,ageLabel;

    @FXML
    private ImageView profilePicture;

    @Override
    public void init() {
        Student student = getConnectedStudent();
        studentNameText.setText(student.getNickname() + " " + student.getName().toUpperCase());

        nameLabel.setText(student.getName());
        nicknameLabel.setText(student.getNickname());
        ageLabel.setText(Integer.toString(student.getAge()));

        System.out.println(student.getPhotoUrl());

        Image image = new Image("file:src/main/resources/com/example/sae_gestion_etudiants/Images/ProfilePictures/" + student.getPhotoUrl() + ".jpg");
        profilePicture.setImage(image);


    }

}
