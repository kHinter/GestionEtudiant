package controllers.secretary;

import com.example.sae_gestion_etudiants.MainApplication;
import dao.StudentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SecretaryStudentListController extends SecretaryController implements Initializable
{
    @FXML
    private Text secretaryNameText;

    @FXML
    private ScrollPane studentListScrollPane;

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

        for(Student student : studentList)
        {
            HBox studentRecord = new HBox();
            studentRecord.setAlignment(Pos.CENTER);
            studentRecord.setBackground(new Background(new BackgroundFill(Color.valueOf("#E6007C"), new CornerRadii(15), Insets.EMPTY)));
            studentRecord.setSpacing(20);
            studentRecord.setPrefHeight(100);

            ImageView studentPhoto = new ImageView(new Image(MainApplication.class.getResource("Images/person.png").toExternalForm()));
            studentPhoto.setFitWidth(65);
            studentPhoto.setFitHeight(78);

            studentRecord.getChildren().add(studentPhoto);

            VBox vbox1 = new VBox();
            vbox1.setPrefWidth(100);
            vbox1.setSpacing(20);

            TextField studentNameField = new TextField(student.getName());
            studentNameField.setEditable(false);
            TextField studentNickNameField = new TextField(student.getNickname());
            studentNameField.setEditable(false);

            vbox1.getChildren().addAll(studentNameField, studentNickNameField);

            VBox vbox2 = new VBox();
            vbox2.setPrefWidth(100);
            vbox2.setSpacing(20);

            TextField numField = new TextField(student.getNum());
            numField.setEditable(false);
            DatePicker birthdatePicker = new DatePicker(student.getBirthdate().toLocalDate());
            birthdatePicker.setEditable(false);

            vbox2.getChildren().addAll(numField, birthdatePicker);

            studentRecord.getChildren().addAll(vbox1, vbox2);
            ((VBox) studentListScrollPane.getContent()).getChildren().add(studentRecord);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        studentListScrollPane.setContent(vBox);
    }
}
