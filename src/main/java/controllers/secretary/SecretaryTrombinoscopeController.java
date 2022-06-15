package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import dao.StudentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretaryTrombinoscopeController extends SecretaryController
{
    @FXML
    private Text groupNameText;

    @FXML
    private Text secretaryNameText;

    @FXML
    private FlowPane trombinoscopeFlowPane;

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

        List<Student> studentList = new ArrayList<>();
        StudentDAO dao = new StudentDAO();

        try
        {
            studentList = dao.getByGroupId(getCurrentGroup().getId());
        }
        catch (SQLException e)
        {
            System.out.println("Erreur lors de la requête :" + e.getMessage());
        }

        trombinoscopeFlowPane.getChildren().clear();
        for(Student student : studentList)
        {
            VBox vbox = new VBox();
            vbox.setPrefWidth(130);
            vbox.setPrefHeight(160);
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.setSpacing(10);

            ImageView imageView = new ImageView(new Image(MainApplication.class.getResource("Images/person.png").toExternalForm()));

            vbox.getChildren().add(imageView);

            vbox.getChildren().add(new Text(student.getNickname() + " " + student.getName().toUpperCase()));
            trombinoscopeFlowPane.getChildren().add(vbox);
        }
    }
}
