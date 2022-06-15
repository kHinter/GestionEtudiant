package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import dao.StudentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretaryStudentListController extends SecretaryController
{
    @FXML
    private Text secretaryNameText;

    @FXML
    private Text groupNameText;


    @FXML
    private void onBackArrowClicked(MouseEvent event)
    {
        //Changement vers la page de trombinoscope
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try
        {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e)
        {
            System.out.println("Impossible d'ouvrir la vue : " + e.getMessage());
        }

        SecretaryHomeController controller = fxmlLoader.getController();
        controller.setConnectedStaff(getConnectedStaff());
        controller.setCurrentGroup(getCurrentGroup().getParent());
        controller.init();

        stage.show();
    }

    @Override
    public void init()
    {
        secretaryNameText.setText(getConnectedStaff().getPrenom() + getConnectedStaff().getNom().toUpperCase());
        if(getCurrentGroup().getType().equals("PROMOTION"))
        {
            groupNameText.setText(getCurrentGroup().getId());
        }
        else
        {
            groupNameText.setText(getCurrentGroup().getType() + getCurrentGroup().getId());
        }

        StudentDAO dao = new StudentDAO();
        List<Student> studentList = new ArrayList<>();
        try {
            studentList = dao.getByGroupId(getCurrentGroup().getId());
        } catch (SQLException e)
        {
            System.out.println("Erreur lors d'une requête : " + e.getMessage());
        }



    }
}
