package controllers;

import com.example.sae_gestion_etudiants.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentTrombinoscopeController extends StudentController
{

    @FXML
    private Text studentNameText;

    @Override
    public void init()
    {

    }

    @Override
    @FXML
    protected void onTrombinoscopeClicked()
    {

    }

    @FXML
    protected void onInfoPersoClicked()
    {
        //Changement vers la page informations personelles de l'étudiant
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Student/infoPerso.fxml"));
        Stage currentStage = (Stage) studentNameText.getScene().getWindow();

        try
        {
            currentStage.setScene(new Scene(fxmlLoader.load()));
        }
        catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        StudentInfoPersoController controller = fxmlLoader.getController();
        controller.setConnectedStudent(getConnectedStudent());
        controller.init();

        currentStage.show();
    }

}
