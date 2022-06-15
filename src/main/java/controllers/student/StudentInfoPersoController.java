package controllers.student;

import controllers.student.StudentController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Student;

public class StudentInfoPersoController extends StudentController {

    @FXML
    private Text studentNameText,groupeNameText;

    @FXML
    ImageView profilePicture;

    @FXML
    private Label nameLabel, nicknameLabel ,ageLabel, promoLabel, groupeTdLabel, groupeTpLabel, descriptionLabel;

    @Override
    public void init() {
        Student student = getConnectedStudent();
        studentNameText.setText(student.getNickname() + " " + student.getName().toUpperCase());

        nameLabel.setText(student.getName());
        nicknameLabel.setText(student.getNickname());
        ageLabel.setText(Integer.toString(student.getAge()));
        promoLabel.setText(student.getPromotion().getId());
        groupeTdLabel.setText(student.getTDGroup().getId());
        groupeTpLabel.setText(student.getTPGroup().getId());

        if(student.getDescription().equals("None")){
            descriptionLabel.setText(" ");
        }else{
            descriptionLabel.setText(student.getDescription());
        }

        Image image = new Image("file:src/main/resources/com/example/sae_gestion_etudiants/Images/ProfilePictures/default.jpg");
        profilePicture.setImage(image);

    }
}