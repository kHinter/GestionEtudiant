package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import controllers.secretary.SecretaryController;
import dao.GroupDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Group;
import models.Staff;

import java.io.IOException;
import java.nio.file.attribute.GroupPrincipal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public class SecretaryHomeController extends SecretaryController
{
    @FXML
    private Text titleText;

    @FXML
    private HBox groupListHBox;

    @FXML
    private Text secretaryNameText;

    public void onAddGroupClicked(MouseEvent mouseEvent) {
        //Changement vers la fenêtre d'ajout de groupe
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/addNewGroupForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nouveau Groupe");
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

    public void onAddStudentClicked(MouseEvent mouseEvent) {
        //Changement vers la fenêtre d'ajout de groupe
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Views/Secretary/addStudent.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Nouvel Étudiant");
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

    @Override
    public void init()
    {
        Staff secretary = getConnectedStaff();
        secretaryNameText.setText(secretary.getPrenom() + secretary.getNom().toUpperCase());
        // Ajout des groupes d'étudiants sur la page d'accueil
        List<Group> groupList = null;
        GroupDAO groupDAO = new GroupDAO();

        if(getCurrentGroup() == null)
        {
            try
            {
                groupList = groupDAO.getAllPromotions();
            }
            catch (SQLException e)
            {
                System.out.println("Erreur lors d'une requête :" + e.getMessage());
            }
        }
        else if(getCurrentGroup().getType() == "PROMOTION")
        {
            titleText.setText("Sous-groupe " + getCurrentGroup().getId());
            groupList = getCurrentGroup().getChilrens();
        }
        else if(getCurrentGroup().getType() == "TD")
        {
            titleText.setText("Sous-groupe " + getCurrentGroup().getType() + getCurrentGroup().getId());
            groupList = getCurrentGroup().getChilrens();
        }

        for(Group group : groupList)
        {
            VBox groupFrame = new VBox();
            groupFrame.setPrefWidth(228);
            groupFrame.setPrefHeight(254);
            groupFrame.setStyle("-fx-background-color : 808791; " + "-fx-background-radius : 15;");
            groupFrame.setSpacing(10);

            AnchorPane header = new AnchorPane();
            header.setPrefHeight(67);
            groupFrame.setStyle("-fx-background-color : E6007C; " + "-fx-background-radius : 15;");

            groupFrame.getChildren().add(header);

            VBox buttonsFrame = new VBox();
            groupFrame.getChildren().add(buttonsFrame);

            groupListHBox.getChildren().add(groupFrame);
        }
    }
}
