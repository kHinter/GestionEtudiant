package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentInfoPersoController extends StudentController implements Initializable {

    @FXML
    private Text studentNameText,groupeNameText;

    @FXML
    private Label nomLabel,prenomLabel,ageLabel;

    @FXML
    private ImageView profilePicture;

    @Override
    public void init() {
        Student student = getConnectedStudent();
        studentNameText.setText(student.getNickname() + " " + student.getName().toUpperCase());

        nomLabel.setText(student.getName());
        prenomLabel.setText(student.getNickname());
        ageLabel.setText(Integer.toString(student.getAge()));

        System.out.println(student.getPhotoUrl());

        Image image = new Image("file:src/main/resources/com/example/sae_gestion_etudiants/Images/ProfilePictures/" + student.getPhotoUrl() + ".jpg");
        profilePicture.setImage(image);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    @FXML
    protected void onTrombinoscopeClicked()
    {
        //Changement vers la page trombinoscope de l'étudiant
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/trombinoscope.fxml"));
        Stage currentStage = (Stage) studentNameText.getScene().getWindow();

        try
        {
            currentStage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        StudentTrombinoscopeController controller = fxmlLoader.getController();
        controller.setConnectedStudent(getConnectedStudent());
        controller.init();

        currentStage.show();
    }

}
