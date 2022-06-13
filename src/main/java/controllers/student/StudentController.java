package controllers.student;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;

public abstract class StudentController {
    private Student connectedStudent;

    public void setConnectedStudent(Student student)
    {
        this.connectedStudent = student;
    }

    public Student getConnectedStudent()
    {
        return this.connectedStudent;
    }

    public abstract void init();

    @FXML
    protected void onTrombinoscopeClicked(MouseEvent event)
    {
        //Changement vers la page trombinoscope de l'étudiant
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/trombinoscope.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try
        {
            currentStage.setScene(new Scene(fxmlLoader.load()));

            StudentController controller = fxmlLoader.getController();
            controller.setConnectedStudent(getConnectedStudent());
            controller.init();
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        currentStage.show();
    }

    @FXML
    protected void onInfoPersoClicked(MouseEvent event)
    {
        //Changement vers la page informations personelles de l'étudiant
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/infoPerso.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try
        {
            currentStage.setScene(new Scene(fxmlLoader.load()));

            StudentController controller = fxmlLoader.getController();
            controller.setConnectedStudent(getConnectedStudent());
            controller.init();
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        currentStage.show();
    }

    @FXML
    protected void onStudentListClicked(MouseEvent event)
    {
        //Changement vers la page d'accueil de l'étudiant et la liste des étudiants
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/home.fxml"));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try
        {
            currentStage.setScene(new Scene(fxmlLoader.load()));

            StudentController controller = fxmlLoader.getController();
            controller.setConnectedStudent(getConnectedStudent());
            controller.init();
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        currentStage.show();
    }

    @FXML
    protected void onDeconnectionClicked(MouseEvent event)
    {
        //Changement vers la page d'accueil de l'étudiant et la liste des étudiants
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/deconnection.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Déconnexion");
        stage.initModality(Modality.APPLICATION_MODAL);

        try
        {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        stage.show();
    }

    @FXML
    protected void onInfoPersoClicked()
    {
        //Changement vers la page d'informations personnelles et la liste des étudiants
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/deconnection.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Déconnexion");
        stage.initModality(Modality.APPLICATION_MODAL);

        try
        {
            stage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        stage.show();
    }
}
