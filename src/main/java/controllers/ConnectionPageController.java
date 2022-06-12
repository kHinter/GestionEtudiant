package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import dao.RoleDAO;
import dao.StaffDAO;
import dao.StudentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import models.Role;
import models.Staff;
import models.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPageController {

    @FXML
    private ImageView IUTLogoImageView;

    @FXML
    private Button connectionButton;

    @FXML
    private TextField identifiantTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Text infoText;

    @FXML
    private TextFlow infoTextFlow;

    @FXML
    private void onConnectionClicked()
    {
        if(identifiantTextField.getText() != null && passwordTextField.getText() != null)
        {
            try
            {
                StudentDAO studentDAO = new StudentDAO();
                StaffDAO staffDAO = new StaffDAO();

                Student student = studentDAO.getById(identifiantTextField.getText());
                Staff staff = staffDAO.getById(identifiantTextField.getText());

                //Si on se connecte en tant qu'étudiant
                if(student != null && student.getPassword().equals(passwordTextField.getText()))
                {
                    infoTextFlow.setVisible(true);
                    infoText.setFill(Paint.valueOf("#00FF00"));
                    infoText.setText("Connexion réussie");

                    //Changement vers la page d'accueil de l'étudiant
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/home.fxml"));
                    Stage stage = (Stage) infoTextFlow.getScene().getWindow();
                    stage.setScene(new Scene(fxmlLoader.load()));

                    StudentHomeController controller = fxmlLoader.getController();
                    controller.setConnectedStudent(student);
                    controller.init();

                    stage.show();
                }
                //Si on se connecte en tant que personnel de l'IUT
                else if(staffDAO.getById(identifiantTextField.getText()) != null && staff.getPassword().equals(passwordTextField.getText()))
                {
                    infoTextFlow.setVisible(true);
                    infoText.setFill(Paint.valueOf("#00FF00"));
                    infoText.setText("Connexion réussie");

                    Role secretary = new Role();
                    secretary.setName("Secrétariat");

                    Role teacher = new Role();
                    teacher.setName("Enseignant");

                    if(staff.getRoles().contains(secretary))
                    {
                        //Changement vers la page d'accueil de la secrétaire
                        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/secretaire.fxml"));
                        Stage currentStage = (Stage) infoTextFlow.getScene().getWindow();
                        currentStage.setScene(new Scene(fxmlLoader.load()));
                        currentStage.show();
                    }
                    else if(staff.getRoles().contains(teacher))
                    {
                        //Changement vers la page d'accueil de l'enseignant

                    }
                }
                else
                {
                    //Clear the text fields
                    identifiantTextField.setText("");
                    passwordTextField.setText("");

                    infoTextFlow.setVisible(true);
                    infoText.setFill(Paint.valueOf("#FF0000"));
                    infoText.setText("Identifiant ou mot de passe incorrect, veuillez réessayer !");
                }
            }
            catch (SQLException e)
            {
                System.out.println("Erreur lors d'une requête :" + e.getMessage());
            }
            catch (IOException e)
            {
                System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
            }
        }
    }
}